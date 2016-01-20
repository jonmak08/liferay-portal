<#include "../init.ftl">

<@aui["field-wrapper"] data=data helpMessage=escape(fieldStructure.tip) label=escape(label)>
	${fieldStructure.children}
</@>