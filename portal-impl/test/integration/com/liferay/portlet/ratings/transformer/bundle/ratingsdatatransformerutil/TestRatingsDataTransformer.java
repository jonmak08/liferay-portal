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

package com.liferay.portlet.ratings.transformer.bundle.ratingsdatatransformerutil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery.PerformActionMethod;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.StackTraceUtil;
>>>>>>> compatible
import com.liferay.ratings.kernel.RatingsType;
import com.liferay.ratings.kernel.model.RatingsEntry;
import com.liferay.ratings.kernel.transformer.RatingsDataTransformer;

import java.util.concurrent.atomic.AtomicBoolean;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Peter Fellwock
 */
@Component(
	immediate = true,
	property = {"service.ranking:Integer=" + Integer.MAX_VALUE}
)
public class TestRatingsDataTransformer implements RatingsDataTransformer {

	@Override
	public PerformActionMethod<RatingsEntry> transformRatingsData(
		RatingsType fromRatingsType, RatingsType toRatingsType) {

		_atomicBoolean.set(Boolean.TRUE);

		return null;
	}

	@Reference(target = "(test=AtomicState)")
	protected void setAtomicBoolean(AtomicBoolean atomicBoolean) {
		_atomicBoolean = atomicBoolean;
	}

	private AtomicBoolean _atomicBoolean;

}