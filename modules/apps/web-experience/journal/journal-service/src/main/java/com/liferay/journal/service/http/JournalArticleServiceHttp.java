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

package com.liferay.journal.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.journal.service.JournalArticleServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link JournalArticleServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleServiceSoap
 * @see HttpPrincipal
 * @see JournalArticleServiceUtil
 * @generated
 */
@ProviderType
public class JournalArticleServiceHttp {
	public static com.liferay.journal.model.JournalArticle addArticle(
		HttpPrincipal httpPrincipal, long groupId, long folderId,
		long classNameId, long classPK, java.lang.String articleId,
		boolean autoArticleId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
<<<<<<< HEAD
		java.util.Map<java.util.Locale, java.lang.String> friendlyURLMap,
=======
>>>>>>> compatible
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, java.lang.String layoutUuid,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire, int reviewDateMonth,
		int reviewDateDay, int reviewDateYear, int reviewDateHour,
		int reviewDateMinute, boolean neverReview, boolean indexable,
		boolean smallImage, java.lang.String smallImageURL,
		java.io.File smallFile, java.util.Map<java.lang.String, byte[]> images,
		java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"addArticle", _addArticleParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, classNameId, classPK, articleId, autoArticleId,
<<<<<<< HEAD
					titleMap, descriptionMap, friendlyURLMap, content,
					ddmStructureKey, ddmTemplateKey, layoutUuid,
					displayDateMonth, displayDateDay, displayDateYear,
					displayDateHour, displayDateMinute, expirationDateMonth,
					expirationDateDay, expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire, reviewDateMonth,
					reviewDateDay, reviewDateYear, reviewDateHour,
					reviewDateMinute, neverReview, indexable, smallImage,
					smallImageURL, smallFile, images, articleURL, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle addArticle(
		HttpPrincipal httpPrincipal, long groupId, long folderId,
		long classNameId, long classPK, java.lang.String articleId,
		boolean autoArticleId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, java.lang.String layoutUuid,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire, int reviewDateMonth,
		int reviewDateDay, int reviewDateYear, int reviewDateHour,
		int reviewDateMinute, boolean neverReview, boolean indexable,
		boolean smallImage, java.lang.String smallImageURL,
		java.io.File smallFile, java.util.Map<java.lang.String, byte[]> images,
		java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"addArticle", _addArticleParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, classNameId, classPK, articleId, autoArticleId,
=======
>>>>>>> compatible
					titleMap, descriptionMap, content, ddmStructureKey,
					ddmTemplateKey, layoutUuid, displayDateMonth,
					displayDateDay, displayDateYear, displayDateHour,
					displayDateMinute, expirationDateMonth, expirationDateDay,
					expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire, reviewDateMonth,
					reviewDateDay, reviewDateYear, reviewDateHour,
					reviewDateMinute, neverReview, indexable, smallImage,
					smallImageURL, smallFile, images, articleURL, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle addArticle(
		HttpPrincipal httpPrincipal, long groupId, long folderId,
		long classNameId, long classPK, java.lang.String articleId,
		boolean autoArticleId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, java.lang.String layoutUuid,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire, int reviewDateMonth,
		int reviewDateDay, int reviewDateYear, int reviewDateHour,
		int reviewDateMinute, boolean neverReview, boolean indexable,
		java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"addArticle", _addArticleParameterTypes2);
=======
					"addArticle", _addArticleParameterTypes1);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, classNameId, classPK, articleId, autoArticleId,
					titleMap, descriptionMap, content, ddmStructureKey,
					ddmTemplateKey, layoutUuid, displayDateMonth,
					displayDateDay, displayDateYear, displayDateHour,
					displayDateMinute, expirationDateMonth, expirationDateDay,
					expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire, reviewDateMonth,
					reviewDateDay, reviewDateYear, reviewDateHour,
					reviewDateMinute, neverReview, indexable, articleURL,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle copyArticle(
		HttpPrincipal httpPrincipal, long groupId,
		java.lang.String oldArticleId, java.lang.String newArticleId,
		boolean autoArticleId, double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"copyArticle", _copyArticleParameterTypes3);
=======
					"copyArticle", _copyArticleParameterTypes2);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					oldArticleId, newArticleId, autoArticleId, version);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteArticle(HttpPrincipal httpPrincipal, long groupId,
		java.lang.String articleId, double version,
		java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"deleteArticle", _deleteArticleParameterTypes4);
=======
					"deleteArticle", _deleteArticleParameterTypes3);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, version, articleURL, serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteArticle(HttpPrincipal httpPrincipal, long groupId,
		java.lang.String articleId, java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"deleteArticle", _deleteArticleParameterTypes5);
=======
					"deleteArticle", _deleteArticleParameterTypes4);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, articleURL, serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle expireArticle(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId,
		double version, java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"expireArticle", _expireArticleParameterTypes6);
=======
					"expireArticle", _expireArticleParameterTypes5);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, version, articleURL, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void expireArticle(HttpPrincipal httpPrincipal, long groupId,
		java.lang.String articleId, java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"expireArticle", _expireArticleParameterTypes7);
=======
					"expireArticle", _expireArticleParameterTypes6);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, articleURL, serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle fetchArticle(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"fetchArticle", _fetchArticleParameterTypes8);
