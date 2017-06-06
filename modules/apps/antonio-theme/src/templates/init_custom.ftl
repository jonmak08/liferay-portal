<#--
This file allows you to override and define new FreeMarker variables.
-->

<#assign
  site_name = getterUtil.getString(themeDisplay.getThemeSetting('Site Name'))

  addFooterSection = getterUtil.getBoolean(themeDisplay.getThemeSetting('Add Footer Section'))
  footerSectionTitle = getterUtil.getString(themeDisplay.getThemeSetting('Footer Section Title'))
  footerSectionEntry = getterUtil.getString(themeDisplay.getThemeSetting('Footer Section Entry'))

  socialMediaOne = getterUtil.getString(themeDisplay.getThemeSetting('Social Media One Font Awesome Class'))
  socialMediaOneLink = getterUtil.getString(themeDisplay.getThemeSetting('Social Media One Link'))

  socialMediaTwo = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Two Font Awesome Class'))
  socialMediaTwoLink = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Two Link'))

  socialMediaThree = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Three Font Awesome Class'))
  socialMediaThreeLink = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Three Link'))

  socialMediaFour = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Four Font Awesome Class'))
  socialMediaFourLink = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Four Link'))

  socialMediaFive = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Five Font Awesome Class'))
  socialMediaFiveLink = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Five Link'))
/>
