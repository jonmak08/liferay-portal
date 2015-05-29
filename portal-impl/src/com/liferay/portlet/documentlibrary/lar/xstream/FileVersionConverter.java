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

package com.liferay.portlet.documentlibrary.lar.xstream;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.lar.xstream.BaseXStreamConverter;
import com.liferay.portal.repository.liferayrepository.model.LiferayFileVersion;
import com.liferay.portal.repository.proxy.FileVersionProxyBean;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.impl.DLFileVersionImpl;

import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Akos Thurzo
 */
public class FileVersionConverter extends BaseXStreamConverter {

	@Override
	public boolean canConvert(Class clazz) {
		return clazz.equals(FileVersionProxyBean.class);
	}

	@Override
	public Object unmarshal(
		HierarchicalStreamReader reader,
		UnmarshallingContext unmarshallingContext) {

		DLFileVersion dlFileVersion = new DLFileVersionImpl();

		boolean escapedModel = false;

		while (reader.hasMoreChildren()) {
			reader.moveDown();

			String nodeName = reader.getNodeName();

			Class<?> clazz = BeanPropertiesUtil.getObjectType(
				dlFileVersion, nodeName);

			Object convertedValue = unmarshallingContext.convertAnother(
				reader.getValue(), clazz);

			if (fields.contains(nodeName)) {
				if (nodeName.equals(FieldConstants.ESCAPED_MODEL)) {
					escapedModel = (Boolean)convertedValue;
				}
				else {
					BeanPropertiesUtil.setProperty(
						dlFileVersion, nodeName, convertedValue);
				}
			}

			reader.moveUp();
		}

		return new LiferayFileVersion(dlFileVersion, escapedModel);
	}

	protected List<String> getFields() {
		return fields;
	}

	protected static List<String> fields = new LinkedList<String>();

	static {
		fields.add(FieldConstants.CHANGE_LOG);
		fields.add(FieldConstants.COMPANY_ID);
		fields.add(FieldConstants.CREATE_DATE);
		fields.add(FieldConstants.DESCRIPTION);
		fields.add(FieldConstants.ESCAPED_MODEL);
		fields.add(FieldConstants.EXTENSION);
		fields.add(FieldConstants.EXTRA_SETTINGS);
		fields.add(FieldConstants.FILE_ENTRY_ID);
		fields.add(FieldConstants.FILE_VERSION_ID);
		fields.add(FieldConstants.GROUP_ID);
		fields.add(FieldConstants.MIME_TYPE);
		fields.add(FieldConstants.MODIFIED_DATE);
		fields.add(FieldConstants.REPOSITORY_ID);
		fields.add(FieldConstants.SIZE);
		fields.add(FieldConstants.STATUS);
		fields.add(FieldConstants.STATUS_BY_USER_ID);
		fields.add(FieldConstants.STATUS_BY_USER_NAME);
		fields.add(FieldConstants.STATUS_DATE);
		fields.add(FieldConstants.TITLE);
		fields.add(FieldConstants.USER_ID);
		fields.add(FieldConstants.USER_NAME);
		fields.add(FieldConstants.UUID);
		fields.add(FieldConstants.VERSION);
	}

}