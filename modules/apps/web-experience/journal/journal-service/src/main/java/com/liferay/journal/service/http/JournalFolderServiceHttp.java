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

import com.liferay.journal.service.JournalFolderServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link JournalFolderServiceUtil} service utility. The
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
 * @see JournalFolderServiceSoap
 * @see HttpPrincipal
 * @see JournalFolderServiceUtil
 * @generated
 */
@ProviderType
public class JournalFolderServiceHttp {
	public static com.liferay.journal.model.JournalFolder addFolder(
		HttpPrincipal httpPrincipal, long groupId, long parentFolderId,
		java.lang.String name, java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"addFolder", _addFolderParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentFolderId, name, description, serviceContext);

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

			return (com.liferay.journal.model.JournalFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteFolder(HttpPrincipal httpPrincipal, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"deleteFolder", _deleteFolderParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, folderId);

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

	public static void deleteFolder(HttpPrincipal httpPrincipal, long folderId,
		boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"deleteFolder", _deleteFolderParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					folderId, includeTrashedEntries);

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

	public static com.liferay.journal.model.JournalFolder fetchFolder(
		HttpPrincipal httpPrincipal, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"fetchFolder", _fetchFolderParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, folderId);

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

			return (com.liferay.journal.model.JournalFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure> getDDMStructures(
		HttpPrincipal httpPrincipal, long[] groupIds, long folderId,
		int restrictionType)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getDDMStructures", _getDDMStructuresParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					groupIds, folderId, restrictionType);

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

			return (java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalFolder getFolder(
		HttpPrincipal httpPrincipal, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFolder", _getFolderParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, folderId);

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

			return (com.liferay.journal.model.JournalFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<java.lang.Long> getFolderIds(
		HttpPrincipal httpPrincipal, long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFolderIds", _getFolderIdsParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId);

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

			return (java.util.List<java.lang.Long>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalFolder> getFolders(
		HttpPrincipal httpPrincipal, long groupId) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFolders", _getFoldersParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalFolder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalFolder> getFolders(
		HttpPrincipal httpPrincipal, long groupId, long parentFolderId) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFolders", _getFoldersParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentFolderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalFolder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalFolder> getFolders(
		HttpPrincipal httpPrincipal, long groupId, long parentFolderId,
		int status) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFolders", _getFoldersParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentFolderId, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalFolder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalFolder> getFolders(
		HttpPrincipal httpPrincipal, long groupId, long parentFolderId,
		int start, int end) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFolders", _getFoldersParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentFolderId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalFolder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.journal.model.JournalFolder> getFolders(
		HttpPrincipal httpPrincipal, long groupId, long parentFolderId,
		int status, int start, int end) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFolders", _getFoldersParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentFolderId, status, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.journal.model.JournalFolder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<java.lang.Object> getFoldersAndArticles(
		HttpPrincipal httpPrincipal, long groupId, long folderId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<?> obc) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFoldersAndArticles",
					_getFoldersAndArticlesParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, status, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<java.lang.Object>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<java.lang.Object> getFoldersAndArticles(
		HttpPrincipal httpPrincipal, long groupId, long folderId, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator<?> obc) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFoldersAndArticles",
					_getFoldersAndArticlesParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<java.lang.Object>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<java.lang.Object> getFoldersAndArticles(
		HttpPrincipal httpPrincipal, long groupId, long userId, long folderId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<?> obc) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFoldersAndArticles",
					_getFoldersAndArticlesParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, folderId, status, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<java.lang.Object>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

<<<<<<< HEAD
	public static java.util.List<java.lang.Object> getFoldersAndArticles(
		HttpPrincipal httpPrincipal, long groupId, long userId, long folderId,
		int status, java.util.Locale locale, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<?> obc) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFoldersAndArticles",
					_getFoldersAndArticlesParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, folderId, status, locale, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<java.lang.Object>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

=======
>>>>>>> compatible
	public static int getFoldersAndArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, java.util.List<java.lang.Long> folderIds, int status) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFoldersAndArticlesCount",
<<<<<<< HEAD
					_getFoldersAndArticlesCountParameterTypes16);
=======
					_getFoldersAndArticlesCountParameterTypes15);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderIds, status);

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

	public static int getFoldersAndArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, long folderId) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFoldersAndArticlesCount",
<<<<<<< HEAD
					_getFoldersAndArticlesCountParameterTypes17);
=======
					_getFoldersAndArticlesCountParameterTypes16);
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

	public static int getFoldersAndArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, long folderId, int status) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFoldersAndArticlesCount",
<<<<<<< HEAD
					_getFoldersAndArticlesCountParameterTypes18);
=======
					_getFoldersAndArticlesCountParameterTypes17);
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

	public static int getFoldersAndArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, long userId, long folderId, int status) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"getFoldersAndArticlesCount",
<<<<<<< HEAD
					_getFoldersAndArticlesCountParameterTypes19);
