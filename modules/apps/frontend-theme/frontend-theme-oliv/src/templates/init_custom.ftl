<#assign
	wrap_content = getterUtil.getBoolean(themeDisplay.getThemeSetting("wrap-content"))
	title_text = getterUtil.getString(themeDisplay.getThemeSetting("title-text"))
	description_text = getterUtil.getString(themeDisplay.getThemeSetting("description-text"))
/>

<#if wrap_content>
	<#assign portal_content_css_class = "container" />
<#else>
	<#assign portal_content_css_class = "" />
</#if>