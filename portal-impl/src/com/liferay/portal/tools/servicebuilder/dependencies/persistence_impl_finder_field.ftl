<#if entity.hasCompoundPK() && finderCol.isPrimary()>
	<#assign finderFieldName = entity.alias + ".id." + finderColName>
<#else>
	<#assign finderFieldName = entity.alias + "." + finderColName>
</#if>

<#if serviceBuilder.getSqlType(packagePath + ".model." + entity.getName(), finderCol.getName(), finderCol.getType()) == "CLOB">
	<#assign textFinderFieldName = "CAST_CLOB_TEXT(" + finderFieldName + ")">
<#else>
	<#assign textFinderFieldName = finderFieldName>
</#if>

<#if !finderCol.isPrimitiveType()>
	private static final String _FINDER_COLUMN_${finder.name?upper_case}_${finderCol.name?upper_case}_1${finderFieldSuffix} =

	<#if finderCol.comparator == "=">
		"${textFinderFieldName} IS NULL${finderColConjunction}"
	<#elseif finderCol.comparator == "<>" || finderCol.comparator = "!=">
		"${textFinderFieldName} IS NOT NULL${finderColConjunction}"
	<#else>
		"${textFinderFieldName} ${finderCol.comparator} NULL${finderColConjunction}"
	</#if>

	;
</#if>

<#if finderCol.type == "String" && !finderCol.isCaseSensitive()>
	<#assign finderColExpression = "lower(" + textFinderFieldName + ") " + finderCol.comparator + " ?">
<#else>
	<#assign finderColExpression = textFinderFieldName + " " + finderCol.comparator + " ?">
</#if>

private static final String _FINDER_COLUMN_${finder.name?upper_case}_${finderCol.name?upper_case}_2${finderFieldSuffix} = "${finderColExpression}${finderColConjunction}";

<#if finderCol.type == "String">
	<#assign finderColExpression = textFinderFieldName + " " + finderCol.comparator + " ''">

	private static final String _FINDER_COLUMN_${finder.name?upper_case}_${finderCol.name?upper_case}_3${finderFieldSuffix} = "(${textFinderFieldName} IS NULL OR ${finderColExpression})${finderColConjunction}";
</#if>

<#if finder.hasArrayableOperator()>
	<#if !finderCol.isPrimitiveType()>
		private static final String _FINDER_COLUMN_${finder.name?upper_case}_${finderCol.name?upper_case}_4${finderFieldSuffix} = "(" + removeConjunction(_FINDER_COLUMN_${finder.name?upper_case}_${finderCol.name?upper_case}_1) + ")";
	</#if>

	private static final String _FINDER_COLUMN_${finder.name?upper_case}_${finderCol.name?upper_case}_5${finderFieldSuffix} = "(" + removeConjunction(_FINDER_COLUMN_${finder.name?upper_case}_${finderCol.name?upper_case}_2) + ")";

	<#if finderCol.type == "String">
		private static final String _FINDER_COLUMN_${finder.name?upper_case}_${finderCol.name?upper_case}_6${finderFieldSuffix} = "(" + removeConjunction(_FINDER_COLUMN_${finder.name?upper_case}_${finderCol.name?upper_case}_3) + ")";
	</#if>
</#if>