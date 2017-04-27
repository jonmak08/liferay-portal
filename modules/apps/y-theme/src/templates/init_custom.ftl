<#--
This file allows you to override and define new FreeMarker variables.
-->

<#assign banner_img = getterUtil.getString(themeDisplay.getThemeSetting("bannerimage")) />
<#assign bannr_img2 = theme.getSetting("bannerimage") />
<#assign banr_img3t = themeDisplay.getThemeSettings() />
<#assign bannr_img3 = banr_img3t["bannerimage"] />
<#assign props = theme_settings["bannerimage"] />
