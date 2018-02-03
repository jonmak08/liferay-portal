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

package com.liferay.journal.model.impl;

<<<<<<< HEAD
import com.liferay.document.library.kernel.model.DLFolderConstants;
=======
>>>>>>> compatible
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
<<<<<<< HEAD
import com.liferay.friendly.url.model.FriendlyURLEntry;
import com.liferay.friendly.url.model.FriendlyURLEntryLocalization;
import com.liferay.friendly.url.service.FriendlyURLEntryLocalServiceUtil;
import com.liferay.journal.constants.JournalConstants;
=======
>>>>>>> compatible
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleResource;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.model.JournalFolderConstants;
<<<<<<< HEAD
=======
import com.liferay.journal.service.JournalArticleImageLocalServiceUtil;
>>>>>>> compatible
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalArticleResourceLocalServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.journal.transformer.JournalTransformerListenerRegistryUtil;
import com.liferay.journal.transformer.LocaleTransformerListener;
import com.liferay.journal.util.impl.JournalUtil;
<<<<<<< HEAD
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
=======
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
>>>>>>> compatible
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.model.Layout;
<<<<<<< HEAD
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.cache.CacheField;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ImageLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
=======
import com.liferay.portal.kernel.model.cache.CacheField;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ImageLocalServiceUtil;
>>>>>>> compatible
import com.liferay.portal.kernel.templateparser.TransformerListener;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
=======
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
<<<<<<< HEAD
import com.liferay.portal.util.PropsValues;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
=======

import java.util.HashMap;
>>>>>>> compatible
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 */
public class JournalArticleImpl extends JournalArticleBaseImpl {

	public static String getContentByLocale(
		Document document, String languageId) {

		return getContentByLocale(document, languageId, null);
	}

	public static String getContentByLocale(
		Document document, String languageId, Map<String, String> tokens) {

		TransformerListener transformerListener =
			JournalTransformerListenerRegistryUtil.getTransformerListener(
				LocaleTransformerListener.class.getName());

		if (transformerListener != null) {
			document = transformerListener.onXml(
				document.clone(), languageId, tokens);
		}

		return document.asXML();
	}

	/**
<<<<<<< HEAD
	 * @deprecated As of 4.0.0, replaced by {@link #getContentByLocale(Document,
=======
	 * @deprecated As of 3.3.0, replaced by {@link #getContentByLocale(Document,
>>>>>>> compatible
	 *             String)}
	 */
	@Deprecated
	public static String getContentByLocale(
		String content, boolean templateDriven, String languageId) {

		try {
			return getContentByLocale(SAXReaderUtil.read(content), languageId);
		}
		catch (DocumentException de) {
			if (_log.isWarnEnabled()) {
				_log.warn(de, de);
			}

			return content;
		}
	}

