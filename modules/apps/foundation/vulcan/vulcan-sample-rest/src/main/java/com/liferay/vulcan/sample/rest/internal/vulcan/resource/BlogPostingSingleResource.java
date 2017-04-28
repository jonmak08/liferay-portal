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

package com.liferay.vulcan.sample.rest.internal.vulcan.resource;

import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.vulcan.resource.SingleResource;

/**
 * @author Alejandro Hernández
 * @author Carlos Sierra Andrés
 * @author Jorge Ferrer
 */
public class BlogPostingSingleResource implements SingleResource<BlogsEntry> {

	public BlogPostingSingleResource(BlogsEntry blogsEntry) {
		_blogsEntry = blogsEntry;
	}

	@Override
	public BlogsEntry getModel() {
		return _blogsEntry;
	}

	private final BlogsEntry _blogsEntry;

}