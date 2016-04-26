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

package com.liferay.portlet.journalcontent.util;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.index.IndexEncoder;
import com.liferay.portal.kernel.cache.index.PortalCacheIndexer;
import com.liferay.portal.kernel.lar.ExportImportThreadLocal;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.journal.model.JournalArticleDisplay;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.permission.JournalArticlePermission;

import java.io.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Michael Young
 */
@DoPrivileged
public class JournalContentImpl implements JournalContent {

	@Override
	public void clearCache() {
		if (ExportImportThreadLocal.isImportInProcess()) {
			return;
		}

		_portalCache.removeAll();
	}

	@Override
	public void clearCache(
		long groupId, String articleId, String ddmTemplateKey) {

		_portalCacheIndexer.removeKeys(
			JournalContentKeyIndexEncoder.encode(
				groupId, articleId, ddmTemplateKey));
	}

	@Override
	public String getContent(
		long groupId, String articleId, String viewMode, String languageId,
		String xmlRequest) {

		return getContent(
			groupId, articleId, null, viewMode, languageId, null, xmlRequest);
	}

	@Override
	public String getContent(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, String xmlRequest) {

		return getContent(
			groupId, articleId, ddmTemplateKey, viewMode, languageId, null,
			xmlRequest);
	}

	@Override
	public String getContent(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, ThemeDisplay themeDisplay) {

		return getContent(
			groupId, articleId, ddmTemplateKey, viewMode, languageId,
			themeDisplay, null);
	}

	@Override
	public String getContent(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, ThemeDisplay themeDisplay, String xmlRequest) {

		JournalArticleDisplay articleDisplay = getDisplay(
			groupId, articleId, ddmTemplateKey, viewMode, languageId,
			themeDisplay, 1, xmlRequest);

		if (articleDisplay != null) {
			return articleDisplay.getContent();
		}
		else {
			return null;
		}
	}

	@Override
	public String getContent(
		long groupId, String articleId, String viewMode, String languageId,
		ThemeDisplay themeDisplay) {

		return getContent(
			groupId, articleId, null, viewMode, languageId, themeDisplay);
	}

	@Override
	public JournalArticleDisplay getDisplay(
		long groupId, String articleId, double version, String ddmTemplateKey,
		String viewMode, String languageId, ThemeDisplay themeDisplay, int page,
		String xmlRequest) {

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		articleId = StringUtil.toUpperCase(GetterUtil.getString(articleId));
		ddmTemplateKey = StringUtil.toUpperCase(
			GetterUtil.getString(ddmTemplateKey));

		long layoutSetId = 0;
		boolean secure = false;

		if (themeDisplay != null) {
			try {
				if (!JournalArticlePermission.contains(
						themeDisplay.getPermissionChecker(), groupId, articleId,
						ActionKeys.VIEW)) {

					return null;
				}

				Layout layout = themeDisplay.getLayout();

				LayoutSet layoutSet = layout.getLayoutSet();

				layoutSetId = layoutSet.getLayoutSetId();
			}
			catch (Exception e) {
			}

			secure = themeDisplay.isSecure();
		}

		JournalContentKey journalContentKey = new JournalContentKey(
			groupId, articleId, version, ddmTemplateKey, layoutSetId, viewMode,
			languageId, page, secure);

		JournalArticleDisplay articleDisplay = _portalCache.get(
			journalContentKey);

		boolean lifecycleRender = isLifecycleRender(themeDisplay, xmlRequest);

		if ((articleDisplay == null) || !lifecycleRender) {
			articleDisplay = getArticleDisplay(
				groupId, articleId, ddmTemplateKey, viewMode, languageId, page,
				xmlRequest, themeDisplay);

			if ((articleDisplay != null) && articleDisplay.isCacheable() &&
				lifecycleRender) {

				_portalCache.put(journalContentKey, articleDisplay);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"getDisplay for {" + groupId + ", " + articleId + ", " +
					ddmTemplateKey + ", " + viewMode + ", " + languageId +
						", " + page + "} takes " + stopWatch.getTime() + " ms");
		}

		return articleDisplay;
	}

	@Override
	public JournalArticleDisplay getDisplay(
		long groupId, String articleId, String viewMode, String languageId,
		String xmlRequest) {

		return getDisplay(
			groupId, articleId, null, viewMode, languageId, null, 1,
			xmlRequest);
	}

	@Override
	public JournalArticleDisplay getDisplay(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, String xmlRequest) {

		return getDisplay(
			groupId, articleId, ddmTemplateKey, viewMode, languageId, null, 1,
			xmlRequest);
	}

	@Override
	public JournalArticleDisplay getDisplay(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, ThemeDisplay themeDisplay) {

		return getDisplay(
			groupId, articleId, ddmTemplateKey, viewMode, languageId,
			themeDisplay, 1, null);
	}

	@Override
	public JournalArticleDisplay getDisplay(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, ThemeDisplay themeDisplay, int page,
		String xmlRequest) {

		return getDisplay(
			groupId, articleId, 0, ddmTemplateKey, viewMode, languageId,
			themeDisplay, 1, xmlRequest);
	}

