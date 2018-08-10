<footer id="footer" role="contentinfo">
	<nav id="navbar-footer">
		<div class="container-fluid-1280">
			<div class="nav navbar-right small text-uppercase" role="menubar">
				<#assign preferencesMap = {"displayDepth": "1", "portletSetupPortletDecoratorId": "barebone"} />

				<@liferay.navigation_menu
					default_preferences=freeMarkerPortletPreferences.getPreferences(preferencesMap)
					instance_id="footer_navigation_menu"
				/>
			</div>
		</div>
	</nav>
	<div class="outro">
		<hr />
		${company_name} - Powered by <a href="https://liferay.com">Liferay</a>
	</div>
</footer>
