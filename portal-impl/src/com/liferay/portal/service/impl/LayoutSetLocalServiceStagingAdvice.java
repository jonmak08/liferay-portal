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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.staging.LayoutStagingUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetStagingHandler;
import com.liferay.portal.staging.StagingAdvicesThreadLocal;
import com.liferay.portal.util.ClassLoaderUtil;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class LayoutSetLocalServiceStagingAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Object returnValue = methodInvocation.proceed();

		if (!StagingAdvicesThreadLocal.isEnabled()) {
			return returnValue;
		}

		if (returnValue instanceof LayoutSet) {
			return wrapLayoutSet((LayoutSet)returnValue);
		}

		if (returnValue instanceof List<?>) {
			List<?> list = (List<?>)returnValue;

			if (!list.isEmpty() && (list.get(0) instanceof LayoutSet)) {
				returnValue = wrapLayoutSets((List<LayoutSet>)returnValue);
			}
		}

		return returnValue;
	}

	protected LayoutSet wrapLayoutSet(LayoutSet layoutSet)
		throws SystemException {

		LayoutSetStagingHandler layoutSetStagingHandler =
			LayoutStagingUtil.getLayoutSetStagingHandler(layoutSet);

		if (layoutSetStagingHandler != null) {
			return layoutSet;
		}

		try {
			if (!LayoutStagingUtil.isBranchingLayoutSet(
					layoutSet.getGroup(), layoutSet.getPrivateLayout())) {

				return layoutSet;
			}
		}
		catch (PortalException pe) {
			return layoutSet;
		}

		return (LayoutSet)ProxyUtil.newProxyInstance(
			ClassLoaderUtil.getPortalClassLoader(),
			new Class[] {LayoutSet.class},
			new LayoutSetStagingHandler(layoutSet));
	}

	protected List<LayoutSet> wrapLayoutSets(List<LayoutSet> layoutSets)
		throws SystemException {

		if (layoutSets.isEmpty()) {
			return layoutSets;
		}

		List<LayoutSet> wrappedLayoutSets = new ArrayList<LayoutSet>(
			layoutSets.size());

		for (LayoutSet layoutSet : layoutSets) {
			wrappedLayoutSets.add(wrapLayoutSet(layoutSet));
		}

		return wrappedLayoutSets;
	}

}