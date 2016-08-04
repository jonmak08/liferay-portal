<#list finderColsList as finderCol>
	<#if finderCol.hasArrayableOperator()>
		if (${finderCol.names} != null) {
		<#if finderCol.type == "String">
			for (String ${finderCol.name} : ${finderCol.names}) {
				if (${finderCol.name} != null && !${finderCol.name}.isEmpty()) {
					qPos.add(${finderCol.name});
				}
			}
		<#else>
			qPos.add(${finderCol.names});
		</#if>
		}
	<#else>
		<#if !finderCol.isPrimitiveType()>
			if (bind${finderCol.methodName}) {
		</#if>

		qPos.add(

		<#if finderCol.type == "Date">
			CalendarUtil.getTimestamp(
		</#if>

		${finderCol.name}${serviceBuilder.getPrimitiveObjValue("${finderCol.type}")}

		<#if finderCol.type == "Date">
			)
		</#if>

		);

		<#if !finderCol.isPrimitiveType()>
			}
		</#if>
	</#if>
</#list>