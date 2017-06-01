<nav class="${nav_css_class} navbar-custom navbar navbar-inverse navbar-fixed-top" id="navigation" role="navigation">
	<h1 class="hide-accessible"><@liferay.language key="navigation" /></h1>

	<div class="container-fluid">
		<div class="navbar-header">
			<button id="hamburgerMenu" type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-reorder"></span>
				<span class="sidebar-menu">Menu</span>
			</button>

			<#if show_site_name>
				<a class="site-name navbar-brand brand-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					${site_name}
				</a>
			</#if>
		</div>

		<div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav nav-links" aria-label="<@liferay.language key="site-pages" />" role="menubar">
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

				<li ${nav_item_attr_selected} class="${nav_item_css_class} dropdown" id="layout_${nav_item.getLayoutId()}" role="presentation">
					<a aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span></a>

					<#if nav_item.hasChildren()>
					<ul class="child-menu dropdown-menu" role="menu">
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
							<span>
								<a class="child-link" aria-labelledby="layout_${nav_child.getLayoutId()}" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
							</span>
						</li>
						</#list>
					</ul>
					</#if>
				</li>
				</#list>

				<li>
					<a id="searchIcon" href="#"><span class="icon-search"></span></a>
				</li>

				<#if !is_signed_in>
				<li>
					<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="signIn" rel="nofollow">${sign_in_text}</a>
				</li>
				</#if>
			</ul>
		</div>
	</div>
</nav>