=======
					"fetchArticle", _fetchArticleParameterTypes7);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle getArticle(
		HttpPrincipal httpPrincipal, long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getArticle", _getArticleParameterTypes9);
=======
					"getArticle", _getArticleParameterTypes8);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, id);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle getArticle(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getArticle", _getArticleParameterTypes10);
=======
					"getArticle", _getArticleParameterTypes9);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle getArticle(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId,
		double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getArticle", _getArticleParameterTypes11);
=======
					"getArticle", _getArticleParameterTypes10);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, version);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle getArticle(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getArticle", _getArticleParameterTypes12);
=======
					"getArticle", _getArticleParameterTypes11);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					className, classPK);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle getArticleByUrlTitle(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getArticleByUrlTitle",
<<<<<<< HEAD
					_getArticleByUrlTitleParameterTypes13);
=======
					_getArticleByUrlTitleParameterTypes12);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					urlTitle);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String getArticleContent(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId,
		double version, java.lang.String languageId,
		com.liferay.portal.kernel.portlet.PortletRequestModel portletRequestModel,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getArticleContent", _getArticleContentParameterTypes14);
=======
					"getArticleContent", _getArticleContentParameterTypes13);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, version, languageId, portletRequestModel,
					themeDisplay);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String getArticleContent(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId,
		double version, java.lang.String languageId,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getArticleContent", _getArticleContentParameterTypes15);
=======
					"getArticleContent", _getArticleContentParameterTypes14);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, version, languageId, themeDisplay);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String getArticleContent(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId,
		java.lang.String languageId,
		com.liferay.portal.kernel.portlet.PortletRequestModel portletRequestModel,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getArticleContent", _getArticleContentParameterTypes16);
=======
					"getArticleContent", _getArticleContentParameterTypes15);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, languageId, portletRequestModel, themeDisplay);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String getArticleContent(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId,
		java.lang.String languageId,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getArticleContent", _getArticleContentParameterTypes17);
=======
					"getArticleContent", _getArticleContentParameterTypes16);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, languageId, themeDisplay);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle> getArticles(
		HttpPrincipal httpPrincipal, long groupId, long folderId) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getArticles", _getArticlesParameterTypes18);
=======
					"getArticles", _getArticlesParameterTypes17);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle> getArticles(
		HttpPrincipal httpPrincipal, long groupId, long folderId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getArticles", _getArticlesParameterTypes19);
=======
					"getArticles", _getArticlesParameterTypes18);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle> getArticlesByArticleId(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getArticlesByArticleId",
<<<<<<< HEAD
					_getArticlesByArticleIdParameterTypes20);
=======
					_getArticlesByArticleIdParameterTypes19);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle> getArticlesByLayoutUuid(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String layoutUuid) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getArticlesByLayoutUuid",
<<<<<<< HEAD
					_getArticlesByLayoutUuidParameterTypes21);
=======
					_getArticlesByLayoutUuidParameterTypes20);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					layoutUuid);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle> getArticlesByStructureId(
		HttpPrincipal httpPrincipal, long groupId, long classNameId,
		java.lang.String ddmStructureKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getArticlesByStructureId",
<<<<<<< HEAD
					_getArticlesByStructureIdParameterTypes22);
=======
					_getArticlesByStructureIdParameterTypes21);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					classNameId, ddmStructureKey, status, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle> getArticlesByStructureId(
		HttpPrincipal httpPrincipal, long groupId,
		java.lang.String ddmStructureKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getArticlesByStructureId",
<<<<<<< HEAD
					_getArticlesByStructureIdParameterTypes23);
=======
					_getArticlesByStructureIdParameterTypes22);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					ddmStructureKey, status, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle> getArticlesByStructureId(
		HttpPrincipal httpPrincipal, long groupId,
		java.lang.String ddmStructureKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getArticlesByStructureId",
<<<<<<< HEAD
					_getArticlesByStructureIdParameterTypes24);
=======
					_getArticlesByStructureIdParameterTypes23);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					ddmStructureKey, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, long folderId) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getArticlesCount", _getArticlesCountParameterTypes25);
=======
					"getArticlesCount", _getArticlesCountParameterTypes24);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, long folderId, int status) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getArticlesCount", _getArticlesCountParameterTypes26);
=======
					"getArticlesCount", _getArticlesCountParameterTypes25);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getArticlesCountByArticleId(HttpPrincipal httpPrincipal,
		long groupId, java.lang.String articleId) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getArticlesCountByArticleId",
<<<<<<< HEAD
					_getArticlesCountByArticleIdParameterTypes27);
=======
					_getArticlesCountByArticleIdParameterTypes26);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getArticlesCountByStructureId(
		HttpPrincipal httpPrincipal, long groupId, long classNameId,
		java.lang.String ddmStructureKey, int status) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getArticlesCountByStructureId",
<<<<<<< HEAD
					_getArticlesCountByStructureIdParameterTypes28);
=======
					_getArticlesCountByStructureIdParameterTypes27);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					classNameId, ddmStructureKey, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getArticlesCountByStructureId(
		HttpPrincipal httpPrincipal, long groupId,
		java.lang.String ddmStructureKey) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getArticlesCountByStructureId",
<<<<<<< HEAD
					_getArticlesCountByStructureIdParameterTypes29);
