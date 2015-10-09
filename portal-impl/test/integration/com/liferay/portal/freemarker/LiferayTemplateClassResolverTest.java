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

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.util.PropsUtil;

import freemarker.template.TemplateException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Tomas Polesovsky
 */
public class LiferayTemplateClassResolverTest {

	@Before
	public void setUp() {
		_liferayTemplateClassResolver = new LiferayTemplateClassResolver();
	}

	@Test()
	public void testResolveAllowedClass1() throws Exception {
		PropsUtil.set(
			PropsKeys.FREEMARKER_ENGINE_ALLOWED_CLASSES,
				"freemarker.template.utility.ClassUtil");
		PropsUtil.set(PropsKeys.FREEMARKER_ENGINE_RESTRICTED_CLASSES, "");

		_liferayTemplateClassResolver.resolve(
			"freemarker.template.utility.ClassUtil", null, null);
	}

	@Test()
	public void testResolveAllowedClass2() throws Exception {
		PropsUtil.set(
			PropsKeys.FREEMARKER_ENGINE_ALLOWED_CLASSES,
				"freemarker.template.utility.*");
		PropsUtil.set(PropsKeys.FREEMARKER_ENGINE_RESTRICTED_CLASSES, "");

		_liferayTemplateClassResolver.resolve(
			"freemarker.template.utility.ClassUtil", null, null);
	}

	@Test(expected = TemplateException.class)
	public void testResolvePortalClass() throws Exception {
		_liferayTemplateClassResolver.resolve(
			"com.liferay.portal.model.User", null, null);
	}

	@Test(expected = TemplateException.class)
	public void testResolveRestrictedClass1() throws Exception {
		_liferayTemplateClassResolver.resolve(
			"freemarker.template.utility.Execute", null, null);
	}

	@Test(expected = TemplateException.class)
	public void testResolveRestrictedClass2() throws Exception {
		PropsUtil.set(
			PropsKeys.FREEMARKER_ENGINE_ALLOWED_CLASSES,
				"freemarker.template.utility.*");
		PropsUtil.set(PropsKeys.FREEMARKER_ENGINE_RESTRICTED_CLASSES, "");

		_liferayTemplateClassResolver.resolve(
			"freemarker.template.utility.Execute", null, null);
	}

	@Test(expected = TemplateException.class)
	public void testResolveRestrictedClass3() throws Exception {
		PropsUtil.set(
			PropsKeys.FREEMARKER_ENGINE_ALLOWED_CLASSES,
				"com.liferay.portal.model.User");
		PropsUtil.set(
			PropsKeys.FREEMARKER_ENGINE_RESTRICTED_CLASSES,
				"com.liferay.portal.model.*");

		_liferayTemplateClassResolver.resolve(
			"com.liferay.portal.model.User", null, null);
	}

	private LiferayTemplateClassResolver _liferayTemplateClassResolver;

}