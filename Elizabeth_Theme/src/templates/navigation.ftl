<nav class="navbar-nav" id="themeNav">
	<nav class="${nav_css_class}" id="navigation" role="navigation">
		<div class="burger">
			<div class="line"></div>
			<div class="line"></div>
			<div class="line"></div>
		</div>
		<ul aria-label="<@liferay.language key="site-pages" />" class="navbar-nav mr-auto" role="menubar" id="ul-nav-items">
			<#list nav_items as nav_item>
				<#assign
					nav_item_attr_has_popup = ""
					nav_item_css_class = "nav-item"
					nav_item_layout = nav_item.getLayout()
					nav_item_caret = ""
					nav_item_counter = nav_items?size
				/>

				<#if nav_item.isSelected()>
					<#assign
						nav_item_attr_has_popup = "aria-haspopup='true'"
						nav_item_css_class = "selected"
					/>
				</#if>

				<#if nav_item.hasChildren()>
					<#assign
						nav_item_css_class = "${nav_item_css_class} dropdown"
						nav_item_caret = '<svg class="lexicon-icon">
						<use xlink:href="${images_folder}/lexicon/icons.svg#caret-bottom" />
						</svg>'
					/>
				</#if>

				<#if (nav_item_counter >= 3)>
					<#if (nav_item?counter <= (nav_item_counter/2))>
						<#include "${full_templates_path}/display_list_item.ftl" />
					<#elseif (nav_item?counter gte (nav_item_counter/2) && nav_item?counter <= ((nav_item_counter/2)+1))>
						<a class="${logo_css_class}" href="${site_default_url}" id="navbar-logo" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
							<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" />
						</a>
						<#--  <div class="right-nav-items">  -->
							<#include "${full_templates_path}/display_list_item.ftl" />
						<#--  </div>  -->
					<#else>
						<#--  <div class="right-nav-items">  -->
							<#include "${full_templates_path}/display_list_item.ftl" />
						<#--  </div>  -->
					</#if>
				</#if>
			</#list>
			
			<div class="theme-user">
				<@liferay.user_personal_bar />
			</div>
		</ul>
	</nav>
</nav>

