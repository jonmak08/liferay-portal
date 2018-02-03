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

package com.liferay.calendar.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
<<<<<<< HEAD
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.search.test.util.HitsAssert;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

=======
import com.liferay.calendar.model.CalendarBookingConstants;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.search.CalendarBookingIndexer;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.util.CalendarResourceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.SearchContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Assert;
>>>>>>> compatible
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adam Brandizzi
 */
@RunWith(Arquillian.class)
<<<<<<< HEAD
public class CalendarBookingIndexerTest extends BaseCalendarIndexerTestCase {
=======
@Sync
public class CalendarBookingIndexerTest {
>>>>>>> compatible

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
<<<<<<< HEAD
			PermissionCheckerTestRule.INSTANCE);

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		setGroup(calendarFixture.addGroup());
		setIndexerClass(CalendarBooking.class);
		setUser(calendarFixture.addUser());
	}

	@Test
	public void testBasicSearch() throws Exception {
		String title = RandomTestUtil.randomString();

		addCalendarBooking(
			new LocalizedValuesMap() {
				{
					put(LocaleUtil.US, title);
				}
			});

		calendarSearchFixture.searchOnlyOne(title, LocaleUtil.US);
	}

	@Test
	public void testMultiLocale() throws Exception {
		String originalTitle = "entity title";
		String translatedTitle = "entitas neve";

		addCalendarBooking(
			new LocalizedValuesMap() {
				{
					put(LocaleUtil.US, originalTitle);
					put(LocaleUtil.HUNGARY, translatedTitle);
				}
			});

		calendarSearchFixture.searchOnlyOne("nev", LocaleUtil.HUNGARY);
	}

	@Test
	public void testTrash() throws Exception {
		String title = RandomTestUtil.randomString();

		CalendarBooking calendarBooking = addCalendarBooking(
			new LocalizedValuesMap() {
				{
					put(LocaleUtil.US, title);
				}
			});

		calendarBookingLocalService.moveCalendarBookingToTrash(
			TestPropsValues.getUserId(), calendarBooking);

		HitsAssert.assertNoHits(
			calendarSearchFixture.search(
				calendarSearchFixture.getSearchContext(title, LocaleUtil.US)));

		HitsAssert.assertOnlyOne(
			calendarSearchFixture.search(
				withStatusInTrash(
					calendarSearchFixture.getSearchContext(
						title, LocaleUtil.US))));
	}

	protected CalendarBooking addCalendarBooking(
		LocalizedValuesMap titleLocalizedValuesMap) {

		try {
			ServiceContext serviceContext = calendarFixture.getServiceContext();

			Calendar calendar = calendarFixture.addCalendar(
				new LocalizedValuesMap() {
					{
						put(
							LocaleUtil.getDefault(),
							RandomTestUtil.randomString());
					}
				},
				new LocalizedValuesMap(), serviceContext);

			return calendarFixture.addCalendarBooking(
				titleLocalizedValuesMap, calendar, serviceContext);
		}
		catch (PortalException pe) {
			throw new RuntimeException(pe);
		}
	}

	protected SearchContext withStatusInTrash(SearchContext searchContext) {
		searchContext.setAttribute(
			Field.STATUS, new int[] {WorkflowConstants.STATUS_IN_TRASH});
=======
			PermissionCheckerTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_user = UserTestUtil.addUser();

		setUpSearchContext(_group, _user);
	}

	@Test
	public void testSearch() throws Exception {
		setUpSearchContext(_group, TestPropsValues.getUser());

		String title = RandomTestUtil.randomString();

		addCalendarBooking(title);

		assertSearchHitsLength(title, 1);
	}

	@Test
	public void testSearchNotAdmin() throws Exception {
		setUpSearchContext(_group, _user);

		String title = RandomTestUtil.randomString();

		addCalendarBooking(title);

		assertSearchHitsLength(title, 1);
	}

	protected static SearchContext getSearchContext(Group group, User user)
		throws Exception {

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setUserId(user.getUserId());
>>>>>>> compatible

		return searchContext;
	}

<<<<<<< HEAD
=======
	protected void addCalendarBooking(String title) throws PortalException {
		ServiceContext serviceContext = new ServiceContext();

		CalendarResource calendarResource =
			CalendarResourceUtil.getGroupCalendarResource(
				_group.getGroupId(), serviceContext);

		Calendar calendar = CalendarLocalServiceUtil.addCalendar(
			_user.getUserId(), _group.getGroupId(),
			calendarResource.getCalendarResourceId(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(), StringPool.UTC,
			RandomTestUtil.randomInt(0, 255), false, false, false,
			serviceContext);

		Map<Locale, String> titleMap = new HashMap<>();

		titleMap.put(LocaleUtil.getDefault(), title);

		long startTime = DateUtil.newTime() + RandomTestUtil.randomInt();

		long endTime = startTime + Time.HOUR;

		HashMap<Locale, String> hashMap = new HashMap<>();

		CalendarBookingLocalServiceUtil.addCalendarBooking(
			_user.getUserId(), calendar.getCalendarId(), new long[0],
			CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
			titleMap, hashMap, null, startTime, endTime, false, null, 0,
			"email", 0, "email", serviceContext);
	}

	protected void assertSearchHitsLength(
			final String keywords, final int expectedLength)
		throws Exception {

		_searchContext.setKeywords(StringUtil.toLowerCase(keywords));

		Indexer<CalendarBooking> indexer = new CalendarBookingIndexer();

		Hits hits = indexer.search(_searchContext);

		Assert.assertEquals(hits.toString(), expectedLength, hits.getLength());
	}

	protected void setUpSearchContext(Group group, User user) throws Exception {
		_searchContext = getSearchContext(_group, _user);
	}

	@DeleteAfterTestRun
	private Group _group;

	private SearchContext _searchContext;

	@DeleteAfterTestRun
	private User _user;

>>>>>>> compatible
}