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

package com.liferay.portlet.asset.service.impl;

<<<<<<< HEAD
import com.liferay.asset.kernel.model.AssetCategoryProperty;
import com.liferay.portal.kernel.exception.PortalException;
=======
import com.liferay.asset.kernel.exception.CategoryPropertyKeyException;
import com.liferay.asset.kernel.exception.CategoryPropertyValueException;
import com.liferay.asset.kernel.exception.DuplicateCategoryPropertyException;
import com.liferay.asset.kernel.model.AssetCategoryProperty;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
>>>>>>> compatible
import com.liferay.portlet.asset.service.base.AssetCategoryPropertyLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @deprecated As of 7.0.0, replaced by {@link
 *             com.liferay.asset.category.property.service.impl.AssetCategoryPropertyLocalServiceImpl}
 */
@Deprecated
public class AssetCategoryPropertyLocalServiceImpl
	extends AssetCategoryPropertyLocalServiceBaseImpl {

	@Override
	public AssetCategoryProperty addCategoryProperty(
			long userId, long categoryId, String key, String value)
		throws PortalException {

<<<<<<< HEAD
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.category.property.service.impl" +
					"AssetCategoryPropertyLocalServiceImpl");
=======
		User user = userPersistence.findByPrimaryKey(userId);

		validate(key, value);

		if (hasCategoryProperty(categoryId, key)) {
			throw new DuplicateCategoryPropertyException(
				"A category property already exists with the key " + key);
		}

		long categoryPropertyId = counterLocalService.increment();

		AssetCategoryProperty categoryProperty =
			assetCategoryPropertyPersistence.create(categoryPropertyId);

		categoryProperty.setCompanyId(user.getCompanyId());
		categoryProperty.setUserId(user.getUserId());
		categoryProperty.setUserName(user.getFullName());
		categoryProperty.setCategoryId(categoryId);
		categoryProperty.setKey(key);
		categoryProperty.setValue(value);

		assetCategoryPropertyPersistence.update(categoryProperty);

		return categoryProperty;
>>>>>>> compatible
	}

	@Override
	public void deleteCategoryProperties(long entryId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.category.property.service.impl" +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public void deleteCategoryProperty(AssetCategoryProperty categoryProperty) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.category.property.service.impl" +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public void deleteCategoryProperty(long categoryPropertyId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.category.property.service.impl" +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public List<AssetCategoryProperty> getCategoryProperties() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.category.property.service.impl" +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public List<AssetCategoryProperty> getCategoryProperties(long entryId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.category.property.service.impl" +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public AssetCategoryProperty getCategoryProperty(long categoryPropertyId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.category.property.service.impl" +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public AssetCategoryProperty getCategoryProperty(
			long categoryId, String key)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.category.property.service.impl" +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public List<AssetCategoryProperty> getCategoryPropertyValues(
		long groupId, String key) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.category.property.service.impl" +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public AssetCategoryProperty updateCategoryProperty(
			long userId, long categoryPropertyId, String key, String value)
		throws PortalException {

<<<<<<< HEAD
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.category.property.service.impl" +
					"AssetCategoryPropertyLocalServiceImpl");
=======
		AssetCategoryProperty categoryProperty =
			assetCategoryPropertyPersistence.findByPrimaryKey(
				categoryPropertyId);

		if (!categoryProperty.getKey().equals(key) &&
			hasCategoryProperty(categoryProperty.getCategoryId(), key)) {

			throw new DuplicateCategoryPropertyException(
				"A category property already exists with the key " + key);
		}

		validate(key, value);

		if (userId != 0) {
			User user = userPersistence.findByPrimaryKey(userId);

			categoryProperty.setUserId(userId);
			categoryProperty.setUserName(user.getFullName());
		}

		categoryProperty.setKey(key);
		categoryProperty.setValue(value);

		assetCategoryPropertyPersistence.update(categoryProperty);

		return categoryProperty;
>>>>>>> compatible
	}

	@Override
	public AssetCategoryProperty updateCategoryProperty(
			long categoryPropertyId, String key, String value)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.category.property.service.impl" +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	protected boolean hasCategoryProperty(long categoryId, String key) {
<<<<<<< HEAD
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.category.property.service.impl" +
					"AssetCategoryPropertyLocalServiceImpl");
	}
=======
		AssetCategoryProperty categoryProperty =
			assetCategoryPropertyPersistence.fetchByCA_K(categoryId, key);

		if (categoryProperty != null) {
			return true;
		}

		return false;
	}

	protected void validate(String key, String value) throws PortalException {
		if (!AssetUtil.isValidWord(key)) {
			throw new CategoryPropertyKeyException("Invalid key " + key);
		}
>>>>>>> compatible

	protected void validate(String key, String value) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.category.property.service.impl" +
					"AssetCategoryPropertyLocalServiceImpl");
	}

}