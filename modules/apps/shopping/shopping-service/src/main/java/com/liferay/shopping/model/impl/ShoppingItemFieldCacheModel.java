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

package com.liferay.shopping.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
<<<<<<< HEAD
=======
import com.liferay.portal.kernel.util.StringPool;
>>>>>>> compatible

import com.liferay.shopping.model.ShoppingItemField;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ShoppingItemField in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingItemField
 * @generated
 */
@ProviderType
public class ShoppingItemFieldCacheModel implements CacheModel<ShoppingItemField>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ShoppingItemFieldCacheModel)) {
			return false;
		}

		ShoppingItemFieldCacheModel shoppingItemFieldCacheModel = (ShoppingItemFieldCacheModel)obj;

		if (itemFieldId == shoppingItemFieldCacheModel.itemFieldId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, itemFieldId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{itemFieldId=");
		sb.append(itemFieldId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", values=");
		sb.append(values);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ShoppingItemField toEntityModel() {
		ShoppingItemFieldImpl shoppingItemFieldImpl = new ShoppingItemFieldImpl();

		shoppingItemFieldImpl.setItemFieldId(itemFieldId);
		shoppingItemFieldImpl.setCompanyId(companyId);
		shoppingItemFieldImpl.setItemId(itemId);

		if (name == null) {
<<<<<<< HEAD
			shoppingItemFieldImpl.setName("");
=======
			shoppingItemFieldImpl.setName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			shoppingItemFieldImpl.setName(name);
		}

		if (values == null) {
<<<<<<< HEAD
			shoppingItemFieldImpl.setValues("");
=======
			shoppingItemFieldImpl.setValues(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			shoppingItemFieldImpl.setValues(values);
		}

		if (description == null) {
<<<<<<< HEAD
			shoppingItemFieldImpl.setDescription("");
=======
			shoppingItemFieldImpl.setDescription(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			shoppingItemFieldImpl.setDescription(description);
		}

		shoppingItemFieldImpl.resetOriginalValues();

		return shoppingItemFieldImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemFieldId = objectInput.readLong();

		companyId = objectInput.readLong();

		itemId = objectInput.readLong();
		name = objectInput.readUTF();
		values = objectInput.readUTF();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(itemFieldId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(itemId);

		if (name == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (values == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(values);
		}

		if (description == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public long itemFieldId;
	public long companyId;
	public long itemId;
	public String name;
	public String values;
	public String description;
}