=======
					_getFoldersAndArticlesCountParameterTypes18);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, folderId, status);

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

	public static int getFoldersCount(HttpPrincipal httpPrincipal,
		long groupId, long parentFolderId) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
<<<<<<< HEAD
					"getFoldersCount", _getFoldersCountParameterTypes20);
=======
					"getFoldersCount", _getFoldersCountParameterTypes19);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentFolderId);

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

	public static int getFoldersCount(HttpPrincipal httpPrincipal,
		long groupId, long parentFolderId, int status) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
<<<<<<< HEAD
					"getFoldersCount", _getFoldersCountParameterTypes21);
=======
					"getFoldersCount", _getFoldersCountParameterTypes20);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentFolderId, status);

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

	public static void getSubfolderIds(HttpPrincipal httpPrincipal,
		java.util.List<java.lang.Long> folderIds, long groupId, long folderId) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
<<<<<<< HEAD
					"getSubfolderIds", _getSubfolderIdsParameterTypes22);
=======
					"getSubfolderIds", _getSubfolderIdsParameterTypes21);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey,
					folderIds, groupId, folderId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void getSubfolderIds(HttpPrincipal httpPrincipal,
		java.util.List<java.lang.Long> folderIds, long groupId, long folderId,
		boolean recurse) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
<<<<<<< HEAD
					"getSubfolderIds", _getSubfolderIdsParameterTypes23);
=======
					"getSubfolderIds", _getSubfolderIdsParameterTypes22);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey,
					folderIds, groupId, folderId, recurse);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<java.lang.Long> getSubfolderIds(
		HttpPrincipal httpPrincipal, long groupId, long folderId,
		boolean recurse) {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
<<<<<<< HEAD
					"getSubfolderIds", _getSubfolderIdsParameterTypes24);
=======
					"getSubfolderIds", _getSubfolderIdsParameterTypes23);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, recurse);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<java.lang.Long>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalFolder moveFolder(
		HttpPrincipal httpPrincipal, long folderId, long parentFolderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
<<<<<<< HEAD
					"moveFolder", _moveFolderParameterTypes25);
=======
					"moveFolder", _moveFolderParameterTypes24);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey,
					folderId, parentFolderId, serviceContext);

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

			return (com.liferay.journal.model.JournalFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalFolder moveFolderFromTrash(
		HttpPrincipal httpPrincipal, long folderId, long parentFolderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
<<<<<<< HEAD
					"moveFolderFromTrash", _moveFolderFromTrashParameterTypes26);
=======
					"moveFolderFromTrash", _moveFolderFromTrashParameterTypes25);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey,
					folderId, parentFolderId, serviceContext);

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

			return (com.liferay.journal.model.JournalFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalFolder moveFolderToTrash(
		HttpPrincipal httpPrincipal, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
<<<<<<< HEAD
					"moveFolderToTrash", _moveFolderToTrashParameterTypes27);
=======
					"moveFolderToTrash", _moveFolderToTrashParameterTypes26);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, folderId);

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

			return (com.liferay.journal.model.JournalFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void restoreFolderFromTrash(HttpPrincipal httpPrincipal,
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"restoreFolderFromTrash",
<<<<<<< HEAD
					_restoreFolderFromTrashParameterTypes28);
=======
					_restoreFolderFromTrashParameterTypes27);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, folderId);

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
	public static java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure> searchDDMStructures(
		HttpPrincipal httpPrincipal, long companyId, long[] groupIds,
		long folderId, int restrictionType, java.lang.String keywords,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.mapping.model.DDMStructure> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
					"searchDDMStructures", _searchDDMStructuresParameterTypes29);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupIds, folderId, restrictionType, keywords,
					start, end, obc);

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

			return (java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

=======
>>>>>>> compatible
	public static void subscribe(HttpPrincipal httpPrincipal, long groupId,
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
<<<<<<< HEAD
					"subscribe", _subscribeParameterTypes30);
=======
					"subscribe", _subscribeParameterTypes28);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId);

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

	public static void unsubscribe(HttpPrincipal httpPrincipal, long groupId,
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
<<<<<<< HEAD
					"unsubscribe", _unsubscribeParameterTypes31);
=======
					"unsubscribe", _unsubscribeParameterTypes29);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId);

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

	public static com.liferay.journal.model.JournalFolder updateFolder(
		HttpPrincipal httpPrincipal, long groupId, long folderId,
		long parentFolderId, java.lang.String name,
		java.lang.String description, boolean mergeWithParentFolder,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
<<<<<<< HEAD
					"updateFolder", _updateFolderParameterTypes32);
