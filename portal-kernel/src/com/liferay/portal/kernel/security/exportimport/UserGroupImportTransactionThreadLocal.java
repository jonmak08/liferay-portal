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

package com.liferay.portal.kernel.security.exportimport;

<<<<<<< HEAD
import com.liferay.petra.lang.CentralizedThreadLocal;
=======
import com.liferay.portal.kernel.util.InitialThreadLocal;
>>>>>>> compatible

/**
 * @author Brian Wing Shun Chan
 */
public class UserGroupImportTransactionThreadLocal {

	public static boolean isOriginatesFromImport() {
		return _originatesFromImport.get();
	}

	public static void setOriginatesFromImport(boolean originatesFromImport) {
		_originatesFromImport.set(originatesFromImport);
	}

	private static final ThreadLocal<Boolean> _originatesFromImport =
<<<<<<< HEAD
		new CentralizedThreadLocal<>(
			UserGroupImportTransactionThreadLocal.class +
				"._originatesFromImport",
			() -> Boolean.FALSE, false);
=======
		new InitialThreadLocal<>(
			UserGroupImportTransactionThreadLocal.class +
				"._originatesFromImport",
			() -> Boolean.FALSE);
>>>>>>> compatible

}