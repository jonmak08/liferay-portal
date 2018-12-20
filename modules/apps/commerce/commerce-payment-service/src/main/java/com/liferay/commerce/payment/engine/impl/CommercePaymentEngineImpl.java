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

package com.liferay.commerce.payment.engine.impl;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.constants.CommercePaymentConstants;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.method.CommercePaymentMethodRegistry;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.request.CommercePaymentRequest;
import com.liferay.commerce.payment.request.CommercePaymentRequestProvider;
import com.liferay.commerce.payment.request.CommercePaymentRequestProviderRegistry;
import com.liferay.commerce.payment.result.CommercePaymentResult;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceOrderPaymentLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(immediate = true, service = CommercePaymentEngine.class)
public class CommercePaymentEngineImpl implements CommercePaymentEngine {

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult cancelPayment(
			long commerceOrderId, String transactionId,
			ServletRequest servletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isCancelEnabled()) {

			return _emptyResult(commerceOrderId);
		}

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		HttpServletRequest request =
			(HttpServletRequest)servletRequest;

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(request), transactionId, null,
				request, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.cancelPayment(commercePaymentRequest);

		_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			commerceOrder.getUserId(), commerceOrderId,
			CommerceOrderConstants.ORDER_STATUS_CANCELLED, transactionId);

		_commerceOrderPaymentLocalService.addCommerceOrderPayment(
			commerceOrderId, commercePaymentResult.getNewPaymentStatus(), "");

		return commercePaymentResult;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult cancelRecurringPayment(long commerceOrderId) {
		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult capturePayment(
			long commerceOrderId, String transactionId,
			ServletRequest servletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isCaptureEnabled()) {

			return _emptyResult(commerceOrderId);
		}

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		HttpServletRequest request =
			(HttpServletRequest)servletRequest;

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(request), transactionId, null,
				request, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.capturePayment(commercePaymentRequest);

		_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			commerceOrder.getUserId(), commerceOrderId,
			commercePaymentResult.getNewPaymentStatus(),
			commercePaymentResult.getAuthTransactionId());

		_commerceOrderPaymentLocalService.addCommerceOrderPayment(
			commerceOrderId, commercePaymentResult.getNewPaymentStatus(), "");

		return commercePaymentResult;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult completePayment(
			long commerceOrderId, String transactionId,
			ServletRequest servletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isCompleteEnabled()) {

			return _emptyResult(commerceOrderId);
		}

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		HttpServletRequest request =
			(HttpServletRequest)servletRequest;

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(request), transactionId, null,
				request, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.completePayment(commercePaymentRequest);

		_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			commerceOrder.getUserId(), commerceOrderId,
			commercePaymentResult.getNewPaymentStatus(),
			commercePaymentResult.getAuthTransactionId());

		_commerceOrderPaymentLocalService.addCommerceOrderPayment(
			commerceOrderId, commercePaymentResult.getNewPaymentStatus(), "");

		return commercePaymentResult;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult completeRecurringPayment(
			long commerceOrderId, String transactionId,
			ServletRequest servletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isCompleteRecurringEnabled()) {

			return _emptyResult(commerceOrderId);
		}

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		HttpServletRequest request =
			(HttpServletRequest)servletRequest;

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(request), transactionId, null,
				request, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.completeRecurringPayment(
				commercePaymentRequest);

		_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			commerceOrder.getUserId(), commerceOrderId,
			commercePaymentResult.getNewPaymentStatus(),
			commercePaymentResult.getAuthTransactionId());

		_commerceOrderPaymentLocalService.addCommerceOrderPayment(
			commerceOrderId, commercePaymentResult.getNewPaymentStatus(), "");

		return commercePaymentResult;
	}

	@Override
	public int getCommercePaymentMethodGroupRelsCount(long groupId) {
		return _commercePaymentMethodGroupRelLocalService.
			getCommercePaymentMethodGroupRelsCount(groupId, true);
	}

	@Override
	public List<CommercePaymentMethod> getCommercePaymentMethods(
			long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		boolean subscriptionOrder = commerceOrder.isSubscriptionOrder();

		CommerceAddress commerceAddress = commerceOrder.getBillingAddress();

		if (commerceAddress != null) {
			return _getCommercePaymentMethodsList(
				_commercePaymentMethodGroupRelLocalService.
					getCommercePaymentMethodGroupRels(
						commerceOrder.getSiteGroupId(),
						commerceAddress.getCommerceCountryId(), true),
				subscriptionOrder);
		}

		return _getCommercePaymentMethodsList(
			_commercePaymentMethodGroupRelLocalService.
				getCommercePaymentMethodGroupRels(
					commerceOrder.getSiteGroupId(), true),
			subscriptionOrder);
	}

	@Override
	public int getCommercePaymentMethodType(long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		String commercePaymentMethodKey =
			commerceOrder.getCommercePaymentMethodKey();

		if (commercePaymentMethodKey.isEmpty()) {
			return -1;
		}

		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentMethodRegistry.getCommercePaymentMethod(
				commercePaymentMethodKey);

		return commercePaymentMethod.getPaymentType();
	}

	@Override
	public String getPaymentMethodName(String paymentMethodKey, Locale locale) {
		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentMethodRegistry.getCommercePaymentMethod(
				paymentMethodKey);

		return commercePaymentMethod.getName(locale);
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult partiallyRefundPayment(long commerceOrderId) {
		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult postProcessPayment(long commerceOrderId)
		throws Exception {

		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult processPayment(
			long commerceOrderId, String checkoutStepUrl,
			ServletRequest servletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isProcessPaymentEnabled()) {

			return _emptyResult(commerceOrderId);
		}

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		HttpServletRequest request =
			(HttpServletRequest)servletRequest;

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(request), null,
				checkoutStepUrl, request, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.processPayment(commercePaymentRequest);

		_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			commerceOrder.getUserId(), commerceOrderId,
			commercePaymentResult.getNewPaymentStatus(),
			commercePaymentResult.getAuthTransactionId());

		return commercePaymentResult;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult processRecurringPayment(
			long commerceOrderId, String checkoutStepUrl,
			ServletRequest servletRequest)
		throws Exception {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isProcessRecurringEnabled()) {

			return _emptyResult(commerceOrderId);
		}

		HttpServletRequest request =
			(HttpServletRequest)servletRequest;

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(request), null,
				checkoutStepUrl, request, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.processRecurringPayment(
				commercePaymentRequest);

		_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			commerceOrder.getUserId(), commerceOrderId,
			commercePaymentResult.getNewPaymentStatus(),
			commercePaymentResult.getAuthTransactionId());

		return commercePaymentResult;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult refundPayment(
			long commerceOrderId, String transactionId,
			ServletRequest servletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isRefundEnabled()) {

			return new CommercePaymentResult(
				null, commerceOrderId, -1, false, null, null,
				Collections.emptyList(), false);
		}

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		HttpServletRequest request =
			(HttpServletRequest)servletRequest;

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(request), transactionId, null,
				request, commercePaymentMethod);

		return commercePaymentMethod.refundPayment(commercePaymentRequest);
	}

	@Override
	public CommercePaymentResult startPayment(
			long commerceOrderId, String checkoutStepUrl,
			ServletRequest servletRequest)
		throws Exception {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		HttpServletRequest request =
			(HttpServletRequest)servletRequest;

		if (commerceOrder.isSubscriptionOrder()) {
			return processRecurringPayment(
				commerceOrderId, checkoutStepUrl, request);
		}

		return processPayment(commerceOrderId, checkoutStepUrl, request);
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult voidTransaction(
			long commerceOrderId, String transactionId,
			ServletRequest servletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isVoidEnabled()) {

			return _emptyResult(commerceOrderId);
		}

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		HttpServletRequest request =
			(HttpServletRequest)servletRequest;

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(request), null, null, request,
				commercePaymentMethod);

		return commercePaymentMethod.voidTransaction(commercePaymentRequest);
	}

	private CommercePaymentResult _emptyResult(long commerceOrderId) {
		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	private StringBundler _getBaseUrl(
		ServletRequest servletRequest, CommerceOrder commerceOrder,
		String redirect, CommercePaymentMethod commercePaymentMethod) {

		HttpServletRequest request =
			(HttpServletRequest)servletRequest;

		StringBundler sb = new StringBundler(11);

		sb.append(_portal.getPortalURL(request));
		sb.append(_portal.getPathModule());
		sb.append(CharPool.SLASH);
		sb.append(commercePaymentMethod.getServletPath());
		sb.append(CharPool.QUESTION);
		sb.append("groupId=");
		sb.append(commerceOrder.getGroupId());
		sb.append("&uuid=");
		sb.append(URLCodec.encodeURL(commerceOrder.getUuid()));

		if (Validator.isNotNull(redirect)) {
			sb.append("&redirect=");
			sb.append(URLCodec.encodeURL(redirect));
		}

		return sb;
	}

	private String _getCancelUrl(
		ServletRequest servletRequest, CommerceOrder commerceOrder,
		String redirect, CommercePaymentMethod commercePaymentMethod) {

		HttpServletRequest request =
			(HttpServletRequest)servletRequest;

		StringBundler sb = _getBaseUrl(
			request, commerceOrder, redirect, commercePaymentMethod);

		sb.append("&cancel=");
		sb.append(StringPool.TRUE);

		return sb.toString();
	}

	private CommercePaymentMethod _getCommercePaymentMethod(
			long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		String commercePaymentMethodKey =
			commerceOrder.getCommercePaymentMethodKey();

		if (commercePaymentMethodKey.isEmpty()) {
			return null;
		}

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			_commercePaymentMethodGroupRelLocalService.
				getCommercePaymentMethodGroupRel(
					commerceOrder.getGroupId(), commercePaymentMethodKey);

		if ((commercePaymentMethodGroupRel != null) &&
			commercePaymentMethodGroupRel.isActive()) {

			return _commercePaymentMethodRegistry.getCommercePaymentMethod(
				commercePaymentMethodGroupRel.getEngineKey());
		}

		return null;
	}

	private List<CommercePaymentMethod> _getCommercePaymentMethodsList(
		List<CommercePaymentMethodGroupRel> commercePaymentMethodGroupRels,
		boolean subscriptionOrder) {

		List<CommercePaymentMethod> commercePaymentMethods = new ArrayList<>();

		for (CommercePaymentMethodGroupRel commercePaymentMethodGroupRel :
				commercePaymentMethodGroupRels) {

			CommercePaymentMethod commercePaymentMethod =
				_commercePaymentMethodRegistry.getCommercePaymentMethod(
					commercePaymentMethodGroupRel.getEngineKey());

			if (!subscriptionOrder ||
				(subscriptionOrder &&
				 commercePaymentMethod.isProcessRecurringEnabled())) {

				commercePaymentMethods.add(commercePaymentMethod);
			}
		}

		return commercePaymentMethods;
	}

	private CommercePaymentRequest _getCommercePaymentRequest(
			CommerceOrder commerceOrder, Locale locale, String transactionId,
			String checkoutStepUrl, ServletRequest servletRequest,
			CommercePaymentMethod commercePaymentMethod)
		throws PortalException {

		String returnUrl = null;
		String cancelUrl = null;

		HttpServletRequest request =
			(HttpServletRequest)servletRequest;

		if (CommercePaymentConstants.
				COMMERCE_PAYMENT_METHOD_TYPE_ONLINE_REDIRECT ==
					commercePaymentMethod.getPaymentType()) {

			returnUrl = _getReturnUrl(
				request, commerceOrder, checkoutStepUrl, commercePaymentMethod);
			cancelUrl = _getCancelUrl(
				request, commerceOrder, checkoutStepUrl, commercePaymentMethod);
		}

		CommercePaymentRequestProvider commercePaymentRequestProvider =
			_getCommercePaymentRequestProvider(commercePaymentMethod);

		return commercePaymentRequestProvider.getCommercePaymentRequest(
			cancelUrl, commerceOrder.getCommerceOrderId(), request, locale,
			returnUrl, transactionId);
	}

	private CommercePaymentRequestProvider _getCommercePaymentRequestProvider(
		CommercePaymentMethod commercePaymentMethod) {

		CommercePaymentRequestProvider commercePaymentRequestProvider =
			_commercePaymentRequestProviderRegistry.
				getCommercePaymentRequestProvider(
					commercePaymentMethod.getKey());

		if (commercePaymentRequestProvider == null) {
			commercePaymentRequestProvider =
				_commercePaymentRequestProviderRegistry.
					getCommercePaymentRequestProvider(
						CommercePaymentConstants.
							DEFAULT_PAYMENT_REQUEST_PROVIDER_KEY);
		}

		return commercePaymentRequestProvider;
	}

	private String _getReturnUrl(
		ServletRequest servletRequest, CommerceOrder commerceOrder,
		String redirect, CommercePaymentMethod commercePaymentMethod) {

		HttpServletRequest httpServletRequest =
			(HttpServletRequest)servletRequest;

		StringBundler sb = _getBaseUrl(
			request, commerceOrder, redirect, commercePaymentMethod);

		return sb.toString();
	}

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CommerceOrderPaymentLocalService _commerceOrderPaymentLocalService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommercePaymentMethodGroupRelLocalService
		_commercePaymentMethodGroupRelLocalService;

	@Reference
	private CommercePaymentMethodRegistry _commercePaymentMethodRegistry;

	@Reference
	private CommercePaymentRequestProviderRegistry
		_commercePaymentRequestProviderRegistry;

	@Reference
	private Portal _portal;

}