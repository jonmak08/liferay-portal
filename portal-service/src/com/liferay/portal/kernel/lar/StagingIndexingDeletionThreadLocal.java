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
 */package com.liferay.portal.kernel.lar;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Tamas Molnar
*/
public class StagingIndexingDeletionThreadLocal {

	public static Map<String, List<String>> getDeletionKeysMap() {
		return _deletionKeysMap.get();
	}

	public static void setDeletionKey(String className, String uid) {
		Map<String, List<String>> deletionKeysMap = _deletionKeysMap.get();

		if (!deletionKeysMap.containsKey(className)) {
			deletionKeysMap.put(className, new ArrayList<String>());
		}

		List<String> uids = deletionKeysMap.get(className);

		if (!uids.contains(uid)) {
			uids.add(uid);
		}
	}

	private static ThreadLocal<Map<String, List<String>>> _deletionKeysMap =
		new AutoResetThreadLocal<Map<String, List<String>>>(
			StagingIndexingDeletionThreadLocal.class + "._deletionKeysMap",
			new HashMap<String, List<String>>());

}