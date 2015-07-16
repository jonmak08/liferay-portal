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

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.util.Locale;

/**
 * @author Alexander Chow
 */
public interface DDMIndexer {

	public static final String DDM_FIELD_NAMESPACE = "ddm";

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #DDM_FIELD_PREFIX_SAFE}
	 */
	@Deprecated
	public static final String DDM_FIELD_PREFIX =
		DDMIndexer.DDM_FIELD_NAMESPACE + DDMIndexer.DDM_FIELD_SEPARATOR;

	public static final String DDM_FIELD_PREFIX_SAFE =
		DDMIndexer.DDM_FIELD_NAMESPACE + DDMIndexer.DDM_FIELD_SEPARATOR_SAFE;

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #DDM_FIELD_SEPARATOR_SAFE}
	 */
	@Deprecated
	public static final String DDM_FIELD_SEPARATOR = StringPool.FORWARD_SLASH;

	public static final String DDM_FIELD_SEPARATOR_SAFE =
		StringPool.DOUBLE_UNDERLINE;

	public void addAttributes(
		Document document, DDMStructure ddmStructure, Fields fields);

	public String encodeName(long ddmStructureId, String fieldName);

	public String encodeName(
		long ddmStructureId, String fieldName, Locale locale);

	public String extractIndexableAttributes(
		DDMStructure ddmStructure, Fields fields, Locale locale);

}