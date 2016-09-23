/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.dynamicdatamapping.service;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatamapping.RequiredStructureException;
import com.liferay.portlet.dynamicdatamapping.StructureDuplicateElementException;
import com.liferay.portlet.dynamicdatamapping.StructureDuplicateStructureKeyException;
import com.liferay.portlet.dynamicdatamapping.StructureNameException;
import com.liferay.portlet.dynamicdatamapping.StructureXsdException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructureConstants;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.storage.StorageType;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eduardo Garcia
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Transactional
public class DDMStructureServiceTest extends BaseDDMServiceTestCase {

	@Test
	public void testAddStructureWithDuplicateKey() throws Exception {
		String structureKey = ServiceTestUtil.randomString();
		String storageType = StorageType.XML.getValue();

		try {
			addStructure(
				_classNameId, structureKey, "Test Structure 1",
				getTestStructureXsd(storageType), storageType,
				DDMStructureConstants.TYPE_DEFAULT);

			addStructure(
				_classNameId, structureKey, "Test Structure 2",
				getTestStructureXsd(storageType), storageType,
				DDMStructureConstants.TYPE_DEFAULT);

			Assert.fail();
		}
		catch (StructureDuplicateStructureKeyException sdske) {
		}
	}

	@Test
	public void testAddStructureWithoutName() throws Exception {
		String storageType = StorageType.XML.getValue();

		try {
			addStructure(
				_classNameId, null, StringPool.BLANK,
				getTestStructureXsd(storageType), storageType,
				DDMStructureConstants.TYPE_DEFAULT);

			Assert.fail();
		}
		catch (StructureNameException sne) {
		}
	}

	@Test
	public void testAddStructureWithoutXsd() throws Exception {
		try {
			addStructure(
				_classNameId, null, "Test Structure", StringPool.BLANK,
				StorageType.XML.getValue(), DDMStructureConstants.TYPE_DEFAULT);

			Assert.fail();
		}
		catch (StructureXsdException sxe) {
		}
	}

	@Test
	public void testAddStructureWithXsdContainingDuplicateElementName()
		throws Exception {

		String storageType = StorageType.XML.getValue();

		try {
			addStructure(
				_classNameId, null, "Test Structure",
				readText("ddm-structure-duplicate-element-name.xsd"),
				storageType, DDMStructureConstants.TYPE_DEFAULT);

			Assert.fail();
		}
		catch (StructureDuplicateElementException sdee) {
		}
	}

	@Test
	public void testAddStructureWithXsdContainingDuplicateElementNameInParent()
		throws Exception {

		String storageType = StorageType.XML.getValue();

		try {
			DDMStructure parentStructure = addStructure(
				_classNameId, null, "Test Parent Structure",
				readText("ddm-structure-duplicate-element-name.xsd"),
				storageType, DDMStructureConstants.TYPE_DEFAULT);

			addStructure(
				parentStructure.getStructureId(), _classNameId, null,
				"Test Structure",
				readText("ddm-structure-duplicate-element-name.xsd"),
				storageType, DDMStructureConstants.TYPE_DEFAULT);

			Assert.fail();
		}
		catch (StructureDuplicateElementException sdee) {
		}
	}

	@Test
	public void testAddStructureWithXsdContainingInvalidElementAttribute()
		throws Exception {

		String storageType = StorageType.XML.getValue();

		try {
			addStructure(
				_classNameId, null, "Test Structure",
				readText("ddm-structure-invalid-element-attribute.xsd"),
				storageType, DDMStructureConstants.TYPE_DEFAULT);

			Assert.fail();
		}
		catch (StructureXsdException sxe) {
		}
	}

	@Test
	public void testAddStructureWithXsdMissingRequiredElementAttribute()
		throws Exception {

		String storageType = StorageType.XML.getValue();

		try {
			addStructure(
				_classNameId, null, "Test Structure",
				readText("ddm-structure-required-element-attribute.xsd"),
				storageType, DDMStructureConstants.TYPE_DEFAULT);

			Assert.fail();
		}
		catch (StructureXsdException sxe) {
		}
	}

	@Test
	public void testCopyStructure() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure");

		DDMStructure copyStructure = copyStructure(structure);

