<footer id="footer" role="contentinfo">
	<nav id="navbar-footer">
		<div class="container-fluid-1280">
			<div class="nav navbar-left navbar-user text-uppercase">
				<@liferay.user_personal_bar />
			</div>

			<div class="nav navbar-right small text-uppercase" role="menubar">
				<#assign preferencesMap = {"displayDepth": "1", "portletSetupPortletDecoratorId": "barebone"} />

				<@liferay.navigation_menu
					default_preferences=freeMarkerPortletPreferences.getPreferences(preferencesMap)
					instance_id="footer_navigation_menu"
				/>
			</div>
		</div>
	</nav>

	<#include "${full_templates_path}/social_media.ftl" />

	<div class="outro">
		<hr />
		${company_name}
		<p id="copyright">
			<small>
				<@liferay.language key="powered-by" />
				<a href="http://www.liferay.com" rel="external">
					Liferay
				</a>
			</small>
		</p>
	</div>
</footer>
