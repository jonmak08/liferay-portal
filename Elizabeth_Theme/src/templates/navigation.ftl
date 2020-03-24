<nav class="navbar-nav" id="themeNav">

	<nav class="${nav_css_class}" id="navigation" role="navigation">

		<ul aria-label="<@liferay.language key="site-pages" />" class="navbar-nav mr-auto" role="menubar">

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
						<li class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
							<a 
								aria-labelledby="layout_${nav_item.getLayoutId()}" 
								class="nav-link" ${nav_item_attr_has_popup} 
								href="${nav_item.getURL()}" 
								id = "navigation-link"
								${nav_item.getTarget()} 
								role="menuitem"
							>
							<span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span> 
							${nav_item_caret}
							</a>

							<#if nav_item.hasChildren()>
								<ul class="child-menu" role="menu">
									<#list nav_item.getChildren() as nav_child>
										<#assign
											nav_child_css_class = "nav-item"
										/>

										<#if nav_item.isSelected()>
											<#assign
												nav_child_css_class = "nav-item selected"
											/>
										</#if>

										<li class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
											<a aria-labelledby="layout_${nav_child.getLayoutId()}" class="nav-link" href="${nav_child.getURL()}" id="navigation-link" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
										</li>
									</#list>
								</ul>
							</#if>
						</li>
					<#else>
						<#sep>
							<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
								<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" />
							</a>
						</#sep>

						<li class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
						<a 
							aria-labelledby="layout_${nav_item.getLayoutId()}" 
							class="nav-link" ${nav_item_attr_has_popup} 
							href="${nav_item.getURL()}" 
							id="navigation-link"
							${nav_item.getTarget()} 
							role="menuitem"
						>
						<span><@liferay_theme["layout-icon"] layout=nav_item_layout />${nav_item.getName()}</span> 
						${nav_item_caret}
						</a>
						
						<#if nav_item.hasChildren()>
							<ul class="child-menu" role="menu">
								<#list nav_item.getChildren() as nav_child>
									<#assign
										nav_child_css_class = "nav-item"
									/>

									<#if nav_item.isSelected()>
										<#assign
											nav_child_css_class = "nav-item selected"
										/>
									</#if>

									<li class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
										<a aria-labelledby="layout_${nav_child.getLayoutId()}" class="nav-link" href="${nav_child.getURL()}" id="navigation-link" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
									</li>
								</#list>
							</ul>
						</#if>
						</li>
					</#if>
				</#if>
			</#list>
		</ul>
	</nav>
</nav>

<div class="theme-user">
	<@liferay.user_personal_bar />
</div>