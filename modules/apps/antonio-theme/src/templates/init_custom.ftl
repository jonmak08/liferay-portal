<#--
This file allows you to override and define new FreeMarker variables.
-->

<#assign
  contactInfo = getterUtil.getString(themeDisplay.getThemeSetting('Contact Information'))
  siteInfo = getterUtil.getString(themeDisplay.getThemeSetting('Site Information'))

  socialMediaOne = getterUtil.getString(themeDisplay.getThemeSetting('Social Media One'))
  socialMediaOneLink = getterUtil.getString(themeDisplay.getThemeSetting('Social Media One Link'))

  socialMediaTwo = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Two'))
  socialMediaTwoLink = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Two Link'))

  socialMediaThree = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Three'))
  socialMediaThreeLink = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Three Link'))

  socialMediaFour = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Four'))
  socialMediaFourLink = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Four Link'))

  socialMediaFive = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Five'))
  socialMediaFiveLink = getterUtil.getString(themeDisplay.getThemeSetting('Social Media Five Link'))
/>
