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

package com.liferay.wiki.util;

<<<<<<< HEAD
import com.liferay.petra.lang.CentralizedThreadLocal;
=======
import com.liferay.portal.kernel.util.InitialThreadLocal;
>>>>>>> compatible

/**
 * @author Jorge Ferrer
 */
public class WikiCacheThreadLocal {

	public static boolean isClearCache() {
		return _clearCache.get();
	}

	public static void setClearCache(boolean clearCache) {
		_clearCache.set(clearCache);
	}

	private static final ThreadLocal<Boolean> _clearCache =
<<<<<<< HEAD
		new CentralizedThreadLocal<>(
			WikiCacheThreadLocal.class + "._clearCache", () -> Boolean.TRUE,
			false);
=======
		new InitialThreadLocal<>(
			WikiCacheThreadLocal.class + "._clearCache", () -> Boolean.TRUE);
>>>>>>> compatible

}