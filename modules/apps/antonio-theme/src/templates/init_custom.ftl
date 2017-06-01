<#--
This file allows you to override and define new FreeMarker variables.
-->

<#assign
  contactInfo = getterUtil.getString(themeDisplay.getThemeSetting('Contact Information'))
  siteInfo = getterUtil.getString(themeDisplay.getThemeSetting('Site Information'))
/>
