<#if has_navigation && is_setup_complete>
	<button aria-controls="navigationCollapse" aria-expanded="false" aria-label="Toggle navigation" class="fa fa-bars navbar-toggler navbar-toggler-right" data-target="#navigationCollapse" data-toggle="collapse" type="button"></button>

	<div aria-expanded="false" class="collapse navbar-collapse" id="navigationCollapse">
		<@liferay.navigation_menu default_preferences="${preferences}" />
	</div>
</#if>