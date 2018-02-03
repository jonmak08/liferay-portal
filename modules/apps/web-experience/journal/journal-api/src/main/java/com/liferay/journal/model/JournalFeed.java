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

package com.liferay.journal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the JournalFeed service. Represents a row in the &quot;JournalFeed&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see JournalFeedModel
 * @see com.liferay.journal.model.impl.JournalFeedImpl
 * @see com.liferay.journal.model.impl.JournalFeedModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.journal.model.impl.JournalFeedImpl")
@ProviderType
public interface JournalFeed extends JournalFeedModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.journal.model.impl.JournalFeedImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<JournalFeed, Long> ID_ACCESSOR = new Accessor<JournalFeed, Long>() {
			@Override
			public Long get(JournalFeed journalFeed) {
				return journalFeed.getId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<JournalFeed> getTypeClass() {
				return JournalFeed.class;
			}
		};

	/**
<<<<<<< HEAD
	* @deprecated As of 4.0.0, replaced by {@link #getDDMRendererTemplateKey()}
=======
	* @deprecated As of 3.3.0, replaced by {@link #getDDMRendererTemplateKey()}
>>>>>>> compatible
	*/
	@java.lang.Deprecated()
	public java.lang.String getRendererTemplateId();

	/**
<<<<<<< HEAD
	* @deprecated As of 4.0.0, replaced by {@link #getDDMStructureKey()}
=======
	* @deprecated As of 3.3.0, replaced by {@link #getDDMStructureKey()}
>>>>>>> compatible
	*/
	@java.lang.Deprecated()
	public java.lang.String getStructureId();

	/**
<<<<<<< HEAD
	* @deprecated As of 4.0.0, replaced by {@link #getDDMTemplateKey()}
=======
	* @deprecated As of 3.3.0, replaced by {@link #getDDMTemplateKey()}
>>>>>>> compatible
	*/
	@java.lang.Deprecated()
	public java.lang.String getTemplateId();

	/**
<<<<<<< HEAD
	* @deprecated As of 4.0.0, replaced by {@link
=======
	* @deprecated As of 3.3.0, replaced by {@link
>>>>>>> compatible
	#setDDMRendererTemplateKey(String)}
	*/
	@java.lang.Deprecated()
	public void setRendererTemplateId(java.lang.String rendererTemplateKey);

	/**
<<<<<<< HEAD
	* @deprecated As of 4.0.0, replaced by {@link #setDDMStructureKey(String)}
=======
	* @deprecated As of 3.3.0, replaced by {@link #setDDMStructureKey(String)}
>>>>>>> compatible
	*/
	@java.lang.Deprecated()
	public void setStructureId(java.lang.String structureKey);

	/**
<<<<<<< HEAD
	* @deprecated As of 4.0.0, replaced by {@link #setDDMTemplateKey(String)}
=======
	* @deprecated As of 3.3.0, replaced by {@link #setDDMTemplateKey(String)}
>>>>>>> compatible
	*/
	@java.lang.Deprecated()
	public void setTemplateId(java.lang.String templateKey);
}