<#assign
	wrap_content = getterUtil.getBoolean(themeDisplay.getThemeSetting("wrap-content"))
	title_text = getterUtil.getString(themeDisplay.getThemeSetting("title-text"))
	description_text = getterUtil.getString(themeDisplay.getThemeSetting("description-text"))
	facebook_handle = getterUtil.getString(themeDisplay.getThemeSetting("facebook-handle"))
	instagram_handle = getterUtil.getString(themeDisplay.getThemeSetting("instagram-handle"))
	twitter_handle = getterUtil.getString(themeDisplay.getThemeSetting("twitter-handle"))
	linkedin_handle = getterUtil.getString(themeDisplay.getThemeSetting("linkedin-handle"))
	pinterest_handle = getterUtil.getString(themeDisplay.getThemeSetting("pinterest-handle"))
/>

<#if wrap_content>
	<#assign portal_content_css_class = "container" />
<#else>
	<#assign portal_content_css_class = "" />
</#if>

<#if facebook_handle?has_content>
	<#assign facebook = "http://facebook.com/${facebook_handle}" />
<#else>
	<#assign facebook = "http://facebook.com/" />
</#if>

<#if instagram_handle?has_content>
	<#assign instagram = "http://instagram.com/${instagram_handle}" />
<#else>
	<#assign instagram = "http://instagram.com/" />
</#if>

<#if twitter_handle?has_content>
	<#assign twitter = "http://twitter.com/${twitter_handle}" />
<#else>
	<#assign twitter = "http://twitter.com/" />
</#if>

<#if linkedin_handle?has_content>
	<#assign linkedin = "http://linkedin.com/in/${linkedin_handle}" />
<#else>
	<#assign linkedin = "http://linkedin.com/" />
</#if>

<#if pinterest_handle?has_content>
	<#assign pinterest = "http://pinterest.com/${pinterest_handle}" />
<#else>
	<#assign pinterest = "http://pinterest.com/" />
</#if>