<div class="collapse navbar-collapse" id="navbarSupportedContent">
<nav class="${nav_css_class}" id="navigation" role="navigation">

	<ul aria-label="<@liferay.language key="site-pages" />" role="menubar" class="navbar-nav navbar-right">
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

			<#if !nav_item.hasChildren()>
				<li ${nav_item_attr_selected} class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
					<a aria-labelledby="layout_${nav_item.getLayoutId()}" class="nav-anchor" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem">
						<span class="navItemSpan"><@liferay_theme["layout-icon"] layout=nav_item_layout />
							${nav_item.getName()}
						</span>
					</a>
				</li>
			<#elseif nav_item.hasChildren()>
				<li class="dropdown" role="menu">
					<a aria-labelledby="layout_${nav_item.getLayoutId()}" class="nav-anchor" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem">
						<span class="navItemSpan"><@liferay_theme["layout-icon"] layout=nav_item_layout />
							${nav_item.getName()}
						</span>
					</a>
					<a aria-expanded="false" aria-haspopup="true" class="dropdown-toggle nav-link" data-toggle="dropdown" href="#" role="button">
						<span class="root-icon">
							<i class="icon-chevron-left"></i>
							<i class="icon-chevron-down"></i>
						</span>
					</a>

					<ul class="dropdown-menu">
						<#list nav_item.getChildren() as nav_child>
							<#assign
								nav_child_attr_has_popup =""
								nav_child_attr_selected = ""
								nav_child_css_class = ""
								nav_child_layout = nav_child.getLayout()
							/>

							<#if nav_item.isSelected()>
								<#assign
									nav_child_attr_has_popup = "aria-haspopup ='true'"
									nav_child_attr_selected = "aria-selected='true'"
									nav_child_css_class = "selected"
								/>
							</#if>

							<#if !nav_child.hasChildren()>
								<li ${nav_child_attr_selected} class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
									<a aria-labelledby="layout_${nav_child.getLayoutId()}" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">
										${nav_child.getName()}
									</a>
								</li>
							<#elseif nav_child.hasChildren()>
								<li class="dropdown-submenu" role="menu">
									<a aria-labelledby="layout_${nav_child.getLayoutId()}" class="nav-anchor" ${nav_child_attr_has_popup} href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem" tabindex="-1">
										<span class="navItemSpan"><@liferay_theme["layout-icon"] layout=nav_item_layout />
											${nav_child.getName()}
											<i class="dropdown-md-icon icon-chevron-left sub-menu"></i>
											<i class="dropdown-md-icon icon-chevron-down sub-menu"></i>
										</span>
										<a aria-expanded="false" aria-haspopup="true" class="dropdown-toggle nav-link" data-toggle="dropdown" href="#" role="button">
											<span class="sub-Icon">
												<i class="icon-chevron-left sub-menu"></i>
												<i class="icon-chevron-down sub-menu"></i>
											</span>
										</a>
									</a>

									<ul class="dropdown-menu">
										<#list nav_child.getChildren() as nav_grand_child>
											<#assign
												nav_grand_child_attr_selected = ""
												nav_grand_child_css_class = ""
											/>

											<#if nav_child.isSelected()>
												<#assign
													nav_grand_child_attr_selected = "aria-selected='true'"
													nav_grand_child_css_class = "selected"
												/>
											</#if>

											<li ${nav_grand_child_attr_selected} class="${nav_grand_child_css_class}" id="layout_${nav_grand_child.getLayoutId()}" role="presentation">
												<p>
													<a aria-labelledby="layout_${nav_grand_child.getLayoutId()}" href="${nav_grand_child.getURL()}" ${nav_grand_child.getTarget()} role="menuitem">
														${nav_grand_child.getName()}
													</a>
												</p>
											</li>			
										</#list>
									</ul>
								</li>
							</#if>
						</#list>
					</ul>
				</li>
			</#if>
		</#list>
	</ul>
</nav>
</div>