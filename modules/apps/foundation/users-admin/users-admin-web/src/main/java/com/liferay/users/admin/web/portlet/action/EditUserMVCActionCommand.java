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

package com.liferay.users.admin.web.portlet.action;

<<<<<<< HEAD
import com.liferay.announcements.kernel.model.AnnouncementsDelivery;
import com.liferay.announcements.kernel.service.AnnouncementsDeliveryLocalService;
import com.liferay.asset.kernel.exception.AssetCategoryException;
import com.liferay.asset.kernel.exception.AssetTagException;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.exception.CompanyMaxUsersException;
import com.liferay.portal.kernel.exception.ContactBirthdayException;
import com.liferay.portal.kernel.exception.ContactNameException;
import com.liferay.portal.kernel.exception.GroupFriendlyURLException;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
=======
import com.liferay.admin.kernel.util.PortalMyAccountApplicationType;
import com.liferay.announcements.kernel.model.AnnouncementsDelivery;
import com.liferay.announcements.kernel.model.AnnouncementsEntryConstants;
import com.liferay.announcements.kernel.service.AnnouncementsDeliveryLocalService;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.exception.AddressCityException;
import com.liferay.portal.kernel.exception.AddressStreetException;
import com.liferay.portal.kernel.exception.AddressZipException;
import com.liferay.portal.kernel.exception.CompanyMaxUsersException;
import com.liferay.portal.kernel.exception.ContactBirthdayException;
import com.liferay.portal.kernel.exception.ContactNameException;
import com.liferay.portal.kernel.exception.EmailAddressException;
import com.liferay.portal.kernel.exception.GroupFriendlyURLException;
import com.liferay.portal.kernel.exception.NoSuchCountryException;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.NoSuchRegionException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PhoneNumberException;
import com.liferay.portal.kernel.exception.PhoneNumberExtensionException;
>>>>>>> compatible
import com.liferay.portal.kernel.exception.RequiredUserException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.exception.UserFieldException;
import com.liferay.portal.kernel.exception.UserIdException;
<<<<<<< HEAD
import com.liferay.portal.kernel.exception.UserReminderQueryException;
import com.liferay.portal.kernel.exception.UserScreenNameException;
=======
import com.liferay.portal.kernel.exception.UserPasswordException;
import com.liferay.portal.kernel.exception.UserReminderQueryException;
import com.liferay.portal.kernel.exception.UserScreenNameException;
import com.liferay.portal.kernel.exception.UserSmsException;
import com.liferay.portal.kernel.exception.WebsiteURLException;
>>>>>>> compatible
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
<<<<<<< HEAD
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.Website;
import com.liferay.portal.kernel.portlet.DynamicActionRequest;
=======
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.model.Website;
import com.liferay.portal.kernel.portlet.DynamicActionRequest;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
>>>>>>> compatible
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.membershippolicy.MembershipPolicyException;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.security.permission.ActionKeys;
>>>>>>> compatible
import com.liferay.portal.kernel.service.ListTypeLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserService;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
>>>>>>> compatible
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.CharPool;
>>>>>>> compatible
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
<<<<<<< HEAD
import com.liferay.portlet.InvokerPortletImpl;
import com.liferay.portlet.admin.util.AdminUtil;
import com.liferay.users.admin.constants.UsersAdminPortletKeys;
=======
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.InvokerPortletImpl;
import com.liferay.portlet.admin.util.AdminUtil;
import com.liferay.portlet.announcements.model.impl.AnnouncementsDeliveryImpl;
import com.liferay.sites.kernel.util.SitesUtil;
import com.liferay.users.admin.constants.UsersAdminPortletKeys;
import com.liferay.users.admin.kernel.util.UsersAdmin;
import com.liferay.users.admin.kernel.util.UsersAdminUtil;
>>>>>>> compatible

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Julio Camarero
 * @author Wesley Gong
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + UsersAdminPortletKeys.MY_ORGANIZATIONS,
		"javax.portlet.name=" + UsersAdminPortletKeys.USERS_ADMIN,
		"mvc.command.name=/users_admin/edit_user"
	},
	service = MVCActionCommand.class
)
public class EditUserMVCActionCommand extends BaseMVCActionCommand {

