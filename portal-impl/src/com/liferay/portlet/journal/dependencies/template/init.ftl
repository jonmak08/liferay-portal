<#-- Common -->

<#assign variableName = ".vars['"+ name + "'].getData()">

<#if repeatable>
	<#assign variableName = "cur_item.getData()">
</#if>

<#-- Util -->

<#function getVariableReferenceCode variableName>
	<#if language == "ftl">
		<#return "${" + variableName + "}">
	<#else>
		<#return "$" + variableName>
	</#if>
</#function>