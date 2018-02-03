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

package com.liferay.alloy.mvc;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
<<<<<<< HEAD
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiServiceUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
=======
import com.liferay.portal.kernel.util.OrderByComparator;
>>>>>>> compatible
import com.liferay.portal.kernel.util.TextFormatter;

import java.lang.reflect.Method;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class AlloyServiceInvoker {

	public AlloyServiceInvoker(String className) {
<<<<<<< HEAD
=======
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

>>>>>>> compatible
		int pos = className.indexOf(".model.");

		String simpleClassName = className.substring(pos + 7);

<<<<<<< HEAD
		String serviceClassName = StringBundler.concat(
			className.substring(0, pos), ".service.", simpleClassName,
			"LocalService");

		try {
			identifiableOSGiService =
				IdentifiableOSGiServiceUtil.getIdentifiableOSGiService(
					serviceClassName);

			Class<?> serviceClass = identifiableOSGiService.getClass();

			createModelMethod = serviceClass.getMethod(
				"create" + simpleClassName, new Class<?>[] {long.class});

			Class<?> modelClass = createModelMethod.getReturnType();
=======
		String serviceClassName =
			className.substring(0, pos) + ".service." + simpleClassName +
				"LocalServiceUtil";

		try {
			Class<?> serviceClass = classLoader.loadClass(serviceClassName);
			Class<?> modelClass = classLoader.loadClass(className);
>>>>>>> compatible

			addModelMethod = serviceClass.getMethod(
				"add" + simpleClassName, new Class<?>[] {modelClass});

<<<<<<< HEAD
=======
			createModelMethod = serviceClass.getMethod(
				"create" + simpleClassName, new Class<?>[] {long.class});
>>>>>>> compatible
			deleteModelMethod = serviceClass.getMethod(
				"delete" + simpleClassName, new Class<?>[] {long.class});
			dynamicQueryCountMethod1 = serviceClass.getMethod(
				"dynamicQueryCount", new Class<?>[] {DynamicQuery.class});
			dynamicQueryCountMethod2 = serviceClass.getMethod(
				"dynamicQueryCount",
				new Class<?>[] {DynamicQuery.class, Projection.class});
			dynamicQueryMethod1 = serviceClass.getMethod(
				"dynamicQuery", new Class<?>[0]);
			dynamicQueryMethod2 = serviceClass.getMethod(
				"dynamicQuery", new Class<?>[] {DynamicQuery.class});
			dynamicQueryMethod3 = serviceClass.getMethod(
				"dynamicQuery",
				new Class<?>[] {DynamicQuery.class, int.class, int.class});
			dynamicQueryMethod4 = serviceClass.getMethod(
				"dynamicQuery",
				new Class<?>[] {
					DynamicQuery.class, int.class, int.class,
					OrderByComparator.class
				});
			fetchModelMethod = serviceClass.getMethod(
				"fetch" + simpleClassName, new Class<?>[] {long.class});
			getModelMethod = serviceClass.getMethod(
				"get" + simpleClassName, new Class<?>[] {long.class});
			getModelsCountMethod = serviceClass.getMethod(
				"get" + TextFormatter.formatPlural(simpleClassName) + "Count",
				new Class<?>[0]);
			getModelsMethod = serviceClass.getMethod(
				"get" + TextFormatter.formatPlural(simpleClassName),
				new Class<?>[] {int.class, int.class});
			updateModelMethod = serviceClass.getMethod(
				"update" + simpleClassName, new Class<?>[] {modelClass});
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public BaseModel addModel(BaseModel baseModel) throws Exception {
<<<<<<< HEAD
		return (BaseModel<?>)addModelMethod.invoke(
			identifiableOSGiService, baseModel);
	}

	public DynamicQuery buildDynamicQuery() throws Exception {
		return (DynamicQuery)dynamicQueryMethod1.invoke(
			identifiableOSGiService);
=======
		return (BaseModel<?>)addModelMethod.invoke(false, baseModel);
	}

	public DynamicQuery buildDynamicQuery() throws Exception {
		return (DynamicQuery)dynamicQueryMethod1.invoke(false);
>>>>>>> compatible
	}

	public DynamicQuery buildDynamicQuery(Object[] properties)
		throws Exception {

		if ((properties.length == 0) || ((properties.length % 2) != 0)) {
			throw new IllegalArgumentException(
				"Properties length is not an even number");
		}

		DynamicQuery dynamicQuery = buildDynamicQuery();

		for (int i = 0; i < properties.length; i += 2) {
			String propertyName = String.valueOf(properties[i]);

			Property property = PropertyFactoryUtil.forName(propertyName);

			Object propertyValue = properties[i + 1];

			dynamicQuery.add(property.eq(propertyValue));
		}

		return dynamicQuery;
	}

	public BaseModel createModel(long id) throws Exception {
<<<<<<< HEAD
		return (BaseModel<?>)createModelMethod.invoke(
			identifiableOSGiService, id);
=======
		return (BaseModel<?>)createModelMethod.invoke(false, id);
>>>>>>> compatible
	}

	public BaseModel<?> deleteModel(BaseModel<?> baseModel) throws Exception {
		return (BaseModel<?>)deleteModelMethod.invoke(
<<<<<<< HEAD
			identifiableOSGiService, baseModel.getPrimaryKeyObj());
	}

	public BaseModel<?> deleteModel(long classPK) throws Exception {
		return (BaseModel<?>)deleteModelMethod.invoke(
			identifiableOSGiService, classPK);
=======
			false, baseModel.getPrimaryKeyObj());
	}

	public BaseModel<?> deleteModel(long classPK) throws Exception {
		return (BaseModel<?>)deleteModelMethod.invoke(false, classPK);
>>>>>>> compatible
	}

	/**
	 * @deprecated As of 1.0.0, replaced by {@link
	 *             #executeDynamicQuery(DynamicQuery)}
	 */
	@Deprecated
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery) throws Exception {
		return executeDynamicQuery(dynamicQuery);
	}

	/**
	 * @deprecated As of 1.0.0, replaced by {@link
	 *             #executeDynamicQueryCount(DynamicQuery)}
	 */
	@Deprecated
	public long dynamicQueryCount(DynamicQuery dynamicQuery) throws Exception {
		return executeDynamicQueryCount(dynamicQuery);
	}

	@SuppressWarnings("rawtypes")
	public List executeDynamicQuery(DynamicQuery dynamicQuery)
		throws Exception {

<<<<<<< HEAD
		return (List)dynamicQueryMethod2.invoke(
			identifiableOSGiService, dynamicQuery);
=======
		return (List)dynamicQueryMethod2.invoke(false, dynamicQuery);
>>>>>>> compatible
	}

	@SuppressWarnings("rawtypes")
	public List executeDynamicQuery(
			DynamicQuery dynamicQuery, int start, int end)
		throws Exception {

		return (List)dynamicQueryMethod3.invoke(
<<<<<<< HEAD
			identifiableOSGiService, dynamicQuery, start, end);
=======
			false, dynamicQuery, start, end);
>>>>>>> compatible
	}

	@SuppressWarnings("rawtypes")
	public List executeDynamicQuery(
			DynamicQuery dynamicQuery, int start, int end,
			OrderByComparator<?> obc)
		throws Exception {

		return (List)dynamicQueryMethod4.invoke(
<<<<<<< HEAD
			identifiableOSGiService, dynamicQuery, start, end, obc);
=======
			false, dynamicQuery, start, end, obc);
>>>>>>> compatible
	}

	@SuppressWarnings("rawtypes")
	public List executeDynamicQuery(Object[] properties) throws Exception {
		return executeDynamicQuery(buildDynamicQuery(properties));
	}

	@SuppressWarnings("rawtypes")
	public List executeDynamicQuery(Object[] properties, int start, int end)
		throws Exception {

		return executeDynamicQuery(buildDynamicQuery(properties), start, end);
	}

	@SuppressWarnings("rawtypes")
	public List executeDynamicQuery(
			Object[] properties, int start, int end, OrderByComparator<?> obc)
		throws Exception {

		return executeDynamicQuery(
			buildDynamicQuery(properties), start, end, obc);
	}

	public long executeDynamicQueryCount(DynamicQuery dynamicQuery)
		throws Exception {

<<<<<<< HEAD
		return (Long)dynamicQueryCountMethod1.invoke(
			identifiableOSGiService, dynamicQuery);
=======
		return (Long)dynamicQueryCountMethod1.invoke(false, dynamicQuery);
>>>>>>> compatible
	}

	public long executeDynamicQueryCount(
			DynamicQuery dynamicQuery, Projection projection)
		throws Exception {

		return (Long)dynamicQueryCountMethod2.invoke(
<<<<<<< HEAD
			identifiableOSGiService, dynamicQuery, projection);
=======
			false, dynamicQuery, projection);