=======
					_getArticlesCountByStructureIdParameterTypes28);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					ddmStructureKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getArticlesCountByStructureId(
		HttpPrincipal httpPrincipal, long groupId,
		java.lang.String ddmStructureKey, int status) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getArticlesCountByStructureId",
<<<<<<< HEAD
					_getArticlesCountByStructureIdParameterTypes30);
=======
					_getArticlesCountByStructureIdParameterTypes29);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					ddmStructureKey, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle getDisplayArticleByUrlTitle(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getDisplayArticleByUrlTitle",
<<<<<<< HEAD
					_getDisplayArticleByUrlTitleParameterTypes31);
=======
					_getDisplayArticleByUrlTitleParameterTypes30);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					urlTitle);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getFoldersAndArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, java.util.List<java.lang.Long> folderIds) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getFoldersAndArticlesCount",
<<<<<<< HEAD
					_getFoldersAndArticlesCountParameterTypes32);
=======
					_getFoldersAndArticlesCountParameterTypes31);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderIds);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle> getGroupArticles(
		HttpPrincipal httpPrincipal, long groupId, long userId,
		long rootFolderId, int status, boolean includeOwner, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getGroupArticles", _getGroupArticlesParameterTypes33);
=======
					"getGroupArticles", _getGroupArticlesParameterTypes32);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, rootFolderId, status, includeOwner, start, end,
					orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle> getGroupArticles(
		HttpPrincipal httpPrincipal, long groupId, long userId,
		long rootFolderId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getGroupArticles", _getGroupArticlesParameterTypes34);
=======
					"getGroupArticles", _getGroupArticlesParameterTypes33);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, rootFolderId, status, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle> getGroupArticles(
		HttpPrincipal httpPrincipal, long groupId, long userId,
		long rootFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getGroupArticles", _getGroupArticlesParameterTypes35);
=======
					"getGroupArticles", _getGroupArticlesParameterTypes34);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, rootFolderId, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getGroupArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, long userId, long rootFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getGroupArticlesCount",
<<<<<<< HEAD
					_getGroupArticlesCountParameterTypes36);
=======
					_getGroupArticlesCountParameterTypes35);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, rootFolderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getGroupArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, long userId, long rootFolderId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getGroupArticlesCount",
<<<<<<< HEAD
					_getGroupArticlesCountParameterTypes37);
=======
					_getGroupArticlesCountParameterTypes36);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, rootFolderId, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getGroupArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, long userId, long rootFolderId, int status,
		boolean includeOwner)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"getGroupArticlesCount",
<<<<<<< HEAD
					_getGroupArticlesCountParameterTypes38);
=======
					_getGroupArticlesCountParameterTypes37);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, rootFolderId, status, includeOwner);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle getLatestArticle(
		HttpPrincipal httpPrincipal, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getLatestArticle", _getLatestArticleParameterTypes39);
=======
					"getLatestArticle", _getLatestArticleParameterTypes38);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey,
					resourcePrimKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle getLatestArticle(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getLatestArticle", _getLatestArticleParameterTypes40);
=======
					"getLatestArticle", _getLatestArticleParameterTypes39);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle getLatestArticle(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getLatestArticle", _getLatestArticleParameterTypes41);
=======
					"getLatestArticle", _getLatestArticleParameterTypes40);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					className, classPK);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle> getLayoutArticles(
		HttpPrincipal httpPrincipal, long groupId) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"getLayoutArticles", _getLayoutArticlesParameterTypes42);
=======
					"getLayoutArticles", _getLayoutArticlesParameterTypes41);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void moveArticle(HttpPrincipal httpPrincipal, long groupId,
		java.lang.String articleId, long newFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"moveArticle", _moveArticleParameterTypes43);
=======
					"moveArticle", _moveArticleParameterTypes42);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, newFolderId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void moveArticle(HttpPrincipal httpPrincipal, long groupId,
		java.lang.String articleId, long newFolderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"moveArticle", _moveArticleParameterTypes44);
=======
					"moveArticle", _moveArticleParameterTypes43);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, newFolderId, serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle moveArticleFromTrash(
		HttpPrincipal httpPrincipal, long groupId, long resourcePrimKey,
		long newFolderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"moveArticleFromTrash",
<<<<<<< HEAD
					_moveArticleFromTrashParameterTypes45);
=======
					_moveArticleFromTrashParameterTypes44);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					resourcePrimKey, newFolderId, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle moveArticleFromTrash(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId,
		long newFolderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"moveArticleFromTrash",
<<<<<<< HEAD
					_moveArticleFromTrashParameterTypes46);
=======
					_moveArticleFromTrashParameterTypes45);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, newFolderId, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle moveArticleToTrash(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"moveArticleToTrash", _moveArticleToTrashParameterTypes47);
=======
					"moveArticleToTrash", _moveArticleToTrashParameterTypes46);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void removeArticleLocale(HttpPrincipal httpPrincipal,
		long companyId, java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"removeArticleLocale", _removeArticleLocaleParameterTypes48);
=======
					"removeArticleLocale", _removeArticleLocaleParameterTypes47);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, languageId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle removeArticleLocale(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId,
		double version, java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"removeArticleLocale", _removeArticleLocaleParameterTypes49);
