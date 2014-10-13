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

package com.liferay.portal.verify;

/**
 * @author Tibor Lipusz
 */
public class VerifyRegion extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		updateRegions();
	}

	protected void updateRegions() throws Exception {
		runSQL(
			"update Region set regionCode = 'BB' where regionId = 4004 and " +
				"regionCode = 'BR'");
		runSQL(
			"update Region set name = 'Monza e Brianza', regionCode = 'MB' " +
				"where regionId = 8060 and regionCode = 'MZ'");
	}

}