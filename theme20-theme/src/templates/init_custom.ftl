<#---------- Header ---------->

<#assign is_signed_in = theme_display.isSignedIn() />
<#assign hero_img_style = getterUtil.getString(themeDisplay.getThemeSetting("hero-image-url"))/>
<#assign logo_css_class = logo_css_class + " navbar-brand" />
<#assign site_logo_height = 56 />
<#assign navigation_background = theme_settings["navigation-background-color"]?lower_case />
<#assign navigation_background_theme = "" />
<#assign navigation_child_background_theme = "" />
<#assign navigation_text_theme = "" />

<#if is_signed_in>
    <#assign sticky_navigation = "sign-in">
<#else>
    <#assign sticky_navigation = "sign-out">
</#if>

<#if stringUtil.equals(navigation_background, "dark")>
    <#assign navigation_background_theme = "dark-background-header" />
    <#assign navigation_child_background_theme = "dark-background-header" />
    <#assign navigation_text_theme = "light-font" />
    <#assign footer_background_theme = "dark-background-footer" />
    <#assign footer_text_theme = "footer-light-font" />
</#if>

<#if stringUtil.equals(navigation_background, "light")>
    <#assign navigation_background_theme = "light-background-header" />
    <#assign navigation_child_background_theme = "light-background-header" />
    <#assign navigation_text_theme = "dark-font" />
    <#assign footer_background_theme = "light-background-footer" />
    <#assign footer_text_theme = "footer-dark-font" />
</#if>

<#assign header_css_class = "navbar navbar-expand-md navbar-dark flex-column flex-md-row bd-navbar ${navigation_background_theme} ${sticky_navigation}" />
<#assign child_menu_css_class = "child-menu dropdown-menu ${navigation_child_background_theme}" />
<#assign nav_link_css_class = "navbar-text-truncate nav-link ${navigation_text_theme}" />

<#---------- Hero ---------->

<#assign hero_text = getterUtil.getString(themeDisplay.getThemeSetting("main-header"))/>

<#---------- Footer ---------->

<#assign show_footer = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-footer"))/>
<#assign show_credits = getterUtil.getBoolean(themeDisplay.getThemeSetting("Show credits"))/>
<#assign facebook_url = getterUtil.getString(themeDisplay.getThemeSetting("facebook-url")) />
<#assign youtube_url = getterUtil.getString(themeDisplay.getThemeSetting("youtube-url")) />
<#assign linkedin_url = getterUtil.getString(themeDisplay.getThemeSetting("linkedin-url")) />
<#assign my_phone_text = getterUtil.getString(themeDisplay.getThemeSetting("Phone number"))/>
<#assign twitter_url = getterUtil.getString(themeDisplay.getThemeSetting("twitter-url")) />