	protected User addUser(ActionRequest actionRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

<<<<<<< HEAD
=======
		boolean autoPassword = ParamUtil.getBoolean(
			actionRequest, "autoPassword", true);
		String password1 = actionRequest.getParameter("password1");
		String password2 = actionRequest.getParameter("password2");

		String reminderQueryQuestion = ParamUtil.getString(
			actionRequest, "reminderQueryQuestion");

		if (reminderQueryQuestion.equals(UsersAdmin.CUSTOM_QUESTION)) {
			reminderQueryQuestion = ParamUtil.getString(
				actionRequest, "reminderQueryCustomQuestion");
		}

		String reminderQueryAnswer = ParamUtil.getString(
			actionRequest, "reminderQueryAnswer");
>>>>>>> compatible
		boolean autoScreenName = ParamUtil.getBoolean(
			actionRequest, "autoScreenName");
		String screenName = ParamUtil.getString(actionRequest, "screenName");
		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");
		long facebookId = 0;
<<<<<<< HEAD
		String languageId = ParamUtil.getString(actionRequest, "languageId");
=======
		String openId = ParamUtil.getString(actionRequest, "openId");
		String languageId = ParamUtil.getString(actionRequest, "languageId");
		String timeZoneId = ParamUtil.getString(actionRequest, "timeZoneId");
		String greeting = ParamUtil.getString(actionRequest, "greeting");
>>>>>>> compatible
		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String middleName = ParamUtil.getString(actionRequest, "middleName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		long prefixId = ParamUtil.getInteger(actionRequest, "prefixId");
		long suffixId = ParamUtil.getInteger(actionRequest, "suffixId");
		boolean male = ParamUtil.getBoolean(actionRequest, "male", true);
		int birthdayMonth = ParamUtil.getInteger(
			actionRequest, "birthdayMonth");
		int birthdayDay = ParamUtil.getInteger(actionRequest, "birthdayDay");
		int birthdayYear = ParamUtil.getInteger(actionRequest, "birthdayYear");
		String comments = ParamUtil.getString(actionRequest, "comments");
<<<<<<< HEAD
		String jobTitle = ParamUtil.getString(actionRequest, "jobTitle");
=======
		String smsSn = ParamUtil.getString(actionRequest, "smsSn");
		String facebookSn = ParamUtil.getString(actionRequest, "facebookSn");
		String jabberSn = ParamUtil.getString(actionRequest, "jabberSn");
		String skypeSn = ParamUtil.getString(actionRequest, "skypeSn");
		String twitterSn = ParamUtil.getString(actionRequest, "twitterSn");
		String jobTitle = ParamUtil.getString(actionRequest, "jobTitle");
		long[] groupIds = UsersAdminUtil.getGroupIds(actionRequest);
		long[] organizationIds = UsersAdminUtil.getOrganizationIds(
			actionRequest);
		long[] roleIds = UsersAdminUtil.getRoleIds(actionRequest);
		List<UserGroupRole> userGroupRoles = UsersAdminUtil.getUserGroupRoles(
			actionRequest);
		long[] userGroupIds = UsersAdminUtil.getUserGroupIds(actionRequest);
		List<Address> addresses = UsersAdminUtil.getAddresses(actionRequest);
		List<EmailAddress> emailAddresses = UsersAdminUtil.getEmailAddresses(
			actionRequest);
		List<Phone> phones = UsersAdminUtil.getPhones(actionRequest);
		List<Website> websites = UsersAdminUtil.getWebsites(actionRequest);
		List<AnnouncementsDelivery> announcementsDeliveries =
			getAnnouncementsDeliveries(actionRequest);
>>>>>>> compatible
		boolean sendEmail = true;

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			User.class.getName(), actionRequest);

		User user = _userService.addUser(
<<<<<<< HEAD
			themeDisplay.getCompanyId(), true, null, null, autoScreenName,
			screenName, emailAddress, facebookId, null,
			LocaleUtil.fromLanguageId(languageId), firstName, middleName,
			lastName, prefixId, suffixId, male, birthdayMonth, birthdayDay,
			birthdayYear, jobTitle, null, null, null, null,
			new ArrayList<Address>(), new ArrayList<EmailAddress>(),
			new ArrayList<Phone>(), new ArrayList<Website>(),
			new ArrayList<AnnouncementsDelivery>(), sendEmail, serviceContext);

		user = _userService.updateUser(
			user.getUserId(), StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, user.getPasswordReset(), null, null,
			user.getScreenName(), user.getEmailAddress(), facebookId,
			user.getOpenId(), true, null, languageId, user.getTimeZoneId(),
			user.getGreeting(), comments, firstName, middleName, lastName,
			prefixId, suffixId, male, birthdayMonth, birthdayDay, birthdayYear,
			null, null, null, null, null, jobTitle, null, null, null, null,
			null, null, null, null, null, null, serviceContext);
=======
			themeDisplay.getCompanyId(), autoPassword, password1, password2,
			autoScreenName, screenName, emailAddress, facebookId, openId,
			LocaleUtil.fromLanguageId(languageId), firstName, middleName,
			lastName, prefixId, suffixId, male, birthdayMonth, birthdayDay,
			birthdayYear, jobTitle, groupIds, organizationIds, roleIds,
			userGroupIds, addresses, emailAddresses, phones, websites,
			announcementsDeliveries, sendEmail, serviceContext);

