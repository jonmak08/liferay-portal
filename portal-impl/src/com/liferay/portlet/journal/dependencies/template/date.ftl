<#include "init.ftl">

<#if language == "ftl">
${r"<#assign"} '${name}_Data' = getterUtil.getLong(${variableName})>

${r"<#if"} (.vars['${name}_Data'] > 0)>
	${r"<#assign"} '${name}_DateObj' = dateUtil.newDate(.vars['${name}_Data'])>

	${r"${"}dateUtil.getDate(.vars['${name}_DateObj'], "dd MMM yyyy - HH:mm:ss", locale, timeZoneUtil.getTimeZone("UTC"))}
${r"</#if>"}
<#else>
#set ($${name}_Data = $getterUtil.getLong($${variableName}))

#if ($${name}_Data > 0)
	#set ($${name}_DateObj = $dateUtil.newDate($${name}_Data))

	$dateUtil.getDate($${name}_DateObj, "dd MMM yyyy - HH:mm:ss", $locale, $timeZoneUtil.getTimeZone("UTC"))
#end
</#if>