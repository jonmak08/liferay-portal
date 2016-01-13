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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portlet.dynamicdatamapping.BaseDDMTestCase;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.io.Serializable;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.powermock.core.classloader.annotations.PrepareForTest;

/**
 * @author Marcellus Tavares
 */
@PrepareForTest({DDMStructureLocalServiceUtil.class})
public class DDMImplTest extends BaseDDMTestCase {

	@Before
	public void setUp() throws Exception {
		super.setUp();

		setUpDDMStructureLocalServiceUtil();
	}

	@Test
	public void testMergeAfterNewFieldIsAddedAndPublishingContentAtBranch()
		throws Exception {

		Document document = createEmptyDocument();

		DDMStructure ddmStructure = createStructure(
			"Test Structure", document.asXML());

		Field existingField = createField(
			ddmStructure.getStructureId(), "Text1807", null);

		Field existingFieldsDisplayField = createFieldsDisplayField(
			ddmStructure.getStructureId(), "Text1807_INSTANCE_ovho");

		Fields existingFields = createFields(
			existingField, existingFieldsDisplayField);

		Field newField = createField(
			ddmStructure.getStructureId(), "Text1853", null);

		Field newFieldsDisplayField = createFieldsDisplayField(
			ddmStructure.getStructureId(),
			"Text1807_INSTANCE_ovho,Text1853_INSTANCE_cgac");

		Fields newFields = createFields(newField, newFieldsDisplayField);

		Fields mergedFields = _ddmImpl.mergeFields(newFields, existingFields);

		Field fieldsDisplayField = mergedFields.get(
			DDMImpl.FIELDS_DISPLAY_NAME);

		Assert.assertNotNull(fieldsDisplayField);

		String fieldsDisplayValue = (String)fieldsDisplayField.getValue();

		String[] fieldsDisplayValues = StringUtil.split(fieldsDisplayValue);

		testValues(
			fieldsDisplayValues, "Text1807_INSTANCE_ovho",
			"Text1853_INSTANCE_cgac");
	}

	@Test
	public void testMergeFieldsWhenAddingTranslationAtBranch()
		throws Exception {

		Document document = createEmptyDocument();

		addTextElement(document.getRootElement(), "Localizable", "", true);

		addTextElement(document.getRootElement(), "NonLocalizable", "", false);

		DDMStructure ddmStructure = createStructure(
				"Test Structure", document.asXML());

		Field existingLocalizableField = createField(
			ddmStructure.getStructureId(), "Localizable",
			createValuesList("Joe"));

		Field existingNonLocalizableField = createField(
			ddmStructure.getStructureId(), "NonLocalizable",
			createValuesList("NonLocalizable"));

		Field existingFieldsDisplayField = createFieldsDisplayField(
			ddmStructure.getStructureId(),
			"Localizable_INSTANCE_ovho,NonLocalizable_INSTANCE_zuvh");

		Fields existingFields = createFields(
			existingLocalizableField, existingNonLocalizableField,
			existingFieldsDisplayField);

		Field newLocalizedField = createBRField(
			ddmStructure.getStructureId(), "Localizable",
			createValuesList("Joao"));

		Field newFieldsDisplayField = createFieldsDisplayField(
			ddmStructure.getStructureId(), "Localizable_INSTANCE_ovho");

		Fields newFields = createFields(
			newLocalizedField, newFieldsDisplayField);

		Fields mergedFields = _ddmImpl.mergeFields(newFields, existingFields);

		Field fieldsDisplayField = mergedFields.get(
			DDMImpl.FIELDS_DISPLAY_NAME);

		Assert.assertNotNull(fieldsDisplayField);

		String fieldsDisplayValue = (String)fieldsDisplayField.getValue();
		String[] fieldsDisplayValues = StringUtil.split(fieldsDisplayValue);

		testValues(
			fieldsDisplayValues, "Localizable_INSTANCE_ovho",
			"NonLocalizable_INSTANCE_zuvh");
	}

	protected void testValues(
		List<Serializable> actualValues, String... expectedValues) {

		Assert.assertEquals(expectedValues.length, actualValues.size());

		for (int i = 0; i < expectedValues.length; i++) {
			Assert.assertEquals(expectedValues[i], actualValues.get(i));
		}
	}

	protected void testValues(String[] actualValues, String... expectedValues) {
		Assert.assertEquals(expectedValues.length, actualValues.length);

		for (int i = 0; i < expectedValues.length; i++) {
			Assert.assertEquals(expectedValues[i], actualValues[i]);
		}
	}

	private final DDMImpl _ddmImpl = new DDMImpl();

}