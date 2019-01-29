<#assign
	show_site_logo = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-site-logo"))
	title_text = getterUtil.getString(themeDisplay.getThemeSetting("title-text"))
	description_text = getterUtil.getString(themeDisplay.getThemeSetting("description-text"))
	facebook_handle = getterUtil.getString(themeDisplay.getThemeSetting("facebook-handle"))
	instagram_handle = getterUtil.getString(themeDisplay.getThemeSetting("instagram-handle"))
	twitter_handle = getterUtil.getString(themeDisplay.getThemeSetting("twitter-handle"))
	linkedin_handle = getterUtil.getString(themeDisplay.getThemeSetting("linkedin-handle"))
	pinterest_handle = getterUtil.getString(themeDisplay.getThemeSetting("pinterest-handle"))
/>

<#assign
	facebook = "http://facebook.com/${facebook_handle}"
	instagram = "http://instagram.com/${instagram_handle}"
	pinterest = "http://pinterest.com/${pinterest_handle}"
	twitter = "http://twitter.com/${twitter_handle}"
/>

<#if linkedin_handle?has_content>
	<#assign linkedin = "http://linkedin.com/in/${linkedin_handle}" />
<#else>
	<#assign linkedin = "http://linkedin.com/" />
</#if>