		if (!userGroupRoles.isEmpty()) {
			for (UserGroupRole userGroupRole : userGroupRoles) {
				userGroupRole.setUserId(user.getUserId());
			}

			user = _userService.updateUser(
				user.getUserId(), StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, false, reminderQueryQuestion,
				reminderQueryAnswer, user.getScreenName(),
				user.getEmailAddress(), facebookId, openId, true, null,
				languageId, timeZoneId, greeting, comments, firstName,
				middleName, lastName, prefixId, suffixId, male, birthdayMonth,
				birthdayDay, birthdayYear, smsSn, facebookSn, jabberSn, skypeSn,
				twitterSn, jobTitle, groupIds, organizationIds, roleIds,
				userGroupRoles, userGroupIds, addresses, emailAddresses, phones,
				websites, announcementsDeliveries, serviceContext);
		}

		long publicLayoutSetPrototypeId = ParamUtil.getLong(
			actionRequest, "publicLayoutSetPrototypeId");
		long privateLayoutSetPrototypeId = ParamUtil.getLong(
			actionRequest, "privateLayoutSetPrototypeId");
		boolean publicLayoutSetPrototypeLinkEnabled = ParamUtil.getBoolean(
			actionRequest, "publicLayoutSetPrototypeLinkEnabled");
		boolean privateLayoutSetPrototypeLinkEnabled = ParamUtil.getBoolean(
			actionRequest, "privateLayoutSetPrototypeLinkEnabled");

		SitesUtil.updateLayoutSetPrototypesLinks(
			user.getGroup(), publicLayoutSetPrototypeId,
			privateLayoutSetPrototypeId, publicLayoutSetPrototypeLinkEnabled,
			privateLayoutSetPrototypeLinkEnabled);
>>>>>>> compatible

