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

package com.liferay.journal.model.impl;

/**
 * @author Brian Wing Shun Chan
 */
public class JournalFeedImpl extends JournalFeedBaseImpl {

	/**
<<<<<<< HEAD
	 * @deprecated As of 4.0.0, replaced by {@link #getDDMRendererTemplateKey()}
=======
	 * @deprecated As of 3.3.0, replaced by {@link #getDDMRendererTemplateKey()}
>>>>>>> compatible
	 */
	@Deprecated
	@Override
	public String getRendererTemplateId() {
		return getDDMRendererTemplateKey();
	}

	/**
<<<<<<< HEAD
	 * @deprecated As of 4.0.0, replaced by {@link #getDDMStructureKey()}
=======
	 * @deprecated As of 3.3.0, replaced by {@link #getDDMStructureKey()}
>>>>>>> compatible
	 */
	@Deprecated
	@Override
	public String getStructureId() {
		return getDDMStructureKey();
	}

	/**
<<<<<<< HEAD
	 * @deprecated As of 4.0.0, replaced by {@link #getDDMTemplateKey()}
=======
	 * @deprecated As of 3.3.0, replaced by {@link #getDDMTemplateKey()}
>>>>>>> compatible
	 */
	@Deprecated
	@Override
	public String getTemplateId() {
		return getDDMTemplateKey();
	}

	/**
<<<<<<< HEAD
	 * @deprecated As of 4.0.0, replaced by {@link
=======
	 * @deprecated As of 3.3.0, replaced by {@link
>>>>>>> compatible
	 *             #setDDMRendererTemplateKey(String)}
	 */
	@Deprecated
	@Override
	public void setRendererTemplateId(String rendererTemplateKey) {
		setDDMRendererTemplateKey(rendererTemplateKey);
	}

	/**
<<<<<<< HEAD
	 * @deprecated As of 4.0.0, replaced by {@link #setDDMStructureKey(String)}
=======
	 * @deprecated As of 3.3.0, replaced by {@link #setDDMStructureKey(String)}
>>>>>>> compatible
	 */
	@Deprecated
	@Override
	public void setStructureId(String structureKey) {
		setDDMStructureKey(structureKey);
	}

	/**
<<<<<<< HEAD
	 * @deprecated As of 4.0.0, replaced by {@link #setDDMTemplateKey(String)}
=======
	 * @deprecated As of 3.3.0, replaced by {@link #setDDMTemplateKey(String)}
>>>>>>> compatible
	 */
	@Deprecated
	@Override
	public void setTemplateId(String templateKey) {
		setDDMTemplateKey(templateKey);
	}

}