	@Override
<<<<<<< HEAD
	public Folder addImagesFolder() throws PortalException {
		if (_imagesFolderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return PortletFileRepositoryUtil.getPortletFolder(_imagesFolderId);
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Repository repository = PortletFileRepositoryUtil.addPortletRepository(
			getGroupId(), JournalConstants.SERVICE_NAME, serviceContext);

		Folder folder = PortletFileRepositoryUtil.addPortletFolder(
			getUserId(), repository.getRepositoryId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			String.valueOf(getResourcePrimKey()), serviceContext);

		_imagesFolderId = folder.getFolderId();

		return folder;
	}

	@Override
=======
>>>>>>> compatible
	public String buildTreePath() throws PortalException {
		if (getFolderId() == JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return StringPool.SLASH;
		}

		JournalFolder folder = getFolder();

		return folder.buildTreePath();
	}

	@Override
<<<<<<< HEAD
	public Object clone() {
		JournalArticleImpl journalArticle = (JournalArticleImpl)super.clone();

		journalArticle.setDescriptionMap(getDescriptionMap());
		journalArticle.setTitleMap(getTitleMap());

		return journalArticle;
=======
	public long getArticleImageId(
		String elInstanceId, String elName, String languageId) {

		return JournalArticleImageLocalServiceUtil.getArticleImageId(
			getGroupId(), getArticleId(), getVersion(), elInstanceId, elName,
			languageId);
>>>>>>> compatible
	}

	@Override
	public String getArticleImageURL(ThemeDisplay themeDisplay) {
		if (!isSmallImage()) {
			return null;
		}

		if (Validator.isNotNull(getSmallImageURL())) {
			return getSmallImageURL();
		}

<<<<<<< HEAD
		return StringBundler.concat(
			themeDisplay.getPathImage(), "/journal/article?img_id=",
			String.valueOf(getSmallImageId()), "&t=",
			WebServerServletTokenUtil.getToken(getSmallImageId()));
=======
		return themeDisplay.getPathImage() + "/journal/article?img_id=" +
			getSmallImageId() + "&t=" +
				WebServerServletTokenUtil.getToken(getSmallImageId());
>>>>>>> compatible
	}

	@Override
	public JournalArticleResource getArticleResource() throws PortalException {
		return JournalArticleResourceLocalServiceUtil.getArticleResource(
			getResourcePrimKey());
	}

	@Override
	public String getArticleResourceUuid() throws PortalException {
		JournalArticleResource articleResource = getArticleResource();

		return articleResource.getUuid();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<>();

<<<<<<< HEAD
		availableLanguageIds.addAll(
			JournalArticleLocalServiceUtil.getArticleLocalizationLanguageIds(
				getId()));
=======
		for (String availableLanguageId : super.getAvailableLanguageIds()) {
			availableLanguageIds.add(availableLanguageId);
		}
>>>>>>> compatible

		Document document = getDocument();

		if (document != null) {
			for (String availableLanguageId :
					LocalizationUtil.getAvailableLanguageIds(document)) {

				availableLanguageIds.add(availableLanguageId);
			}
		}

		return availableLanguageIds.toArray(
			new String[availableLanguageIds.size()]);
	}

	@Override
	public String getContentByLocale(String languageId) {
		Map<String, String> tokens = new HashMap<>();

<<<<<<< HEAD
		DDMStructure ddmStructure = getDDMStructure();

		if (ddmStructure != null) {
=======
		try {
			DDMStructure ddmStructure = getDDMStructure();

>>>>>>> compatible
			tokens.put(
				"ddm_structure_id",
				String.valueOf(ddmStructure.getStructureId()));
		}
<<<<<<< HEAD

		return getContentByLocale(getDocument(), languageId, tokens);
	}

	@Override
	public DDMStructure getDDMStructure() {
		DDMStructure ddmStructure = null;

		try {
			ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(
				PortalUtil.getSiteGroupId(getGroupId()),
				ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class),
				getDDMStructureKey(), true);
		}
		catch (PortalException pe) {
			_log.error(
				"Unable to get DDM structure with DDM structure key " +
					getDDMStructureKey(),
				pe);
		}

		return ddmStructure;
	}

	@Override
	public DDMTemplate getDDMTemplate() {
		DDMTemplate ddmTemplate = null;

		try {
			ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(
				PortalUtil.getSiteGroupId(getGroupId()),
				ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class),
				getDDMTemplateKey(), true);
		}
		catch (PortalException pe) {
			_log.error(
				"Unable to get DDM template for DDM structure with" +
					"DDM structure key " + getDDMStructureKey(),
				pe);
		}

		return ddmTemplate;
	}

	@JSON
	@Override
	public String getDescription() {
		String description =
			JournalArticleLocalServiceUtil.getArticleDescription(
				getId(), getDefaultLanguageId());

		if (description == null) {
			return StringPool.BLANK;
		}
		else {
			return description;
		}
	}

	@Override
	public String getDescription(Locale locale) {
		String description =
			JournalArticleLocalServiceUtil.getArticleDescription(
				getId(), locale);

		if (description == null) {
			return getDescription();
		}
		else {
			return description;
		}
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		Locale locale = LocaleUtil.fromLanguageId(languageId);

		return getDescription(locale);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		String description =
			JournalArticleLocalServiceUtil.getArticleDescription(
				getId(), languageId);

		if (description != null) {
			return description;
		}
		else if (useDefault) {
			return getDescription();
		}

		return StringPool.BLANK;
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		if (_descriptionMap != null) {
			return _descriptionMap;
		}

		_descriptionMap =
			JournalArticleLocalServiceUtil.getArticleDescriptionMap(getId());

		return _descriptionMap;
	}

	@Override
	public String getDescriptionMapAsXML() {
		return LocalizationUtil.updateLocalization(
			getDescriptionMap(), StringPool.BLANK, "Description",
			getDefaultLanguageId());
	}

	@Override
	public Date getDisplayDate() {
		if (!PropsValues.SCHEDULER_ENABLED) {
			return null;
		}

		return super.getDisplayDate();
=======
		catch (PortalException pe) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}
		}

