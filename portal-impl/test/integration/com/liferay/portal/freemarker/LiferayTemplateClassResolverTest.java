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

package com.liferay.portal.freemarker;

import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.util.PropsValues;

import freemarker.template.TemplateException;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Tomas Polesovsky
 * @author Tibor Lipusz
 */
public class LiferayTemplateClassResolverTest {

	@Before
	public void setUp() {
		_liferayTemplateClassResolver = new LiferayTemplateClassResolver();
	}

	@Test()
	public void testResolveAllowedClass1() throws Exception {
		testResolve(
			"freemarker.template.utility.ClassUtil", "",
			"freemarker.template.utility.ClassUtil");
	}

	@Test()
	public void testResolveAllowedClass2() throws Exception {
		testResolve(
			"freemarker.template.utility.*", "",
			"freemarker.template.utility.ClassUtil");
	}

	@Test(expected = TemplateException.class)
	public void testResolvePortalClass() throws Exception {
		testResolve("", "", "com.liferay.portal.model.User");
	}

	@Test(expected = TemplateException.class)
	public void testResolveRestrictedClass1() throws Exception {
		testResolve("", "", "freemarker.template.utility.Execute");
	}

	@Test(expected = TemplateException.class)
	public void testResolveRestrictedClass2() throws Exception {
		testResolve(
			"freemarker.template.utility.*", "",
			"freemarker.template.utility.Execute");
	}

	@Test(expected = TemplateException.class)
	public void testResolveRestrictedClass3() throws Exception {
		testResolve(
			"com.liferay.portal.model.User", "com.liferay.portal.model.*",
			"com.liferay.portal.model.User");
	}

	protected void testResolve(
			String allowedClasses, String restrictedClasses,
			String resolveClass)
		throws Exception {

		Field allowedClassesField = ReflectionUtil.getDeclaredField(
			PropsValues.class, "FREEMARKER_ENGINE_ALLOWED_CLASSES");
		Field restrictedClassesField = ReflectionUtil.getDeclaredField(
			PropsValues.class, "FREEMARKER_ENGINE_RESTRICTED_CLASSES");

		Object allowedClassesValue = allowedClassesField.get(null);
		Object restrictedClassesValue = restrictedClassesField.get(null);

		try {
			allowedClassesField.set(null, new String[] {allowedClasses});
			restrictedClassesField.set(null, new String[] {restrictedClasses});

			_liferayTemplateClassResolver.resolve(resolveClass, null, null);
		}
		finally {
			allowedClassesField.set(null, allowedClassesValue);
			restrictedClassesField.set(null, restrictedClassesValue);
		}
	}

	private LiferayTemplateClassResolver _liferayTemplateClassResolver;

}