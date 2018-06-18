package com.liferay.commerce.data.integration.apio.internal.resource;

import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.NestedCollectionResource;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.data.integration.apio.identifiers.AccountIdentifier;
import com.liferay.commerce.data.integration.apio.identifiers.AddressIdentifier;
import com.liferay.commerce.data.integration.apio.identifiers.OrderIdentifier;
import com.liferay.commerce.data.integration.apio.identifiers.PaymentMethodIdentifier;
import com.liferay.commerce.data.integration.apio.internal.form.OrderUpdaterForm;
import com.liferay.commerce.data.integration.apio.internal.security.permission.OrderPermissionChecker;
import com.liferay.commerce.data.integration.apio.internal.util.OrderHelper;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.organization.service.CommerceOrganizationService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.person.apio.architect.identifier.PersonIdentifier;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;

import java.util.List;

import javax.ws.rs.ServerErrorException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(immediate = true)
public class OrderNestedCollectionResource
	implements
		NestedCollectionResource<CommerceOrder, Long, OrderIdentifier, Long,
			AccountIdentifier> {

	@Override
	public NestedCollectionRoutes<CommerceOrder, Long, Long> collectionRoutes(
		NestedCollectionRoutes.Builder<CommerceOrder, Long, Long> builder) {

		return builder.addGetter(
			this::_getPageItems
		).build();
	}

	@Override
	public String getName() {
		return "orders";
	}

	@Override
	public ItemRoutes<CommerceOrder, Long> itemRoutes(
		ItemRoutes.Builder<CommerceOrder, Long> builder) {

		return builder.addGetter(
			this::_getCommerceOrder
		).addUpdater(
			this::_updateCommerceOrder,
			_orderPermissionChecker.forUpdating()::apply,
			OrderUpdaterForm::buildForm
		).build();
	}

	@Override
	public Representor<CommerceOrder> representor(
		Representor.Builder<CommerceOrder, Long> builder) {

		return builder.types(
			"Order"
		).identifier(
			CommerceOrder::getCommerceOrderId
		).addBidirectionalModel(
			"account", "orders", AccountIdentifier.class,
			CommerceOrder::getOrderOrganizationId
		).addLinkedModel(
			"account", AccountIdentifier.class,
			CommerceOrder::getOrderOrganizationId
		).addLinkedModel(
			"paymentMethod", PaymentMethodIdentifier.class,
			CommerceOrder::getCommercePaymentMethodId
		).addString(
			"purchaseOrderNumber", CommerceOrder::getPurchaseOrderNumber
		).addNumber(
			"shippingPrice", CommerceOrder::getShippingPrice
		).addNumber(
			"total", CommerceOrder::getTotal
		).addString(
			"orderStatus",
			order -> CommerceOrderConstants.getOrderStatusLabel(
				order.getOrderStatus())
		).addString(
			"paymentStatus",
			order -> CommerceOrderConstants.getPaymentStatusLabel(
				order.getPaymentStatus())
		).addDate(
			"dateCreated", CommerceOrder::getCreateDate
		).addDate(
			"dateModified", CommerceOrder::getModifiedDate
		).addLinkedModel(
			"author", PersonIdentifier.class, CommerceOrder::getUserId
		).addLinkedModel(
			"shippingAddress", AddressIdentifier.class,
			CommerceOrder::getShippingAddressId
		).addLinkedModel(
			"billingAddress", AddressIdentifier.class,
			CommerceOrder::getBillingAddressId
		).build();
	}

	private CommerceOrder _getCommerceOrder(Long commerceOrderId) {
		try {
			return _commerceOrderService.getCommerceOrder(commerceOrderId);
		}
		catch (PortalException pe) {
			throw new ServerErrorException(500, pe);
		}
	}

	private PageItems<CommerceOrder> _getPageItems(
		Pagination pagination, Long organizationId) {

		try {
			Organization organization = _commerceOrganizationService.getOrganization(
				organizationId);

			Group group = organization.getGroup();

			List<CommerceOrder> commerceOrders =
				_commerceOrderService.getCommerceOrdersByGroupId(
					group.getGroupId(), pagination.getStartPosition(),
					pagination.getEndPosition(), null);

			int total = _commerceOrderService.getCommerceOrdersCountByGroupId(
				group.getGroupId());

			return new PageItems<>(commerceOrders, total);
		}
		catch (PortalException pe) {
			throw new ServerErrorException(500, pe);
		}
	}

	private CommerceOrder _updateCommerceOrder(
			Long commerceOrderId, OrderUpdaterForm orderUpdaterForm)
		throws PortalException {

		return _orderHelper.updateCommerceOrder(
			commerceOrderId, orderUpdaterForm.getOrderStatus(),
			orderUpdaterForm.getPaymentStatus());
	}

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceOrganizationService _commerceOrganizationService;

	@Reference
	private OrderHelper _orderHelper;

	@Reference
	private OrderPermissionChecker _orderPermissionChecker;

}