		return getContentByLocale(getDocument(), languageId, tokens);
	}

	@Override
	public DDMStructure getDDMStructure() throws PortalException {
		return DDMStructureLocalServiceUtil.fetchStructure(
			PortalUtil.getSiteGroupId(getGroupId()),
			ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class),
			getDDMStructureKey(), true);
	}

	@Override
	public DDMTemplate getDDMTemplate() throws PortalException {
		return DDMTemplateLocalServiceUtil.fetchTemplate(
			PortalUtil.getSiteGroupId(getGroupId()),
			ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class),
			getDDMTemplateKey(), true);
	}

	@Override
	public String getDefaultLanguageId() {
		if (_defaultLanguageId == null) {
			_defaultLanguageId = super.getDefaultLanguageId();

			if (Validator.isNull(_defaultLanguageId)) {
				_defaultLanguageId = LocaleUtil.toLanguageId(
					LocaleUtil.getSiteDefault());
			}
		}

		return _defaultLanguageId;
>>>>>>> compatible
	}

	@Override
	public Document getDocument() {
		if (_document == null) {
			try {
				_document = SAXReaderUtil.read(getContent());
			}
			catch (DocumentException de) {
				if (_log.isWarnEnabled()) {
					_log.warn(de, de);
				}
			}
		}

		return _document;
	}

	@Override
<<<<<<< HEAD
	public Date getExpirationDate() {
		if (!PropsValues.SCHEDULER_ENABLED) {
			return null;
		}

		return super.getExpirationDate();
	}

	@Override
=======
>>>>>>> compatible
	public JournalFolder getFolder() throws PortalException {
		if (getFolderId() <= 0) {
			return new JournalFolderImpl();
		}

		return JournalFolderLocalServiceUtil.getFolder(getFolderId());
	}

	@Override
