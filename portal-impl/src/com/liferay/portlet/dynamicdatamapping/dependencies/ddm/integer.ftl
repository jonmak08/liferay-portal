<#include "../init.ftl">

<@aui["field-wrapper"] data=data>
	<@aui.input cssClass=cssClass dir=requestedLanguageDir helpMessage=escape(fieldStructure.tip) label=escape(label) name=namespacedFieldName required=required type="text" value=fieldValue>
		<@aui.validator name="digits" />
	</@aui.input>

	${fieldStructure.children}
</@>