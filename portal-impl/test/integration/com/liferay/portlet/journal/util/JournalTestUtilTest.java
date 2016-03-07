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

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Image;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.CompanyTestUtil;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.ServiceContextTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.dynamicdatamapping.StructureNameException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMStructureTestUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMTemplateTestUtil;
import com.liferay.portlet.journal.NoSuchArticleException;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalFolder;
import com.liferay.portlet.journal.model.JournalFolderConstants;
import com.liferay.portlet.journal.service.JournalArticleImageLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Manuel de la Peña
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		SynchronousDestinationExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
@Transactional
public class JournalTestUtilTest {

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testAddArticleWithDDMStructureAndDDMTemplate()
		throws Exception {

		Document document = JournalTestUtil.createDocument("en_US", "en_US");

		Element dynamicElementElement =
			JournalTestUtil.addDynamicElementElement(
				document.getRootElement(), "text", "name");

		JournalTestUtil.addDynamicContentElement(
			dynamicElementElement, "en_US", "Joe Bloggs");

		String xml = document.asXML();

		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			JournalArticle.class.getName());

		DDMTemplate ddmTemplate = DDMTemplateTestUtil.addTemplate(
			ddmStructure.getStructureId(), TemplateConstants.LANG_TYPE_VM,
			JournalTestUtil.getSampleTemplateXSL());

