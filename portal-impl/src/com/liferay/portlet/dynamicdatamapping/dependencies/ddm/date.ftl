<#include "../init.ftl">

<#assign DATE = staticUtil["java.util.Calendar"].DATE>
<#assign MONTH = staticUtil["java.util.Calendar"].MONTH>
<#assign YEAR = staticUtil["java.util.Calendar"].YEAR>
<#assign GMT = staticUtil["com.liferay.portal.kernel.util.TimeZoneUtil"].GMT>

<#assign nullable = false>

<#if (fieldRawValue?is_date)>
	<#assign fieldValue = calendarFactory.getCalendar(fieldRawValue?long, GMT)>
<#elseif (validator.isNotNull(predefinedValue))>
	<#assign predefinedDate = dateUtil.parseDate(predefinedValue, requestedLocale)>

	<#assign fieldValue = calendarFactory.getCalendar(predefinedDate?long)>
<#else>
	<#assign calendar = calendarFactory.getCalendar(GMT)>

	<#assign fieldValue = calendarFactory.getCalendar(calendar.get(YEAR), calendar.get(MONTH), calendar.get(DATE))>

	<#assign nullable = true>
</#if>

<#assign dayValue = paramUtil.getInteger(request, "${namespacedFieldName}Day", fieldValue.get(DATE))>
<#assign monthValue = paramUtil.getInteger(request, "${namespacedFieldName}Month", fieldValue.get(MONTH))>
<#assign yearValue = paramUtil.getInteger(request, "${namespacedFieldName}Year", fieldValue.get(YEAR))>

<@aui["field-wrapper"] data=data helpMessage=escape(fieldStructure.tip) label=escape(label) name=namespacedFieldName>
	<@liferay_ui["input-date"]
		cssClass=cssClass
		dayParam="${namespacedFieldName}Day"
		dayValue=dayValue
		disabled=false
		monthParam="${namespacedFieldName}Month"
		monthValue=monthValue
		name="${namespacedFieldName}"
		nullable=nullable
		required=required
		yearParam="${namespacedFieldName}Year"
		yearValue=yearValue
	/>

	${fieldStructure.children}
</@>