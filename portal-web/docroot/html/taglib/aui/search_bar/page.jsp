<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ include file="init.jsp" %>

<%
if (!useNamespace) {
	namespace = StringPool.BLANK;
}

boolean buildForm = false;
if (Validator.isNotNull(formAction)) {
	buildForm = true;
	//TODO: Check if setting inputs to null works
	formRedirectURL = Validator.isNotNull(formRedirectURL) ? formRedirectURL : StringPool.BLANK;
	formHeading = Validator.isNotNull(formHeading) ? formHeading : StringPool.BLANK;
}

formName = Validator.isNotNull(formName) ? formName : StringPool.BLANK;

//TODO: Check if setting inputs to null works
//cssClass = Validator.isNotNull(cssClass) ? cssClass : StringPool.BLANK;
inputId = Validator.isNotNull(inputId) ? inputId : StringPool.BLANK;
inputLabel = Validator.isNotNull(inputLabel) ? inputLabel : StringPool.BLANK;
inputTitle = Validator.isNotNull(inputTitle) ? inputTitle : StringPool.BLANK;
inputValue = Validator.isNotNull(inputValue) ? inputValue : StringPool.BLANK;
%>

<div <%= Validator.isNotNull(cssClass) ? "class=\"" + cssClass + "\"" : StringPool.BLANK %>>

	<c:choose>
		<c:when test="<%= buildForm %>">

			<aui:form action="<%= formAction %>" method="<%= formMethod %>" name="<%= formName %>">
				<liferay-portlet:renderURLParams varImpl="<%= formAction %>" />
				<aui:input name="redirect" type="hidden" value="<%= formRedirectURL %>" />
				<liferay-ui:header backURL="<%= formRedirectURL %>" title="<%= formHeading %>" />

				<span class="aui-search-bar">
					<aui:input id="<%= inputId %>" inlineField="<%= inputInlineField %>" label="<%= inputLabel %>" name="<%= inputName %>" size="<%= inputSize %>" title="<%= inputTitle %>" type="text" value="<%= inputValue %>" />
					<aui:button type="submit" value="<%= buttonValue %>" />
				</span>

			</aui:form>

		</c:when>
		<c:otherwise>

			<span class="aui-search-bar">
				<aui:input id="<%= inputId %>" inlineField="<%= inputInlineField %>" label="<%= inputLabel %>" name="<%= inputName %>" size="<%= inputSize %>" title="<%= inputTitle %>" type="text" value="<%= inputValue %>" />
				<aui:button type="submit" value="<%= buttonValue %>" />
			</span>

		</c:otherwise>
	</c:choose>

	<c:if test="<%= useAutoFocus && portletDisplay.isStateMax() %>">
		<aui:script>
			Liferay.Util.focusFormField(document.<%= namespace + formName %>.<%= namespace + inputName %>);
		</aui:script>
	</c:if>

</div>