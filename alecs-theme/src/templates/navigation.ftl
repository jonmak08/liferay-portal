<div aria-expanded="true" class="collapse navbar-collapse" id="navigationCollapse">
	<nav class="navbar-nav site-navigation navbar-right" id="navigation" data-spy="affix" data-offset-top="0" role="navigation">
		<#assign preferencesMap = {"displayDepth": "1", "portletSetupPortletDecoratorId": "barebone"} />

		<@liferay.navigation_menu
			default_preferences=freeMarkerPortletPreferences.getPreferences(preferencesMap)
			instance_id="main_navigation_menu"
		/>

		<div class="user-area">
		</div>
	</nav>
</div>
