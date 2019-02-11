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

<#assign show_instagram =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-instagram"))/>
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

<#assign show_site_description =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-site-description"))/>
<#assign site_description_text =
getterUtil.getString(themeDisplay.getThemeSetting("site-description-text"))/>

<#assign show_footer_heading_1 =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-footer-heading-1"))/>
<#assign footer_heading_1_text =
getterUtil.getString(themeDisplay.getThemeSetting("footer-heading-1-text"))/>

<#assign show_link_1 =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-link-1"))/>
<#assign link_1_text =
getterUtil.getString(themeDisplay.getThemeSetting("link-1-text"))/>
<#assign link_1_url =
getterUtil.getString(themeDisplay.getThemeSetting("link-1-url"))/>

<#assign show_link_2 =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-link-2"))/>
<#assign link_2_text =
getterUtil.getString(themeDisplay.getThemeSetting("link-2-text"))/>
<#assign link_2_url =
getterUtil.getString(themeDisplay.getThemeSetting("link-2-url"))/>

<#assign show_link_3 =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-link-3"))/>
<#assign link_3_text =
getterUtil.getString(themeDisplay.getThemeSetting("link-3-text"))/>
<#assign link_3_url =
getterUtil.getString(themeDisplay.getThemeSetting("link-3-url"))/>

<#assign show_link_4 =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-link-4"))/>
<#assign link_4_text =
getterUtil.getString(themeDisplay.getThemeSetting("link-4-text"))/>
<#assign link_4_url =
getterUtil.getString(themeDisplay.getThemeSetting("link-4-url"))/>

<#assign show_link_5 =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-link-5"))/>
<#assign link_5_text =
getterUtil.getString(themeDisplay.getThemeSetting("link-5-text"))/>
<#assign link_5_url =
getterUtil.getString(themeDisplay.getThemeSetting("link-5-url"))/>

<#assign show_footer_heading_2 =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-footer-heading-2"))/>
<#assign footer_heading_2_text =
getterUtil.getString(themeDisplay.getThemeSetting("footer-heading-2-text"))/>

<#assign show_link_6 =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-link-6"))/>
<#assign link_6_text =
getterUtil.getString(themeDisplay.getThemeSetting("link-6-text"))/>
<#assign link_6_url =
getterUtil.getString(themeDisplay.getThemeSetting("link-6-url"))/>

<#assign show_link_7 =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-link-7"))/>
<#assign link_7_text =
getterUtil.getString(themeDisplay.getThemeSetting("link-7-text"))/>
<#assign link_7_url =
getterUtil.getString(themeDisplay.getThemeSetting("link-7-url"))/>

<#assign show_link_8 =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-link-8"))/>
<#assign link_8_text =
getterUtil.getString(themeDisplay.getThemeSetting("link-8-text"))/>
<#assign link_8_url =
getterUtil.getString(themeDisplay.getThemeSetting("link-8-url"))/>

<#assign show_link_9 =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-link-9"))/>
<#assign link_9_text =
getterUtil.getString(themeDisplay.getThemeSetting("link-9-text"))/>
<#assign link_9_url =
getterUtil.getString(themeDisplay.getThemeSetting("link-9-url"))/>

<#assign show_link_10 =
getterUtil.getBoolean(themeDisplay.getThemeSetting("show-link-10"))/>
<#assign link_10_text =
getterUtil.getString(themeDisplay.getThemeSetting("link-10-text"))/>
<#assign link_10_url =
getterUtil.getString(themeDisplay.getThemeSetting("link-10-url"))/>