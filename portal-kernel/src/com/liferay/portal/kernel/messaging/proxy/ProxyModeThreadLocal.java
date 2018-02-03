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

package com.liferay.portal.kernel.messaging.proxy;

<<<<<<< HEAD
import com.liferay.petra.lang.CentralizedThreadLocal;
=======
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
>>>>>>> compatible

/**
 * @author Shuyang Zhou
 */
public class ProxyModeThreadLocal {

	public static boolean isForceSync() {
		return _forceSync.get();
	}

	public static void setForceSync(boolean forceSync) {
		_forceSync.set(forceSync);
	}

	private static final ThreadLocal<Boolean> _forceSync =
<<<<<<< HEAD
		new CentralizedThreadLocal<>(
=======
		new AutoResetThreadLocal<>(
>>>>>>> compatible
			ProxyModeThreadLocal.class + "_forceSync", () -> Boolean.FALSE);

}