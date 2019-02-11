<nav class="${nav_css_class}" id="navigation" role="navigation">
	<h1 class="hide-accessible"><@liferay.language key="navigation" /></h1>

	<ul aria-label="<@liferay.language key="site-pages" />" class="navbar-blank navbar-nav navbar-site parent-menu" role="menubar">
		<#list nav_items as nav_item>
			<#assign
				nav_item_attr_has_popup = ""
				nav_item_css_class = "nav-li"
				nav_item_layout = nav_item.getLayout()
				nav_item_text_class = "nav-text"
			/>

			<#if nav_item.isSelected()>
				<#assign
					nav_item_attr_has_popup = "aria-haspopup='true'"
					nav_item_css_class += " selected"
				/>
			</#if>

			<#if nav_item.hasChildren()>
				<#assign
					nav_item_css_class += " dropdown"
					nav_item_text_class += " dropdown-toggle"
				/>
			</#if>

			<li class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
				<div class="nav-obj">
					<div class="${nav_item_text_class}">
						<a aria-labelledby="layout_${nav_item.getLayoutId()}" class="nav-link text-truncate" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem">
						<@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}
						</a>
					</div>
				</div>

				<#if nav_item.hasChildren()>
					<ul class="child-menu dropdown-overlay" role="menu">
						<#list nav_item.getChildren() as nav_child>
							<#assign
								nav_child_css_class = "dropdown-li"
								nav_child_text_class = "dropdown-text"
							/>

							<#if nav_child.isSelected()>
								<#assign
									nav_child_css_class += " selected"
								/>
							</#if>

							<#if nav_child.hasChildren()>
								<#assign
									nav_child_css_class += " dropdown"
									nav_child_text_class += " dropdown-toggle"
								/>
							</#if>

							<li class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
								<div class="dropdown-obj">
									<div class="${nav_child_text_class}">
										<a aria-labelledby="layout_${nav_child.getLayoutId()}" class="dropdown-link text-truncate" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">
											${nav_child.getName()}
										</a>
									</div>
								</div>

								<#if nav_child.hasChildren()>
									<ul class="dropdown-overlay grandchild-menu" role="menu">
										<#list nav_child.getChildren() as nav_grandchild>
											<#assign
												nav_grandchild_css_class = "dropdown-li"
											/>

											<#if nav_grandchild.isSelected()>
												<#assign
													nav_grandchild_css_class += " selected"
												/>
											</#if>

											<li class="${nav_grandchild_css_class}" id="layout_${nav_grandchild.getLayoutId()}" role="presentation">
												<div class="dropdown-obj">
													<div class="dropdown-text">
														<a aria-labelledby="layout_${nav_grandchild.getLayoutId()}" class="dropdown-link text-truncate" href="${nav_grandchild.getURL()}" ${nav_grandchild.getTarget()} role="menuitem">${nav_grandchild.getName()}</a>
													</div>
												</div>
											</li>
										</#list>
									</ul>
								</#if>
							</li>
						</#list>
					</ul>
				</#if>
			</li>
		</#list>
	</ul>
</nav>