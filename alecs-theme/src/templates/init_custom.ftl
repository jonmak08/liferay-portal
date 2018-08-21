<#assign
	facebook_icon = getterUtil.getBoolean(themeDisplay.getThemeSetting("facebook-icon"))
	facebook_icon_link_url = themeDisplay.getThemeSetting("facebook-icon-link-url")
	instagram_icon = getterUtil.getBoolean(themeDisplay.getThemeSetting("instagram-icon"))
	instagram_icon_link_url = themeDisplay.getThemeSetting("instagram-icon-link-url")
	linkedin_icon = getterUtil.getBoolean(themeDisplay.getThemeSetting("linkedin-icon"))
	linkedin_icon_link_url = themeDisplay.getThemeSetting("linkedin-icon-link-url")
	show_footer = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-footer"))
	show_header = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-header"))
	header_fixed = getterUtil.getBoolean(themeDisplay.getThemeSetting("header-fixed"))
	show_site_name = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-site-name"))
	show_header_main_search = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-header-main-search"))
	show_page_title = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-page-title"))
	twitter_icon = getterUtil.getBoolean(themeDisplay.getThemeSetting("twitter-icon"))
	twitter_icon_link_url = themeDisplay.getThemeSetting("twitter-icon-link-url")
	youtube_icon = getterUtil.getBoolean(themeDisplay.getThemeSetting("youtube-icon"))
	youtube_icon_link_url = themeDisplay.getThemeSetting("youtube-icon-link-url")
	nav_css_right = "ml-auto"
	nav_collapse = "navbar-expand-md"
	the_title = ""
/>

<#if page.getName(locale)??>
	<#assign
		the_title = page.getName(locale)
	/>
</#if>