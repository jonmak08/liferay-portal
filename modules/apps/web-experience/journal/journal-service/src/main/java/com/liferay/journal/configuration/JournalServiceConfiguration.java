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

package com.liferay.journal.configuration;

import aQute.bnd.annotation.ProviderType;
import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Pavel Savinov
 */
@ExtendedObjectClassDefinition(
	category = "web-experience",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.journal.configuration.JournalServiceConfiguration",
	localization = "content/Language",
	name = "journal-service-configuration-name"
)
@ProviderType
public interface JournalServiceConfiguration {

<<<<<<< HEAD
	@Meta.AD(deflt = "true", name = "add-default-structures", required = false)
=======
	@Meta.AD(deflt = "true", required = false)
>>>>>>> compatible
	public boolean addDefaultStructures();

	@Meta.AD(
		deflt = "&|\\'|@|\\\\|]|}|:|=|>|/|<|[|{|%|+|#|`|?|\\\"|;|*|~",
		description = "specifcy-characters-that-are-not-allowed-in-journal-folder-names",
<<<<<<< HEAD
		name = "characters-blacklist", required = false
=======
		required = false
>>>>>>> compatible
	)
	public String[] charactersblacklist();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/error.ftl}",
<<<<<<< HEAD
		name = "error-template-ftl", required = false
=======
		required = false
>>>>>>> compatible
	)
	public String errorTemplateFTL();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/error.vm}",
<<<<<<< HEAD
		name = "error-template-vm", required = false
=======
		required = false
>>>>>>> compatible
	)
	public String errorTemplateVM();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/error.xsl}",
<<<<<<< HEAD
		name = "error-template-xsl", required = false
	)
	public String errorTemplateXSL();

	@Meta.AD(deflt = "15", name = "check-interval", required = false)
	public int checkInterval();

	@Meta.AD(
		deflt = "", description = "journal-article-custom-token-names",
		name = "custom-token-names", required = false
=======
		required = false
	)
	public String errorTemplateXSL();

	@Meta.AD(deflt = "86400000", required = false)
	public long checkInterval();

	@Meta.AD(
		deflt = "", description = "journal-article-custom-token-names",
		required = false
>>>>>>> compatible
	)
	public String[] customTokenNames();

	@Meta.AD(
		deflt = "", description = "journal-article-custom-token-values",
<<<<<<< HEAD
		name = "custom-token-values", required = false
=======
		required = false
>>>>>>> compatible
	)
	public String[] customTokenValues();

	@Meta.AD(
		deflt = "true", description = "journal-article-comments",
<<<<<<< HEAD
		name = "article-comments-enabled", required = false
=======
		required = false
>>>>>>> compatible
	)
	public boolean articleCommentsEnabled();

	@Meta.AD(
		deflt = "true",
		description = "journal-article-database-search-content-keywords",
<<<<<<< HEAD
		name = "database-content-keyword-search-enabled", required = false
=======
		required = false
>>>>>>> compatible
	)
	public boolean databaseContentKeywordSearchEnabled();

	@Meta.AD(
		deflt = "true", description = "journal-article-expire-all-versions",
<<<<<<< HEAD
		name = "expire-all-article-versions-enabled", required = false
	)
	public boolean expireAllArticleVersionsEnabled();

	/**
	 * @deprecated As of 4.0.0, with no direct replacement
	 */
	@Deprecated
	@Meta.AD(
		deflt = "true", description = "journal-article-view-permission-check",
		name = "article-view-permissions-check-enabled", required = false
=======
		required = false
	)
	public boolean expireAllArticleVersionsEnabled();

	@Meta.AD(
		deflt = "false", description = "journal-article-view-permission-check",
		required = false
>>>>>>> compatible
	)
	public boolean articleViewPermissionsCheckEnabled();

	@Meta.AD(
		deflt = "true", description = "journal-article-index-all-versions",
<<<<<<< HEAD
		name = "index-all-article-versions-enabled", required = false
=======
		required = false
>>>>>>> compatible
	)
	public boolean indexAllArticleVersionsEnabled();

	@Meta.AD(
		deflt = "true", description = "journal-folder-icon-check-count",
<<<<<<< HEAD
		name = "folder-icon-check-count-enabled", required = false
=======
		required = false
>>>>>>> compatible
	)
	public boolean folderIconCheckCountEnabled();

	@Meta.AD(
<<<<<<< HEAD
		deflt = "true", description = "publish-to-live-by-default-help",
		name = "publish-to-live-by-default", required = false
=======
		deflt = "true", description = "publish-to-live-by-default",
		required = false
>>>>>>> compatible
	)
	public boolean publishToLiveByDefaultEnabled();

	@Meta.AD(
		deflt = "true", description = "publish-version-history-by-default",
<<<<<<< HEAD
		name = "version-history-by-default-enabled", required = false
=======
		required = false
>>>>>>> compatible
	)
	public boolean versionHistoryByDefaultEnabled();

	@Meta.AD(
<<<<<<< HEAD
		deflt = "false", description = "sync-content-search-on-startup-help",
		name = "sync-content-search-on-startup", required = false
=======
		deflt = "false", description = "sync-content-search-on-startup",
		required = false
>>>>>>> compatible
	)
	public boolean syncContentSearchOnStartup();

	@Meta.AD(
		deflt = "@page_break@",
<<<<<<< HEAD
		description = "journal-article-token-page-break",
		name = "journal-article-page-break-token", required = false
=======
		description = "journal-article-token-page-break", required = false
>>>>>>> compatible
	)
	public String journalArticlePageBreakToken();

	@Meta.AD(
<<<<<<< HEAD
		deflt = "json", description = "journal-article-storage-type-help",
		name = "journal-article-storage-type", required = false
=======
		deflt = "json", description = "journal-article-storage-type",
		required = false
>>>>>>> compatible
	)
	public String journalArticleStorageType();

}