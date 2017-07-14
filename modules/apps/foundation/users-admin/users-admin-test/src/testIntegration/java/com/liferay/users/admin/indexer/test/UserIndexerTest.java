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

package com.liferay.users.admin.indexer.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

/**
 * @author André de Oliveira
 */
@RunWith(Arquillian.class)
@Sync
public class UserIndexerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		Registry registry = RegistryUtil.getRegistry();

		_userLocalService = registry.getService(UserLocalService.class);

		IndexerRegistry indexerRegistry = registry.getService(
			IndexerRegistry.class);

		_indexer = indexerRegistry.getIndexer(User.class);

		_serviceContext = ServiceContextTestUtil.getServiceContext();
	}

	@Test
	public void testEmailAddress() throws Exception {
		User expectedUser = UserTestUtil.addUser();

		String expectedEmail = expectedUser.getEmailAddress();

		User actualUser = assertSearchOneUser(
			StringUtil.toUpperCase(expectedEmail), expectedUser);

		Assert.assertEquals(expectedEmail, actualUser.getEmailAddress());
	}

	@Test
	public void testEmailAddressField() throws Exception {
		User expectedUser = UserTestUtil.addUser();

		String expectedEmail = expectedUser.getEmailAddress();

		User actualUser = assertSearchOneUser(
			"emailAddress", expectedEmail, expectedUser);

		Assert.assertEquals(expectedEmail, actualUser.getEmailAddress());
	}

	@Test
	public void testEmailAddressPrefix() throws Exception {
		User expectedUser = UserTestUtil.addUser();

		String expectedEmail = expectedUser.getEmailAddress();

		User actualUser = assertSearchOneUser(
			StringUtil.removeSubstring(expectedEmail, "@liferay.com"),
			expectedUser);

		Assert.assertEquals(expectedEmail, actualUser.getEmailAddress());
	}

	@Test
	public void testEmailAddressSubstring() throws Exception {
		User expectedUser = UserTestUtil.addUser();

		String expectedEmail = expectedUser.getEmailAddress();

		User actualUser = assertSearchOneUser(
			expectedEmail.substring(4, expectedEmail.length() - 7),
			expectedUser);

		Assert.assertEquals(expectedEmail, actualUser.getEmailAddress());
	}

	@Test
	public void testEmptyQuery() throws Exception {
		User user = UserTestUtil.addUser();

		assertSearch(StringPool.BLANK, user);
	}

	@Test
	public void testFirstNameExactPhrase() throws Exception {
		String firstName = "Mary Jane";
		String middleName = "Watson";
		String lastName = "Parker";

		User expectedUser = UserTestUtil.addUser();

		expectedUser.setFirstName(firstName);
		expectedUser.setMiddleName(middleName);
		expectedUser.setLastName(lastName);

		_userLocalService.updateUser(expectedUser);

		User actualUser = assertSearchOneUser(
			"firstName", "\"Mary Jane\"", expectedUser);

		Assert.assertEquals(firstName, actualUser.getFirstName());
	}

	@Test
	public void testFirstNameMixedExactPhrase() throws Exception {
		String firstName = "Mary Jane Watson";
		String middleName = "Joanne";
		String lastName = "Parker";

		User expectedUser = UserTestUtil.addUser();

		expectedUser.setFirstName(firstName);
		expectedUser.setMiddleName(middleName);
		expectedUser.setLastName(lastName);

		_userLocalService.updateUser(expectedUser);

		assertNoHits("firstName", "\"Mary Watson\"");
		assertNoHits("firstName", "\"Mary Jane\" Missingword");

		User actualUser = assertSearchOneUser(
			"firstName", "Mary \"Jane Watson\"", expectedUser);

		Assert.assertEquals(firstName, actualUser.getFirstName());
	}

	@Test
	public void testLikeCharacter() throws Exception {
		User user = UserTestUtil.addUser();

		assertSearch(StringPool.PERCENT, user);

		assertNoHits(StringPool.PERCENT + RandomTestUtil.randomString());
	}

	@Test
	public void testLuceneQueryParserUnfriendlyCharacters() throws Exception {
		User user = UserTestUtil.addUser();

		assertSearch(StringPool.AT, user);

		assertNoHits(StringPool.AT + RandomTestUtil.randomString());
		assertNoHits(StringPool.EXCLAMATION);
		assertNoHits(StringPool.EXCLAMATION + RandomTestUtil.randomString());
	}

	@Test
	public void testNameFieldsNotTokenized() throws Exception {
		String firstName = "Liferay7";
		String lastName = "dell'Apostrophe";
		String middleName = "ALLOY_4";

		testNameFields(firstName, lastName, middleName);
	}

	@Test
	public void testNameFieldsNotTokenizedLowercase() throws Exception {
		String firstName = "liferay7";
		String lastName = "dell'apostrophe";
		String middleName = "alloy_4";

		testNameFields(firstName, lastName, middleName);
	}

	@Test
	public void testNamesPrefix() throws Exception {
		String firstName = "First";
		String lastName = "Last";
		String middleName = "Middle";

		User expectedUser = UserTestUtil.addUser();

		expectedUser.setFirstName(firstName);
		expectedUser.setMiddleName(middleName);
		expectedUser.setLastName(lastName);

		_userLocalService.updateUser(expectedUser);

		User actualUser = assertSearchOneUser("Fir", expectedUser);

		Assert.assertEquals("First", actualUser.getFirstName());

		actualUser = assertSearchOneUser("LasT", expectedUser);

		Assert.assertEquals("Last", actualUser.getLastName());

		actualUser = assertSearchOneUser("midd", expectedUser);

		Assert.assertEquals("Middle", actualUser.getMiddleName());
	}

	@Test
	public void testNamesSubstring() throws Exception {
		String firstName = "First";
		String lastName = "Last";
		String middleName = "Middle";

		User expectedUser = UserTestUtil.addUser();

		expectedUser.setFirstName(firstName);
		expectedUser.setMiddleName(middleName);
		expectedUser.setLastName(lastName);

		_userLocalService.updateUser(expectedUser);

		User actualUser = assertSearchOneUser("Fir", expectedUser);

		Assert.assertEquals("First", actualUser.getFirstName());

		actualUser = assertSearchOneUser("asT", expectedUser);

		Assert.assertEquals("Last", actualUser.getLastName());

		actualUser = assertSearchOneUser("idd", expectedUser);

		Assert.assertEquals("Middle", actualUser.getMiddleName());
	}

	@Test
	public void testScreenName() throws Exception {
		User user = addUserScreenName("Open4Life");

		user = assertSearchOneUser("Open4Life", user);

		Assert.assertEquals("open4life", user.getScreenName());
	}

	@Test
	public void testScreenNameField() throws Exception {
		User user = addUserScreenName("Open4Life");

		user = assertSearchOneUser("screenName", "open4life", user);

		Assert.assertEquals("open4life", user.getScreenName());
	}

	@Test
	public void testScreenNameSubstring() throws Exception {
		User user = addUserScreenName("Open4Life");

		user = assertSearchOneUser("open lite", user);

		Assert.assertEquals("open4life", user.getScreenName());

		user = assertSearchOneUser("OPE", user);

		Assert.assertEquals("open4life", user.getScreenName());

		user = assertSearchOneUser("4lif", user);

		Assert.assertEquals("open4life", user.getScreenName());
	}

	@Rule
	public TestName testName = new TestName();

	protected User addUser(
			String firstName, String lastName, String middleName,
			String screenName, String emailAddress)
		throws Exception {

		long creatorUserId = TestPropsValues.getUserId();
		long companyId = TestPropsValues.getCompanyId();
		boolean autoPassword = true;
		String password1 = null;
		String password2 = null;
		boolean autoScreenName = false;
		long facebookId = 0;
		String openId = null;
		Locale locale = LocaleUtil.getDefault();
		long prefixId = 0;
		long suffixId = 0;
		boolean male = false;
		int birthdayMonth = Calendar.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String jobTitle = null;
		long[] groupIds = new long[] {TestPropsValues.getGroupId()};
		long[] organizationIds = null;
		long[] roleIds = null;
		long[] userGroupIds = null;
		boolean sendMail = false;

		User user = _userLocalService.addUser(
			creatorUserId, companyId, autoPassword, password1, password2,
			autoScreenName, screenName, emailAddress, facebookId, openId,
			locale, firstName, middleName, lastName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
			organizationIds, roleIds, userGroupIds, sendMail, _serviceContext);

		_users.add(user);

		return user;
	}

	protected User addUserScreenName(String screenName) throws Exception {
		String firstName = RandomTestUtil.randomString();
		String lastName = RandomTestUtil.randomString();
		String middleName = RandomTestUtil.randomString();
		String emailAddress = RandomTestUtil.randomString() + "@liferay.com";

		return addUser(
			firstName, lastName, middleName, screenName, emailAddress);
	}

	protected void assertLength(Hits hits, int length) {
		Assert.assertEquals(hits.toString(), length, hits.getLength());
	}

	protected void assertNoHits(String keywords) throws Exception {
		Hits hits = search(keywords);

		assertLength(hits, 0);
	}

	protected void assertNoHits(String field, String value) throws Exception {
		Hits hits = search(field, value);

		assertLength(hits, 0);
	}

	protected List<User> assertSearch(Hits hits, User... expectedUsers)
		throws Exception {

		List<User> actualUsers = getUsers(hits);

		List<String> actualScreenNames = getScreenNames(actualUsers);

		List<String> expectedScreenNames = getScreenNames(
			Arrays.asList(expectedUsers));

		if (!actualScreenNames.containsAll(expectedScreenNames)) {
			Assert.assertEquals(
				toString(expectedScreenNames), toString(actualScreenNames));
		}

		return actualUsers;
	}

	protected List<User> assertSearch(String keywords, User... expectedUsers)
		throws Exception {

		Hits hits = search(keywords);

		return assertSearch(hits, expectedUsers);
	}

	protected User assertSearchOneUser(String field, String value, User user)
		throws Exception {

		Hits hits = search(field, value);

		List<User> actualUsers = assertSearch(hits, user);

		return findByUserId(actualUsers, user.getUserId());
	}

	protected User assertSearchOneUser(String keywords, User user)
		throws Exception {

		List<User> actualUsers = assertSearch(keywords, user);

		return findByUserId(actualUsers, user.getUserId());
	}

	protected User findByUserId(List<User> users, long userId) {
		for (User user : users) {
			if (user.getUserId() == userId) {
				return user;
			}
		}

		return null;
	}

	protected List<String> getScreenNames(List<User> users) {
		List<String> screenNames = new ArrayList<>(users.size());

		for (User user : users) {
			screenNames.add(user.getScreenName());
		}

		return screenNames;
	}

	protected SearchContext getSearchContext() throws Exception {
		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(TestPropsValues.getCompanyId());
		searchContext.setGroupIds(new long[] {TestPropsValues.getGroupId()});

		return searchContext;
	}

	protected User getUser(Document document) throws Exception {
		long userId = GetterUtil.getLong(document.get(Field.USER_ID));

		return _userLocalService.getUser(userId);
	}

	protected List<User> getUsers(Hits hits) throws Exception {
		Document[] documents = hits.getDocs();

		List<User> users = new ArrayList<>(documents.length);

		for (Document document : documents) {
			users.add(getUser(document));
		}

		return users;
	}

	protected Hits search(SearchContext searchContext) throws Exception {
		return _indexer.search(searchContext);
	}

	protected Hits search(String keywords) throws Exception {
		SearchContext searchContext = getSearchContext();

		searchContext.setKeywords(keywords);

		return search(searchContext);
	}

	protected Hits search(String field, String value) throws Exception {
		SearchContext searchContext = getSearchContext();

		searchContext.setAttribute(field, value);

		return search(searchContext);
	}

	protected void testNameFields(
			String firstName, String lastName, String middleName)
		throws Exception {

		User expectedUser = UserTestUtil.addUser();

		expectedUser.setFirstName(firstName);
		expectedUser.setMiddleName(middleName);
		expectedUser.setLastName(lastName);

		_userLocalService.updateUser(expectedUser);

		User actualUser = assertSearchOneUser(
			"firstName", firstName, expectedUser);

		Assert.assertEquals(firstName, actualUser.getFirstName());

		actualUser = assertSearchOneUser("lastName", lastName, expectedUser);

		Assert.assertEquals(lastName, actualUser.getLastName());

		actualUser = assertSearchOneUser(
			"middleName", middleName, expectedUser);

		Assert.assertEquals(middleName, actualUser.getMiddleName());
	}

	protected String toString(List<String> strings) {
		Collections.sort(strings);

		return strings.toString();
	}

	private Indexer<User> _indexer;
	private ServiceContext _serviceContext;
	private UserLocalService _userLocalService;

	@DeleteAfterTestRun
	private final List<User> _users = new ArrayList<>();

}