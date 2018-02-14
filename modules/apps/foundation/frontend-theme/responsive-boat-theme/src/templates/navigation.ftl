<nav class="${nav_css_class}" id="navigation" role="navigation">
	<h1 class="hide-accessible">
		<@liferay.language key="navigation" />
	</h1>

	<div class="nav-btn icon-reorder"></div>
	
	<@liferay.navigation_menu default_preferences=freeMarkerPortletPreferences.getPreferences("portletSetupPortletDecoratorId", "dark") />
</nav>