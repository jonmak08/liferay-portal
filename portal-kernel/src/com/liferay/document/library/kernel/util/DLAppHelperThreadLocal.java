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

package com.liferay.document.library.kernel.util;

<<<<<<< HEAD
import com.liferay.petra.lang.CentralizedThreadLocal;
=======
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
>>>>>>> compatible

/**
 * @author Eudaldo Alonso
 */
public class DLAppHelperThreadLocal {

	public static boolean isEnabled() {
		return _enabled.get();
	}

	public static void setEnabled(boolean enabled) {
		_enabled.set(enabled);
	}

	private static final ThreadLocal<Boolean> _enabled =
<<<<<<< HEAD
		new CentralizedThreadLocal<>(
=======
		new AutoResetThreadLocal<>(
>>>>>>> compatible
			DLAppHelperThreadLocal.class + "._enabled", () -> Boolean.TRUE);

}