		return user;
	}

	protected void deleteRole(ActionRequest actionRequest) throws Exception {
		User user = portal.getSelectedUser(actionRequest);

		long roleId = ParamUtil.getLong(actionRequest, "roleId");

		_userService.deleteRoleUser(roleId, user.getUserId());
	}

	protected void deleteUsers(ActionRequest actionRequest) throws Exception {
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long[] deleteUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "deleteUserIds"), 0L);

		for (long deleteUserId : deleteUserIds) {
			if (cmd.equals(Constants.DEACTIVATE) ||
				cmd.equals(Constants.RESTORE)) {

				int status = WorkflowConstants.STATUS_APPROVED;

				if (cmd.equals(Constants.DEACTIVATE)) {
					status = WorkflowConstants.STATUS_INACTIVE;
				}

				_userService.updateStatus(
					deleteUserId, status, new ServiceContext());
			}
			else {
				_userService.deleteUser(deleteUserId);
			}
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		actionRequest = _wrapActionRequest(actionRequest);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			User user = null;
			String oldScreenName = StringPool.BLANK;
			boolean updateLanguageId = false;

			if (cmd.equals(Constants.ADD)) {
				user = addUser(actionRequest);
			}
			else if (cmd.equals(Constants.DEACTIVATE) ||
					 cmd.equals(Constants.DELETE) ||
					 cmd.equals(Constants.RESTORE)) {

				deleteUsers(actionRequest);
			}
			else if (cmd.equals("deleteRole")) {
				deleteRole(actionRequest);
			}
			else if (cmd.equals(Constants.UPDATE)) {
				Object[] returnValue = updateUser(
					actionRequest, actionResponse);

				user = (User)returnValue[0];
				oldScreenName = (String)returnValue[1];
				updateLanguageId = (Boolean)returnValue[2];
			}
			else if (cmd.equals("unlock")) {
				user = updateLockout(actionRequest);
			}

			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			if (user != null) {
				if (Validator.isNotNull(oldScreenName)) {

					// This will fix the redirect if the user is on his personal
					// my account page and changes his screen name. A redirect
					// that references the old screen name no longer points to a
					// valid screen name and therefore needs to be updated.

					Group group = user.getGroup();

					if (group.getGroupId() == themeDisplay.getScopeGroupId()) {
						Layout layout = themeDisplay.getLayout();

						String friendlyURLPath = group.getPathFriendlyURL(
							layout.isPrivateLayout(), themeDisplay);

						String oldPath =
							friendlyURLPath + StringPool.SLASH + oldScreenName;
						String newPath =
							friendlyURLPath + StringPool.SLASH +
								user.getScreenName();

						redirect = StringUtil.replace(
							redirect, oldPath, newPath);

						redirect = StringUtil.replace(
							redirect, URLCodec.encodeURL(oldPath),
							URLCodec.encodeURL(newPath));
					}
				}

				if (updateLanguageId && themeDisplay.isI18n()) {
					String i18nLanguageId = user.getLanguageId();

					int pos = i18nLanguageId.indexOf(CharPool.UNDERLINE);

					if (pos != -1) {
						i18nLanguageId = i18nLanguageId.substring(0, pos);
					}

					String i18nPath = StringPool.SLASH + i18nLanguageId;

					redirect = StringUtil.replace(
						redirect, themeDisplay.getI18nPath(), i18nPath);
				}

				redirect = http.setParameter(
					redirect, actionResponse.getNamespace() + "p_u_i_d",
					user.getUserId());
			}

			Group scopeGroup = themeDisplay.getScopeGroup();

			if (scopeGroup.isUser() &&
				(userLocalService.fetchUserById(scopeGroup.getClassPK()) ==
					null)) {

				redirect = http.setParameter(redirect, "doAsGroupId", 0);
				redirect = http.setParameter(redirect, "refererPlid", 0);
			}

			sendRedirect(actionRequest, actionResponse, redirect);
		}
		catch (Exception e) {
			String mvcPath = "/edit_user.jsp";

			if (e instanceof NoSuchUserException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				mvcPath = "/error.jsp";
			}
<<<<<<< HEAD
			else if (e instanceof AssetCategoryException ||
					 e instanceof AssetTagException ||
					 e instanceof CompanyMaxUsersException ||
					 e instanceof ContactBirthdayException ||
					 e instanceof ContactNameException ||
					 e instanceof GroupFriendlyURLException ||
					 e instanceof MembershipPolicyException ||
					 e instanceof NoSuchListTypeException ||
=======
			else if (e instanceof AddressCityException ||
					 e instanceof AddressStreetException ||
					 e instanceof AddressZipException ||
					 e instanceof CompanyMaxUsersException ||
					 e instanceof ContactBirthdayException ||
					 e instanceof ContactNameException ||
					 e instanceof EmailAddressException ||
					 e instanceof GroupFriendlyURLException ||
					 e instanceof MembershipPolicyException ||
					 e instanceof NoSuchCountryException ||
					 e instanceof NoSuchListTypeException ||
					 e instanceof NoSuchRegionException ||
					 e instanceof PhoneNumberException ||
					 e instanceof PhoneNumberExtensionException ||
>>>>>>> compatible
					 e instanceof RequiredUserException ||
					 e instanceof UserEmailAddressException ||
					 e instanceof UserFieldException ||
					 e instanceof UserIdException ||
<<<<<<< HEAD
					 e instanceof UserReminderQueryException ||
					 e instanceof UserScreenNameException) {
=======
					 e instanceof UserPasswordException ||
					 e instanceof UserReminderQueryException ||
					 e instanceof UserScreenNameException ||
					 e instanceof UserSmsException ||
					 e instanceof WebsiteURLException) {
>>>>>>> compatible

				if (e instanceof NoSuchListTypeException) {
					NoSuchListTypeException nslte = (NoSuchListTypeException)e;

					Class<?> clazz = e.getClass();

					SessionErrors.add(
						actionRequest, clazz.getName() + nslte.getType());
				}
				else {
					SessionErrors.add(actionRequest, e.getClass(), e);
				}

<<<<<<< HEAD
				if (e instanceof CompanyMaxUsersException ||
					e instanceof RequiredUserException) {
=======
				String password1 = actionRequest.getParameter("password1");
				String password2 = actionRequest.getParameter("password2");

				boolean submittedPassword = false;

				if (!Validator.isBlank(password1) ||
					!Validator.isBlank(password2)) {

					submittedPassword = true;
				}

				if (e instanceof CompanyMaxUsersException ||
					e instanceof RequiredUserException || submittedPassword) {
>>>>>>> compatible

					String redirect = portal.escapeRedirect(
						ParamUtil.getString(actionRequest, "redirect"));

<<<<<<< HEAD
=======
					if (submittedPassword) {
						User user = portal.getSelectedUser(actionRequest);

						redirect = http.setParameter(
							redirect, actionResponse.getNamespace() + "p_u_i_d",
							user.getUserId());
					}

>>>>>>> compatible
					if (Validator.isNotNull(redirect)) {
						sendRedirect(actionRequest, actionResponse, redirect);

						return;
					}
				}
			}
			else {
				throw e;
			}

			actionResponse.setRenderParameter("mvcPath", mvcPath);
		}
	}

<<<<<<< HEAD
	/**
	* @deprecated As of 2.5.0, with no direct replacement
	*/
	@Deprecated
	protected List<AnnouncementsDelivery> getAnnouncementsDeliveries(
		ActionRequest actionRequest) {

		return null;
	}

	/**
	* @deprecated As of 2.5.0, with no direct replacement
	*/
	@Deprecated
=======
	protected List<AnnouncementsDelivery> getAnnouncementsDeliveries(
		ActionRequest actionRequest) {

		List<AnnouncementsDelivery> announcementsDeliveries = new ArrayList<>();

		for (String type : AnnouncementsEntryConstants.TYPES) {
			boolean email = ParamUtil.getBoolean(
				actionRequest, "announcementsType" + type + "Email");
			boolean sms = ParamUtil.getBoolean(
				actionRequest, "announcementsType" + type + "Sms");
			boolean website = ParamUtil.getBoolean(
				actionRequest, "announcementsType" + type + "Website");

			AnnouncementsDelivery announcementsDelivery =
				new AnnouncementsDeliveryImpl();

			announcementsDelivery.setType(type);
			announcementsDelivery.setEmail(email);
			announcementsDelivery.setSms(sms);
			announcementsDelivery.setWebsite(website);

			announcementsDeliveries.add(announcementsDelivery);
		}

		return announcementsDeliveries;
	}

>>>>>>> compatible
	protected List<AnnouncementsDelivery> getAnnouncementsDeliveries(
			ActionRequest actionRequest, User user)
		throws Exception {

<<<<<<< HEAD
		return null;
=======
		if (actionRequest.getParameter(
				"announcementsType" + AnnouncementsEntryConstants.TYPES[0] +
					"Email") == null) {

			return _announcementsDeliveryLocalService.getUserDeliveries(
				user.getUserId());
		}

		return getAnnouncementsDeliveries(actionRequest);
>>>>>>> compatible
	}

	protected long getListTypeId(
			PortletRequest portletRequest, String parameterName, String type)
		throws Exception {

		String parameterValue = ParamUtil.getString(
			portletRequest, parameterName);

		ListType listType = _listTypeLocalService.addListType(
			parameterValue, type);

		return listType.getListTypeId();
	}

<<<<<<< HEAD
	/**
	* @deprecated As of 2.5.0, with no direct replacement
	*/
	@Deprecated
	protected void setAnnouncementsDeliveryLocalService(
		AnnouncementsDeliveryLocalService announcementsDeliveryLocalService) {
=======
	@Reference(unbind = "-")
	protected void setAnnouncementsDeliveryLocalService(
		AnnouncementsDeliveryLocalService announcementsDeliveryLocalService) {

		_announcementsDeliveryLocalService = announcementsDeliveryLocalService;
>>>>>>> compatible
	}

	@Reference(unbind = "-")
	protected void setDLAppLocalService(DLAppLocalService dlAppLocalService) {
		_dlAppLocalService = dlAppLocalService;
	}

	@Reference(unbind = "-")
	protected void setListTypeLocalService(
		ListTypeLocalService listTypeLocalService) {

		_listTypeLocalService = listTypeLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserService(UserService userService) {
		_userService = userService;
	}

	protected User updateLockout(ActionRequest actionRequest) throws Exception {
		User user = portal.getSelectedUser(actionRequest);

		_userService.updateLockoutById(user.getUserId(), false);

		return user;
	}

	protected Object[] updateUser(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = portal.getSelectedUser(actionRequest);

		Contact contact = user.getContact();

		String oldPassword = AdminUtil.getUpdateUserPassword(
			actionRequest, user.getUserId());

<<<<<<< HEAD
=======
		String newPassword1 = actionRequest.getParameter("password1");
		String newPassword2 = actionRequest.getParameter("password2");

		boolean passwordReset = false;

		PasswordPolicy passwordPolicy = user.getPasswordPolicy();

		if ((user.getLastLoginDate() == null) &&
			((passwordPolicy == null) ||
			 (passwordPolicy.isChangeable() &&
			  passwordPolicy.isChangeRequired()))) {

			passwordReset = true;
		}
		else {
			passwordReset = ParamUtil.getBoolean(
				actionRequest, "passwordReset");
		}

		String reminderQueryQuestion = BeanParamUtil.getString(
			user, actionRequest, "reminderQueryQuestion");

		if (reminderQueryQuestion.equals(UsersAdmin.CUSTOM_QUESTION)) {
			reminderQueryQuestion = BeanParamUtil.getStringSilent(
				user, actionRequest, "reminderQueryCustomQuestion");
		}

		String reminderQueryAnswer = BeanParamUtil.getString(
			user, actionRequest, "reminderQueryAnswer");
>>>>>>> compatible
		String oldScreenName = user.getScreenName();
		String screenName = BeanParamUtil.getString(
			user, actionRequest, "screenName");
		String oldEmailAddress = user.getEmailAddress();
		String emailAddress = BeanParamUtil.getString(
			user, actionRequest, "emailAddress");
		long facebookId = user.getFacebookId();
<<<<<<< HEAD

=======
		String openId = BeanParamUtil.getString(user, actionRequest, "openId");
>>>>>>> compatible
		boolean deleteLogo = ParamUtil.getBoolean(actionRequest, "deleteLogo");

		byte[] portraitBytes = null;

		long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

		if (fileEntryId > 0) {
			FileEntry fileEntry = _dlAppLocalService.getFileEntry(fileEntryId);

			portraitBytes = FileUtil.getBytes(fileEntry.getContentStream());
		}

		String languageId = BeanParamUtil.getString(
			user, actionRequest, "languageId");
<<<<<<< HEAD
=======
		String timeZoneId = BeanParamUtil.getString(
			user, actionRequest, "timeZoneId");
		String greeting = BeanParamUtil.getString(
			user, actionRequest, "greeting");
>>>>>>> compatible
		String firstName = BeanParamUtil.getString(
			user, actionRequest, "firstName");
		String middleName = BeanParamUtil.getString(
			user, actionRequest, "middleName");
		String lastName = BeanParamUtil.getString(
			user, actionRequest, "lastName");
		long prefixId = BeanParamUtil.getInteger(
			contact, actionRequest, "prefixId");
		long suffixId = BeanParamUtil.getInteger(
			contact, actionRequest, "suffixId");
		boolean male = BeanParamUtil.getBoolean(
			user, actionRequest, "male", true);

		Calendar birthdayCal = CalendarFactoryUtil.getCalendar();

		birthdayCal.setTime(contact.getBirthday());

		int birthdayMonth = ParamUtil.getInteger(
			actionRequest, "birthdayMonth", birthdayCal.get(Calendar.MONTH));
		int birthdayDay = ParamUtil.getInteger(
			actionRequest, "birthdayDay", birthdayCal.get(Calendar.DATE));
		int birthdayYear = ParamUtil.getInteger(
			actionRequest, "birthdayYear", birthdayCal.get(Calendar.YEAR));

		String comments = BeanParamUtil.getString(
			user, actionRequest, "comments");
<<<<<<< HEAD
		String jobTitle = BeanParamUtil.getString(
			user, actionRequest, "jobTitle");
=======
		String smsSn = BeanParamUtil.getString(contact, actionRequest, "smsSn");
		String facebookSn = BeanParamUtil.getString(
			contact, actionRequest, "facebookSn");
		String jabberSn = BeanParamUtil.getString(
			contact, actionRequest, "jabberSn");
		String skypeSn = BeanParamUtil.getString(
			contact, actionRequest, "skypeSn");
		String twitterSn = BeanParamUtil.getString(
			contact, actionRequest, "twitterSn");
		String jobTitle = BeanParamUtil.getString(
			user, actionRequest, "jobTitle");
		long[] groupIds = UsersAdminUtil.getGroupIds(actionRequest);
		long[] organizationIds = UsersAdminUtil.getOrganizationIds(
			actionRequest);
		long[] roleIds = UsersAdminUtil.getRoleIds(actionRequest);

		List<UserGroupRole> userGroupRoles = null;

		if ((actionRequest.getParameter("addGroupRolesGroupIds") != null) ||
			(actionRequest.getParameter("addGroupRolesRoleIds") != null) ||
			(actionRequest.getParameter("deleteGroupRolesGroupIds") != null) ||
			(actionRequest.getParameter("deleteGroupRolesRoleIds") != null)) {

			userGroupRoles = UsersAdminUtil.getUserGroupRoles(actionRequest);
		}

		long[] userGroupIds = UsersAdminUtil.getUserGroupIds(actionRequest);
		List<Address> addresses = UsersAdminUtil.getAddresses(
			actionRequest, user.getAddresses());
		List<EmailAddress> emailAddresses = UsersAdminUtil.getEmailAddresses(
			actionRequest, user.getEmailAddresses());
		List<Phone> phones = UsersAdminUtil.getPhones(
			actionRequest, user.getPhones());
		List<Website> websites = UsersAdminUtil.getWebsites(
			actionRequest, user.getWebsites());
		List<AnnouncementsDelivery> announcementsDeliveries =
			getAnnouncementsDeliveries(actionRequest, user);
>>>>>>> compatible

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			User.class.getName(), actionRequest);

		user = _userService.updateUser(
<<<<<<< HEAD
			user.getUserId(), oldPassword, null, null, user.getPasswordReset(),
			null, null, screenName, emailAddress, facebookId, user.getOpenId(),
			!deleteLogo, portraitBytes, languageId, user.getTimeZoneId(),
			user.getGreeting(), comments, firstName, middleName, lastName,
			prefixId, suffixId, male, birthdayMonth, birthdayDay, birthdayYear,
			null, null, null, null, null, jobTitle, null, null, null, null,
			null, null, null, null, null, null, serviceContext);
=======
			user.getUserId(), oldPassword, newPassword1, newPassword2,
			passwordReset, reminderQueryQuestion, reminderQueryAnswer,
			screenName, emailAddress, facebookId, openId, !deleteLogo,
			portraitBytes, languageId, timeZoneId, greeting, comments,
			firstName, middleName, lastName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, smsSn, facebookSn,
			jabberSn, skypeSn, twitterSn, jobTitle, groupIds, organizationIds,
			roleIds, userGroupRoles, userGroupIds, addresses, emailAddresses,
			phones, websites, announcementsDeliveries, serviceContext);
>>>>>>> compatible

		if (oldScreenName.equals(user.getScreenName())) {
			oldScreenName = StringPool.BLANK;
		}

		boolean updateLanguageId = false;

		if (user.getUserId() == themeDisplay.getUserId()) {

			// Reset the locale

<<<<<<< HEAD
			HttpServletRequest request = portal.getOriginalServletRequest(
				portal.getHttpServletRequest(actionRequest));
=======
			HttpServletRequest request = portal.getHttpServletRequest(
				actionRequest);
>>>>>>> compatible
			HttpServletResponse response = portal.getHttpServletResponse(
				actionResponse);
			HttpSession session = request.getSession();

			session.removeAttribute(Globals.LOCALE_KEY);

			Locale locale = LocaleUtil.fromLanguageId(languageId);

			LanguageUtil.updateCookie(request, response, locale);

			// Clear cached portlet responses

			PortletSession portletSession = actionRequest.getPortletSession();

			InvokerPortletImpl.clearResponses(portletSession);

<<<<<<< HEAD
			updateLanguageId = true;
		}

=======
			// Password

			if (PropsValues.SESSION_STORE_PASSWORD &&
				Validator.isNotNull(newPassword1)) {

				portletSession.setAttribute(
					WebKeys.USER_PASSWORD, newPassword1,
					PortletSession.APPLICATION_SCOPE);
			}

			updateLanguageId = true;
		}

		String portletId = serviceContext.getPortletId();
		String myAccountPortletId = PortletProviderUtil.getPortletId(
			PortalMyAccountApplicationType.MyAccount.CLASS_NAME,
			PortletProvider.Action.VIEW);

		Group group = user.getGroup();

		if (!portletId.equals(myAccountPortletId) &&
			GroupPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), group.getGroupId(),
				ActionKeys.UPDATE)) {

			long publicLayoutSetPrototypeId = ParamUtil.getLong(
				actionRequest, "publicLayoutSetPrototypeId");
			long privateLayoutSetPrototypeId = ParamUtil.getLong(
				actionRequest, "privateLayoutSetPrototypeId");
			boolean publicLayoutSetPrototypeLinkEnabled = ParamUtil.getBoolean(
				actionRequest, "publicLayoutSetPrototypeLinkEnabled");
			boolean privateLayoutSetPrototypeLinkEnabled = ParamUtil.getBoolean(
				actionRequest, "privateLayoutSetPrototypeLinkEnabled");

			LayoutSet publicLayoutSet = group.getPublicLayoutSet();
			LayoutSet privateLayoutSet = group.getPrivateLayoutSet();

			if ((publicLayoutSetPrototypeId > 0) ||
				(privateLayoutSetPrototypeId > 0) ||
				(publicLayoutSetPrototypeLinkEnabled !=
					publicLayoutSet.isLayoutSetPrototypeLinkEnabled()) ||
				(privateLayoutSetPrototypeLinkEnabled !=
					privateLayoutSet.isLayoutSetPrototypeLinkEnabled())) {

				SitesUtil.updateLayoutSetPrototypesLinks(
					group, publicLayoutSetPrototypeId,
					privateLayoutSetPrototypeId,
					publicLayoutSetPrototypeLinkEnabled,
					privateLayoutSetPrototypeLinkEnabled);
			}
		}

