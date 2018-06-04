<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone")>

<div aria-expanded="false" class="collapse navbar-collapse" id="navigationCollapse">
	<#if has_navigation && is_setup_complete>
		<nav class="${nav_css_class} site-navigation" id="navigation" role="navigation">
			<div class="navbar-form navbar-right" role="search">
				<@liferay.search default_preferences="${freeMarkerPortletPreferences}" />
			</div>

			<div class="navbar-right">
				<@liferay.navigation_menu default_preferences="${freeMarkerPortletPreferences}" />

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

            <li ${nav_item_attr_selected} class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
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

                                <#if nav_child.hasChildren()>
                                    <ul class="child-menu-level-3" role="menu">
                                        <#list nav_child.getChildren() as nav_child_level_3>
                                            <#assign
                                                nav_child_level_3_attr_selected = ""
                                                nav_child_level_3_css_class = ""
                                            />

                                            <#if nav_child.isSelected()>
                                                <#assign
                                                    nav_child_level_3_attr_selected = "aria-selected='true'"
                                                    nav_child_level_3_css_class = "selected"
                                                />
                                            </#if>

                                            <li ${nav_child_level_3_attr_selected} class="${nav_child_level_3_css_class}" id="layout_${nav_child_level_3.getLayoutId()}" role="presentation">
                                                <a aria-labelledby="layout_${nav_child_level_3.getLayoutId()}" href="${nav_child_level_3.getURL()}" ${nav_child_level_3.getTarget()} role="menuitem">${nav_child_level_3.getName()}</a>
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
		</nav>
	</#if>
</div>

<#assign VOID = freeMarkerPortletPreferences.reset()>