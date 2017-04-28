<#--
This file allows you to override and define new FreeMarker variables.
-->

<#assign banner_img = getterUtil.getString(themeDisplay.getThemeSetting("bannerimage")) />
<#assign bannr_img2 = theme.getSetting("bannerimage") />
<#assign banr_img3t = themeDisplay.getThemeSettings() />
<#assign bannr_img3 = banr_img3t["bannerimage"] />
<#assign header_title = theme_settings["header-title"] />
<#assign header_subtitle = theme_settings["header-subtitle"] />
<#assign props = theme_settings["bannerimage"] />
<#assign show_powered_by = getterUtil.getBoolean(theme_settings["show-powered-by"]) />
<#assign font_awesome_url = getterUtil.getString(theme_settings["font-awesome-url"]) />
<#assign copyright = getterUtil.getString(theme_settings["copyright"]) />
<#assign physical_address = getterUtil.getString(theme_settings["physical-address"]) />
<#assign telephone = getterUtil.getString(theme_settings["telephone"]) />

<#assign social_media = "" />
<#if theme_settings["social-media-0-url"] != "" && theme_settings["social-media-0-icon"] != "">
	<#assign 
		social_media = social_media + "<a href=\"" + theme_settings["social-media-0-url"] + "\" target=\"_blank\"><span style=\"padding: 10px;\" class=\"fa " + theme_settings["social-media-0-icon"] + "\"></span></a> " 
	/>
</#if>
<#if theme_settings["social-media-1-url"] != "" && theme_settings["social-media-1-icon"] != "">
	<#assign 
		social_media = social_media + "<a href=\"" + theme_settings["social-media-1-url"] + "\" target=\"_blank\"><span style=\"padding: 10px;\" class=\"fa " + theme_settings["social-media-1-icon"] + "\"></span></a> " 
	/>
</#if>
<#if theme_settings["social-media-2-url"] != "" && theme_settings["social-media-2-icon"] != "">
	<#assign 
		social_media = social_media + "<a href=\"" + theme_settings["social-media-2-url"] + "\" target=\"_blank\"><span style=\"padding: 10px;\" class=\"fa " + theme_settings["social-media-2-icon"] + "\"></span></a> " 
	/>
</#if>
<#if theme_settings["social-media-3-url"] != "" && theme_settings["social-media-3-icon"] != "">
	<#assign 
		social_media = social_media + "<a href=\"" + theme_settings["social-media-3-url"] + "\" target=\"_blank\"><span style=\"padding: 10px;\" class=\"fa " + theme_settings["social-media-3-icon"] + "\"></span></a> " 
	/>
</#if>
<#if theme_settings["social-media-4-url"] != "" && theme_settings["social-media-4-icon"] != "">
	<#assign 
		social_media = social_media + "<a href=\"" + theme_settings["social-media-4-url"] + "\" target=\"_blank\"><span style=\"padding: 10px;\" class=\"fa " + theme_settings["social-media-4-icon"] + "\"></span></a> " 
	/>
</#if>

