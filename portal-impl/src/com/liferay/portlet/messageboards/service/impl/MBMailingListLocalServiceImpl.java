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

package com.liferay.portlet.messageboards.service.impl;

<<<<<<< HEAD
import com.liferay.message.boards.kernel.model.MBMailingList;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
=======
import com.liferay.message.boards.kernel.exception.MailingListEmailAddressException;
import com.liferay.message.boards.kernel.exception.MailingListInServerNameException;
import com.liferay.message.boards.kernel.exception.MailingListInUserNameException;
import com.liferay.message.boards.kernel.exception.MailingListOutEmailAddressException;
import com.liferay.message.boards.kernel.exception.MailingListOutServerNameException;
import com.liferay.message.boards.kernel.exception.MailingListOutUserNameException;
import com.liferay.message.boards.kernel.model.MBMailingList;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.messageboards.messaging.MailingListRequest;
>>>>>>> compatible
import com.liferay.portlet.messageboards.service.base.MBMailingListLocalServiceBaseImpl;

/**
 * @author Thiago Moreira
 * @deprecated As of 7.0.0, replaced by {@link
 *             com.liferay.message.boards.service.impl.MBMailingListLocalServiceImpl}
 */
@Deprecated
public class MBMailingListLocalServiceImpl
	extends MBMailingListLocalServiceBaseImpl {

	@Override
	public MBMailingList addMailingList(
			long userId, long groupId, long categoryId, String emailAddress,
			String inProtocol, String inServerName, int inServerPort,
			boolean inUseSSL, String inUserName, String inPassword,
			int inReadInterval, String outEmailAddress, boolean outCustom,
			String outServerName, int outServerPort, boolean outUseSSL,
			String outUserName, String outPassword, boolean allowAnonymous,
			boolean active, ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.message.boards.service.impl." +
					"MBMailingListLocalServiceImpl");
	}

	@Override
	public void deleteCategoryMailingList(long groupId, long categoryId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.message.boards.service.impl." +
					"MBMailingListLocalServiceImpl");
	}

	@Override
	public void deleteMailingList(long mailingListId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.message.boards.service.impl." +
					"MBMailingListLocalServiceImpl");
	}

	@Override
	public void deleteMailingList(MBMailingList mailingList)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.message.boards.service.impl." +
					"MBMailingListLocalServiceImpl");
	}

	@Override
	public MBMailingList fetchCategoryMailingList(
		long groupId, long categoryId) {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.message.boards.service.impl." +
					"MBMailingListLocalServiceImpl");
	}

	@Override
	public MBMailingList getCategoryMailingList(long groupId, long categoryId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.message.boards.service.impl." +
					"MBMailingListLocalServiceImpl");
	}

	@Override
	public MBMailingList updateMailingList(
			long mailingListId, String emailAddress, String inProtocol,
			String inServerName, int inServerPort, boolean inUseSSL,
			String inUserName, String inPassword, int inReadInterval,
			String outEmailAddress, boolean outCustom, String outServerName,
			int outServerPort, boolean outUseSSL, String outUserName,
			String outPassword, boolean allowAnonymous, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

<<<<<<< HEAD
		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.message.boards.service.impl." +
					"MBMailingListLocalServiceImpl");