		Assert.assertNotNull(
			JournalTestUtil.addArticleWithXMLContent(
				xml, ddmStructure.getStructureKey(),
				ddmTemplate.getTemplateKey()));
	}

	@Test
	public void testAddArticleWithStructureAndTemplateWithTranslationsImages()
		throws Exception {

		Document document = JournalTestUtil.createDocument(
			"en_US,fr_FR", "en_US");

		Element dynamicElementTextElement =
			JournalTestUtil.addDynamicElementElement(
				document.getRootElement(), "text", "name");

		JournalTestUtil.addDynamicContentElement(
			dynamicElementTextElement, "en_US", "Joe Bloggs");

		JournalTestUtil.addDynamicContentElement(
			dynamicElementTextElement, "fr_FR", "Joe Bloggs French");

		String index = "0";
		String instanceId = "qabd";

		Element dynamicElementImageElement =
			JournalTestUtil.addDynamicElementElement(
				document.getRootElement(), "image", "image", "imageName",
				instanceId, index);

		long groupId = _group.getGroupId();

		String articleId = String.valueOf(CounterLocalServiceUtil.increment());

		String elName = "imageName_" + index;

		Map<String, byte[]> images = new HashMap<String, byte[]>();

		byte[] englishBinary = readBinary("liferay.png");

		images.put(
			instanceId + StringPool.UNDERLINE + elName + "_en_US",
			englishBinary);

		byte[] frenchBinary = readBinary("company_logo.png");

		Assert.assertNotEquals(englishBinary, frenchBinary);

		String frenchImageKey = instanceId + StringPool.UNDERLINE + elName +
			"_fr_FR";

		images.put(frenchImageKey, frenchBinary);

		JSONObject jsonObjectEnglish = JSONFactoryUtil.createJSONObject();

		jsonObjectEnglish.put(
			"data", UnicodeFormatter.bytesToHex(englishBinary));

		JSONObject jsonObjectFrench = JSONFactoryUtil.createJSONObject();

		jsonObjectFrench.put("data", UnicodeFormatter.bytesToHex(frenchBinary));

		JournalTestUtil.addDynamicContentElement(
			dynamicElementImageElement, "en_US", jsonObjectEnglish.toString());

		JournalTestUtil.addDynamicContentElement(
			dynamicElementImageElement, "fr_FR", jsonObjectFrench.toString());

		String xml = document.asXML();

		String xsd = readText ("test-ddm-structure-image.xml");

		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			groupId, JournalArticle.class.getName(), xsd);

		DDMTemplate ddmTemplate = DDMTemplateTestUtil.addTemplate(
			groupId, ddmStructure.getStructureId(),
			TemplateConstants.LANG_TYPE_VM,
			JournalTestUtil.getSampleTemplateXSL(), Locale.US);

		JournalArticle article = JournalTestUtil.addArticleWithXMLContent(
			articleId, groupId, xml, ddmStructure.getStructureKey(),
			ddmTemplate.getTemplateKey(), images);

		Assert.assertNotNull(article);

		long imageIdEn = JournalArticleImageLocalServiceUtil.getArticleImageId(
			groupId, articleId, 1.0, instanceId, elName, "_en_US");

		long imageIdFr = JournalArticleImageLocalServiceUtil.getArticleImageId(
			groupId, articleId, 1.0, instanceId, elName, "_fr_FR");

		String englishContent = article.getContentByLocale("en_US");

		Document englishDocument = SAXReaderUtil.read(englishContent);

		String imageXPath = "//dynamic-element[contains(@name, 'imageName')]" +
			"//dynamic-content";

		Element englishImageElement = (Element)englishDocument.selectSingleNode(
			imageXPath);

		String englishImageURL = englishImageElement.getText();

		Assert.assertTrue(englishImageURL.indexOf("img_id=" + imageIdEn) != -1);

		Image englishImage = ImageLocalServiceUtil.getImage(imageIdEn);

		Assert.assertArrayEquals(englishBinary, englishImage.getTextObj());

		String frenchContent = article.getContentByLocale("fr_FR");

		Document frenchDocument = SAXReaderUtil.read(frenchContent);

		Element frenchImageElement = (Element)frenchDocument.selectSingleNode(
			imageXPath);

		String frenchImageURL = frenchImageElement.getText();

		Assert.assertTrue(frenchImageURL.indexOf("img_id=" + imageIdFr) != -1);

		Image frenchImage = ImageLocalServiceUtil.getImage(imageIdFr);

		Assert.assertArrayEquals(frenchBinary, frenchImage.getTextObj());

		images.remove(frenchImageKey);

		xml = article.getContent();

		article = JournalTestUtil.updateArticle(
			article, article.getTitle(), xml,
			ServiceContextTestUtil.getServiceContext(_group.getGroupId()),
			images);

		frenchContent = article.getContentByLocale("fr_FR");

		frenchDocument = SAXReaderUtil.read(frenchContent);

		frenchImageElement = (Element)frenchDocument.selectSingleNode(
			imageXPath);

		frenchImageURL = frenchImageElement.getText();

		imageIdFr = JournalArticleImageLocalServiceUtil.getArticleImageId(
			groupId, articleId, 1.1, instanceId, elName, "_fr_FR");

		Assert.assertTrue(frenchImageURL.indexOf("img_id=" + imageIdFr) != -1);

		frenchImage = ImageLocalServiceUtil.getImage(imageIdFr);

		Assert.assertArrayEquals(frenchBinary, frenchImage.getTextObj());
	}

	@Test
	public void testAddArticleWithFolder() throws Exception {
		JournalFolder folder = JournalTestUtil.addFolder(
			_group.getGroupId(), 0, "Test Folder");

		Assert.assertNotNull(
			JournalTestUtil.addArticle(
				_group.getGroupId(), folder.getFolderId(), "Test Article",
				"This is a test article."));
	}

	@Test
	public void testAddArticleWithoutFolder()throws Exception {
		Assert.assertNotNull(
			JournalTestUtil.addArticle(
				_group.getGroupId(), "Test Article",
				"This is a test article."));
	}

	@Test
	public void testAddDDMStructure() throws Exception {
		Assert.assertNotNull(
			DDMStructureTestUtil.addStructure(JournalArticle.class.getName()));
	}

	@Test
	public void testAddDDMStructureWithLocale() throws Exception {
		Assert.assertNotNull(
			DDMStructureTestUtil.addStructure(
				JournalArticle.class.getName(), LocaleUtil.getSiteDefault()));
	}

	@Test
	public void testAddDDMStructureWithNonexistingLocale() throws Exception {
		try {
			CompanyTestUtil.resetCompanyLocales(
				PortalUtil.getDefaultCompanyId(), "en_US");

			DDMStructureTestUtil.addStructure(
				JournalArticle.class.getName(), LocaleUtil.CANADA);

			Assert.fail();
		}
		catch (StructureNameException sne) {
		}
	}

	@Test
	public void testAddDDMStructureWithXSD() throws Exception {
		Assert.assertNotNull(
			DDMStructureTestUtil.addStructure(JournalArticle.class.getName()));
	}

	@Test
	public void testAddDDMStructureWithXSDAndLocale() throws Exception {
		Assert.assertNotNull(
			DDMStructureTestUtil.addStructure(
				JournalArticle.class.getName(), LocaleUtil.getSiteDefault()));
	}

	@Test
	public void testAddDDMTemplateToDDMStructure() throws Exception {
		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			JournalArticle.class.getName());

		Assert.assertNotNull(
			DDMTemplateTestUtil.addTemplate(ddmStructure.getStructureId()));
	}

	@Test
	public void testAddDDMTemplateToDDMStructureWithXSLAndLanguage()
		throws Exception {

		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			JournalArticle.class.getName());

		Assert.assertNotNull(
			DDMTemplateTestUtil.addTemplate(
				ddmStructure.getStructureId(), TemplateConstants.LANG_TYPE_VM,
				JournalTestUtil.getSampleTemplateXSL()));
	}

	@Test
	public void testAddDynamicContent() throws Exception {
		Document document = JournalTestUtil.createDocument(
			"en_US,pt_BR", "en_US");

		Element dynamicElementElement =
			JournalTestUtil.addDynamicElementElement(
				document.getRootElement(), "text", "name");

		JournalTestUtil.addDynamicContentElement(
			dynamicElementElement, "en_US", "Joe Bloggs");

		String xml = document.asXML();

		String content = JournalUtil.transform(
			null, getTokens(), Constants.VIEW, "en_US", xml,
			JournalTestUtil.getSampleTemplateXSL(),
			TemplateConstants.LANG_TYPE_VM);

		Assert.assertEquals("Joe Bloggs", content);
	}

	@Test
	public void testAddDynamicElement() {
		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("root");

		Assert.assertNotNull(
			JournalTestUtil.addDynamicElementElement(
				rootElement, "text", "name"));
	}

	@Test
	public void testAddFolder() throws Exception {
		Assert.assertNotNull(
			JournalTestUtil.addFolder(_group.getGroupId(), 0, "Test Folder"));
	}

	@Test
	public void testCreateDocument() {
		Assert.assertNotNull(JournalTestUtil.createDocument("en_US", "en_US"));
	}

	@Test
	public void testCreateLocalizedContent() {
		Assert.assertNotNull(
			JournalTestUtil.createLocalizedContent(
				"This is localized content.", LocaleUtil.getSiteDefault()));
	}

	@Test
	public void testDeleteDDMStructure() throws Exception {
		String content = DDMStructureTestUtil.getSampleStructuredContent();

		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			JournalArticle.class.getName());

		Assert.assertNotNull(
			JournalTestUtil.addArticleWithXMLContent(
				TestPropsValues.getGroupId(),
				JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				PortalUtil.getClassNameId(DDMStructure.class),
				ddmStructure.getStructureId(), content,
				ddmStructure.getStructureKey(), null,
				LocaleUtil.getSiteDefault()));

		DDMStructureLocalServiceUtil.deleteDDMStructure(ddmStructure);

		try {
			Assert.assertNull(
				JournalArticleLocalServiceUtil.getArticle(
					ddmStructure.getGroupId(), DDMStructure.class.getName(),
					ddmStructure.getStructureId()));
		}
		catch (NoSuchArticleException nsae) {
		}
	}

	@Test
	public void testGetSampleStructuredContent() throws Exception {
		String xml = DDMStructureTestUtil.getSampleStructuredContent(
			"name", "Joe Bloggs");

		String content = JournalUtil.transform(
			null, getTokens(), Constants.VIEW, "en_US", xml,
			JournalTestUtil.getSampleTemplateXSL(),
			TemplateConstants.LANG_TYPE_VM);

		Assert.assertEquals("Joe Bloggs", content);
	}

	@Test
	public void testGetSampleStructureXSD() {
		Assert.assertNotNull(DDMStructureTestUtil.getSampleStructureXSD());
	}

	@Test
	public void testGetSampleTemplateXSL() {
		Assert.assertEquals(
			"$name.getData()", JournalTestUtil.getSampleTemplateXSL());
	}

	@Test
	public void testUpdateArticle() throws Exception {
		JournalArticle article = JournalTestUtil.addArticle(
			_group.getGroupId(), "Test Article", "This is a test article.");

		String localizedContent = JournalTestUtil.createLocalizedContent(
			"This is an updated test article.", LocaleUtil.getSiteDefault());

		Assert.assertNotNull(
			JournalTestUtil.updateArticle(
				article, article.getTitle(), localizedContent));
	}

	protected Map<String, String> getTokens() throws Exception {
		Map<String, String> tokens = JournalUtil.getTokens(
			TestPropsValues.getGroupId(), null, null);

		tokens.put(
			"article_group_id", String.valueOf(TestPropsValues.getGroupId()));
		tokens.put(
			"company_id", String.valueOf(TestPropsValues.getCompanyId()));

		return tokens;
	}

	protected byte[] readBinary(String fileName) throws Exception {
		fileName =
			"portal-impl/test/integration/com/liferay/portlet/journal/" +
				"dependencies/" + fileName;

		File file = new File(fileName);

		byte[] bytes = null;

		RandomAccessFile randomAccessFile = null;

		try {
			randomAccessFile = new RandomAccessFile(file, "r");

			bytes = new byte[(int)randomAccessFile.length()];

			randomAccessFile.readFully(bytes);
		}
		finally {
			if (randomAccessFile != null) {
				randomAccessFile.close();
			}
		}

		return bytes;
	}

	protected String readText(String fileName) throws Exception {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(
			"com/liferay/portlet/journal/dependencies/" + fileName);

		return StringUtil.read(inputStream);
	}

	private Group _group;

}