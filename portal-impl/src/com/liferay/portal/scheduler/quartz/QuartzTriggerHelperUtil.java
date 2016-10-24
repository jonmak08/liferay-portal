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

import static com.liferay.portal.kernel.scheduler.SchedulerEngine.GROUP_NAME_MAX_LENGTH;
import static com.liferay.portal.kernel.scheduler.SchedulerEngine.JOB_NAME_MAX_LENGTH;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.TriggerType;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

/**
 * @author Tina Tian
 */
public class QuartzTriggerHelperUtil {

	public static Trigger getQuartzTrigger(
			com.liferay.portal.kernel.scheduler.Trigger trigger)
		throws SchedulerException {

		if (trigger == null) {
			return null;
		}

		Date endDate = trigger.getEndDate();
		String jobName = _fixMaxLength(
			trigger.getJobName(), JOB_NAME_MAX_LENGTH);
		String groupName = _fixMaxLength(
			trigger.getGroupName(), GROUP_NAME_MAX_LENGTH);

		Date startDate = trigger.getStartDate();

		if (startDate == null) {
			startDate = new Date(System.currentTimeMillis());
		}

		Trigger quartzTrigger = null;

		TriggerType triggerType = trigger.getTriggerType();

		if (triggerType.equals(TriggerType.CRON)) {
			TriggerBuilder<Trigger>triggerBuilder = TriggerBuilder.newTrigger();

			triggerBuilder.endAt(endDate);
			triggerBuilder.forJob(jobName, groupName);
			triggerBuilder.startAt(startDate);
			triggerBuilder.withIdentity(jobName, groupName);

			CronScheduleBuilder cronScheduleBuilder =
				CronScheduleBuilder.cronSchedule(
					(String)trigger.getTriggerContent());

			triggerBuilder.withSchedule(cronScheduleBuilder);

			quartzTrigger = triggerBuilder.build();
		}
		else if (triggerType.equals(TriggerType.SIMPLE)) {
			long interval = (Long)trigger.getTriggerContent();

			if (interval <= 0) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Not scheduling " + trigger.getJobName() +
							" because interval is less than or equal to 0");
				}

				return null;
			}

			TriggerBuilder<Trigger>triggerBuilder = TriggerBuilder.newTrigger();

			triggerBuilder.endAt(endDate);
			triggerBuilder.forJob(jobName, groupName);
			triggerBuilder.startAt(startDate);
			triggerBuilder.withIdentity(jobName, groupName);

			SimpleScheduleBuilder simpleScheduleBuilder =
				SimpleScheduleBuilder.simpleSchedule();

			simpleScheduleBuilder.withIntervalInMilliseconds(interval);
			simpleScheduleBuilder.withRepeatCount(
				SimpleTrigger.REPEAT_INDEFINITELY);

			triggerBuilder.withSchedule(simpleScheduleBuilder);

			quartzTrigger = triggerBuilder.build();
		}
		else {
			throw new SchedulerException(
				"Unknown trigger type " + trigger.getTriggerType());
		}

		return quartzTrigger;
	}

	protected static String _fixMaxLength(String argument, int maxLength) {
		if (argument == null) {
			return null;
		}

		if (argument.length() > maxLength) {
			argument = argument.substring(0, maxLength);
		}

		return argument;
	}

	private static Log _log = LogFactoryUtil.getLog(
		QuartzTriggerHelperUtil.class);

}