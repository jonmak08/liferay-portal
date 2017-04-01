<#assign show_logo_in_navigation = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-logo-in-navigation")) />
<#assign show_site_name_in_navigation = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-site-name-in-navigation")) />
<#assign the_page_title = "" />

<#if layout.getHTMLTitle(locale)??>
	<#assign the_page_title = layout.getHTMLTitle(locale) />
</#if>

<#if pageTitle??>
	<#assign the_page_title = pageTitle />
</#if>

<#if tilesTitle?has_content>
	<#assign the_page_title = languageUtil.get(locale, tilesTitle) />
</#if>

<#if page_group.isLayoutPrototype()>
	<#assign the_page_title = page_group.getDescriptiveName(locale) />
</#if>

<#if !tilesTitle?has_content>
	<#assign the_page_title = htmlUtil.escape(the_page_title) />
</#if>