=======
					"removeArticleLocale", _removeArticleLocaleParameterTypes48);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, version, languageId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void restoreArticleFromTrash(HttpPrincipal httpPrincipal,
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"restoreArticleFromTrash",
<<<<<<< HEAD
					_restoreArticleFromTrashParameterTypes50);
=======
					_restoreArticleFromTrashParameterTypes49);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey,
					resourcePrimKey);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void restoreArticleFromTrash(HttpPrincipal httpPrincipal,
		long groupId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"restoreArticleFromTrash",
<<<<<<< HEAD
					_restoreArticleFromTrashParameterTypes51);
=======
					_restoreArticleFromTrashParameterTypes50);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.search.Hits search(
		HttpPrincipal httpPrincipal, long groupId, long creatorUserId,
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"search", _searchParameterTypes52);
=======
					"search", _searchParameterTypes51);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					creatorUserId, status, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.search.Hits)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle> search(
		HttpPrincipal httpPrincipal, long companyId, long groupId,
		java.util.List<java.lang.Long> folderIds, long classNameId,
		java.lang.String keywords, java.lang.Double version,
		java.lang.String ddmStructureKey, java.lang.String ddmTemplateKey,
		java.util.Date displayDateGT, java.util.Date displayDateLT, int status,
		java.util.Date reviewDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"search", _searchParameterTypes53);
=======
					"search", _searchParameterTypes52);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupId, folderIds, classNameId, keywords,
					version, ddmStructureKey, ddmTemplateKey, displayDateGT,
					displayDateLT, status, reviewDate, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle> search(
		HttpPrincipal httpPrincipal, long companyId, long groupId,
		java.util.List<java.lang.Long> folderIds, long classNameId,
		java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, java.util.Date displayDateGT,
		java.util.Date displayDateLT, int status, java.util.Date reviewDate,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"search", _searchParameterTypes54);
=======
					"search", _searchParameterTypes53);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupId, folderIds, classNameId, articleId,
					version, title, description, content, ddmStructureKey,
					ddmTemplateKey, displayDateGT, displayDateLT, status,
					reviewDate, andOperator, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle> search(
		HttpPrincipal httpPrincipal, long companyId, long groupId,
		java.util.List<java.lang.Long> folderIds, long classNameId,
		java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String[] ddmStructureKeys,
		java.lang.String[] ddmTemplateKeys, java.util.Date displayDateGT,
		java.util.Date displayDateLT, int status, java.util.Date reviewDate,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"search", _searchParameterTypes55);
=======
					"search", _searchParameterTypes54);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupId, folderIds, classNameId, articleId,
					version, title, description, content, ddmStructureKeys,
					ddmTemplateKeys, displayDateGT, displayDateLT, status,
					reviewDate, andOperator, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchCount(HttpPrincipal httpPrincipal, long companyId,
		long groupId, java.util.List<java.lang.Long> folderIds,
		long classNameId, java.lang.String keywords, java.lang.Double version,
		java.lang.String ddmStructureKey, java.lang.String ddmTemplateKey,
		java.util.Date displayDateGT, java.util.Date displayDateLT, int status,
		java.util.Date reviewDate) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"searchCount", _searchCountParameterTypes56);
=======
					"searchCount", _searchCountParameterTypes55);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupId, folderIds, classNameId, keywords,
					version, ddmStructureKey, ddmTemplateKey, displayDateGT,
					displayDateLT, status, reviewDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchCount(HttpPrincipal httpPrincipal, long companyId,
		long groupId, java.util.List<java.lang.Long> folderIds,
		long classNameId, java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, java.util.Date displayDateGT,
		java.util.Date displayDateLT, int status, java.util.Date reviewDate,
		boolean andOperator) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"searchCount", _searchCountParameterTypes57);
=======
					"searchCount", _searchCountParameterTypes56);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupId, folderIds, classNameId, articleId,
					version, title, description, content, ddmStructureKey,
					ddmTemplateKey, displayDateGT, displayDateLT, status,
					reviewDate, andOperator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchCount(HttpPrincipal httpPrincipal, long companyId,
		long groupId, java.util.List<java.lang.Long> folderIds,
		long classNameId, java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String[] ddmStructureKeys,
		java.lang.String[] ddmTemplateKeys, java.util.Date displayDateGT,
		java.util.Date displayDateLT, int status, java.util.Date reviewDate,
		boolean andOperator) {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"searchCount", _searchCountParameterTypes58);
=======
					"searchCount", _searchCountParameterTypes57);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupId, folderIds, classNameId, articleId,
					version, title, description, content, ddmStructureKeys,
					ddmTemplateKeys, displayDateGT, displayDateLT, status,
					reviewDate, andOperator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

<<<<<<< HEAD
	public static void subscribe(HttpPrincipal httpPrincipal, long groupId,
		long articleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"subscribe", _subscribeParameterTypes59);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

=======
>>>>>>> compatible
	public static void subscribeStructure(HttpPrincipal httpPrincipal,
		long groupId, long userId, long ddmStructureId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"subscribeStructure", _subscribeStructureParameterTypes60);
=======
					"subscribeStructure", _subscribeStructureParameterTypes58);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, ddmStructureId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

<<<<<<< HEAD
	public static void unsubscribe(HttpPrincipal httpPrincipal, long groupId,
		long articleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"unsubscribe", _unsubscribeParameterTypes61);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

