<#include "../init.ftl">

<#assign DATE = staticUtil["java.util.Calendar"].DATE>
<#assign MONTH = staticUtil["java.util.Calendar"].MONTH>
<#assign YEAR = staticUtil["java.util.Calendar"].YEAR>

<#assign utcTimeZone = timeZoneUtil.getTimeZone("UTC")>

<#if fieldValue != "">
	<#if (fieldRawValue?is_date)>
		<#assign fieldValue = calendarFactory.getCalendar(fieldRawValue?long, utcTimeZone)>
	<#elseif (validator.isNotNull(predefinedValue))>
		<#assign predefinedDate = dateUtil.parseDate(predefinedValue, requestedLocale)>

		<#assign fieldValue = calendarFactory.getCalendar(predefinedDate?long)>
	<#else>
		<#assign calendar = calendarFactory.getCalendar(timeZone)>

		<#assign fieldValue = calendarFactory.getCalendar(calendar.get(YEAR), calendar.get(MONTH), calendar.get(DATE))>
	</#if>

	<#assign day = fieldValue.get(DATE)>
	<#assign month = fieldValue.get(MONTH)>
	<#assign year = fieldValue.get(YEAR)>
<#else>
	<#if required>
		<#assign calendar = calendarFactory.getCalendar(timeZone)>

		<#assign day = calendar.get(DATE)>
		<#assign month = calendar.get(MONTH)>
		<#assign year = calendar.get(YEAR)>
	<#else>
		<#assign day = 0>
		<#assign month = -1>
		<#assign year = 0>
	</#if>
</#if>

<#if cssClass??>
	<#assign cssClass = cssClass?replace("\\bspan[0-9]+", "", "ir")>
</#if>

<#assign dayValue = paramUtil.getInteger(request, "${namespacedFieldName}Day", day)>
<#assign monthValue = paramUtil.getInteger(request, "${namespacedFieldName}Month", month)>
<#assign yearValue = paramUtil.getInteger(request, "${namespacedFieldName}Year", year)>

<@aui["field-wrapper"] data=data helpMessage=escape(fieldStructure.tip) label=escape(label) name=namespacedFieldName>
	<@liferay_ui["input-date"]
		cssClass=cssClass
		dayParam="${namespacedFieldName}Day"
		dayValue=dayValue
		disabled=false
		monthParam="${namespacedFieldName}Month"
		monthValue=monthValue
		name="${namespacedFieldName}"
		nullable=true
		required=required
		yearParam="${namespacedFieldName}Year"
		yearValue=yearValue
	/>

	${fieldStructure.children}
</@>