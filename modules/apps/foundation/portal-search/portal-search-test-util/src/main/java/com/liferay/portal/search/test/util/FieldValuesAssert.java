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

package com.liferay.portal.search.test.util;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
<<<<<<< HEAD
import java.util.function.Predicate;
=======
>>>>>>> compatible
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author André de Oliveira
 */
public class FieldValuesAssert {

	public static void assertFieldValues(
<<<<<<< HEAD
		Map<String, String> expected, Document document, String message) {

		AssertUtils.assertEquals(
			message, expected, _getFieldValues(document, null));
	}

	public static void assertFieldValues(
=======
>>>>>>> compatible
		Map<String, String> expected, String prefix, Document document,
		String message) {

		AssertUtils.assertEquals(
<<<<<<< HEAD
			message, expected,
			_getFieldValues(document, name -> name.startsWith(prefix)));
	}

	private static Map<String, String> _getFieldValues(
		Document document, Predicate<String> predicate) {
=======
			message, expected, _getFieldValues(prefix, document));
	}

	private static Map<String, String> _getFieldValues(
		String prefix, Document document) {
>>>>>>> compatible

		Map<String, Field> fieldsMap = document.getFields();

		Set<Entry<String, Field>> entrySet = fieldsMap.entrySet();

<<<<<<< HEAD
		Stream<Entry<String, Field>> stream = entrySet.stream();

		if (predicate != null) {
			stream = stream.filter(entry -> predicate.test(entry.getKey()));
		}

		return stream.collect(
=======
		Stream<Entry<String, Field>> entries = entrySet.stream();

		Stream<Entry<String, Field>> prefixedEntries = entries.filter(
			entry -> {
				String name = entry.getKey();

				return name.startsWith(prefix);
			});

		return prefixedEntries.collect(
>>>>>>> compatible
			Collectors.toMap(
				Map.Entry::getKey,
				entry -> {
					Field field = entry.getValue();

					return field.getValue();
				}));
	}

}