=======
>>>>>>> compatible
	public static void unsubscribeStructure(HttpPrincipal httpPrincipal,
		long groupId, long userId, long ddmStructureId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"unsubscribeStructure",
<<<<<<< HEAD
					_unsubscribeStructureParameterTypes62);
=======
					_unsubscribeStructureParameterTypes59);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, ddmStructureId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle updateArticle(
		HttpPrincipal httpPrincipal, long userId, long groupId, long folderId,
		java.lang.String articleId, double version,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String content, java.lang.String layoutUuid,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"updateArticle", _updateArticleParameterTypes63);
=======
					"updateArticle", _updateArticleParameterTypes60);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					groupId, folderId, articleId, version, titleMap,
					descriptionMap, content, layoutUuid, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle updateArticle(
		HttpPrincipal httpPrincipal, long groupId, long folderId,
		java.lang.String articleId, double version,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
<<<<<<< HEAD
		java.util.Map<java.util.Locale, java.lang.String> friendlyURLMap,
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, java.lang.String layoutUuid,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire, int reviewDateMonth,
		int reviewDateDay, int reviewDateYear, int reviewDateHour,
		int reviewDateMinute, boolean neverReview, boolean indexable,
		boolean smallImage, java.lang.String smallImageURL,
		java.io.File smallFile, java.util.Map<java.lang.String, byte[]> images,
		java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"updateArticle", _updateArticleParameterTypes64);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, articleId, version, titleMap, descriptionMap,
					friendlyURLMap, content, ddmStructureKey, ddmTemplateKey,
					layoutUuid, displayDateMonth, displayDateDay,
					displayDateYear, displayDateHour, displayDateMinute,
					expirationDateMonth, expirationDateDay, expirationDateYear,
					expirationDateHour, expirationDateMinute, neverExpire,
					reviewDateMonth, reviewDateDay, reviewDateYear,
					reviewDateHour, reviewDateMinute, neverReview, indexable,
					smallImage, smallImageURL, smallFile, images, articleURL,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle updateArticle(
		HttpPrincipal httpPrincipal, long groupId, long folderId,
		java.lang.String articleId, double version,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
=======
>>>>>>> compatible
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, java.lang.String layoutUuid,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire, int reviewDateMonth,
		int reviewDateDay, int reviewDateYear, int reviewDateHour,
		int reviewDateMinute, boolean neverReview, boolean indexable,
		boolean smallImage, java.lang.String smallImageURL,
		java.io.File smallFile, java.util.Map<java.lang.String, byte[]> images,
		java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"updateArticle", _updateArticleParameterTypes65);
=======
					"updateArticle", _updateArticleParameterTypes61);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, articleId, version, titleMap, descriptionMap,
					content, ddmStructureKey, ddmTemplateKey, layoutUuid,
					displayDateMonth, displayDateDay, displayDateYear,
					displayDateHour, displayDateMinute, expirationDateMonth,
					expirationDateDay, expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire, reviewDateMonth,
					reviewDateDay, reviewDateYear, reviewDateHour,
					reviewDateMinute, neverReview, indexable, smallImage,
					smallImageURL, smallFile, images, articleURL, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle updateArticle(
		HttpPrincipal httpPrincipal, long groupId, long folderId,
		java.lang.String articleId, double version, java.lang.String content,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"updateArticle", _updateArticleParameterTypes66);
=======
					"updateArticle", _updateArticleParameterTypes62);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, articleId, version, content, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle updateArticleTranslation(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId,
		double version, java.util.Locale locale, java.lang.String title,
		java.lang.String description, java.lang.String content,
		java.util.Map<java.lang.String, byte[]> images,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
					"updateArticleTranslation",
<<<<<<< HEAD
					_updateArticleTranslationParameterTypes67);
=======
					_updateArticleTranslationParameterTypes63);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, version, locale, title, description, content,
					images, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle updateContent(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId,
		double version, java.lang.String content)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"updateContent", _updateContentParameterTypes68);
=======
					"updateContent", _updateContentParameterTypes64);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, version, content);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalArticle updateStatus(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId,
		double version, int status, java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalArticleServiceUtil.class,
<<<<<<< HEAD
					"updateStatus", _updateStatusParameterTypes69);
