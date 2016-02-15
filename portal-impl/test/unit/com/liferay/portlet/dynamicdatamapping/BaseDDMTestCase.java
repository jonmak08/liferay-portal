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

package com.liferay.portlet.dynamicdatamapping;

import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.UnsecureSAXReaderUtil;
import com.liferay.portal.util.HtmlImpl;
import com.liferay.portal.util.LocalizationImpl;
import com.liferay.portal.util.RandomTestUtil;
import com.liferay.portal.xml.SAXReaderImpl;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureImpl;
import com.liferay.portlet.dynamicdatamapping.model.impl.DDMTemplateImpl;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.util.DDMImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Before;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Pablo Carvalho
 * @author Miguel Angelo Caldas Gallindo
 */
@PrepareForTest(
	{
		HtmlUtil.class, LocaleUtil.class, LocalizationUtil.class,
		PropsUtil.class, SAXReaderUtil.class, UnsecureSAXReaderUtil.class
	})
@RunWith(PowerMockRunner.class)
public class BaseDDMTestCase extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		setUpHtmlUtil();
		setUpLocaleUtil();
		setUpLocalizationUtil();
		setUpPropsUtil();
		setUpSAXReaderUtil();
		setUpUnsecureSAXReaderUtil();
	}

	protected Element addTextElement(
		Element element, String name, String label, boolean localizable) {

		Element dynamicElement = element.addElement("dynamic-element");

		dynamicElement.addAttribute("dataType", "string");
		dynamicElement.addAttribute("localizable", String.valueOf(localizable));
		dynamicElement.addAttribute("name", name);
		dynamicElement.addAttribute("type", "text");

		Element metadataElement = dynamicElement.addElement("meta-data");

		metadataElement.addAttribute("locale", LocaleUtil.toLanguageId(
			LocaleUtil.US));

		Element entryElement = metadataElement.addElement("entry");

		entryElement.addAttribute("name", "label");
		entryElement.setText(label);

		return dynamicElement;
	}

	protected Field createBRField(
		long ddmStructureId, String fieldName, List<Serializable> ptValues) {

		return new MockField(
			ddmStructureId, fieldName, ptValues, LocaleUtil.BRAZIL,
			LocaleUtil.US);
	}

	protected Document createDocument(String... fieldNames) {
		Document document = createEmptyDocument();

		for (String fieldName : fieldNames) {
			addTextElement(
				document.getRootElement(), fieldName, fieldName, false);
		}

		return document;
	}

	protected Document createEmptyDocument() {
		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("root");

		rootElement.addAttribute("available-locales", "en_US");
		rootElement.addAttribute("default-locale", "en_US");

		return document;
	}

	protected Field createField(
		long ddmStructureId, String fieldName, List<Serializable> values) {

		Field field = new MockField(
			ddmStructureId, fieldName, values, LocaleUtil.US, LocaleUtil.US);

		return field;
	}

	protected Fields createFields(Field... fieldsArray) {
		Fields fields = new Fields();

		for (Field field : fieldsArray) {
			fields.put(field);
		}

		return fields;
	}

	protected Field createFieldsDisplayField(
		long ddmStructureId, String value) {

		return createFieldsDisplayField(ddmStructureId, value, LocaleUtil.US);
	}

	protected Field createFieldsDisplayField(
			long ddmStructureId, String value, Locale locale) {

		return createFieldsDisplayField(ddmStructureId, value, locale, locale);
	}

	protected Field createFieldsDisplayField(
			long ddmStructureId, String value, Locale locale,
			Locale defaultLocale) {

			Field fieldsDisplayField = new MockField(
				ddmStructureId, DDMImpl.FIELDS_DISPLAY_NAME,
				createValuesList(value), locale);

			fieldsDisplayField.setDefaultLocale(defaultLocale);

			return fieldsDisplayField;
	}

	protected Document createSampleDocument() {
		Document document = createEmptyDocument();

		addTextElement(
			document.getRootElement(), "Unlocalizable", "Text 2", false);

		return document;
	}

	protected DDMStructure createStructure(String name, String... fieldNames) {
		DDMStructure structure = new DDMStructureImpl();

		structure.setStructureId(RandomTestUtil.randomLong());
		structure.setName(name);

		Document document = createDocument(fieldNames);

		structure.setDocument(document);

		_structures.put(structure.getStructureId(), structure);

		return structure;
	}

	protected DDMStructure createStructure(String name, String definition) {
		DDMStructure structure = new DDMStructureImpl();

		structure.setStructureId(RandomTestUtil.randomLong());
		structure.setName(name);
		structure.setXsd(definition);

		_structures.put(structure.getStructureId(), structure);

		return structure;
	}

	protected DDMTemplate createTemplate(
		long templateId, String name, String mode, String script) {

		DDMTemplate template = new DDMTemplateImpl();

		template.setTemplateId(templateId);
		template.setName(name);
		template.setMode(mode);
		template.setScript(script);

		_templates.put(template.getTemplateId(), template);

		return template;
	}

	protected List<Serializable> createValuesList(String... valuesString) {
		List<Serializable> values = new ArrayList<Serializable>();

		for (String valueString : valuesString) {
			values.add(valueString);
		}

		return values;
	}

	protected DDMStructure getStructure(long structureId) {
		try {
			return DDMStructureLocalServiceUtil.getStructure(structureId);
		}
		catch (Exception e) {
			return null;
		}
	}

	protected DDMTemplate getTemplate(long templateId) {
		try {
			return DDMTemplateLocalServiceUtil.getTemplate(templateId);
		}
		catch (Exception e) {
			return null;
		}
	}

	protected void setUpHtmlUtil() {
		spy(HtmlUtil.class);

		when(
			HtmlUtil.getHtml()
		).thenReturn(
			new HtmlImpl()
		);
	}

	protected void setUpDDMStructureLocalServiceUtil() throws Exception {
		mockStatic(DDMStructureLocalServiceUtil.class);

		when(
			getStructure(Matchers.anyLong())
		).then(
			new Answer<DDMStructure>() {

				@Override
				public DDMStructure answer(InvocationOnMock invocationOnMock)
					throws Throwable {

					Object[] args = invocationOnMock.getArguments();

					Long structureId = (Long)args[0];

					DDMStructure ddmStructure = _structures.get(structureId);

					if (ddmStructure == null) {
						throw new NoSuchStructureException(
							"No DDMStructure exists with the primary key " +
								structureId);
					}

					return ddmStructure;
				}

			}
		);
	}

	protected void setUpDDMTemplateLocalServiceUtil() throws Exception {
		mockStatic(DDMTemplateLocalServiceUtil.class);

		when(
			getTemplate(Matchers.anyLong())
		).then(
			new Answer<DDMTemplate>() {

				@Override
				public DDMTemplate answer(InvocationOnMock invocationOnMock)
					throws Throwable {

					Object[] args = invocationOnMock.getArguments();

					Long templateId = (Long)args[0];

					return _templates.get(templateId);
				}

			}
		);
	}

	protected void setUpLocaleUtil() {
		spy(LocaleUtil.class);

		when(
			LocaleUtil.fromLanguageId("en_US")
		).thenReturn(
			LocaleUtil.US
		);

		when(
			LocaleUtil.fromLanguageId("pt_BR")
		).thenReturn(
			LocaleUtil.BRAZIL
		);
	}

	protected void setUpLocalizationUtil() {
		spy(LocalizationUtil.class);

		when(
			LocalizationUtil.getLocalization()
		).thenReturn(
			new LocalizationImpl()
		);
	}

	protected void setUpPropsUtil() {
		mockStatic(PropsUtil.class);

		when(
			PropsUtil.get(
				PropsKeys.DYNAMIC_DATA_MAPPING_STRUCTURE_PRIVATE_FIELD_DATATYPE)
		).thenReturn(
			"string"
		);

		when(
			PropsUtil.get(
				PropsKeys.
					DYNAMIC_DATA_MAPPING_STRUCTURE_PRIVATE_FIELD_REPEATABLE)
		).thenReturn(
			"false"
		);

		when(
			PropsUtil.get(PropsKeys.DYNAMIC_DATA_MAPPING_IMAGE_EXTENSIONS)
		).thenReturn(
			".gif,.jpeg,.jpg,.png"
		);

		when(
			PropsUtil.get(PropsKeys.DYNAMIC_DATA_MAPPING_IMAGE_SMALL_MAX_SIZE)
		).thenReturn(
			"51200"
		);

		when(
			PropsUtil.get(PropsKeys.INDEX_DATE_FORMAT_PATTERN)
		).thenReturn(
			"yyyyMMddHHmmss"
		);
	}

	protected void setUpSAXReaderUtil() {
		spy(SAXReaderUtil.class);

		when(
			SAXReaderUtil.getSAXReader()
		).thenReturn(
			new SAXReaderImpl()
		);
	}

	protected void setUpUnsecureSAXReaderUtil() {
		spy(UnsecureSAXReaderUtil.class);

		when(
			UnsecureSAXReaderUtil.getSAXReader()
		).thenReturn(
			new SAXReaderImpl()
		);
	}

	private Map<Long, DDMStructure> _structures =
		new HashMap<Long, DDMStructure>();
	protected Map<Long, DDMTemplate> _templates =
		new HashMap<Long, DDMTemplate>();

	protected class MockField extends Field {

		public MockField(long ddmStructureId, String name, Serializable value) {
			super(ddmStructureId, name, value);
		}

		public MockField(
			long ddmStructureId, String name, List<Serializable> values,
			Locale locale) {

			this(ddmStructureId, name, values, locale, locale);
		}

		public MockField(
				long ddmStructureId, String name, List<Serializable> values,
				Locale locale, Locale defaultLocale) {

				super(ddmStructureId, name, values, locale);
				setDefaultLocale(defaultLocale);
		}

		@Override
		public DDMStructure getDDMStructure() {
			return _structures.get(getDDMStructureId());
		}

		private static final long serialVersionUID = 1L;

	}

}