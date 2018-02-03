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

import com.liferay.shopping.model.ShoppingItem;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ShoppingItem in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingItem
 * @generated
 */
@ProviderType
public class ShoppingItemCacheModel implements CacheModel<ShoppingItem>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ShoppingItemCacheModel)) {
			return false;
		}

		ShoppingItemCacheModel shoppingItemCacheModel = (ShoppingItemCacheModel)obj;

		if (itemId == shoppingItemCacheModel.itemId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, itemId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(69);

		sb.append("{itemId=");
		sb.append(itemId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", categoryId=");
		sb.append(categoryId);
		sb.append(", sku=");
		sb.append(sku);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", properties=");
		sb.append(properties);
		sb.append(", fields=");
		sb.append(fields);
		sb.append(", fieldsQuantities=");
		sb.append(fieldsQuantities);
		sb.append(", minQuantity=");
		sb.append(minQuantity);
		sb.append(", maxQuantity=");
		sb.append(maxQuantity);
		sb.append(", price=");
		sb.append(price);
		sb.append(", discount=");
		sb.append(discount);
		sb.append(", taxable=");
		sb.append(taxable);
		sb.append(", shipping=");
		sb.append(shipping);
		sb.append(", useShippingFormula=");
		sb.append(useShippingFormula);
		sb.append(", requiresShipping=");
		sb.append(requiresShipping);
		sb.append(", stockQuantity=");
		sb.append(stockQuantity);
		sb.append(", featured=");
		sb.append(featured);
		sb.append(", sale=");
		sb.append(sale);
		sb.append(", smallImage=");
		sb.append(smallImage);
		sb.append(", smallImageId=");
		sb.append(smallImageId);
		sb.append(", smallImageURL=");
		sb.append(smallImageURL);
		sb.append(", mediumImage=");
		sb.append(mediumImage);
		sb.append(", mediumImageId=");
		sb.append(mediumImageId);
		sb.append(", mediumImageURL=");
		sb.append(mediumImageURL);
		sb.append(", largeImage=");
		sb.append(largeImage);
		sb.append(", largeImageId=");
		sb.append(largeImageId);
		sb.append(", largeImageURL=");
		sb.append(largeImageURL);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ShoppingItem toEntityModel() {
		ShoppingItemImpl shoppingItemImpl = new ShoppingItemImpl();

		shoppingItemImpl.setItemId(itemId);
		shoppingItemImpl.setGroupId(groupId);
		shoppingItemImpl.setCompanyId(companyId);
		shoppingItemImpl.setUserId(userId);

		if (userName == null) {
<<<<<<< HEAD
			shoppingItemImpl.setUserName("");
=======
			shoppingItemImpl.setUserName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			shoppingItemImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			shoppingItemImpl.setCreateDate(null);
		}
		else {
			shoppingItemImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			shoppingItemImpl.setModifiedDate(null);
		}
		else {
			shoppingItemImpl.setModifiedDate(new Date(modifiedDate));
		}

		shoppingItemImpl.setCategoryId(categoryId);

		if (sku == null) {
<<<<<<< HEAD
			shoppingItemImpl.setSku("");
=======
			shoppingItemImpl.setSku(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			shoppingItemImpl.setSku(sku);
		}

		if (name == null) {
<<<<<<< HEAD
			shoppingItemImpl.setName("");
=======
			shoppingItemImpl.setName(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			shoppingItemImpl.setName(name);
		}

		if (description == null) {
<<<<<<< HEAD
			shoppingItemImpl.setDescription("");
=======
			shoppingItemImpl.setDescription(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			shoppingItemImpl.setDescription(description);
		}

		if (properties == null) {
<<<<<<< HEAD
			shoppingItemImpl.setProperties("");
=======
			shoppingItemImpl.setProperties(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			shoppingItemImpl.setProperties(properties);
		}

		shoppingItemImpl.setFields(fields);

		if (fieldsQuantities == null) {
<<<<<<< HEAD
			shoppingItemImpl.setFieldsQuantities("");
=======
			shoppingItemImpl.setFieldsQuantities(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			shoppingItemImpl.setFieldsQuantities(fieldsQuantities);
		}

		shoppingItemImpl.setMinQuantity(minQuantity);
		shoppingItemImpl.setMaxQuantity(maxQuantity);
		shoppingItemImpl.setPrice(price);
		shoppingItemImpl.setDiscount(discount);
		shoppingItemImpl.setTaxable(taxable);
		shoppingItemImpl.setShipping(shipping);
		shoppingItemImpl.setUseShippingFormula(useShippingFormula);
		shoppingItemImpl.setRequiresShipping(requiresShipping);
		shoppingItemImpl.setStockQuantity(stockQuantity);
		shoppingItemImpl.setFeatured(featured);
		shoppingItemImpl.setSale(sale);
		shoppingItemImpl.setSmallImage(smallImage);
		shoppingItemImpl.setSmallImageId(smallImageId);

		if (smallImageURL == null) {
<<<<<<< HEAD
			shoppingItemImpl.setSmallImageURL("");
=======
			shoppingItemImpl.setSmallImageURL(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			shoppingItemImpl.setSmallImageURL(smallImageURL);
		}

		shoppingItemImpl.setMediumImage(mediumImage);
		shoppingItemImpl.setMediumImageId(mediumImageId);

		if (mediumImageURL == null) {
<<<<<<< HEAD
			shoppingItemImpl.setMediumImageURL("");
=======
			shoppingItemImpl.setMediumImageURL(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			shoppingItemImpl.setMediumImageURL(mediumImageURL);
		}

		shoppingItemImpl.setLargeImage(largeImage);
		shoppingItemImpl.setLargeImageId(largeImageId);

		if (largeImageURL == null) {
<<<<<<< HEAD
			shoppingItemImpl.setLargeImageURL("");
=======
			shoppingItemImpl.setLargeImageURL(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			shoppingItemImpl.setLargeImageURL(largeImageURL);
		}

		shoppingItemImpl.resetOriginalValues();

		return shoppingItemImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		categoryId = objectInput.readLong();
		sku = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		properties = objectInput.readUTF();

		fields = objectInput.readBoolean();
		fieldsQuantities = objectInput.readUTF();

		minQuantity = objectInput.readInt();

		maxQuantity = objectInput.readInt();

		price = objectInput.readDouble();

		discount = objectInput.readDouble();

		taxable = objectInput.readBoolean();

		shipping = objectInput.readDouble();

		useShippingFormula = objectInput.readBoolean();

		requiresShipping = objectInput.readBoolean();

		stockQuantity = objectInput.readInt();

		featured = objectInput.readBoolean();

		sale = objectInput.readBoolean();

		smallImage = objectInput.readBoolean();

		smallImageId = objectInput.readLong();
		smallImageURL = objectInput.readUTF();

		mediumImage = objectInput.readBoolean();

		mediumImageId = objectInput.readLong();
		mediumImageURL = objectInput.readUTF();

		largeImage = objectInput.readBoolean();

		largeImageId = objectInput.readLong();
		largeImageURL = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(itemId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(categoryId);

		if (sku == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(sku);
		}

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

		if (properties == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(properties);
		}

		objectOutput.writeBoolean(fields);

		if (fieldsQuantities == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(fieldsQuantities);
		}

		objectOutput.writeInt(minQuantity);

		objectOutput.writeInt(maxQuantity);

		objectOutput.writeDouble(price);

		objectOutput.writeDouble(discount);

		objectOutput.writeBoolean(taxable);

		objectOutput.writeDouble(shipping);

		objectOutput.writeBoolean(useShippingFormula);

		objectOutput.writeBoolean(requiresShipping);

		objectOutput.writeInt(stockQuantity);

		objectOutput.writeBoolean(featured);

		objectOutput.writeBoolean(sale);

		objectOutput.writeBoolean(smallImage);

		objectOutput.writeLong(smallImageId);

		if (smallImageURL == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(smallImageURL);
		}

		objectOutput.writeBoolean(mediumImage);

		objectOutput.writeLong(mediumImageId);

		if (mediumImageURL == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(mediumImageURL);
		}

		objectOutput.writeBoolean(largeImage);

		objectOutput.writeLong(largeImageId);

		if (largeImageURL == null) {
<<<<<<< HEAD
			objectOutput.writeUTF("");
=======
			objectOutput.writeUTF(StringPool.BLANK);
>>>>>>> compatible
		}
		else {
			objectOutput.writeUTF(largeImageURL);
		}
	}

	public long itemId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long categoryId;
	public String sku;
	public String name;
	public String description;
	public String properties;
	public boolean fields;
	public String fieldsQuantities;
	public int minQuantity;
	public int maxQuantity;
	public double price;
	public double discount;
	public boolean taxable;
	public double shipping;
	public boolean useShippingFormula;
	public boolean requiresShipping;
	public int stockQuantity;
	public boolean featured;
	public boolean sale;
	public boolean smallImage;
	public long smallImageId;
	public String smallImageURL;
	public boolean mediumImage;
	public long mediumImageId;
	public String mediumImageURL;
	public boolean largeImage;
	public long largeImageId;
	public String largeImageURL;
}