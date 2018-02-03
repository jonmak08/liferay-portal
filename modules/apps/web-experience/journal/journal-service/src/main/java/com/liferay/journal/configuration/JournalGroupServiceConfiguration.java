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

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;

/**
 * @author Juergen Kappler
 */
@ExtendedObjectClassDefinition(
	category = "web-experience",
	scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
	id = "com.liferay.journal.configuration.JournalGroupServiceConfiguration",
	localization = "content/Language",
	name = "journal-group-service-configuration-name"
)
public interface JournalGroupServiceConfiguration {

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_added_body.tmpl}",
<<<<<<< HEAD
		name = "email-article-added-body", required = false
	)
	public LocalizedValuesMap emailArticleAddedBody();

	@Meta.AD(
		deflt = "true", name = "email-article-added-enabled", required = false
	)
=======
		required = false
	)
	public LocalizedValuesMap emailArticleAddedBody();

	@Meta.AD(deflt = "true", required = false)
>>>>>>> compatible
	public boolean emailArticleAddedEnabled();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_added_subject.tmpl}",
<<<<<<< HEAD
		name = "email-article-added-subject", required = false
=======
		required = false
>>>>>>> compatible
	)
	public LocalizedValuesMap emailArticleAddedSubject();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_approval_denied_body.tmpl}",
<<<<<<< HEAD
		name = "email-article-approval-denied-body", required = false
	)
	public LocalizedValuesMap emailArticleApprovalDeniedBody();

	@Meta.AD(
		deflt = "false", name = "email-article-approval-denied-enabled",
		required = false
	)
=======
		required = false
	)
	public LocalizedValuesMap emailArticleApprovalDeniedBody();

	@Meta.AD(deflt = "false", required = false)
>>>>>>> compatible
	public boolean emailArticleApprovalDeniedEnabled();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_approval_denied_subject.tmpl}",
<<<<<<< HEAD
		name = "email-article-approval-denied-subject", required = false
=======
		required = false
>>>>>>> compatible
	)
	public LocalizedValuesMap emailArticleApprovalDeniedSubject();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_approval_granted_body.tmpl}",
<<<<<<< HEAD
		name = "email-article-approval-granted-body", required = false
	)
	public LocalizedValuesMap emailArticleApprovalGrantedBody();

	@Meta.AD(
		deflt = "false", name = "email-article-approval-granted-enabled",
		required = false
	)
=======
		required = false
	)
	public LocalizedValuesMap emailArticleApprovalGrantedBody();

	@Meta.AD(deflt = "false", required = false)
>>>>>>> compatible
	public boolean emailArticleApprovalGrantedEnabled();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_approval_granted_subject.tmpl}",
<<<<<<< HEAD
		name = "email-article-approval-granted-subject", required = false
=======
		required = false
>>>>>>> compatible
	)
	public LocalizedValuesMap emailArticleApprovalGrantedSubject();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_approval_requested_body.tmpl}",
<<<<<<< HEAD
		name = "email-article-approval-requested-body", required = false
	)
	public LocalizedValuesMap emailArticleApprovalRequestedBody();

	@Meta.AD(
		deflt = "false", name = "email-article-approval-requested-enabled",
		required = false
	)
=======
		required = false
	)
	public LocalizedValuesMap emailArticleApprovalRequestedBody();

	@Meta.AD(deflt = "false", required = false)
>>>>>>> compatible
	public boolean emailArticleApprovalRequestedEnabled();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_approval_requested_subject.tmpl}",
<<<<<<< HEAD
		name = "email-article-approval-requested-subject", required = false
=======
		required = false
>>>>>>> compatible
	)
	public LocalizedValuesMap emailArticleApprovalRequestedSubject();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_moved_from_folder_body.tmpl}",
<<<<<<< HEAD
		name = "email-article-moved-from-folder-body", required = false
	)
	public LocalizedValuesMap emailArticleMovedFromFolderBody();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_moved_from_trash_body.tmpl}",
		name = "email-article-moved-from-trash-body", required = false
	)
	public LocalizedValuesMap emailArticleMovedFromTrashBody();

	@Meta.AD(
		deflt = "true", name = "email-article-moved-from-folder-enabled",
		required = false
	)
