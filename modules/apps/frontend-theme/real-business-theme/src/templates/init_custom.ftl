<#assign
	show_footer = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-footer"))
	show_header = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-header"))
	show_header_search = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-header-search"))
	wrap_content = getterUtil.getBoolean(themeDisplay.getThemeSetting("wrap-content"))
/>

<#if wrap_content>
	<#assign portal_content_css_class = "container" />
<#else>
	<#assign portal_content_css_class = "" />
</#if>

<#assign site_description =
getterUtil.getString(themeDisplay.getThemeSetting("site-description"))/>

<#assign show_facebook =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-facebook"))/>
<#assign facebook_url =
getterUtil.getString(themeDisplay.getThemeSetting("facebook-url"))/>

<#assign show_twitter =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-twitter"))/>
<#assign twitter_url =
getterUtil.getString(themeDisplay.getThemeSetting("twitter-url"))/>

<#assign show_pinterest =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-pinterest"))/>
<#assign pinterest_url =
getterUtil.getString(themeDisplay.getThemeSetting("pinterest-url"))/>

<#assign show_google_plus =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-google-plus"))/>
<#assign google_plus_url =
getterUtil.getString(themeDisplay.getThemeSetting("google-plus-url"))/>

<#assign show_skype =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-skype"))/>
<#assign skype_url =
getterUtil.getString(themeDisplay.getThemeSetting("skype-url"))/>

<#assign show_instagram =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-instagram"))/>
<#assign instagram_url =
getterUtil.getString(themeDisplay.getThemeSetting("instagram-url"))/>

<#assign show_youtube =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-youtube"))/>
<#assign youtube_url =
getterUtil.getString(themeDisplay.getThemeSetting("youtube-url"))/>

<#assign show_linkedin =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-linkedin"))/>
<#assign linkedin_url =
getterUtil.getString(themeDisplay.getThemeSetting("linkedin-url"))/>