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

package com.liferay.portal.struts;

import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.struts.action.Action;
import org.apache.struts.util.WildcardHelper;

/**
 * @author Mika Koivisto
 * @author Raymond Augé
 */
public class StrutsActionRegistryImpl implements StrutsActionRegistry {

	@Override
	public Action getAction(String path) {
		Action action = _actions.get(path);

		if (action != null) {
			return action;
		}

		Map<String, String> matchesMap = new HashMap<String, String>();

		for (Map.Entry<String, Action> entry : _actions.entrySet()) {
			int[] pattern = _patterns.get(entry.getKey());

			if (_wildcardHelper.match(matchesMap, path, pattern)) {
				return entry.getValue();
			}
		}

		return null;
	}

	@Override
	public Map<String, Action> getActions() {
		return _actions;
	}

	@Override
	public void register(String path, StrutsAction strutsAction) {
		Action action = new ActionAdapter(strutsAction);

		_actions.put(path, action);

		_patterns.put(path, _wildcardHelper.compilePattern(path));
	}

	@Override
	public void register(String path, StrutsPortletAction strutsPortletAction) {
		Action action = new PortletActionAdapter(strutsPortletAction);

		_actions.put(path, action);

		_patterns.put(path, _wildcardHelper.compilePattern(path));
	}

	@Override
	public void unregister(String path) {
		_actions.remove(path);

		_patterns.remove(path);
	}

	private static Map<String, Action> _actions =
		new ConcurrentHashMap<String, Action>();

	private static Map<String, int[]> _patterns =
		new ConcurrentHashMap<String, int[]>();

	private static WildcardHelper _wildcardHelper = new WildcardHelper();

}