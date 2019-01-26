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

<#-------- Secondary Logo -------->


<#--  <#assign
	secondaryLogo_css_class = "logo"
	use_company_secondaryLogo = !layoutSet.isLogo()
	site_secondaryLogo_height = company_secondaryLogo_height
	site_secondaryLogo_width = company_secondaryLogo_width
/>

<#if (company.getLogoId() == 0) && use_company_logo>
	<#assign logo_css_class = logo_css_class + " default-logo" />
<#else>
	<#assign logo_css_class = logo_css_class + " custom-logo" />
</#if>  -->

<#--  <#if theme_settings["show-site-name-supported"]??>
	<#assign show_site_name_supported = getterUtil.getBoolean(theme_settings["show-site-name-supported"]!"", true) />
<#else>
	<#assign show_site_name_supported = true />
</#if>

<#if theme_settings["show-site-name-default"]??>
	<#assign show_site_name_default = getterUtil.getBoolean(theme_settings["show-site-name-default"]!"", show_site_name_supported) />
<#else>
	<#assign show_site_name_default = show_site_name_supported />
</#if>

<#assign
	show_site_name = getterUtil.getBoolean(layoutSet.getSettingsProperty("showSiteName"), show_site_name_default)

	site_logo = company_logo

	logo_description = ""
/>

<#if !show_site_name>
	<#assign logo_description = htmlUtil.escape(site_name) />
</#if>  -->
