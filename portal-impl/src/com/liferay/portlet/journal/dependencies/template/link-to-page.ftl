<#include "init.ftl">

<#assign variableName = ".vars['"+ name + "'].getFriendlyUrl()">

<a href="${getVariableReferenceCode(variableName)}">
	${label}
</a>