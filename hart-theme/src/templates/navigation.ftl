<nav class="${nav_css_class}" id="navigation" role="navigation">
	<h1 class="hide-accessible"><@liferay.language key="navigation" /></h1>
	<div class="control-menu-nav-item active nav-menu-hamburger-icon">
		<a class="control-menu-icon lfr-portal-tooltip product-menu-toggle sidenav-toggler" data-content="body" data-qa-id="productMenu" data-target="#_com_liferay_product_navigation_product_menu_web_portlet_ProductMenuPortlet_sidenavSliderId" data-title="Menu" data-toggle="sidenav" data-type="fixed-push" data-type-mobile="fixed" href="javascript:;" id="_com_liferay_product_navigation_product_menu_web_portlet_ProductMenuPortlet_sidenavToggleId" aria-describedby="yui_patched_v3_18_1_1_1527098774302_3014_com_liferay_product_navigation_product_menu_web_portlet_ProductMenuPortlet_sidenavToggleId">
			<div class="nav-menu-hamburger">
				<div class="pm"></div>
				<div class="cn"></div>
			</div>
		</a>
	</div>
	<ul aria-label="<@liferay.language key="site-pages" />" role="menubar">
		<#list nav_items as nav_item>
			<#assign
				nav_item_attr_has_popup = ""
				nav_item_attr_selected = ""
				nav_item_css_class = ""
				nav_item_layout = nav_item.getLayout()
			/>

			<#if nav_item.isSelected()>
				<#assign
					nav_item_attr_has_popup = "aria-haspopup='true'"
					nav_item_attr_selected = "aria-selected='true'"
					nav_item_css_class = "selected"
				/>
			</#if>

			<li ${nav_item_attr_selected} class="${nav_item_css_class} customNavLi" id="layout_${nav_item.getLayoutId()}" role="presentation">
				<a class="myNavLinks" aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span></a>

				<#if nav_item.hasChildren()>
					<ul class="child-menu" role="menu">
						<#list nav_item.getChildren() as nav_child>
							<#assign
								nav_child_attr_selected = ""
								nav_child_css_class = ""
							/>

							<#if nav_item.isSelected()>
								<#assign
									nav_child_attr_selected = "aria-selected='true'"
									nav_child_css_class = "selected"
								/>
							</#if>

							<li ${nav_child_attr_selected} class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
								<a aria-labelledby="layout_${nav_child.getLayoutId()}" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
							</li>
						</#list>
					</ul>
				</#if>
			</li>
		</#list>
		<div id="nav-menu-search-icon2" class="navbar-form navbar-right" style="display:none" role="search">
    	<@liferay.search default_preferences="${freeMarkerPortletPreferences}" />
		</div>
	</ul>
	<div id="nav-menu-search-icon" onclick="revealSearchBar()"></div>
</nav>