=======
		// Mailing list

		validate(
			emailAddress, inServerName, inUserName, outEmailAddress, outCustom,
			outServerName, outUserName, active);

		MBMailingList mailingList = mbMailingListPersistence.findByPrimaryKey(
			mailingListId);

		mailingList.setEmailAddress(emailAddress);
		mailingList.setInProtocol(inUseSSL ? inProtocol + "s" : inProtocol);
		mailingList.setInServerName(inServerName);
		mailingList.setInServerPort(inServerPort);
		mailingList.setInUseSSL(inUseSSL);
		mailingList.setInUserName(inUserName);
		mailingList.setInPassword(inPassword);
		mailingList.setInReadInterval(inReadInterval);
		mailingList.setOutEmailAddress(outEmailAddress);
		mailingList.setOutCustom(outCustom);
		mailingList.setOutServerName(outServerName);
		mailingList.setOutServerPort(outServerPort);
		mailingList.setOutUseSSL(outUseSSL);
		mailingList.setOutUserName(outUserName);
		mailingList.setOutPassword(outPassword);
		mailingList.setAllowAnonymous(allowAnonymous);
		mailingList.setActive(active);

		mbMailingListPersistence.update(mailingList);

		// Scheduler

		if (active) {
			scheduleMailingList(mailingList);
		}
		else {
			unscheduleMailingList(mailingList);
		}

		return mailingList;
>>>>>>> compatible
	}

	protected String getSchedulerGroupName(MBMailingList mailingList) {
		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.message.boards.service.impl." +
					"MBMailingListLocalServiceImpl");
	}

	protected void scheduleMailingList(MBMailingList mailingList)
		throws PortalException {

<<<<<<< HEAD
		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.message.boards.service.impl." +
					"MBMailingListLocalServiceImpl");
=======
		String groupName = getSchedulerGroupName(mailingList);

		Calendar startDate = CalendarFactoryUtil.getCalendar();

		Trigger trigger = TriggerFactoryUtil.createTrigger(
			groupName, groupName, startDate.getTime(),
			mailingList.getInReadInterval(), TimeUnit.MINUTE);

		MailingListRequest mailingListRequest = new MailingListRequest();

		mailingListRequest.setCompanyId(mailingList.getCompanyId());
		mailingListRequest.setUserId(mailingList.getUserId());
		mailingListRequest.setGroupId(mailingList.getGroupId());
		mailingListRequest.setCategoryId(mailingList.getCategoryId());
		mailingListRequest.setInProtocol(mailingList.getInProtocol());
		mailingListRequest.setInServerName(mailingList.getInServerName());
		mailingListRequest.setInServerPort(mailingList.getInServerPort());
		mailingListRequest.setInUseSSL(mailingList.getInUseSSL());
		mailingListRequest.setInUserName(mailingList.getInUserName());
		mailingListRequest.setInPassword(mailingList.getInPassword());
		mailingListRequest.setAllowAnonymous(mailingList.getAllowAnonymous());

		SchedulerEngineHelperUtil.schedule(
			trigger, StorageType.PERSISTED, null,
			DestinationNames.MESSAGE_BOARDS_MAILING_LIST, mailingListRequest,
			0);
>>>>>>> compatible
	}

	protected void unscheduleMailingList(MBMailingList mailingList)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.message.boards.service.impl." +
					"MBMailingListLocalServiceImpl");
	}

	protected void validate(
			String emailAddress, String inServerName, String inUserName,
			String outEmailAddress, boolean outCustom, String outServerName,
			String outUserName, boolean active)
		throws PortalException {

<<<<<<< HEAD
		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.message.boards.service.impl." +
					"MBMailingListLocalServiceImpl");
=======
		if (!active) {
			return;
		}

		if (!Validator.isEmailAddress(emailAddress)) {
			throw new MailingListEmailAddressException(emailAddress);
		}
		else if (Validator.isNull(inServerName)) {
			throw new MailingListInServerNameException(
				"In server name is null");
		}
		else if (Validator.isNull(inUserName)) {
			throw new MailingListInUserNameException("In user name is null");
		}
		else if (Validator.isNull(outEmailAddress)) {
			throw new MailingListOutEmailAddressException(
				"Out email address is null");
		}
		else if (outCustom) {
			if (Validator.isNull(outServerName)) {
				throw new MailingListOutServerNameException(
					"Out server name is null");
			}
			else if (Validator.isNull(outUserName)) {
				throw new MailingListOutUserNameException(
					"Out user name is null");
			}
		}
>>>>>>> compatible
	}

}