=======
					"updateFolder", _updateFolderParameterTypes30);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, parentFolderId, name, description,
					mergeWithParentFolder, serviceContext);

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

			return (com.liferay.journal.model.JournalFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.journal.model.JournalFolder updateFolder(
		HttpPrincipal httpPrincipal, long groupId, long folderId,
		long parentFolderId, java.lang.String name,
		java.lang.String description, long[] ddmStructureIds,
		int restrictionType, boolean mergeWithParentFolder,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(JournalFolderServiceUtil.class,
<<<<<<< HEAD
					"updateFolder", _updateFolderParameterTypes33);
=======
					"updateFolder", _updateFolderParameterTypes31);
>>>>>>> compatible

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, parentFolderId, name, description,
					ddmStructureIds, restrictionType, mergeWithParentFolder,
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

			return (com.liferay.journal.model.JournalFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(JournalFolderServiceHttp.class);
	private static final Class<?>[] _addFolderParameterTypes0 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteFolderParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteFolderParameterTypes2 = new Class[] {
			long.class, boolean.class
		};
	private static final Class<?>[] _fetchFolderParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getDDMStructuresParameterTypes4 = new Class[] {
			long[].class, long.class, int.class
		};
	private static final Class<?>[] _getFolderParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getFolderIdsParameterTypes6 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getFoldersParameterTypes7 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getFoldersParameterTypes8 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getFoldersParameterTypes9 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getFoldersParameterTypes10 = new Class[] {
			long.class, long.class, int.class, int.class
		};
	private static final Class<?>[] _getFoldersParameterTypes11 = new Class[] {
			long.class, long.class, int.class, int.class, int.class
		};
	private static final Class<?>[] _getFoldersAndArticlesParameterTypes12 = new Class[] {
			long.class, long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getFoldersAndArticlesParameterTypes13 = new Class[] {
			long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getFoldersAndArticlesParameterTypes14 = new Class[] {
			long.class, long.class, long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _getFoldersAndArticlesParameterTypes15 = new Class[] {
			long.class, long.class, long.class, int.class,
			java.util.Locale.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getFoldersAndArticlesCountParameterTypes16 = new Class[] {
			long.class, java.util.List.class, int.class
		};
	private static final Class<?>[] _getFoldersAndArticlesCountParameterTypes17 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getFoldersAndArticlesCountParameterTypes18 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getFoldersAndArticlesCountParameterTypes19 = new Class[] {
			long.class, long.class, long.class, int.class
		};
	private static final Class<?>[] _getFoldersCountParameterTypes20 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getFoldersCountParameterTypes21 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getSubfolderIdsParameterTypes22 = new Class[] {
			java.util.List.class, long.class, long.class
		};
	private static final Class<?>[] _getSubfolderIdsParameterTypes23 = new Class[] {
			java.util.List.class, long.class, long.class, boolean.class
		};
	private static final Class<?>[] _getSubfolderIdsParameterTypes24 = new Class[] {
			long.class, long.class, boolean.class
		};
	private static final Class<?>[] _moveFolderParameterTypes25 = new Class[] {
			long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveFolderFromTrashParameterTypes26 = new Class[] {
			long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveFolderToTrashParameterTypes27 = new Class[] {
			long.class
		};
	private static final Class<?>[] _restoreFolderFromTrashParameterTypes28 = new Class[] {
			long.class
		};
	private static final Class<?>[] _searchDDMStructuresParameterTypes29 = new Class[] {
			long.class, long[].class, long.class, int.class,
			java.lang.String.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _subscribeParameterTypes30 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _unsubscribeParameterTypes31 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _updateFolderParameterTypes32 = new Class[] {
=======
	private static final Class<?>[] _getFoldersAndArticlesCountParameterTypes15 = new Class[] {
			long.class, java.util.List.class, int.class
		};
	private static final Class<?>[] _getFoldersAndArticlesCountParameterTypes16 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getFoldersAndArticlesCountParameterTypes17 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getFoldersAndArticlesCountParameterTypes18 = new Class[] {
			long.class, long.class, long.class, int.class
		};
	private static final Class<?>[] _getFoldersCountParameterTypes19 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getFoldersCountParameterTypes20 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getSubfolderIdsParameterTypes21 = new Class[] {
			java.util.List.class, long.class, long.class
		};
	private static final Class<?>[] _getSubfolderIdsParameterTypes22 = new Class[] {
			java.util.List.class, long.class, long.class, boolean.class
		};
	private static final Class<?>[] _getSubfolderIdsParameterTypes23 = new Class[] {
			long.class, long.class, boolean.class
		};
	private static final Class<?>[] _moveFolderParameterTypes24 = new Class[] {
			long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveFolderFromTrashParameterTypes25 = new Class[] {
			long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveFolderToTrashParameterTypes26 = new Class[] {
			long.class
		};
	private static final Class<?>[] _restoreFolderFromTrashParameterTypes27 = new Class[] {
			long.class
		};
	private static final Class<?>[] _subscribeParameterTypes28 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _unsubscribeParameterTypes29 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _updateFolderParameterTypes30 = new Class[] {
>>>>>>> compatible
			long.class, long.class, long.class, java.lang.String.class,
			java.lang.String.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
<<<<<<< HEAD
	private static final Class<?>[] _updateFolderParameterTypes33 = new Class[] {
=======
	private static final Class<?>[] _updateFolderParameterTypes31 = new Class[] {
>>>>>>> compatible
			long.class, long.class, long.class, java.lang.String.class,
			java.lang.String.class, long[].class, int.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}