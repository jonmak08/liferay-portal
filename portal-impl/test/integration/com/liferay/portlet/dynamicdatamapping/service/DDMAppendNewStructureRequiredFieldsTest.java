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

import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureImpl;
import com.liferay.portlet.dynamicdatamapping.service.impl.DDMStructureLocalServiceImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * @author Tyler Wong
 */
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class DDMAppendNewStructureRequiredFieldsTest
	extends DDMStructureLocalServiceImpl {

	@Before
	public void setUp() throws Exception {
		_templateDocument = SAXReaderUtil.createDocument();

		addRootElement(_templateDocument);

		_ddmStructure = new DDMStructureImpl();

		Document structureDocument = SAXReaderUtil.createDocument();

		Element rootElement = addRootElement(structureDocument);

		_ddmStructure.setXsd(rootElement.asXML());
	}

	@Test
	public void testAddSelectOptionRequired() throws Exception {
		addSelectElement(_ddmStructure, "true");

		addOptionElement(_ddmStructure, "select", 4);

		appendNewStructureRequiredFields(_ddmStructure, _templateDocument);

		Assert.assertEquals(_ddmStructure.getXsd(), _templateDocument.asXML());
	}

	@Test
	public void testAddSelectOptionNotRequired() throws Exception {
		addSelectElement(_ddmStructure, "false");

		addOptionElement(_ddmStructure, "select", 4);

		appendNewStructureRequiredFields(_ddmStructure, _templateDocument);

		Assert.assertNotEquals(_ddmStructure.getXsd(), _templateDocument.asXML());
	}

	@Test
	public void testAddRadioOptionRequired() throws Exception {
		addRadioElement(_ddmStructure, "true");

		addOptionElement(_ddmStructure, "radio", 4);

		appendNewStructureRequiredFields(_ddmStructure, _templateDocument);

		Assert.assertEquals(_ddmStructure.getXsd(), _templateDocument.asXML());
	}

	@Test
	public void testAddRadioOptionNotRequired() throws Exception {
		addRadioElement(_ddmStructure, "false");

		addOptionElement(_ddmStructure, "radio", 4);

		appendNewStructureRequiredFields(_ddmStructure, _templateDocument);

		Assert.assertNotEquals(_ddmStructure.getXsd(), _templateDocument.asXML());
	}

	@Test
	public void testAddTextElementRequired() throws Exception {
		addTextElement(_ddmStructure, "true");

		appendNewStructureRequiredFields(_ddmStructure, _templateDocument);

		Assert.assertEquals(_ddmStructure.getXsd(), _templateDocument.asXML());
	}

	@Test
	public void testAddTextElementNotRequired() throws Exception {
		addTextElement(_ddmStructure, "false");

		appendNewStructureRequiredFields(_ddmStructure, _templateDocument);

		Assert.assertNotEquals(_ddmStructure.getXsd(), _templateDocument.asXML());
	}

	protected Element addRootElement(Document document) {
		Element rootElement = document.addElement("root");

		rootElement.addAttribute("available-locales", "en_US");
		rootElement.addAttribute("default-locale", "en_US");

		return rootElement;
	}

	protected Element addTextElement(DDMStructure ddmStructure, String required) throws Exception {
		Document structureDocument = getStructureDocument(ddmStructure);

		Element rootElement = structureDocument.getRootElement();

		Element textElement = rootElement.addElement("dynamic-element");

		addDynamicElementAttributes(textElement, required, "text");

		addDynamicElementMetadata(textElement, "text");

		_ddmStructure.setXsd(structureDocument.asXML());

		return textElement;
	}

	protected Element addSelectElement(DDMStructure ddmStructure, String required) throws Exception {
		Document structureDocument = getStructureDocument(ddmStructure);

		Element rootElement = structureDocument.getRootElement();

		Element dynamicElement = addSelectElement(rootElement, required);

		_ddmStructure.setXsd(structureDocument.asXML());

		return dynamicElement;
	}

	protected Element addSelectElement(Element element, String required) throws Exception {
		Element dynamicElement = element.addElement("dynamic-element");

		addDynamicElementAttributes(dynamicElement, required, "select");

		addOptionElement(dynamicElement, 1);
		addOptionElement(dynamicElement, 2);
		addOptionElement(dynamicElement, 3);

		addDynamicElementMetadata(dynamicElement, "select");

		return dynamicElement;
	}

	protected Element addRadioElement(DDMStructure ddmStructure, String required) throws Exception {
		Document structureDocument = getStructureDocument(ddmStructure);

		Element rootElement = structureDocument.getRootElement();

		Element dynamicElement = addRadioElement(rootElement, required);

		_ddmStructure.setXsd(structureDocument.asXML());

		return dynamicElement;
	}

	protected Element addRadioElement(Element element, String required) throws Exception {
		Element dynamicElement = element.addElement("dynamic-element");

		addDynamicElementAttributes(dynamicElement, required, "radio");

		addOptionElement(dynamicElement, 1);
		addOptionElement(dynamicElement, 2);
		addOptionElement(dynamicElement, 3);

		addDynamicElementMetadata(dynamicElement, "radio");

		return dynamicElement;
	}

	protected void addOptionElement(DDMStructure ddmStructure, String type, int num) throws Exception {
		XPath structureXPath = SAXReaderUtil.createXPath("//dynamic-element[@type=\"" + type + "\"]");

		Document structureDocument = getStructureDocument(ddmStructure);

		List<Node> selectNodes = structureXPath.selectNodes(structureDocument);

		Node selectNode = selectNodes.get(0);  // Only testing for first selected element

		Element selectElement = (Element) selectNode;

		addOptionElement(selectElement, num);

		_ddmStructure.setXsd(structureDocument.asXML());
	}

	protected void addOptionElement(Element dynamicElement, int num) throws Exception {
		Element optionElement = dynamicElement.addElement("dynamic-element");

		optionElement.addAttribute("name", ServiceTestUtil.randomString());
		optionElement.addAttribute("type", "option");
		optionElement.addAttribute("value", "value " + num);

		Element optionMetadataElement = optionElement.addElement("meta-data");

		optionMetadataElement.addAttribute("locale", "en_US");

		Element optionMetadataEntryElement = optionMetadataElement.addElement("entry");

		optionMetadataEntryElement.addAttribute("name", "label");
		optionMetadataEntryElement.addCDATA("option " + num);
	}

	protected Element addDynamicElementAttributes(Element dynamicElement, String required, String type) throws Exception {
		dynamicElement.addAttribute("dataType", "string");
		dynamicElement.addAttribute("indexType", "keyword");
		dynamicElement.addAttribute("localizable", "true");

		if (type.equals("select")) {
			dynamicElement.addAttribute("multiple", "false");
		}

		dynamicElement.addAttribute("name", ServiceTestUtil.randomString());
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

	protected void addDynamicElementMetadata(Element dynamicElement, String type) {
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

	protected Document getStructureDocument(DDMStructure ddmStructure) throws DocumentException {
		String xsd = ddmStructure.getXsd();

		return SAXReaderUtil.read(xsd);
	}

	private DDMStructure _ddmStructure;
	private Document _templateDocument;

}