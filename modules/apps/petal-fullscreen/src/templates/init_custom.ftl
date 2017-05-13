<#--
This file allows you to override and define new FreeMarker variables.
-->
<#assign banner_img = getterUtil.getString(themeDisplay.getThemeSetting("banner-image")) />
<#assign bannr_img2 = theme.getSetting("banner-image") />
<#assign banr_img3t = themeDisplay.getThemeSettings() />
<#assign bannr_img3 = banr_img3t["banner-image"] />
<#assign header_title = theme_settings["header-title"] />
<#assign header_title_color = theme_settings["header-title-color"] />
<#assign header_subtitle = theme_settings["header-subtitle"] />
<#assign header_subtitle_color = theme_settings["header-subtitle-color"] />
<#assign props = theme_settings["banner-image"] />
<#assign show_powered_by = getterUtil.getBoolean(theme_settings["show-powered-by"]) />
<#assign font_awesome_url = getterUtil.getString(theme_settings["font-awesome-url"]) />
<#assign copyright = getterUtil.getString(theme_settings["copyright"]) />
<#assign physical_address = getterUtil.getString(theme_settings["physical-address"]) />
<#assign telephone = getterUtil.getString(theme_settings["telephone"]) />
