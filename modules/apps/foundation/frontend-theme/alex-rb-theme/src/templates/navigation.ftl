<nav class="navbar-right ${nav_css_class}" id="navigation" role="navigation">
	<h1 class="hide-accessible">
		<@liferay.language key="navigation" />
	</h1>

	<div class="icon-reorder nav-btn"></div>
	
	<@liferay.navigation_menu default_preferences=freeMarkerPortletPreferences.getPreferences("portletSetupPortletDecoratorId", "barebone") />
</nav>