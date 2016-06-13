<#include "../init.ftl">

<#assign layoutLocalService = serviceLocator.findService("com.liferay.portal.service.LayoutLocalService")>
<#assign layoutService = serviceLocator.findService("com.liferay.portal.service.LayoutService")>

<@aui["field-wrapper"] data=data>
	<#assign selectedLayoutName = "">

	<#assign fieldRawValue = paramUtil.getString(request, "${namespacedFieldName}", fieldRawValue)>

	<#if (validator.isNotNull(fieldRawValue))>
		<#assign fieldLayoutJSONObject = jsonFactoryUtil.createJSONObject(fieldRawValue)>

		<#if (fieldLayoutJSONObject.getLong("groupId") > 0)>
			<#assign selectedLayoutGroupId = fieldLayoutJSONObject.getLong("groupId")>
		<#else>
			<#assign selectedLayoutGroupId = scopeGroupId>
		</#if>

		<#assign selectedLayout = layoutLocalService.fetchLayout(selectedLayoutGroupId, fieldLayoutJSONObject.getBoolean("privateLayout"), fieldLayoutJSONObject.getLong("layoutId"))!"">

		<#if (validator.isNotNull(selectedLayout))>
			<#assign selectedLayoutName = selectedLayout.getName(requestedLocale)>
		</#if>
	</#if>

	<div class="form-group">
		<@aui.input dir=requestedLanguageDir helpMessage=escape(fieldStructure.tip) label=escape(label) name="${namespacedFieldName}LayoutName" readonly="readonly" required=required type="text" value=selectedLayoutName />

		<@aui.input name=namespacedFieldName type="hidden" value=fieldRawValue />

		<@aui["button-row"]>
			<@aui.button
				cssClass="select-button"
				name="${namespacedFieldName}SelectButton"
				value="select-layout"
			/>

			<@aui.button
				cssClass="clear-button ${(fieldRawValue?has_content)?string('', 'hide')}"
				name="${namespacedFieldName}ClearButton"
				value="clear"
			/>
		</@>
	</div>

	${fieldStructure.children}
</@>

<@aui.script use="liferay-ddm-link-to-page-field">
	new A.LinkToPageField(
		{
			container: '[data-fieldnamespace="${fieldNamespace}"]',
			fieldName: '${fieldName}',
			fieldNamespace: '${fieldNamespace}',
			portletNamespace: '${portletNamespace}'
		}
	);
</@>