>>>>>>> compatible
		Company company = portal.getCompany(actionRequest);

		if (company.isStrangersVerify() &&
			!StringUtil.equalsIgnoreCase(oldEmailAddress, emailAddress)) {

			SessionMessages.add(actionRequest, "verificationEmailSent");
		}

		return new Object[] {user, oldScreenName, updateLanguageId};
	}

	@Reference
	protected Http http;

	@Reference
	protected Portal portal;

	protected UserLocalService userLocalService;

	private ActionRequest _wrapActionRequest(ActionRequest actionRequest)
		throws Exception {

		DynamicActionRequest dynamicActionRequest = new DynamicActionRequest(
			actionRequest);

		long prefixId = getListTypeId(
			actionRequest, "prefixValue", ListTypeConstants.CONTACT_PREFIX);

		dynamicActionRequest.setParameter("prefixId", String.valueOf(prefixId));

		long suffixId = getListTypeId(
			actionRequest, "suffixValue", ListTypeConstants.CONTACT_SUFFIX);

		dynamicActionRequest.setParameter("suffixId", String.valueOf(suffixId));

		return dynamicActionRequest;
	}

<<<<<<< HEAD
=======
	private AnnouncementsDeliveryLocalService
		_announcementsDeliveryLocalService;
>>>>>>> compatible
	private DLAppLocalService _dlAppLocalService;
	private ListTypeLocalService _listTypeLocalService;
	private UserService _userService;

}