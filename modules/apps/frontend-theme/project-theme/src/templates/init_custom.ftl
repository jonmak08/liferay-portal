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
<<<<<<< HEAD
</#if>

<#assign show_instagram =
getterUtil.getBoolean(themeDisplay.getThemeSetting("Show Instagram"))/>
<#assign instagram_url =
getterUtil.getString(themeDisplay.getThemeSetting("instagram-url"))/>

<#assign show_facebook =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-facebook"))/>
<#assign facebook_url =
getterUtil.getString(themeDisplay.getThemeSetting("facebook-url"))/>

<#assign show_twitter =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-twitter"))/>
<#assign twitter_url =
getterUtil.getString(themeDisplay.getThemeSetting("twitter-url"))/>

<#assign show_googleplus =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-googleplus"))/>
<#assign googleplus_url =
getterUtil.getString(themeDisplay.getThemeSetting("googleplus-url"))/>

<#assign show_linkedin =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-linkedin"))/>
<#assign linkedin_url =
getterUtil.getString(themeDisplay.getThemeSetting("linkedin-url"))/>

<#assign show_youtube =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-youtube"))/>
<#assign youtube_url =
getterUtil.getString(themeDisplay.getThemeSetting("youtube-url"))/>

<#assign show_dribbble =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-dribbble"))/>
<#assign dribbble_url =
getterUtil.getString(themeDisplay.getThemeSetting("dribbble-url"))/>

<#assign show_github =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-github"))/>
<#assign github_url =
getterUtil.getString(themeDisplay.getThemeSetting("github-url"))/>

<#assign show_phone =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-phone"))/>
<#assign phone_url =
getterUtil.getString(themeDisplay.getThemeSetting("phone-url"))/>

<#assign show_email =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-email"))/>
<#assign email_url =
getterUtil.getString(themeDisplay.getThemeSetting("email-url"))/>
=======
</#if>
>>>>>>> c11802cafe84... add project theme feature 1
