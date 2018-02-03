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

package com.liferay.portal.comment.display.context;

import com.liferay.portal.comment.display.context.util.DiscussionRequestHelper;
import com.liferay.portal.comment.display.context.util.DiscussionTaglibHelper;
import com.liferay.portal.kernel.comment.Discussion;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.comment.DiscussionComment;
>>>>>>> compatible
import com.liferay.portal.kernel.comment.DiscussionPermission;
import com.liferay.portal.kernel.comment.display.context.CommentSectionDisplayContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;

/**
 * @author Adolfo Pérez
 */
public class DefaultCommentSectionDisplayContext
	extends BaseCommentDisplayContext implements CommentSectionDisplayContext {

	public DefaultCommentSectionDisplayContext(
		DiscussionRequestHelper discussionRequestHelper,
		DiscussionTaglibHelper discussionTaglibHelper,
		DiscussionPermission discussionPermission, Discussion discussion) {

		_discussionRequestHelper = discussionRequestHelper;
		_discussionTaglibHelper = discussionTaglibHelper;
		_discussionPermission = discussionPermission;
<<<<<<< HEAD
		_discussion = discussion;
=======

		if (discussion == null) {
			_rootDiscussionComment = null;
		}
		else {
			_rootDiscussionComment = discussion.getRootDiscussionComment();
		}
>>>>>>> compatible
	}

	@Override
	public boolean isControlsVisible() throws PortalException {
		if ((_discussionPermission == null) ||
			_discussionTaglibHelper.isHideControls()) {

			return false;
		}

		return _discussionPermission.hasAddPermission(
			_discussionRequestHelper.getCompanyId(),
			_discussionRequestHelper.getScopeGroupId(),
			_discussionTaglibHelper.getClassName(),
			_discussionTaglibHelper.getClassPK());
	}

	@Override
	public boolean isDiscussionVisible() throws PortalException {
<<<<<<< HEAD
		if (_discussion == null) {
			return false;
		}

		if ((_discussion.getDiscussionCommentsCount() > 0) ||
=======
		if (_rootDiscussionComment == null) {
			return false;
		}

		if ((_rootDiscussionComment.getThreadCommentsCount() > 0) ||
>>>>>>> compatible
			hasViewPermission()) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isMessageThreadVisible() {
<<<<<<< HEAD
		if ((_discussion != null) &&
			(_discussion.getDiscussionCommentsCount() > 0)) {
=======
		if ((_rootDiscussionComment != null) &&
			(_rootDiscussionComment.getThreadCommentsCount() > 0)) {
>>>>>>> compatible

			return true;
		}

		return false;
	}

	@Override
	protected ThemeDisplay getThemeDisplay() {
		return _discussionRequestHelper.getThemeDisplay();
	}

	protected boolean hasViewPermission() throws PortalException {
		return _discussionPermission.hasViewPermission(
			_discussionRequestHelper.getCompanyId(),
			_discussionRequestHelper.getScopeGroupId(),
			_discussionTaglibHelper.getClassName(),
			_discussionTaglibHelper.getClassPK());
	}

<<<<<<< HEAD
	private final Discussion _discussion;
	private final DiscussionPermission _discussionPermission;
	private final DiscussionRequestHelper _discussionRequestHelper;
	private final DiscussionTaglibHelper _discussionTaglibHelper;
=======
	private final DiscussionPermission _discussionPermission;
	private final DiscussionRequestHelper _discussionRequestHelper;
	private final DiscussionTaglibHelper _discussionTaglibHelper;
	private final DiscussionComment _rootDiscussionComment;
>>>>>>> compatible

}