<#assign
	description_text = getterUtil.getString(themeDisplay.getThemeSetting("description-text"))
	facebook_handle = getterUtil.getString(themeDisplay.getThemeSetting("facebook-handle"))
	instagram_handle = getterUtil.getString(themeDisplay.getThemeSetting("instagram-handle"))
	linkedin_handle = getterUtil.getString(themeDisplay.getThemeSetting("linkedin-handle"))
	pinterest_handle = getterUtil.getString(themeDisplay.getThemeSetting("pinterest-handle"))
	show_site_logo = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-site-logo"))
	title_text = getterUtil.getString(themeDisplay.getThemeSetting("title-text"))
	twitter_handle = getterUtil.getString(themeDisplay.getThemeSetting("twitter-handle"))
	wrap_content = getterUtil.getBoolean(themeDisplay.getThemeSetting("wrap-content"))
/>

<#if wrap_content>
	<#assign portal_content_css_class = "container" />
<#else>
	<#assign portal_content_css_class = "" />
</#if>