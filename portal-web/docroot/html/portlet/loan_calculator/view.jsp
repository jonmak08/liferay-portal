<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
--%>

<%@ include file="/html/portlet/loan_calculator/init.jsp" %>

<%
int loanAmount = ParamUtil.get(request, "loanAmount", 300000);
double interest = ParamUtil.get(request, "interest", 7.00);
int years = ParamUtil.get(request, "years", 30);
int paymentsPerYear = ParamUtil.get(request, "paymentsPerYear", 12);

double tempValue = Math.pow((1 + (interest / 100 / paymentsPerYear)), (years * paymentsPerYear));
double amountPerPayment = (loanAmount * tempValue * (interest / 100 / paymentsPerYear)) / (tempValue - 1);
double totalPaid = amountPerPayment * years * paymentsPerYear;
double interestPaid = totalPaid - loanAmount;

NumberFormat doubleFormat = NumberFormat.getNumberInstance(locale);

doubleFormat.setMaximumFractionDigits(2);
doubleFormat.setMinimumFractionDigits(2);

NumberFormat integerFormat = NumberFormat.getNumberInstance(locale);

integerFormat.setMaximumFractionDigits(0);
integerFormat.setMinimumFractionDigits(0);
%>

<portlet:actionURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="viewLoanURL">
	<portlet:param name="struts_action" value="/loan_calculator/view" />
</portlet:actionURL>

<aui:form action="<%= viewLoanURL %>" id="fm" method="post" name="fm">

<aui:field-wrapper>

<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" label="loan-amount" name="loanAmount" value="<%= integerFormat.format(loanAmount) %>" />

<aui:input label="interest-rate" name="interest" value="<%= doubleFormat.format(interest) %>" />

<aui:input label="years" name="years" value="<%= years %>" />

<aui:input name="monthly-payment" size="5" value="<%= integerFormat.format(amountPerPayment) %>" />

</aui:field-wrapper>

<table class="lfr-table">

<tr>
	<td>
		<liferay-ui:message key="monthly-payment" />
	</td>
	<td>
		<strong><%= integerFormat.format(amountPerPayment) %></strong>
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="interest-paid" />
	</td>
	<td>
		<strong><%= integerFormat.format(interestPaid) %></strong>
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="total-paid" />
	</td>
	<td>
		<strong><%= integerFormat.format(totalPaid) %></strong>
	</td>
</tr>
</table>

<br />

<aui:button type="submit" value="calculate" />

</aui:form>

<aui:script use="aui-io-request,aui-parse-content">
	var form = A.one('#<portlet:namespace />fm');
	var parentNode = form.get('parentNode');

	parentNode.plug(A.Plugin.ParseContent);

	form.on(
		'submit',
		function(event) {
			var uri = form.getAttribute('action');

			A.io.request(
				uri,
				{
					form: {
						id: form
					},
					on: {
						success: function(event, id, obj) {
							var responseData = this.get('responseData');

							parentNode.setContent(responseData);
						}
					}
				}
			);

			event.halt();
		}
	);
</aui:script>