/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.test.CodeCoverageAssertor;

import javax.servlet.http.Cookie;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class CookieUtilTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Test
	public void testConstructor() {

		// For code coverage

		new CookieUtil();
	}

	@Test
	public void testEquals() {
		Cookie cookie1 = new Cookie("name", null);
		Cookie cookie2 = new Cookie("name2", null);

		// Different comment

		cookie1.setComment("comment");
		cookie2.setComment("comment2");

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setComment("comment");

		// Different domain

		cookie1.setDomain("domain");
		cookie2.setDomain("domain2");

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setDomain("domain");

		// Different maxAge

		cookie1.setMaxAge(1);
		cookie2.setMaxAge(2);

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setMaxAge(1);

		// Different name;

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2 = new Cookie("name", null);

		cookie2.setComment("comment");
		cookie2.setDomain("domain");
		cookie2.setMaxAge(1);

		// Different path

		cookie1.setPath("path");
		cookie2.setPath("path2");

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setPath("path");

		// Different secure

		cookie1.setSecure(true);
		cookie2.setSecure(false);

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setSecure(true);

		// Different value

		cookie1.setValue("value");
		cookie2.setValue("value2");

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setValue("value");

		// Different version

		cookie1.setVersion(1);
		cookie2.setVersion(2);

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setVersion(1);

		// Different http only

		cookie1.setHttpOnly(true);
		cookie2.setHttpOnly(false);

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setHttpOnly(true);

		// Equals

		Assert.assertTrue(CookieUtil.equals(cookie1, cookie2));
	}

	@Test
	public void testSerializationAndDeserialization() {
		Cookie cookie1 = new Cookie("name1", null);

		byte[] data = CookieUtil.serialize(cookie1);

		Assert.assertTrue(
			CookieUtil.equals(cookie1, CookieUtil.deserialize(data)));

		Cookie cookie2 = new Cookie("name2", "value");

		cookie2.setComment("comment");
		cookie2.setDomain("domain");
		cookie2.setHttpOnly(true);
		cookie2.setMaxAge(1);
		cookie2.setPath("path");
		cookie2.setSecure(true);
		cookie2.setVersion(1);

		data = CookieUtil.serialize(cookie2);

		Assert.assertTrue(
			CookieUtil.equals(cookie2, CookieUtil.deserialize(data)));
	}

	@Test
	public void testToString() {
		Cookie cookie = new Cookie("name", "value");

		cookie.setComment("comment");
		cookie.setDomain("domain");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(1);
		cookie.setPath("path");
		cookie.setSecure(true);
		cookie.setVersion(1);

		Assert.assertEquals(
			"{comment=comment, domain=domain, httpOnly=true, maxAge=1, " +
				"name=name, path=path, secure=true, value=value, version=1}",
			CookieUtil.toString(cookie));
	}

}