	@Override
	public JournalArticleDisplay getDisplay(
		long groupId, String articleId, String viewMode, String languageId,
		ThemeDisplay themeDisplay) {

		return getDisplay(
			groupId, articleId, viewMode, languageId, themeDisplay, 1);
	}

	@Override
	public JournalArticleDisplay getDisplay(
		long groupId, String articleId, String viewMode, String languageId,
		ThemeDisplay themeDisplay, int page) {

		return getDisplay(
			groupId, articleId, null, viewMode, languageId, themeDisplay, page,
			null);
	}

	protected JournalArticleDisplay getArticleDisplay(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, int page, String xmlRequest,
		ThemeDisplay themeDisplay) {

		try {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Get article display {" + groupId + ", " + articleId +
						", " + ddmTemplateKey + "}");
			}

			return JournalArticleLocalServiceUtil.getArticleDisplay(
				groupId, articleId, ddmTemplateKey, viewMode, languageId, page,
				xmlRequest, themeDisplay);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get display for " + groupId + " " +
						articleId + " " + languageId);
			}

			return null;
		}
	}

	protected boolean isLifecycleRender(
		ThemeDisplay themeDisplay, String xmlRequest) {

		if (themeDisplay != null) {
			return themeDisplay.isLifecycleRender();
		}
		else if (Validator.isNotNull(xmlRequest)) {
			Matcher matcher = lifecycleRenderPhasePattern.matcher(xmlRequest);

			return matcher.find();
		}
		else {
			return false;
		}
	}

	protected static final String CACHE_NAME = JournalContent.class.getName();

	protected static Pattern lifecycleRenderPhasePattern = Pattern.compile(
		"<lifecycle>\\s*RENDER_PHASE\\s*</lifecycle>");

	private static final IndexEncoder<String, JournalContentKey> _indexEncoder =
		new JournalContentKeyIndexEncoder();
	private static final PortalCache<JournalContentKey, JournalArticleDisplay>
		_portalCache = MultiVMPoolUtil.getCache(CACHE_NAME);
	private static final PortalCacheIndexer
		<String, JournalContentKey, JournalArticleDisplay> _portalCacheIndexer =
			new PortalCacheIndexer
				<String, JournalContentKey, JournalArticleDisplay> (
					_indexEncoder, _portalCache);

	private static Log _log = LogFactoryUtil.getLog(JournalContentImpl.class);

	private static class JournalContentKey implements Serializable {

		@Override
		public boolean equals(Object obj) {
			JournalContentKey journalContentKey = (JournalContentKey)obj;

			if ((journalContentKey._groupId == _groupId) &&
				Validator.equals(journalContentKey._articleId, _articleId) &&
				(journalContentKey._version == _version) &&
				Validator.equals(
					journalContentKey._ddmTemplateKey, _ddmTemplateKey) &&
				(journalContentKey._layoutSetId == _layoutSetId) &&
				Validator.equals(journalContentKey._viewMode, _viewMode) &&
				Validator.equals(journalContentKey._languageId, _languageId) &&
				(journalContentKey._page == _page) &&
				(journalContentKey._secure == _secure)) {

				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			int hashCode = HashUtil.hash(0, _groupId);

			hashCode = HashUtil.hash(hashCode, _articleId);
			hashCode = HashUtil.hash(hashCode, _version);
			hashCode = HashUtil.hash(hashCode, _ddmTemplateKey);
			hashCode = HashUtil.hash(hashCode, _layoutSetId);
			hashCode = HashUtil.hash(hashCode, _viewMode);
			hashCode = HashUtil.hash(hashCode, _languageId);
			hashCode = HashUtil.hash(hashCode, _page);

			return HashUtil.hash(hashCode, _secure);
		}

		private JournalContentKey(
			long groupId, String articleId, double version,
			String ddmTemplateKey, long layoutSetId, String viewMode,
			String languageId, int page, boolean secure) {

			_groupId = groupId;
			_articleId = articleId;
			_version = version;
			_ddmTemplateKey = ddmTemplateKey;
			_layoutSetId = layoutSetId;
			_viewMode = viewMode;
			_languageId = languageId;
			_page = page;
			_secure = secure;
		}

		private static final long serialVersionUID = 1L;

		private final String _articleId;
		private final String _ddmTemplateKey;
		private final long _groupId;
		private final String _languageId;
		private final long _layoutSetId;
		private final int _page;
		private final boolean _secure;
		private final double _version;
		private final String _viewMode;

	}

	private static class JournalContentKeyIndexEncoder
		implements IndexEncoder<String, JournalContentKey> {

		public static String encode(
			long groupId, String articleId, String ddmTemplateKey) {

			StringBundler sb = new StringBundler(5);

			sb.append(groupId);
			sb.append(StringPool.UNDERLINE);
			sb.append(articleId);
			sb.append(StringPool.UNDERLINE);
			sb.append(ddmTemplateKey);

			return sb.toString();
		}

		@Override
		public String encode(JournalContentKey key) {
			return encode(key._groupId, key._articleId, key._ddmTemplateKey);
		}

	}

}