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

package com.liferay.portal.scheduler.quartz;

import com.liferay.portal.kernel.scheduler.CronTrigger;
import com.liferay.portal.kernel.scheduler.IntervalTrigger;
import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.test.CaptureHandler;
import com.liferay.portal.kernel.test.JDKLoggerTestUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.util.PropsImpl;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Tina Tian
 */
public class QuartzTriggerHelperUtilTest {

	@Before
	public void setUp() throws Exception {
		PropsUtil.setProps(new PropsImpl());
	}

	@Test
	public void testGetQuartzTrigger1() throws Exception {
		Date startDate = new Date(System.currentTimeMillis() + 10000);

		CronTrigger cronTrigger1 = new CronTrigger(
			_TEST_JOB_NAME_0, _MEMORY_TEST_GROUP_NAME, null, "0/1 * * * * ?");
		CronTrigger cronTrigger2 = new CronTrigger(
			_TEST_JOB_NAME_0, _MEMORY_TEST_GROUP_NAME, startDate,
			"0/1 * * * * ?");

		org.quartz.Trigger trigger1 =  QuartzTriggerHelperUtil.getQuartzTrigger(
			cronTrigger1);
		org.quartz.Trigger trigger2 = QuartzTriggerHelperUtil.getQuartzTrigger(
			cronTrigger2);

		Date nextFireDate1 = trigger1.getStartTime();
		Date nextFireDate2 = trigger2.getStartTime();

		Assert.assertTrue(nextFireDate1.before(nextFireDate2));
	}

	@Test
	public void testGetQuartzTrigger2() {
		String wrongCronTriggerContent = "bad-cron-trigger-content";

		Trigger cronTrigger = new CronTrigger(
			_TEST_JOB_NAME_0, _MEMORY_TEST_GROUP_NAME, null,
			wrongCronTriggerContent);

		try {
			QuartzTriggerHelperUtil.getQuartzTrigger(cronTrigger);

			Assert.fail();
		}
		catch (Exception e) {
		}
	}

	@Test
	public void testGetQuartzTrigger3() throws Exception {
		CaptureHandler captureHandler = JDKLoggerTestUtil.configureJDKLogger(
			QuartzTriggerHelperUtil.class.getName(), Level.FINE);

		try {
			List<LogRecord> logRecords = captureHandler.getLogRecords();

			IntervalTrigger intervalTrigger = new IntervalTrigger(
				_TEST_JOB_NAME_0, _MEMORY_TEST_GROUP_NAME, 0);

			org.quartz.Trigger trigger =
				QuartzTriggerHelperUtil.getQuartzTrigger(intervalTrigger);

			Assert.assertNull(trigger);

			Assert.assertEquals(1, logRecords.size());

			LogRecord logRecord = logRecords.get(0);

			Assert.assertEquals(
				"Not scheduling " + _TEST_JOB_NAME_0 + " because interval " +
					"is less than or equal to 0",
				logRecord.getMessage());
		}
		finally {
			captureHandler.close();
		}
	}

	@Test
	public void testGetQuartzTrigger4() throws Exception {
		String jobName = _TEST_JOB_NAME_0;

		while (jobName.length() <= SchedulerEngine.JOB_NAME_MAX_LENGTH) {
			jobName = jobName.concat(_TEST_JOB_NAME_0);
		}

		Trigger intervalTrigger = new IntervalTrigger(
			jobName, _MEMORY_TEST_GROUP_NAME, _DEFAULT_INTERVAL);

		org.quartz.Trigger trigger = QuartzTriggerHelperUtil.getQuartzTrigger(
			intervalTrigger);

		Assert.assertFalse(jobName.equals(trigger.getJobKey().getName()));
	}

	private static final long _DEFAULT_INTERVAL = 10000;

	private static final String _MEMORY_TEST_GROUP_NAME = "MEMORY#test.group";

	private static final String _TEST_GROUP_NAME = "test.group";

	private static final String _TEST_JOB_NAME_0 = "test.job.0";

}