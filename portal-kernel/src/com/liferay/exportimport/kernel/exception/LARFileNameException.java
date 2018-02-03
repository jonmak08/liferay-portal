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

package com.liferay.exportimport.kernel.exception;

<<<<<<< HEAD
import aQute.bnd.annotation.ProviderType;

=======
>>>>>>> compatible
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Eric Min
 */
<<<<<<< HEAD
@ProviderType
=======
>>>>>>> compatible
public class LARFileNameException extends PortalException {

	public LARFileNameException() {
	}

	public LARFileNameException(String msg) {
		super(msg);
	}

	public LARFileNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public LARFileNameException(Throwable cause) {
		super(cause);
	}

}