		Assert.assertEquals(structure.getGroupId(), copyStructure.getGroupId());
		Assert.assertEquals(structure.getXsd(), copyStructure.getXsd());
		Assert.assertEquals(
			structure.getStorageType(), copyStructure.getStorageType());
		Assert.assertEquals(structure.getType(), copyStructure.getType());
	}

	@Test
	public void testDeleteStructure() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure");

		DDMStructureLocalServiceUtil.deleteStructure(
			structure.getStructureId());

		Assert.assertNull(
			DDMStructureLocalServiceUtil.fetchDDMStructure(
				structure.getStructureId()));
	}

	@Test
	public void testDeleteStructureReferencedByTemplates() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure");

		addDisplayTemplate(structure.getPrimaryKey(), "Test Display Template");
		addFormTemplate(structure.getPrimaryKey(), "Test Form Template");

		try {
			DDMStructureLocalServiceUtil.deleteStructure(
				structure.getStructureId());

			Assert.fail();
		}
		catch (RequiredStructureException rse) {
		}
	}

	@Test
	public void testFetchStructure() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure");

		Assert.assertNotNull(
			DDMStructureLocalServiceUtil.fetchStructure(
				structure.getGroupId(), _classNameId,
				structure.getStructureKey()));
	}

	@Test
	public void testGetStructures() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure");

		List<DDMStructure> structures =
			DDMStructureLocalServiceUtil.getStructures(structure.getGroupId());

		Assert.assertTrue(structures.contains(structure));
	}

	@Test
	public void testGetTemplates() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure");

		addDisplayTemplate(structure.getStructureId(), "Test Display Template");
		addFormTemplate(structure.getStructureId(), "Test Form Template");

		List<DDMTemplate> templates = structure.getTemplates();

		Assert.assertEquals(2, templates.size());
	}

	@Test
	public void testSearch() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure 1");

		addStructure(_classNameId, "Test Structure 2");

		List<DDMStructure> structures = DDMStructureLocalServiceUtil.search(
			structure.getCompanyId(), new long[] {structure.getGroupId()},
			new long[] {structure.getClassNameId()}, null, null,
			structure.getStorageType(), structure.getType(), false, 0, 1, null);

		Assert.assertEquals(1, structures.size());
	}

	@Test
	public void testSearchByKeywords() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure 1");

		addStructure(_classNameId, "Test Structure 2");

		List<DDMStructure> structures = DDMStructureLocalServiceUtil.search(
			structure.getCompanyId(), new long[] {structure.getGroupId()},
			new long[] {structure.getClassNameId()}, null, 0, 1, null);

		Assert.assertEquals(1, structures.size());
	}

	@Test
	public void testSearchCount() throws Exception {
		int initialCount = DDMStructureLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			new long[] {_classNameId}, "Test Structure", null, null,
			DDMStructureConstants.TYPE_DEFAULT, false);

		addStructure(_classNameId, "Test Structure");

		int count = DDMStructureLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			new long[] {_classNameId}, "Test Structure", null, null,
			DDMStructureConstants.TYPE_DEFAULT, false);

		Assert.assertEquals(initialCount + 1, count);
	}

	@Test
	public void testSearchCountByKeywords() throws Exception {
		int initialCount = DDMStructureLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			new long[] {_classNameId}, null);

		addStructure(_classNameId, "Test Structure");

		int count = DDMStructureLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			new long[] {_classNameId}, null);

		Assert.assertEquals(initialCount + 1, count);
	}

	protected DDMStructure copyStructure(DDMStructure structure)
		throws Exception {

		return DDMStructureLocalServiceUtil.copyStructure(
			structure.getUserId(), structure.getStructureId(),
			structure.getNameMap(), structure.getDescriptionMap(),
			ServiceTestUtil.getServiceContext(group.getGroupId()));
	}

	protected DDMStructure updateStructure(DDMStructure structure)
		throws Exception {

		return DDMStructureLocalServiceUtil.updateStructure(
			structure.getStructureId(), structure.getParentStructureId(),
			structure.getNameMap(), structure.getDescriptionMap(),
			structure.getXsd(),
			ServiceTestUtil.getServiceContext(group.getGroupId()));
	}

	@Test
	public void testAddSelectOptionRequired() throws Exception {
		String structureName = ServiceTestUtil.randomString();
		String templateName = ServiceTestUtil.randomString();

		DDMStructure ddmStructure = addStructure(_classNameId, structureName);

		DDMTemplate ddmTemplate = addFormTemplate(
			ddmStructure.getStructureId(), templateName,
			getTestStructureXsd("xml"));

		Document structureDocument = getStructureDocument(ddmStructure);

		Element rootElement = structureDocument.getRootElement();

		Element selectElement = addSelectElement(rootElement, "true", null);

		addOptionElement(selectElement, 4);

		addDynamicElementMetadata(selectElement, "select");

		ddmStructure.setXsd(structureDocument.asXML());

		ddmStructure = updateStructure(ddmStructure);

		ddmTemplate = DDMTemplateLocalServiceUtil.getDDMTemplate(
			ddmTemplate.getTemplateId());

		Assert.assertEquals(ddmStructure.getXsd(), ddmTemplate.getScript());
	}

	@Test
	public void testAddSelectOptionNotRequired() throws Exception {
		String structureName = ServiceTestUtil.randomString();
		String templateName = ServiceTestUtil.randomString();

		DDMStructure ddmStructure = addStructure(_classNameId, structureName);

		DDMTemplate ddmTemplate = addFormTemplate(
			ddmStructure.getStructureId(), templateName,
			getTestStructureXsd("xml"));

		Document structureDocument = getStructureDocument(ddmStructure);

		Element rootElement = structureDocument.getRootElement();

		Element selectElement = addSelectElement(rootElement, "false", null);

		addOptionElement(selectElement, 4);

		addDynamicElementMetadata(selectElement, "select");

		ddmStructure.setXsd(structureDocument.asXML());

		ddmStructure = updateStructure(ddmStructure);

		ddmTemplate = DDMTemplateLocalServiceUtil.getDDMTemplate(
			ddmTemplate.getTemplateId());

		Assert.assertNotEquals(ddmStructure.getXsd(), ddmTemplate.getScript());
	}

	@Test
	public void testAddRadioOptionRequired() throws Exception {
		String structureName = ServiceTestUtil.randomString();
		String templateName = ServiceTestUtil.randomString();

		DDMStructure ddmStructure = addStructure(_classNameId, structureName);

		DDMTemplate ddmTemplate = addFormTemplate(
			ddmStructure.getStructureId(), templateName,
			getTestStructureXsd("xml"));

		Document structureDocument = getStructureDocument(ddmStructure);

		Element rootElement = structureDocument.getRootElement();

		Element radioElement = addRadioElement(rootElement, "true", null);

		addOptionElement(radioElement, 4);

		addDynamicElementMetadata(radioElement, "radio");

		ddmStructure.setXsd(structureDocument.asXML());

		ddmStructure = updateStructure(ddmStructure);

		ddmTemplate = DDMTemplateLocalServiceUtil.getDDMTemplate(
			ddmTemplate.getTemplateId());

		Assert.assertEquals(ddmStructure.getXsd(), ddmTemplate.getScript());
	}

	@Test
	public void testAddRadioOptionNotRequired() throws Exception {
		String structureName = ServiceTestUtil.randomString();
		String templateName = ServiceTestUtil.randomString();

		DDMStructure ddmStructure = addStructure(_classNameId, structureName);

		DDMTemplate ddmTemplate = addFormTemplate(
			ddmStructure.getStructureId(), templateName,
			getTestStructureXsd("xml"));

		Document structureDocument = getStructureDocument(ddmStructure);

		Element rootElement = structureDocument.getRootElement();

		Element radioElement = addRadioElement(rootElement, "false", null);

		addOptionElement(radioElement, 4);

		addDynamicElementMetadata(radioElement, "radio");

		ddmStructure.setXsd(structureDocument.asXML());

		ddmStructure = updateStructure(ddmStructure);

		ddmTemplate = DDMTemplateLocalServiceUtil.getDDMTemplate(
			ddmTemplate.getTemplateId());

		Assert.assertNotEquals(ddmStructure.getXsd(), ddmTemplate.getScript());
	}

	@Test
	public void testOverrideTemplateSelectElement() throws Exception {
		String structureName = ServiceTestUtil.randomString();
		String templateName = ServiceTestUtil.randomString();

		DDMStructure ddmStructure = addStructure(_classNameId, structureName);

		DDMTemplate ddmTemplate = addFormTemplate(
			ddmStructure.getStructureId(), templateName,
			getTestStructureXsd("xml"));

		Document structureDocument = getStructureDocument(ddmStructure);

		Element rootElement = structureDocument.getRootElement();

		Element selectElement = addSelectElement(rootElement, "true", null);

		addDynamicElementMetadata(selectElement, "select");

		ddmStructure.setXsd(structureDocument.asXML());

		ddmStructure = updateStructure(ddmStructure);

		ddmTemplate = DDMTemplateLocalServiceUtil.getDDMTemplate(
			ddmTemplate.getTemplateId());

		Document templateDocument = SAXReaderUtil.read(ddmTemplate.getScript());

		addOptionElement(templateDocument, "select", 4);

		ddmTemplate.setScript(templateDocument.asXML());

		DDMTemplateLocalServiceUtil.updateDDMTemplate(ddmTemplate);

		ddmStructure = updateStructure(ddmStructure);

		ddmTemplate = DDMTemplateLocalServiceUtil.getDDMTemplate(
			ddmTemplate.getTemplateId());

		Assert.assertEquals(ddmStructure.getXsd(), ddmTemplate.getScript());
	}

	@Test
	public void testOverrideTemplateSelectElement2() throws Exception {
		String structureName = ServiceTestUtil.randomString();
		String templateName = ServiceTestUtil.randomString();

		DDMStructure ddmStructure = addStructure(_classNameId, structureName);

		DDMTemplate ddmTemplate = addFormTemplate(
			ddmStructure.getStructureId(), templateName,
			getTestStructureXsd("xml"));

		Document templateDocument = SAXReaderUtil.read(ddmTemplate.getScript());

		Element selectElement = addSelectElement(
			templateDocument, "true", "select1");

		addDynamicElementMetadata(selectElement, "select");

		ddmTemplate.setScript(templateDocument.asXML());

		DDMTemplateLocalServiceUtil.updateDDMTemplate(ddmTemplate);

		Document structureDocument = getStructureDocument(ddmStructure);

		selectElement = addSelectElement(structureDocument, "true", "select1");

		addDynamicElementMetadata(selectElement, "select");

		ddmStructure.setXsd(structureDocument.asXML());

		ddmStructure = updateStructure(ddmStructure);

		ddmTemplate = DDMTemplateLocalServiceUtil.getDDMTemplate(
			ddmTemplate.getTemplateId());

		Assert.assertEquals(ddmStructure.getXsd(), ddmTemplate.getScript());
	}

	@Test
	public void testFailOverrideTemplateSelectElement() throws Exception {
		String structureName = ServiceTestUtil.randomString();
		String templateName = ServiceTestUtil.randomString();

		DDMStructure ddmStructure = addStructure(_classNameId, structureName);

		DDMTemplate ddmTemplate = addFormTemplate(
			ddmStructure.getStructureId(), templateName,
			getTestStructureXsd("xml"));

		Document templateDocument = SAXReaderUtil.read(ddmTemplate.getScript());

		Element selectElement = addSelectElement(
			templateDocument, "true", "select2");

		addDynamicElementMetadata(selectElement, "select");

		ddmTemplate.setScript(templateDocument.asXML());

		DDMTemplateLocalServiceUtil.updateDDMTemplate(ddmTemplate);

		Document structureDocument = getStructureDocument(ddmStructure);

		selectElement = addSelectElement(structureDocument, "true", "select1");

		addDynamicElementMetadata(selectElement, "select");

		ddmStructure.setXsd(structureDocument.asXML());

		ddmStructure = updateStructure(ddmStructure);

		ddmTemplate = DDMTemplateLocalServiceUtil.getDDMTemplate(
			ddmTemplate.getTemplateId());

		Assert.assertEquals(ddmStructure.getXsd(), ddmTemplate.getScript());
	}

	protected Element addRootElement(Document document) {
		Element rootElement = document.addElement("root");

		rootElement.addAttribute("available-locales", "en_US");
		rootElement.addAttribute("default-locale", "en_US");

		return rootElement;
	}

	protected Element addSelectElement(
		DDMStructure ddmStructure, String required, String name)
			throws Exception {

		Document structureDocument = getStructureDocument(ddmStructure);

		Element dynamicElement = addSelectElement(
			structureDocument, required, name);

		ddmStructure.setXsd(structureDocument.asXML());

		return dynamicElement;
	}

	protected Element addSelectElement(
			Document document, String required, String name)
		throws Exception {

		Element rootElement = document.getRootElement();

		return addSelectElement(rootElement, required, name);
	}

	protected Element addSelectElement(
			Element element, String required, String name)
		throws Exception {

		Element dynamicElement = element.addElement("dynamic-element");

		addDynamicElementAttributes(dynamicElement, required, "select", name);

		addOptionElement(dynamicElement, 1);
		addOptionElement(dynamicElement, 2);
		addOptionElement(dynamicElement, 3);

		return dynamicElement;
	}

	protected Element addRadioElement(
			Element element, String required, String name)
		throws Exception {

		Element dynamicElement = element.addElement("dynamic-element");

		addDynamicElementAttributes(dynamicElement, required, "radio", name);

		addOptionElement(dynamicElement, 1);
		addOptionElement(dynamicElement, 2);
		addOptionElement(dynamicElement, 3);

		return dynamicElement;
	}

	protected void addOptionElement(
			DDMStructure ddmStructure, String type, int num)
		throws Exception {

		Document structureDocument = getStructureDocument(ddmStructure);

		addOptionElement(structureDocument, type, num);

		ddmStructure.setXsd(structureDocument.asXML());
	}

	protected void addOptionElement(Document document, String type, int num)
		throws Exception {

		XPath structureXPath = SAXReaderUtil.createXPath(
			"//dynamic-element[@type=\"" + type + "\"]");

		List<Node> selectNodes = structureXPath.selectNodes(document);

		Node selectNode = selectNodes.get(0);

		Element selectElement = (Element)selectNode;

		addOptionElement(selectElement, num);
	}

	protected void addOptionElement(Element dynamicElement, int num)
		throws Exception {

		Element optionElement = dynamicElement.addElement("dynamic-element");

		optionElement.addAttribute("name", ServiceTestUtil.randomString());
		optionElement.addAttribute("type", "option");
		optionElement.addAttribute("value", "value " + num);

		Element optionMetadataElement = optionElement.addElement("meta-data");

		optionMetadataElement.addAttribute("locale", "en_US");

		Element optionMetadataEntryElement = optionMetadataElement.addElement(
			"entry");

		optionMetadataEntryElement.addAttribute("name", "label");
		optionMetadataEntryElement.addCDATA("option " + num);
	}

	protected Element addDynamicElementAttributes(
			Element dynamicElement, String required, String type, String name)
		throws Exception {

		dynamicElement.addAttribute("dataType", "string");
		dynamicElement.addAttribute("indexType", "keyword");
		dynamicElement.addAttribute("localizable", "true");

		if (type.equals("select")) {
			dynamicElement.addAttribute("multiple", "false");
		}

		if ((name != null) && !name.equals("")) {
			dynamicElement.addAttribute("name", name);
		}
		else {
			dynamicElement.addAttribute("name", ServiceTestUtil.randomString());
		}

		dynamicElement.addAttribute("readOnly", "false");
		dynamicElement.addAttribute("repeatable", "false");
		dynamicElement.addAttribute("required", required);
		dynamicElement.addAttribute("showLabel", "true");
		dynamicElement.addAttribute("type", type);

		if (type.equals("select")) {
			dynamicElement.addAttribute("type", "select");
		}

		if (type.equals("radio")) {
			dynamicElement.addAttribute("type", "radio");
		}

		if (type.equals("text")) {
			dynamicElement.addAttribute("type", "text");
		}

		if (type.equals("select") || type.equals("radio")) {
			dynamicElement.addAttribute("width", "");
		}

		if (type.equals("text")) {
			dynamicElement.addAttribute("width", "small");
		}

		return dynamicElement;
	}

	protected void addDynamicElementMetadata(
		Element dynamicElement, String type) {

		Element metadataElement = dynamicElement.addElement("meta-data");

		metadataElement.addAttribute("locale", "en_US");

		Element metadataEntryElement1 = metadataElement.addElement("entry");

		metadataEntryElement1.addAttribute("name", "label");

		if (type.equals("select")) {
			metadataEntryElement1.addCDATA("Select");
		}

		if (type.equals("radio")) {
			metadataEntryElement1.addCDATA("Radio");
		}

		if (type.equals("text")) {
			metadataEntryElement1.addCDATA("Text");
		}

		Element metadataEntryElement2 = metadataElement.addElement("entry");

		metadataEntryElement2.addAttribute("name", "predefinedValue");

		if (type.equals("select") || type.equals("radio")) {
			metadataEntryElement2.addCDATA("\"\"");
		}

		if (type.equals("text")) {
			metadataEntryElement2.addCDATA("");
		}

		Element metadataEntryElement3 = metadataElement.addElement("entry");

		metadataEntryElement3.addAttribute("name", "tip");
		metadataEntryElement3.addCDATA("");
	}

	protected Document getStructureDocument(DDMStructure ddmStructure)
		throws Exception {

		String xsd = ddmStructure.getXsd();

		return SAXReaderUtil.read(xsd);
	}

	private long _classNameId = PortalUtil.getClassNameId(DDLRecord.class);

}