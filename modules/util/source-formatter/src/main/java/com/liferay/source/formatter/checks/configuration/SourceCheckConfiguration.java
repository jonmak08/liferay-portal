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

package com.liferay.source.formatter.checks.configuration;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
=======
import java.util.HashMap;
>>>>>>> compatible
import java.util.Map;
import java.util.Set;

/**
 * @author Hugo Huijser
 */
public class SourceCheckConfiguration {

	public SourceCheckConfiguration(String name) {
		_name = name;
	}

	public void addAttribute(String name, String value) {
<<<<<<< HEAD
		List<String> attributeValues = _attributesMap.get(name);

		if (attributeValues == null) {
			attributeValues = new ArrayList<>();
		}

		attributeValues.add(value);

		_attributesMap.put(name, attributeValues);
=======
		_attributesMap.put(name, value);
>>>>>>> compatible
	}

	public Set<String> attributeNames() {
		return _attributesMap.keySet();
	}

<<<<<<< HEAD
	public List<String> getAttributeValues(String name) {
=======
	public String getAttributeValue(String name) {
>>>>>>> compatible
		return _attributesMap.get(name);
	}

	public String getName() {
		return _name;
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public void setEnabled(boolean enabled) {
		_enabled = enabled;
	}

<<<<<<< HEAD
	private final Map<String, List<String>> _attributesMap = new HashMap<>();
=======
	private final Map<String, String> _attributesMap = new HashMap<>();
>>>>>>> compatible
	private boolean _enabled;
	private final String _name;

}