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

package com.liferay.portal.vulcan.fields;

import java.util.Set;

/**
 * Instances of this interface represent the value of the {@code fields} query
 * param, which can be used to filter the fields returned in a request.
 *
 * @author Alejandro Hernández
 * @review
 */
public interface FieldsQueryParam {

	/**
	 * The list of fields. An empty set means that no fields should be returned.
	 * A {@code null} value means that no {@code fields} query param was sent.
	 *
	 * @review
	 */
	public Set<String> getFieldNames();

}