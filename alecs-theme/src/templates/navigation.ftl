<div aria-expanded="false" class="collapse navbar-collapse" id="navigationCollapse">
	<#if has_navigation && is_setup_complete>
		<div class="${nav_css_class} ${nav_css_right} navbar-right site-navigation" id="navigation" role="navigation">
			<@liferay.navigation_menu default_preferences=freeMarkerPortletPreferences.getPreferences("portletSetupPortletDecoratorId", "barebone") />
		</div>
	</#if>
</div>