=======
					"updateStatus", _updateStatusParameterTypes65);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, version, status, articleURL, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.journal.model.JournalArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(JournalArticleServiceHttp.class);
	private static final Class<?>[] _addArticleParameterTypes0 = new Class[] {
			long.class, long.class, long.class, long.class,
			java.lang.String.class, boolean.class, java.util.Map.class,
<<<<<<< HEAD
			java.util.Map.class, java.util.Map.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, int.class, int.class, int.class, int.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			boolean.class, int.class, int.class, int.class, int.class, int.class,
			boolean.class, boolean.class, boolean.class, java.lang.String.class,
			java.io.File.class, java.util.Map.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addArticleParameterTypes1 = new Class[] {
			long.class, long.class, long.class, long.class,
			java.lang.String.class, boolean.class, java.util.Map.class,
=======
>>>>>>> compatible
			java.util.Map.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, int.class, int.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			int.class, int.class, boolean.class, int.class, int.class, int.class,
			int.class, int.class, boolean.class, boolean.class, boolean.class,
			java.lang.String.class, java.io.File.class, java.util.Map.class,
			java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _addArticleParameterTypes2 = new Class[] {
=======
	private static final Class<?>[] _addArticleParameterTypes1 = new Class[] {
>>>>>>> compatible
			long.class, long.class, long.class, long.class,
			java.lang.String.class, boolean.class, java.util.Map.class,
			java.util.Map.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, int.class, int.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			int.class, int.class, boolean.class, int.class, int.class, int.class,
			int.class, int.class, boolean.class, boolean.class,
			java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _copyArticleParameterTypes3 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			boolean.class, double.class
		};
	private static final Class<?>[] _deleteArticleParameterTypes4 = new Class[] {
=======
	private static final Class<?>[] _copyArticleParameterTypes2 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			boolean.class, double.class
		};
	private static final Class<?>[] _deleteArticleParameterTypes3 = new Class[] {
>>>>>>> compatible
			long.class, java.lang.String.class, double.class,
			java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _deleteArticleParameterTypes5 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _expireArticleParameterTypes6 = new Class[] {
=======
	private static final Class<?>[] _deleteArticleParameterTypes4 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _expireArticleParameterTypes5 = new Class[] {
>>>>>>> compatible
			long.class, java.lang.String.class, double.class,
			java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _expireArticleParameterTypes7 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _fetchArticleParameterTypes8 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getArticleParameterTypes9 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getArticleParameterTypes10 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getArticleParameterTypes11 = new Class[] {
			long.class, java.lang.String.class, double.class
		};
	private static final Class<?>[] _getArticleParameterTypes12 = new Class[] {
			long.class, java.lang.String.class, long.class
		};
	private static final Class<?>[] _getArticleByUrlTitleParameterTypes13 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getArticleContentParameterTypes14 = new Class[] {
=======
	private static final Class<?>[] _expireArticleParameterTypes6 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _fetchArticleParameterTypes7 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getArticleParameterTypes8 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getArticleParameterTypes9 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getArticleParameterTypes10 = new Class[] {
			long.class, java.lang.String.class, double.class
		};
	private static final Class<?>[] _getArticleParameterTypes11 = new Class[] {
			long.class, java.lang.String.class, long.class
		};
	private static final Class<?>[] _getArticleByUrlTitleParameterTypes12 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getArticleContentParameterTypes13 = new Class[] {
>>>>>>> compatible
			long.class, java.lang.String.class, double.class,
			java.lang.String.class,
			com.liferay.portal.kernel.portlet.PortletRequestModel.class,
			com.liferay.portal.kernel.theme.ThemeDisplay.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _getArticleContentParameterTypes15 = new Class[] {
=======
	private static final Class<?>[] _getArticleContentParameterTypes14 = new Class[] {
>>>>>>> compatible
			long.class, java.lang.String.class, double.class,
			java.lang.String.class,
			com.liferay.portal.kernel.theme.ThemeDisplay.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _getArticleContentParameterTypes16 = new Class[] {
=======
	private static final Class<?>[] _getArticleContentParameterTypes15 = new Class[] {
>>>>>>> compatible
			long.class, java.lang.String.class, java.lang.String.class,
			com.liferay.portal.kernel.portlet.PortletRequestModel.class,
			com.liferay.portal.kernel.theme.ThemeDisplay.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _getArticleContentParameterTypes17 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			com.liferay.portal.kernel.theme.ThemeDisplay.class
		};
	private static final Class<?>[] _getArticlesParameterTypes18 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getArticlesParameterTypes19 = new Class[] {
			long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getArticlesByArticleIdParameterTypes20 = new Class[] {
			long.class, java.lang.String.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getArticlesByLayoutUuidParameterTypes21 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getArticlesByStructureIdParameterTypes22 = new Class[] {
			long.class, long.class, java.lang.String.class, int.class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getArticlesByStructureIdParameterTypes23 = new Class[] {
			long.class, java.lang.String.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getArticlesByStructureIdParameterTypes24 = new Class[] {
			long.class, java.lang.String.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getArticlesCountParameterTypes25 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getArticlesCountParameterTypes26 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getArticlesCountByArticleIdParameterTypes27 =
		new Class[] { long.class, java.lang.String.class };
	private static final Class<?>[] _getArticlesCountByStructureIdParameterTypes28 =
		new Class[] { long.class, long.class, java.lang.String.class, int.class };
	private static final Class<?>[] _getArticlesCountByStructureIdParameterTypes29 =
		new Class[] { long.class, java.lang.String.class };
	private static final Class<?>[] _getArticlesCountByStructureIdParameterTypes30 =
		new Class[] { long.class, java.lang.String.class, int.class };
	private static final Class<?>[] _getDisplayArticleByUrlTitleParameterTypes31 =
		new Class[] { long.class, java.lang.String.class };
	private static final Class<?>[] _getFoldersAndArticlesCountParameterTypes32 = new Class[] {
			long.class, java.util.List.class
		};
	private static final Class<?>[] _getGroupArticlesParameterTypes33 = new Class[] {
=======
	private static final Class<?>[] _getArticleContentParameterTypes16 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			com.liferay.portal.kernel.theme.ThemeDisplay.class
		};
	private static final Class<?>[] _getArticlesParameterTypes17 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getArticlesParameterTypes18 = new Class[] {
			long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getArticlesByArticleIdParameterTypes19 = new Class[] {
			long.class, java.lang.String.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getArticlesByLayoutUuidParameterTypes20 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getArticlesByStructureIdParameterTypes21 = new Class[] {
			long.class, long.class, java.lang.String.class, int.class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getArticlesByStructureIdParameterTypes22 = new Class[] {
			long.class, java.lang.String.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getArticlesByStructureIdParameterTypes23 = new Class[] {
			long.class, java.lang.String.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getArticlesCountParameterTypes24 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getArticlesCountParameterTypes25 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getArticlesCountByArticleIdParameterTypes26 =
		new Class[] { long.class, java.lang.String.class };
	private static final Class<?>[] _getArticlesCountByStructureIdParameterTypes27 =
		new Class[] { long.class, long.class, java.lang.String.class, int.class };
	private static final Class<?>[] _getArticlesCountByStructureIdParameterTypes28 =
		new Class[] { long.class, java.lang.String.class };
	private static final Class<?>[] _getArticlesCountByStructureIdParameterTypes29 =
		new Class[] { long.class, java.lang.String.class, int.class };
	private static final Class<?>[] _getDisplayArticleByUrlTitleParameterTypes30 =
		new Class[] { long.class, java.lang.String.class };
	private static final Class<?>[] _getFoldersAndArticlesCountParameterTypes31 = new Class[] {
			long.class, java.util.List.class
		};
	private static final Class<?>[] _getGroupArticlesParameterTypes32 = new Class[] {
>>>>>>> compatible
			long.class, long.class, long.class, int.class, boolean.class,
			int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _getGroupArticlesParameterTypes34 = new Class[] {
			long.class, long.class, long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupArticlesParameterTypes35 = new Class[] {
			long.class, long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupArticlesCountParameterTypes36 = new Class[] {
			long.class, long.class, long.class
		};
	private static final Class<?>[] _getGroupArticlesCountParameterTypes37 = new Class[] {
			long.class, long.class, long.class, int.class
		};
	private static final Class<?>[] _getGroupArticlesCountParameterTypes38 = new Class[] {
			long.class, long.class, long.class, int.class, boolean.class
		};
	private static final Class<?>[] _getLatestArticleParameterTypes39 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getLatestArticleParameterTypes40 = new Class[] {
			long.class, java.lang.String.class, int.class
		};
	private static final Class<?>[] _getLatestArticleParameterTypes41 = new Class[] {
			long.class, java.lang.String.class, long.class
		};
	private static final Class<?>[] _getLayoutArticlesParameterTypes42 = new Class[] {
			long.class
		};
	private static final Class<?>[] _moveArticleParameterTypes43 = new Class[] {
			long.class, java.lang.String.class, long.class
		};
	private static final Class<?>[] _moveArticleParameterTypes44 = new Class[] {
			long.class, java.lang.String.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveArticleFromTrashParameterTypes45 = new Class[] {
			long.class, long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveArticleFromTrashParameterTypes46 = new Class[] {
			long.class, java.lang.String.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveArticleToTrashParameterTypes47 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _removeArticleLocaleParameterTypes48 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _removeArticleLocaleParameterTypes49 = new Class[] {
			long.class, java.lang.String.class, double.class,
			java.lang.String.class
		};
	private static final Class<?>[] _restoreArticleFromTrashParameterTypes50 = new Class[] {
			long.class
		};
	private static final Class<?>[] _restoreArticleFromTrashParameterTypes51 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _searchParameterTypes52 = new Class[] {
			long.class, long.class, int.class, int.class, int.class
		};
	private static final Class<?>[] _searchParameterTypes53 = new Class[] {
=======
	private static final Class<?>[] _getGroupArticlesParameterTypes33 = new Class[] {
			long.class, long.class, long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupArticlesParameterTypes34 = new Class[] {
			long.class, long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupArticlesCountParameterTypes35 = new Class[] {
			long.class, long.class, long.class
		};
	private static final Class<?>[] _getGroupArticlesCountParameterTypes36 = new Class[] {
			long.class, long.class, long.class, int.class
		};
	private static final Class<?>[] _getGroupArticlesCountParameterTypes37 = new Class[] {
			long.class, long.class, long.class, int.class, boolean.class
		};
	private static final Class<?>[] _getLatestArticleParameterTypes38 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getLatestArticleParameterTypes39 = new Class[] {
			long.class, java.lang.String.class, int.class
		};
	private static final Class<?>[] _getLatestArticleParameterTypes40 = new Class[] {
			long.class, java.lang.String.class, long.class
		};
	private static final Class<?>[] _getLayoutArticlesParameterTypes41 = new Class[] {
			long.class
		};
	private static final Class<?>[] _moveArticleParameterTypes42 = new Class[] {
			long.class, java.lang.String.class, long.class
		};
	private static final Class<?>[] _moveArticleParameterTypes43 = new Class[] {
			long.class, java.lang.String.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveArticleFromTrashParameterTypes44 = new Class[] {
			long.class, long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveArticleFromTrashParameterTypes45 = new Class[] {
			long.class, java.lang.String.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveArticleToTrashParameterTypes46 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _removeArticleLocaleParameterTypes47 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _removeArticleLocaleParameterTypes48 = new Class[] {
			long.class, java.lang.String.class, double.class,
			java.lang.String.class
		};
	private static final Class<?>[] _restoreArticleFromTrashParameterTypes49 = new Class[] {
			long.class
		};
	private static final Class<?>[] _restoreArticleFromTrashParameterTypes50 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _searchParameterTypes51 = new Class[] {
			long.class, long.class, int.class, int.class, int.class
		};
	private static final Class<?>[] _searchParameterTypes52 = new Class[] {
>>>>>>> compatible
			long.class, long.class, java.util.List.class, long.class,
			java.lang.String.class, java.lang.Double.class,
			java.lang.String.class, java.lang.String.class, java.util.Date.class,
			java.util.Date.class, int.class, java.util.Date.class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _searchParameterTypes54 = new Class[] {
=======
	private static final Class<?>[] _searchParameterTypes53 = new Class[] {
>>>>>>> compatible
			long.class, long.class, java.util.List.class, long.class,
			java.lang.String.class, java.lang.Double.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.util.Date.class, java.util.Date.class,
			int.class, java.util.Date.class, boolean.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _searchParameterTypes55 = new Class[] {
=======
	private static final Class<?>[] _searchParameterTypes54 = new Class[] {
>>>>>>> compatible
			long.class, long.class, java.util.List.class, long.class,
			java.lang.String.class, java.lang.Double.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String[].class,
			java.lang.String[].class, java.util.Date.class, java.util.Date.class,
			int.class, java.util.Date.class, boolean.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _searchCountParameterTypes56 = new Class[] {
=======
	private static final Class<?>[] _searchCountParameterTypes55 = new Class[] {
>>>>>>> compatible
			long.class, long.class, java.util.List.class, long.class,
			java.lang.String.class, java.lang.Double.class,
			java.lang.String.class, java.lang.String.class, java.util.Date.class,
			java.util.Date.class, int.class, java.util.Date.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _searchCountParameterTypes57 = new Class[] {
=======
	private static final Class<?>[] _searchCountParameterTypes56 = new Class[] {
>>>>>>> compatible
			long.class, long.class, java.util.List.class, long.class,
			java.lang.String.class, java.lang.Double.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.util.Date.class, java.util.Date.class,
			int.class, java.util.Date.class, boolean.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _searchCountParameterTypes58 = new Class[] {
=======
	private static final Class<?>[] _searchCountParameterTypes57 = new Class[] {
>>>>>>> compatible
			long.class, long.class, java.util.List.class, long.class,
			java.lang.String.class, java.lang.Double.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String[].class,
			java.lang.String[].class, java.util.Date.class, java.util.Date.class,
			int.class, java.util.Date.class, boolean.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _subscribeParameterTypes59 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _subscribeStructureParameterTypes60 = new Class[] {
			long.class, long.class, long.class
		};
	private static final Class<?>[] _unsubscribeParameterTypes61 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _unsubscribeStructureParameterTypes62 = new Class[] {
			long.class, long.class, long.class
		};
	private static final Class<?>[] _updateArticleParameterTypes63 = new Class[] {
=======
	private static final Class<?>[] _subscribeStructureParameterTypes58 = new Class[] {
			long.class, long.class, long.class
		};
	private static final Class<?>[] _unsubscribeStructureParameterTypes59 = new Class[] {
			long.class, long.class, long.class
		};
	private static final Class<?>[] _updateArticleParameterTypes60 = new Class[] {
>>>>>>> compatible
			long.class, long.class, long.class, java.lang.String.class,
			double.class, java.util.Map.class, java.util.Map.class,
			java.lang.String.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _updateArticleParameterTypes64 = new Class[] {
			long.class, long.class, java.lang.String.class, double.class,
			java.util.Map.class, java.util.Map.class, java.util.Map.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, int.class, int.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			int.class, int.class, boolean.class, int.class, int.class, int.class,
			int.class, int.class, boolean.class, boolean.class, boolean.class,
			java.lang.String.class, java.io.File.class, java.util.Map.class,
			java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateArticleParameterTypes65 = new Class[] {
=======
	private static final Class<?>[] _updateArticleParameterTypes61 = new Class[] {
>>>>>>> compatible
			long.class, long.class, java.lang.String.class, double.class,
			java.util.Map.class, java.util.Map.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, int.class, int.class, int.class, int.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			boolean.class, int.class, int.class, int.class, int.class, int.class,
			boolean.class, boolean.class, boolean.class, java.lang.String.class,
			java.io.File.class, java.util.Map.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _updateArticleParameterTypes66 = new Class[] {
=======
	private static final Class<?>[] _updateArticleParameterTypes62 = new Class[] {
>>>>>>> compatible
			long.class, long.class, java.lang.String.class, double.class,
			java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _updateArticleTranslationParameterTypes67 = new Class[] {
=======
	private static final Class<?>[] _updateArticleTranslationParameterTypes63 = new Class[] {
>>>>>>> compatible
			long.class, java.lang.String.class, double.class,
			java.util.Locale.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, java.util.Map.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _updateContentParameterTypes68 = new Class[] {
			long.class, java.lang.String.class, double.class,
			java.lang.String.class
		};
	private static final Class<?>[] _updateStatusParameterTypes69 = new Class[] {
=======
	private static final Class<?>[] _updateContentParameterTypes64 = new Class[] {
			long.class, java.lang.String.class, double.class,
			java.lang.String.class
		};
	private static final Class<?>[] _updateStatusParameterTypes65 = new Class[] {
>>>>>>> compatible
			long.class, java.lang.String.class, double.class, int.class,
			java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}