<<<<<<< HEAD
	public Map<Locale, String> getFriendlyURLMap() throws PortalException {
		Map<Locale, String> friendlyURLMap = new HashMap<>();

		long classNameId = PortalUtil.getClassNameId(JournalArticle.class);

		List<FriendlyURLEntry> friendlyURLEntries =
			FriendlyURLEntryLocalServiceUtil.getFriendlyURLEntries(
				getGroupId(), classNameId, getResourcePrimKey());

		if (friendlyURLEntries.isEmpty()) {
			friendlyURLMap.put(
				LocaleUtil.fromLanguageId(getDefaultLanguageId()),
				getUrlTitle());

			return friendlyURLMap;
		}

		FriendlyURLEntry friendlyURLEntry =
			FriendlyURLEntryLocalServiceUtil.getMainFriendlyURLEntry(
				classNameId, getResourcePrimKey());

		List<FriendlyURLEntryLocalization> friendlyURLEntryLocalizations =
			FriendlyURLEntryLocalServiceUtil.getFriendlyURLEntryLocalizations(
				friendlyURLEntry.getFriendlyURLEntryId());

		for (FriendlyURLEntryLocalization friendlyURLEntryLocalization :
				friendlyURLEntryLocalizations) {

			Locale locale = LocaleUtil.fromLanguageId(
				friendlyURLEntryLocalization.getLanguageId());

			friendlyURLMap.put(
				locale, friendlyURLEntryLocalization.getUrlTitle());
		}

		Locale defaultSiteLocale = LocaleUtil.getSiteDefault();

		if (Validator.isNull(friendlyURLMap.get(defaultSiteLocale))) {
			Locale defaultLocale = LocaleUtil.fromLanguageId(
				getDefaultLanguageId());

			friendlyURLMap.put(
				defaultSiteLocale, friendlyURLMap.get(defaultLocale));
		}

		return friendlyURLMap;
	}

	@Override
	public String getFriendlyURLsXML() throws PortalException {
		Map<Locale, String> friendlyURLMap = getFriendlyURLMap();

		return LocalizationUtil.updateLocalization(
			friendlyURLMap, StringPool.BLANK, "FriendlyURL",
			LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault()));
	}

	@Override
	public List<FileEntry> getImagesFileEntries() throws PortalException {
		return getImagesFileEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	@Override
	public List<FileEntry> getImagesFileEntries(int start, int end)
		throws PortalException {

		return getImagesFileEntries(start, end, null);
	}

	@Override
	public List<FileEntry> getImagesFileEntries(
			int start, int end, OrderByComparator obc)
		throws PortalException {

		long imagesFolderId = getImagesFolderId();

		if (imagesFolderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return new ArrayList<>();
		}

		return PortletFileRepositoryUtil.getPortletFileEntries(
			getGroupId(), imagesFolderId, WorkflowConstants.STATUS_APPROVED,
			start, end, obc);
	}

	@Override
	public int getImagesFileEntriesCount() throws PortalException {
		long imagesFolderId = getImagesFolderId();

		if (imagesFolderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return 0;
		}

		return PortletFileRepositoryUtil.getPortletFileEntriesCount(
			getGroupId(), imagesFolderId, WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	public long getImagesFolderId() {
		if (_imagesFolderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return _imagesFolderId;
		}

		Repository repository =
			PortletFileRepositoryUtil.fetchPortletRepository(
				getGroupId(), JournalConstants.SERVICE_NAME);

		if (repository == null) {
			return DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
		}

		try {
			Folder folder = PortletFileRepositoryUtil.getPortletFolder(
				repository.getRepositoryId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				String.valueOf(getResourcePrimKey()));

			_imagesFolderId = folder.getFolderId();
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to get folder for " + getResourcePrimKey());
			}
		}

		return _imagesFolderId;
	}

	@Override
=======
>>>>>>> compatible
	public Layout getLayout() {
		String layoutUuid = getLayoutUuid();

		if (Validator.isNull(layoutUuid)) {
			return null;
		}

		return JournalUtil.getArticleLayout(layoutUuid, getGroupId());
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	public String getLegacyDescription() {
		return _description;
	}

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	public String getLegacyTitle() {
		return _title;
	}

	@Override
	public Date getReviewDate() {
		if (!PropsValues.SCHEDULER_ENABLED) {
			return null;
		}

		return super.getReviewDate();
	}

=======
>>>>>>> compatible
	@Override
	public String getSmallImageType() throws PortalException {
		if ((_smallImageType == null) && isSmallImage()) {
			Image smallImage = ImageLocalServiceUtil.getImage(
				getSmallImageId());

			_smallImageType = smallImage.getType();
		}

		return _smallImageType;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(JournalArticle.class);
	}

	/**
<<<<<<< HEAD
	 * @deprecated As of 4.0.0, replaced by {@link #getDDMStructureKey()}
=======
	 * @deprecated As of 3.3.0, replaced by {@link #getDDMStructureKey()}
>>>>>>> compatible
	 */
	@Deprecated
	@Override
	public String getStructureId() {
		return getDDMStructureKey();
	}

	/**
<<<<<<< HEAD
	 * @deprecated As of 4.0.0, replaced by {@link #getDDMTemplateKey()}
=======
	 * @deprecated As of 3.3.0, replaced by {@link #getDDMTemplateKey()}
>>>>>>> compatible
	 */
	@Deprecated
	@Override
	public String getTemplateId() {
		return getDDMTemplateKey();
	}

<<<<<<< HEAD
	@JSON
	@Override
	public String getTitle() {
		String title = JournalArticleLocalServiceUtil.getArticleTitle(
			getId(), getDefaultLanguageId());

		if (title == null) {
			return StringPool.BLANK;
		}
		else {
			return title;
		}
	}

	@Override
	public String getTitle(Locale locale) {
		String title = JournalArticleLocalServiceUtil.getArticleTitle(
			getId(), locale);

		if (title == null) {
			return getTitle();
		}
		else {
			return title;
		}
	}

	@Override
	public String getTitle(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId, useDefault);
	}

	@Override
	public String getTitle(String languageId) {
		Locale locale = LocaleUtil.fromLanguageId(languageId);

		return getTitle(locale);
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		String title = JournalArticleLocalServiceUtil.getArticleTitle(
			getId(), languageId);

		if (title != null) {
			return title;
		}
		else if (useDefault) {
			return getTitle();
		}

		return StringPool.BLANK;
	}

	@JSON
	@Override
	public String getTitleCurrentValue() {
		Locale locale = LocaleThreadLocal.getThemeDisplayLocale();

		return getTitle(locale, true);
	}

	@Override
	public Map<Locale, String> getTitleMap() {
		if (_titleMap != null) {
			return _titleMap;
		}

		_titleMap = JournalArticleLocalServiceUtil.getArticleTitleMap(getId());

		return _titleMap;
	}

	@Override
	public String getTitleMapAsXML() {
		return LocalizationUtil.updateLocalization(
			getTitleMap(), StringPool.BLANK, "Title", getDefaultLanguageId());
=======
	@Override
	public Map<Locale, String> getTitleMap() {
		Locale defaultLocale = LocaleThreadLocal.getDefaultLocale();

		try {
			Locale articleDefaultLocale = LocaleUtil.fromLanguageId(
				getDefaultLanguageId());

			LocaleThreadLocal.setDefaultLocale(articleDefaultLocale);

			return super.getTitleMap();
		}
		finally {
			LocaleThreadLocal.setDefaultLocale(defaultLocale);
		}
>>>>>>> compatible
	}

	@Override
	public long getTrashEntryClassPK() {
		return getResourcePrimKey();
	}

<<<<<<< HEAD
	public String getUrlTitle(Locale locale) throws PortalException {
		String urlTitle = getFriendlyURLMap().get(locale);

		if (Validator.isNull(urlTitle)) {
			return getUrlTitle();
		}

		return urlTitle;
	}

=======
>>>>>>> compatible
	@Override
	public boolean hasApprovedVersion() {
		JournalArticle article =
			JournalArticleLocalServiceUtil.fetchLatestArticle(
				getGroupId(), getArticleId(),
				WorkflowConstants.STATUS_APPROVED);

		if (article == null) {
			return false;
		}

		return true;
	}

	/**
<<<<<<< HEAD
	 * @deprecated As of 4.0.0, with no direct replacement
=======
	 * @deprecated As of 3.3.0, with no direct replacement
>>>>>>> compatible
	 */
	@Deprecated
	@Override
	public boolean isTemplateDriven() {
		return true;
	}

<<<<<<< HEAD
=======
	/**
	 * @param defaultImportLocale the default imported locale
	 */
	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		super.prepareLocalizedFieldsForImport(defaultImportLocale);

		String content = StringPool.BLANK;

		try {
			content = JournalUtil.prepareLocalizedContentForImport(
				getContent(), defaultImportLocale);
		}
		catch (Exception e) {
			throw new LocaleException(LocaleException.TYPE_DEFAULT, e);
		}

		setContent(content);
	}

>>>>>>> compatible
	@Override
	public void setContent(String content) {
		super.setContent(content);

		_document = null;
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	public void setDescription(String description) {
		_description = description;
	}

	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		_descriptionMap = descriptionMap;
=======
	@Override
	public void setDefaultLanguageId(String defaultLanguageId) {
		_defaultLanguageId = defaultLanguageId;
>>>>>>> compatible
	}

	@Override
	public void setDocument(Document document) {
		_document = document;
	}

	@Override
<<<<<<< HEAD
	public void setImagesFolderId(long imagesFolderId) {
		_imagesFolderId = imagesFolderId;
	}

	@Override
=======
>>>>>>> compatible
	public void setSmallImageType(String smallImageType) {
		_smallImageType = smallImageType;
	}

	/**
<<<<<<< HEAD
	 * @deprecated As of 4.0.0, replaced by {@link #setDDMStructureKey(String)}
=======
	 * @deprecated As of 3.3.0, replaced by {@link #setDDMStructureKey(String)}
>>>>>>> compatible
	 */
	@Deprecated
	@Override
	public void setStructureId(String ddmStructureKey) {
		setDDMStructureKey(ddmStructureKey);
	}

	/**
<<<<<<< HEAD
	 * @deprecated As of 4.0.0, replaced by {@link #setDDMTemplateKey(String)}
=======
	 * @deprecated As of 3.3.0, replaced by {@link #setDDMTemplateKey(String)}
>>>>>>> compatible
	 */
	@Deprecated
	@Override
	public void setTemplateId(String ddmTemplateKey) {
		setDDMTemplateKey(ddmTemplateKey);
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	public void setTitle(String title) {
		_title = title;
	}

	public void setTitleMap(Map<Locale, String> titleMap) {
		_titleMap = titleMap;
=======
	@Override
	public void setTitle(String title) {
		super.setTitle(title);

		_defaultLanguageId = null;
>>>>>>> compatible
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JournalArticleImpl.class);

<<<<<<< HEAD
	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	private String _description;

	private Map<Locale, String> _descriptionMap;
=======
	@CacheField(propagateToInterface = true)
	private String _defaultLanguageId;
>>>>>>> compatible

	@CacheField(propagateToInterface = true)
	private Document _document;

<<<<<<< HEAD
	private long _imagesFolderId;
	private String _smallImageType;

	/**
	 * @deprecated As of 4.0.0
	 */
	@Deprecated
	private String _title;

	private Map<Locale, String> _titleMap;

=======
	private String _smallImageType;

>>>>>>> compatible
}