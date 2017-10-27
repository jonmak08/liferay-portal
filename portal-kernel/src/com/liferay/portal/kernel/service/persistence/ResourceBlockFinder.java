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

package com.liferay.portal.kernel.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @deprecated As of 7.0.0, with no direct replacement
 * @generated
 */
@Deprecated
@ProviderType
public interface ResourceBlockFinder {
	public com.liferay.portal.kernel.security.permission.ResourceBlockIdsBag findByC_G_N_R(
		long companyId, long groupId, java.lang.String name, long[] roleIds);
}