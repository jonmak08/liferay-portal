<div aria-expanded="true" class="collapse navbar-collapse" id="navigationCollapse">
	<nav class="navbar-nav navbar-right site-navigation" id="navigation" role="navigation">
		<#assign preferencesMap = {"displayDepth": "1", "portletSetupPortletDecoratorId": "barebone"} />

		<@liferay.navigation_menu
			default_preferences=freeMarkerPortletPreferences.getPreferences(preferencesMap)
			instance_id="main_navigation_menu"
		/>

		<div class="user-area" />
	</nav>
</div>
