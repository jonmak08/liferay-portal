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

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.service.DLFileShortcutLocalServiceUtil;

/**
 * The extended model base implementation for the DLFileShortcut service. Represents a row in the &quot;DLFileShortcut&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLFileShortcutImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileShortcutImpl
 * @see com.liferay.portlet.documentlibrary.model.DLFileShortcut
 * @generated
 */
public abstract class DLFileShortcutBaseImpl extends DLFileShortcutModelImpl
	implements DLFileShortcut {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a document library file shortcut model instance should use the {@link DLFileShortcut} interface instead.
	 */
	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			DLFileShortcutLocalServiceUtil.addDLFileShortcut(this);
		}
		else {
			DLFileShortcutLocalServiceUtil.updateDLFileShortcut(this);
		}
	}

	@Override
	public void updateTreePath(String treePath) throws SystemException {
		DLFileShortcut dlFileShortcut = this;

		dlFileShortcut.setTreePath(treePath);

		DLFileShortcutLocalServiceUtil.updateDLFileShortcut(dlFileShortcut);
	}
}