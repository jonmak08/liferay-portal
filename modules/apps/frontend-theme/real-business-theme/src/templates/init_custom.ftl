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

<#assign show_facebook =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-facebook"))/>
<#assign show_google_plus =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-google-plus"))/>
<#assign show_instagram =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-instagram"))/>
<#assign show_linkedin =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-linkedin"))/>
<#assign show_pinterest =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-pinterest"))/>
<#assign show_skype =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-skype"))/>
<#assign show_twitter =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-twitter"))/>
<#assign show_youtube =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-youtube"))/>

<#assign footer_nav_text =
getterUtil.getString(themeDisplay.getThemeSetting("footer-nav-text"))/>
<#assign footer_social_text =
getterUtil.getString(themeDisplay.getThemeSetting("footer-social-text"))/>
<#assign facebook_url =
getterUtil.getString(themeDisplay.getThemeSetting("facebook-url"))/>
<#assign google_plus_url =
getterUtil.getString(themeDisplay.getThemeSetting("google-plus-url"))/>
<#assign instagram_url =
getterUtil.getString(themeDisplay.getThemeSetting("instagram-url"))/>
<#assign linkedin_url =
getterUtil.getString(themeDisplay.getThemeSetting("linkedin-url"))/>
<#assign pinterest_url =
getterUtil.getString(themeDisplay.getThemeSetting("pinterest-url"))/>
<#assign skype_url =
getterUtil.getString(themeDisplay.getThemeSetting("skype-url"))/>
<#assign site_description =
getterUtil.getString(themeDisplay.getThemeSetting("site-description"))/>
<#assign twitter_url =
getterUtil.getString(themeDisplay.getThemeSetting("twitter-url"))/>
<#assign youtube_url =
getterUtil.getString(themeDisplay.getThemeSetting("youtube-url"))/>