=======
		required = false
	)
	public LocalizedValuesMap emailArticleMovedFromFolderBody();

	@Meta.AD(deflt = "true", required = false)
>>>>>>> compatible
	public boolean emailArticleMovedFromFolderEnabled();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_moved_from_folder_subject.tmpl}",
<<<<<<< HEAD
		name = "email-article-moved-from-folder-subject", required = false
=======
		required = false
>>>>>>> compatible
	)
	public LocalizedValuesMap emailArticleMovedFromFolderSubject();

	@Meta.AD(
<<<<<<< HEAD
		deflt = "${resource:com/liferay/journal/dependencies/email_article_moved_from_trash_subject.tmpl}",
		name = "email-article-moved-from-trash-subject", required = false
	)
	public LocalizedValuesMap emailArticleMovedFromTrashSubject();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_moved_to_folder_body.tmpl}",
		name = "email-article-moved-to-folder-body", required = false
	)
	public LocalizedValuesMap emailArticleMovedToFolderBody();

	@Meta.AD(
		deflt = "true", name = "email-article-moved-to-folder-enabled",
		required = false
	)
=======
		deflt = "${resource:com/liferay/journal/dependencies/email_article_moved_to_folder_body.tmpl}",
		required = false
	)
	public LocalizedValuesMap emailArticleMovedToFolderBody();

	@Meta.AD(deflt = "true", required = false)
>>>>>>> compatible
	public boolean emailArticleMovedToFolderEnabled();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_moved_to_folder_subject.tmpl}",
<<<<<<< HEAD
		name = "email-article-moved-to-folder-subject", required = false
	)
	public LocalizedValuesMap emailArticleMovedToFolderSubject();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_moved_to_trash_body.tmpl}",
		name = "email-article-moved-to-trash-body", required = false
	)
	public LocalizedValuesMap emailArticleMovedToTrashBody();

	@Meta.AD(
		deflt = "true", name = "email-article-moved-to-trash-enabled",
		required = false
	)
	public boolean emailArticleMovedToTrashEnabled();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_moved_to_trash_subject.tmpl}",
		name = "email-article-moved-to-trash-subject", required = false
	)
	public LocalizedValuesMap emailArticleMovedToTrashSubject();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_review_body.tmpl}",
		name = "email-article-review-body", required = false
	)
	public LocalizedValuesMap emailArticleReviewBody();

	@Meta.AD(
		deflt = "true", name = "email-article-review-enabled", required = false
	)
=======
		required = false
	)
	public LocalizedValuesMap emailArticleMovedToFolderSubject();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_review_body.tmpl}",
		required = false
	)
	public LocalizedValuesMap emailArticleReviewBody();

	@Meta.AD(deflt = "true", required = false)
>>>>>>> compatible
	public boolean emailArticleReviewEnabled();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_review_subject.tmpl}",
<<<<<<< HEAD
		name = "email-article-review-subject", required = false
=======
		required = false
>>>>>>> compatible
	)
	public LocalizedValuesMap emailArticleReviewSubject();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_updated_body.tmpl}",
<<<<<<< HEAD
		name = "email-article-updated-body", required = false
	)
	public LocalizedValuesMap emailArticleUpdatedBody();

	@Meta.AD(
		deflt = "true", name = "email-article-updated-enabled", required = false
	)
=======
		required = false
	)
	public LocalizedValuesMap emailArticleUpdatedBody();

	@Meta.AD(deflt = "true", required = false)
>>>>>>> compatible
	public boolean emailArticleUpdatedEnabled();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/email_article_updated_subject.tmpl}",
<<<<<<< HEAD
		name = "email-article-updated-subject", required = false
=======
		required = false
>>>>>>> compatible
	)
	public LocalizedValuesMap emailArticleUpdatedSubject();

	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/admin.email.from.address}",
<<<<<<< HEAD
		name = "email-from-address", required = false
=======
		required = false
>>>>>>> compatible
	)
	public String emailFromAddress();

	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/admin.email.from.name}",
<<<<<<< HEAD
		name = "email-from-name", required = false
=======
		required = false
>>>>>>> compatible
	)
	public String emailFromName();

}