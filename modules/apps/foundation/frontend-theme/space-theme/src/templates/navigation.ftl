<nav class="${nav_css_class}" id="navigation" role="navigation">
	<div class="container-fluid">
		<h1 class="hide-accessible">

			<@liferay.language key="navigation" />

		</h1>	
		<h1 class="site-title">		
			<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
				<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
			</a>
		
			<#if show_site_name>

				<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">

					${site_name} 

				</span>

			</#if>

		</h1>
		<ul aria-label="<@liferay.language key="site-pages" />" id="navbar" role="menubar" >
		<span id="hamburger" onclick="openNav()" style="font-size:47px;cursor:pointer" >&#9776;</span>

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
				
				<li ${nav_item_attr_selected} class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation" class="active">
					<a aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span></a>

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

		</ul>
	</div>
</nav>