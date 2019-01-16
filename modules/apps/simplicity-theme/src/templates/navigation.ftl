<div class="navigation-top" id="navigation-top">
	<div class="wrap">
		<nav class="${nav_css_class} navigation-bar" id="navigation" role="navigation">
			<button class="icon nav-toggle-icon" aria-controls="top-menu" aria-expanded="false">
				<a class="responsive-icon" href="javascript:void(0);" onclick="checkNavBarToggled()">
					<svg aria-hidden="true" class="lexicon-icon lexicon-icon-add-column lexicon-icon-bars">
						<use xlink:href="${themeDisplay.getPathThemeImages()}/clay/icons.svg#bars" />
					</svg>
					Menu
				</a>
			</button>

			<div class="navigation-bar-container" id="navigation-bar">
				<h1 class="hide-accessible"><@liferay.language key="navigation" /></h1>

				<ul class="parent-menu" aria-label="<@liferay.language key="site-pages" />" role="menubar">
					<#list nav_items as nav_item>
						<#assign
							nav_item_attr_has_popup = ""
							nav_item_css_class = ""
							nav_item_layout = nav_item.getLayout()
						/>

						<#if nav_item.isSelected()>
							<#assign
								nav_item_attr_has_popup = "aria-haspopup='true'"
								nav_item_css_class = "selected"
							/>
						</#if>

						<li class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
							<a aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span>
								<#if nav_item.hasChildren()>
									<svg aria-hidden="true" class="lexicon-icon lexicon-icon-add-column lexicon-icon-angle-down">
										<use xlink:href="${themeDisplay.getPathThemeImages()}/clay/icons.svg#angle-down" />
									</svg>
								</#if>
							</a>

							<#if nav_item.hasChildren()>
								<ul class="child-menu" role="menu">
									<#list nav_item.getChildren() as nav_child>
										<#assign
											nav_child_css_class = ""
										/>

										<#if nav_item.isSelected()>
											<#assign
												nav_child_css_class = "selected"
											/>

										</#if>

										<li class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
											<a aria-labelledby="layout_${nav_child.getLayoutId()}" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}
												<#if nav_child.hasChildren()>
													<svg aria-hidden="true" class="lexicon-icon lexicon-icon-add-column lexicon-icon-angle-right">
														<use xlink:href="${themeDisplay.getPathThemeImages()}/clay/icons.svg#angle-right" />
													</svg>
												</#if>
											</a>

											<#if nav_child.hasChildren()>
												<ul class="nested-child-menu" role="menu">
													<#list nav_child.getChildren() as nav_child>
														<#assign
															nav_child_css_class = ""
														/>

														<#if nav_child.isSelected()>
															<#assign
																nav_child_css_class = "selected"
															/>
														</#if>

														<li class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
															<a aria-labelledby="layout_${nav_child.getLayoutId()}" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
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
			</div>

			<a href="#main-content" class="menu-scroll-down">
				<svg aria-hidden="true" class="lexicon-icon lexicon-icon-add-column lexicon-icon-arrow-down">
					<use xlink:href="${themeDisplay.getPathThemeImages()}/clay/icons.svg#order-arrow-down" />
				</svg>
			</a>
		</nav>
	</div>
</div>