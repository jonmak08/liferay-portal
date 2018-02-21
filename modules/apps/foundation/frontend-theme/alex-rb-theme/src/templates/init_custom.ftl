<#assign custom_background_css = "">
<#assign include_custom_background = getterUtil.getBoolean(theme_settings["Include custom background."])/>

<#if include_custom_background>
	<#assign custom_background_css = "custom-background">
</#if>