>>>>>>> compatible
	}

	public long executeDynamicQueryCount(Object[] properties) throws Exception {
		return executeDynamicQueryCount(buildDynamicQuery(properties));
	}

	public BaseModel<?> fetchModel(long classPK) throws Exception {
<<<<<<< HEAD
		return (BaseModel<?>)fetchModelMethod.invoke(
			identifiableOSGiService, classPK);
	}

	public BaseModel<?> getModel(long classPK) throws Exception {
		return (BaseModel<?>)getModelMethod.invoke(
			identifiableOSGiService, classPK);
=======
		return (BaseModel<?>)fetchModelMethod.invoke(false, classPK);
	}

	public BaseModel<?> getModel(long classPK) throws Exception {
		return (BaseModel<?>)getModelMethod.invoke(false, classPK);
>>>>>>> compatible
	}

	@SuppressWarnings("rawtypes")
	public List getModels(int start, int end) throws Exception {
<<<<<<< HEAD
		return (List)getModelsMethod.invoke(
			identifiableOSGiService, start, end);
	}

	public int getModelsCount() throws Exception {
		return (Integer)getModelsCountMethod.invoke(identifiableOSGiService);
	}

	public BaseModel<?> updateModel(BaseModel baseModel) throws Exception {
		return (BaseModel<?>)updateModelMethod.invoke(
			identifiableOSGiService, baseModel);
=======
		return (List)getModelsMethod.invoke(false, start, end);
	}

	public int getModelsCount() throws Exception {
		return (Integer)getModelsCountMethod.invoke(false);
	}

	public BaseModel updateModel(BaseModel baseModel) throws Exception {
		return (BaseModel<?>)updateModelMethod.invoke(false, baseModel);
>>>>>>> compatible
	}

	protected Method addModelMethod;
	protected Method createModelMethod;
	protected Method deleteModelMethod;
	protected Method dynamicQueryCountMethod1;
	protected Method dynamicQueryCountMethod2;
	protected Method dynamicQueryMethod1;
	protected Method dynamicQueryMethod2;
	protected Method dynamicQueryMethod3;
	protected Method dynamicQueryMethod4;
	protected Method fetchModelMethod;
	protected Method getModelMethod;
	protected Method getModelsCountMethod;
	protected Method getModelsMethod;
<<<<<<< HEAD
	protected IdentifiableOSGiService identifiableOSGiService;
=======
>>>>>>> compatible
	protected Method updateModelMethod;

}