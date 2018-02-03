<<<<<<< HEAD
<#if has_navigation && is_setup_complete>
	<button aria-controls="navigationCollapse" aria-expanded="false" aria-label="Toggle navigation"  class="navbar-toggler navbar-toggler-right" data-target="#navigationCollapse" data-toggle="collapse" type="button">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div aria-expanded="false" class="collapse mt-4 mt-md-0 navbar-collapse" id="navigationCollapse">
		<@liferay.navigation_menu default_preferences="${preferences}" />
	</div>
</#if>
=======
<div aria-expanded="false" class="collapse navbar-collapse" id="navigationCollapse">
	<#if has_navigation && is_setup_complete>
		<nav class="${nav_css_class} site-navigation" id="navigation" role="navigation">
			<#assign preferences = freeMarkerPortletPreferences.getPreferences("portletSetupPortletDecoratorId", "barebone") />

			<#if show_header_search>
				<div class="navbar-form navbar-right" role="search">
					<@liferay.search default_preferences="${preferences}" />
				</div>
			</#if>

			<div class="navbar-right">
				<@liferay.navigation_menu default_preferences="${preferences}" />
			</div>
		</nav>
	</#if>
</div>
>>>>>>> compatible
