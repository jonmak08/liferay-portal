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

package com.liferay.item.selector.criteria.upload.criterion;

import com.liferay.item.selector.BaseItemSelectorCriterion;
<<<<<<< HEAD
import com.liferay.portal.kernel.upload.UploadServletRequestConfigurationHelperUtil;
=======
import com.liferay.portal.util.PropsValues;
>>>>>>> compatible

/**
 * @author Ambrín Chaudhary
 */
public class UploadItemSelectorCriterion extends BaseItemSelectorCriterion {

	public UploadItemSelectorCriterion() {
	}

	/**
<<<<<<< HEAD
	 * @deprecated As of 2.1.0, replaced by {@link
=======
	 * @deprecated As of 2.0.0, replaced by {@link
>>>>>>> compatible
	 *             #UploadItemSelectorCriterion(String, String, String)}
	 */
	@Deprecated
	public UploadItemSelectorCriterion(String url, String repositoryName) {
		this(
			null, url, repositoryName,
<<<<<<< HEAD
			UploadServletRequestConfigurationHelperUtil.getMaxSize());
	}

	/**
	 * @deprecated As of 2.1.0, replaced by {@link
=======
			PropsValues.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE);
	}

	/**
	 * @deprecated As of 2.0.0, replaced by {@link
>>>>>>> compatible
	 *             #UploadItemSelectorCriterion(String, String, String, long)}
	 */
	@Deprecated
	public UploadItemSelectorCriterion(
		String url, String repositoryName, long maxFileSize) {

		this(null, url, repositoryName, maxFileSize);
	}

<<<<<<< HEAD
	/**
	 * @deprecated As of 2.1.0, replaced by {@link
	 *             #UploadItemSelectorCriterion(String, String, String, long,
	 *             String[])}
	 */
	@Deprecated
	public UploadItemSelectorCriterion(
		String url, String repositoryName, long maxFileSize,
		String[] extensions) {

		this(null, url, repositoryName, maxFileSize, extensions);
	}

=======
>>>>>>> compatible
	public UploadItemSelectorCriterion(
		String portletId, String url, String repositoryName) {

		this(
			portletId, url, repositoryName,
<<<<<<< HEAD
			UploadServletRequestConfigurationHelperUtil.getMaxSize());
=======
			PropsValues.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE);
>>>>>>> compatible
	}

	public UploadItemSelectorCriterion(
		String portletId, String url, String repositoryName, long maxFileSize) {

		_portletId = portletId;
		_url = url;
		_repositoryName = repositoryName;
		_maxFileSize = maxFileSize;
	}

<<<<<<< HEAD
	public UploadItemSelectorCriterion(
		String portletId, String url, String repositoryName, long maxFileSize,
		String[] extensions) {

		_portletId = portletId;
		_url = url;
		_repositoryName = repositoryName;
		_maxFileSize = maxFileSize;
		_extensions = extensions;
	}

	public String[] getExtensions() {
		return _extensions;
	}

=======
>>>>>>> compatible
	public long getMaxFileSize() {
		return _maxFileSize;
	}

	public String getPortletId() {
		return _portletId;
	}

	public String getRepositoryName() {
		return _repositoryName;
	}

	public String getURL() {
		return _url;
	}

<<<<<<< HEAD
	public void setExtensions(String[] extensions) {
		_extensions = extensions;
	}

=======
>>>>>>> compatible
	public void setMaxFileSize(long maxFileSize) {
		_maxFileSize = maxFileSize;
	}

	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	public void setRepositoryName(String repositoryName) {
		_repositoryName = repositoryName;
	}

	public void setURL(String url) {
		_url = url;
	}

<<<<<<< HEAD
	private String[] _extensions;
=======
>>>>>>> compatible
	private long _maxFileSize;
	private String _portletId;
	private String _repositoryName;
	private String _url;

}