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

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructureConstants;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eudaldo Alonso
 */
public class DDMStructureTestUtil {

	public static DDMStructure addStructure(long groupId, String className)
		throws Exception {

		return addStructure(
			groupId, className, 0, getSampleStructureXSD(),
			LocaleUtil.getSiteDefault(), ServiceTestUtil.getServiceContext());
	}

	public static DDMStructure addStructure(
			long groupId, String className, Locale defaultLocale)
		throws Exception {

		return addStructure(
			groupId, className, 0, getSampleStructureXSD(), defaultLocale,
			ServiceTestUtil.getServiceContext());
	}

	public static DDMStructure addStructure(
			long groupId, String className, long parentStructureId)
		throws Exception {

		return addStructure(
			groupId, className, parentStructureId, getSampleStructureXSD(),
			LocaleUtil.getSiteDefault(), ServiceTestUtil.getServiceContext());
	}

	public static DDMStructure addStructure(
			long groupId, String className, long parentStructureId, String xsd,
			Locale defaultLocale, ServiceContext serviceContext)
		throws Exception {

		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		nameMap.put(defaultLocale, "Test Structure");

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return DDMStructureLocalServiceUtil.addStructure(
			TestPropsValues.getUserId(), groupId, parentStructureId,
			PortalUtil.getClassNameId(className), null, nameMap, null, xsd,
			PropsValues.DYNAMIC_DATA_LISTS_STORAGE_TYPE,
			DDMStructureConstants.TYPE_DEFAULT, serviceContext);
	}

	public static DDMStructure addStructure(
			long groupId, String className, String xsd)
		throws Exception {

		return addStructure(
			groupId, className, 0, xsd, LocaleUtil.getSiteDefault(),
			ServiceTestUtil.getServiceContext());
	}

	public static DDMStructure addStructure(String className) throws Exception {
		return addStructure(
			TestPropsValues.getGroupId(), className, 0, getSampleStructureXSD(),
			LocaleUtil.getSiteDefault(), ServiceTestUtil.getServiceContext());
	}

	public static DDMStructure addStructure(
			String className, Locale defaultLocale)
		throws Exception {

		return addStructure(
			TestPropsValues.getGroupId(), className, 0,
			getSampleStructureXSD(
				"name", new Locale[] {LocaleUtil.US}, defaultLocale),
			defaultLocale, ServiceTestUtil.getServiceContext());
	}

	public static DDMStructure addStructure(
			String className, Locale[] availableLocales, Locale defaultLocale)
		throws Exception {

		return addStructure(
			TestPropsValues.getGroupId(), className, 0,
			getSampleStructureXSD("name", availableLocales, defaultLocale),
			defaultLocale, ServiceTestUtil.getServiceContext());
	}

	public static DDMStructure addStructure(String className, String xsd)
		throws Exception {

		return addStructure(
			TestPropsValues.getGroupId(), className, 0, xsd,
			LocaleUtil.getSiteDefault(), ServiceTestUtil.getServiceContext());
	}

	public static DDMStructure addStructure(
			String className, String xsd, Locale defaultLocale)
		throws Exception {

		return addStructure(
			TestPropsValues.getGroupId(), className, 0, xsd, defaultLocale,
			ServiceTestUtil.getServiceContext());
	}

	public static String getSampleStructuredContent() {
		return getSampleStructuredContent("name", "title");
	}

	public static String getSampleStructuredContent(
			Map<Locale, String> contents, String defaultLocale) {

		return getSampleStructuredContent(
			"name", Collections.singletonList(contents), defaultLocale);
	}

	public static String getSampleStructuredContent(String keywords) {
		return getSampleStructuredContent("name", keywords);
	}

	public static String getSampleStructuredContent(
		String name, List<Map<Locale, String>> contents, String defaultLocale) {

		StringBundler availableLocales = new StringBundler(2 * contents.size());

		for (Map<Locale, String> map : contents) {
			StringBundler sb = new StringBundler(2 * map.size());

			for (Locale locale : map.keySet()) {
				sb.append(LocaleUtil.toLanguageId(locale));
				sb.append(StringPool.COMMA);
			}

			sb.setIndex(sb.index() - 1);

			availableLocales.append(sb);
		}

		Document document = createDocumentContent(
			availableLocales.toString(), defaultLocale);

		Element rootElement = document.getRootElement();

		for (Map<Locale, String> map : contents) {
			Element dynamicElementElement = rootElement.addElement(
				"dynamic-element");

			dynamicElementElement.addAttribute("index-type", "keyword");
			dynamicElementElement.addAttribute("name", name);
			dynamicElementElement.addAttribute("type", "text");

			for (Map.Entry<Locale, String> entry : map.entrySet()) {
				Element element = dynamicElementElement.addElement(
					"dynamic-content");

				element.addAttribute(
					"language-id", LocaleUtil.toLanguageId(entry.getKey()));
				element.addCDATA(entry.getValue());
			}
		}

		return document.asXML();
	}

	public static String getSampleStructuredContent(
			String name, String keywords) {

		Map<Locale, String> contents = new HashMap<Locale, String>();

		contents.put(Locale.US, keywords);

		return getSampleStructuredContent(
			name, Collections.singletonList(contents), "en_US");
	}

	public static String getSampleStructureXSD() {
		return getSampleStructureXSD("name");
	}

	public static String getSampleStructureXSD(
		Locale[] availableLocales, Locale defaultLocale) {

		return getSampleStructureXSD("name", availableLocales, defaultLocale);
	}

	public static String getSampleStructureXSD(String name) {
		return getSampleStructureXSD(
			name, new Locale[] {LocaleUtil.US}, LocaleUtil.US);
	}

	public static String getSampleStructureXSD(
		String name, Locale[] availableLocales, Locale defaultLocale) {

		return getSampleStructureXSD(
			name, "string", true, "text", availableLocales, defaultLocale);
	}

	public static String getSampleStructureXSD(
		String name, String dataType, boolean repeatable, String type,
		Locale[] availableLocales, Locale defaultLocale) {

		Document document = createDocumentStructure(
			availableLocales, defaultLocale);

		Element rootElement = document.getRootElement();

		Element dynamicElementElement = rootElement.addElement(
			"dynamic-element");

		dynamicElementElement.addAttribute("dataType", dataType);
		dynamicElementElement.addAttribute("indexType", "text");
		dynamicElementElement.addAttribute("name", name);
		dynamicElementElement.addAttribute(
			"repeatable", Boolean.toString(repeatable));
		dynamicElementElement.addAttribute("required", "false");
		dynamicElementElement.addAttribute("type", type);

		Element metaDataElement = dynamicElementElement.addElement("meta-data");

		metaDataElement.addAttribute(
			"locale", LocaleUtil.toLanguageId(defaultLocale));

		Element labelElement = metaDataElement.addElement("entry");

		labelElement.addAttribute("name", "label");
		labelElement.addCDATA("Field");

		return document.asXML();
	}

	protected static Document createDocumentContent(
			String availableLocales, String defaultLocale) {

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("root");

		rootElement.addAttribute("available-locales", availableLocales);
		rootElement.addAttribute("default-locale", defaultLocale);
		rootElement.addElement("request");

		return document;
	}

	protected static Document createDocumentStructure(
		Locale[] availableLocales, Locale defaultLocale) {

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("root");

		rootElement.addAttribute(
			"available-locales",
			StringUtil.merge(LocaleUtil.toLanguageIds(availableLocales)));
		rootElement.addAttribute(
			"default-locale", LocaleUtil.toLanguageId(defaultLocale));

		return document;
	}

}