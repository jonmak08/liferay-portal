<#--
This file allows you to override and define new FreeMarker variables.
-->
<#assign
  show_header_search = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-header-search"))
  year = .now
  phone = getterUtil.getString(themeDisplay.getThemeSetting("phone"))
  email = getterUtil.getString(themeDisplay.getThemeSetting("email"))
  facebook = getterUtil.getBoolean(themeDisplay.getThemeSetting("facebook"))
  facebookUrl = getterUtil.getString(themeDisplay.getThemeSetting("facebook-url"))
  twitter = getterUtil.getBoolean(themeDisplay.getThemeSetting("twitter"))
  twitterUrl = getterUtil.getString(themeDisplay.getThemeSetting("twitter-url"))
  instagram = getterUtil.getBoolean(themeDisplay.getThemeSetting("instagram"))
  instagramUrl = getterUtil.getString(themeDisplay.getThemeSetting("instagram-url"))
  youtube = getterUtil.getBoolean(themeDisplay.getThemeSetting("youtube"))
  youtubeUrl = getterUtil.getString(themeDisplay.getThemeSetting("youtube-url"))
  linkedin = getterUtil.getBoolean(themeDisplay.getThemeSetting("linkedin"))
  linkedinUrl = getterUtil.getString(themeDisplay.getThemeSetting("linkedin-url"))
  github = getterUtil.getBoolean(themeDisplay.getThemeSetting("github"))
  githubUrl = getterUtil.getString(themeDisplay.getThemeSetting("github-url"))
  dribbble = getterUtil.getBoolean(themeDisplay.getThemeSetting("dribbble"))
  dribbbleUrl = getterUtil.getString(themeDisplay.getThemeSetting("dribbble-url"))
/>