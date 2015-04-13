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

package com.liferay.portlet.journal.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.templateparser.BaseTransformerListener;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMXMLUtil;
import com.liferay.portlet.journal.model.JournalArticle;

import java.util.List;
import java.util.Map;

/**
 * @author Raymond Augé
 */
public class LocaleTransformerListener extends BaseTransformerListener {

	@Override
	public String onScript(
		String script, String xml, String languageId,
		Map<String, String> tokens) {

		if (_log.isDebugEnabled()) {
			_log.debug("onScript");
		}

		return StringUtil.replace(script, "@language_id@", languageId);
	}

	@Override
	public String onXml(
		String xml, String languageId, Map<String, String> tokens) {

		if (_log.isDebugEnabled()) {
			_log.debug("onXml");
		}

		xml = filterByLocalizability(xml, tokens);

		return filterByLanguage(xml, languageId);
	}

	protected void filterByLanguage(
		Element root, String languageId, String defaultLanguageId) {

		Element defaultLanguageElement = null;

		boolean hasLanguageIdElement = false;

		List<Element> elements = root.elements();

		int listIndex = elements.size() - 1;

		while (listIndex >= 0) {
			Element element = elements.get(listIndex);

			String tempLanguageId = element.attributeValue(
				"language-id", languageId);

			if (StringUtil.equalsIgnoreCase(tempLanguageId, languageId)) {
				hasLanguageIdElement = true;

				filterByLanguage(element, languageId, defaultLanguageId);
			}
			else {
				if (StringUtil.equalsIgnoreCase(
						tempLanguageId, defaultLanguageId)) {

					defaultLanguageElement = element;
				}

				root.remove(element);
			}

			listIndex--;
		}

		if (!hasLanguageIdElement && (defaultLanguageElement != null)) {
			root.add(defaultLanguageElement);

			filterByLanguage(
				defaultLanguageElement, languageId, defaultLanguageId);
		}
	}

	protected String filterByLanguage(String xml, String languageId) {
		if (xml == null) {
			return xml;
		}

		try {
			Document document = SAXReaderUtil.read(xml);

			Element rootElement = document.getRootElement();

			String defaultLanguageId = LocaleUtil.toLanguageId(
				LocaleUtil.getSiteDefault());

			String[] availableLocales = StringUtil.split(
				rootElement.attributeValue(
					"available-locales", defaultLanguageId));

			String defaultLocale = rootElement.attributeValue(
				"default-locale", defaultLanguageId);

			boolean supportedLocale = false;

			for (String availableLocale : availableLocales) {
				if (StringUtil.equalsIgnoreCase(availableLocale, languageId)) {
					supportedLocale = true;

					break;
				}
			}

			if (!supportedLocale) {
				filterByLanguage(rootElement, defaultLocale, defaultLanguageId);
			}
			else {
				filterByLanguage(rootElement, languageId, defaultLanguageId);
			}

			xml = DDMXMLUtil.formatXML(document);
		}
		catch (Exception e) {
			_log.error(e);
		}

		return xml;
	}

	protected String filterByLocalizability(
		String xml, Map<String, String> tokens) {

		if (xml == null || tokens == null) {
			return xml;
		}

		try {
			Document document = SAXReaderUtil.read(xml);

			String structureKey = tokens.get("structure_id");

			long groupId = Long.parseLong(tokens.get("article_group_id"));

			DDMStructure structure = null;

			structure = DDMStructureLocalServiceUtil.fetchStructure(
					groupId, ClassNameLocalServiceUtil
						.getClassNameId(JournalArticle.class), structureKey,
					true);

			if (Validator.isNull(structure)) {
				return xml;
			}

			Element rootElement = document.getRootElement();

			String defaultLanguageId = LocaleUtil.toLanguageId(
				LocaleUtil.getSiteDefault());

			String defaultLocale = rootElement.attributeValue(
				"default-locale", defaultLanguageId);

			filterByLocalizability(rootElement, defaultLocale, structure);

			xml = DDMXMLUtil.formatXML(document);
		}
		catch (Exception e) {
			_log.error(e);
		}

		return xml;
	}

	protected void filterByLocalizability(
			Element root, String defaultLanguageId, DDMStructure structure)
		throws PortalException, SystemException {

		List<Element> elements = root.elements("dynamic-element");

		int listIndex = elements.size() - 1;

		while (listIndex >= 0) {
			Element element = elements.get(listIndex);

			String name = element.attributeValue("name");

			if (!structure.hasField(name)) {
				listIndex--;
				continue;
			}

			if (!structure.isFieldTransient(name)) {
				filterFields(element, structure, name, defaultLanguageId);
			}

			filterByLocalizability(element, defaultLanguageId, structure);

			listIndex--;
		}
	}

	protected void filterFields(
			Element dynamicElementElement, DDMStructure ddmStructure,
			String name, String defaultLanguageId)
		throws PortalException, SystemException {

		boolean localizable = GetterUtil.getBoolean(
			ddmStructure.getFieldProperty(name, "localizable"));

		List<Element> dynamicContentElements = dynamicElementElement.elements(
			"dynamic-content");

		for (Element dynamicContentElement : dynamicContentElements) {
			String languageId = dynamicContentElement.attributeValue(
				"language-id");

			if (!localizable && !languageId.equals(defaultLanguageId)) {
				dynamicElementElement.remove(dynamicContentElement);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LocaleTransformerListener.class);

}