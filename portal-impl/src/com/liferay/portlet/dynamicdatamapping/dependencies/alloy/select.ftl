<#include "../init.ftl">

<#assign multiple = false>

<#if fieldStructure.multiple?? && (fieldStructure.multiple == "true")>
	<#assign multiple = true>
</#if>

<@aui["field-wrapper"] data=data>
	<@aui.select cssClass=cssClass helpMessage=escape(fieldStructure.tip) label=escape(label) multiple=multiple name=namespacedFieldName required=required>
		${fieldStructure.children}
	</@aui.select>
</@>