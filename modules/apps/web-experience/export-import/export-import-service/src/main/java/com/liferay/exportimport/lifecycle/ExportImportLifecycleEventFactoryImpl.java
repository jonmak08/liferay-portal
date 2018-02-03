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

package com.liferay.exportimport.lifecycle;

<<<<<<< HEAD
import aQute.bnd.annotation.ProviderType;

=======
>>>>>>> compatible
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleEvent;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleEventFactory;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.Serializable;

import org.osgi.service.component.annotations.Component;

/**
 * @author Daniel Kocsis
 */
@Component(immediate = true)
<<<<<<< HEAD
@ProviderType
=======
>>>>>>> compatible
public class ExportImportLifecycleEventFactoryImpl
	implements ExportImportLifecycleEventFactory {

	/**
<<<<<<< HEAD
	 * @deprecated As of 4.0.0
=======
	 * @deprecated As of 3.6.0
>>>>>>> compatible
	 */
	@Deprecated
	@Override
	public ExportImportLifecycleEvent create(
		int code, int processFlag, Serializable... attributes) {

		ExportImportLifecycleEvent exportImportLifecycleEvent =
			new ExportImportLifecycleEventImpl();

		exportImportLifecycleEvent.setAttributes(attributes);
		exportImportLifecycleEvent.setCode(code);
		exportImportLifecycleEvent.setProcessFlag(processFlag);
		exportImportLifecycleEvent.setProcessId(
			GetterUtil.getString(processFlag));

		return exportImportLifecycleEvent;
	}

	@Override
	public ExportImportLifecycleEvent create(
		int code, int processFlag, String processId,
		Serializable... attributes) {

		ExportImportLifecycleEvent exportImportLifecycleEvent =
			new ExportImportLifecycleEventImpl();

		exportImportLifecycleEvent.setAttributes(attributes);
		exportImportLifecycleEvent.setCode(code);
		exportImportLifecycleEvent.setProcessFlag(processFlag);
		exportImportLifecycleEvent.setProcessId(processId);

		return exportImportLifecycleEvent;
	}

}