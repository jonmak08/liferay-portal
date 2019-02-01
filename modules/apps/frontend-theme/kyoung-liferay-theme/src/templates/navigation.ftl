<#if has_navigation && is_setup_complete>
	<button aria-controls="navigationCollapse" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler navbar-toggler-right" data-target="#navigationCollapse" data-toggle="collapse" type="button">
		<span class="navbar-toggler-icon"></span>
	</button>
	<#--  Removed .collapse class from navba, since li links were dissapearing past 760 media query  -->
	<div aria-expanded="false" class="navbar-collapse" id="navigationCollapse">
		<@liferay.navigation_menu default_preferences="${preferences}"
 />
	</div>
</#if>