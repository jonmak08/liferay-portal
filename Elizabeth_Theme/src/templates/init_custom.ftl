<#assign logo_css_class = logo_css_class + " navbar-brand" />
<#assign site_logo_height = 56 />

<#assign welcome_content = getterUtil.getString(themeDisplay.getThemeSetting("Welcome Content"))/>
<#assign welcome_header = getterUtil.getString(themeDisplay.getThemeSetting("Welcome Header"))/>
<#assign welcome_subheader1 = getterUtil.getString(themeDisplay.getThemeSetting("Welcome Subheader 1"))/>
<#assign welcome_subheader2 = getterUtil.getString(themeDisplay.getThemeSetting("Welcome Subheader 2"))/>

<#assign address = getterUtil.getString(themeDisplay.getThemeSetting("Address"))/>
<#assign facebook_link = getterUtil.getString(themeDisplay.getThemeSetting("Facebook Link"))/>
<#assign linkedin_link = getterUtil.getString(themeDisplay.getThemeSetting("LinkedIn Link"))/>
<#assign show_address = getterUtil.getBoolean(themeDisplay.getThemeSetting("Show Address"))/>
<#assign show_facebook = getterUtil.getBoolean(themeDisplay.getThemeSetting("Show Facebook"))/>
<#assign show_linkedin = getterUtil.getBoolean(themeDisplay.getThemeSetting("Show LinkedIn"))/>
<#assign show_twitter = getterUtil.getBoolean(themeDisplay.getThemeSetting("Show Twitter"))/>
<#assign twitter_link = getterUtil.getString(themeDisplay.getThemeSetting("Twitter Link"))/>

<#assign latitude = getterUtil.getString(themeDisplay.getThemeSetting("latitude"))/>
<#assign longitude = getterUtil.getString(themeDisplay.getThemeSetting("longitude"))/>
<#assign show_map = getterUtil.getBoolean(themeDisplay.getThemeSetting("Show Map"))/>

<#assign section_one_content = getterUtil.getString(themeDisplay.getThemeSetting("Section One Content"))/>
<#assign section_one_title = getterUtil.getString(themeDisplay.getThemeSetting("Section One Title"))/>
<#assign section_two_content = getterUtil.getString(themeDisplay.getThemeSetting("Section Two Content"))/>
<#assign section_two_title = getterUtil.getString(themeDisplay.getThemeSetting("Section Two Title"))/>
<#assign section_three_content = getterUtil.getString(themeDisplay.getThemeSetting("Section Three Content"))/>
<#assign section_three_title = getterUtil.getString(themeDisplay.getThemeSetting("Section Three Title"))/>
<#assign show_footer = getterUtil.getBoolean(themeDisplay.getThemeSetting("Show Footer"))/>