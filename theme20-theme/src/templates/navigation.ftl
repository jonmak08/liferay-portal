<div class="navbar navbar-collapse-absolute navbar-expand-md navbar-underline navigation-bar" id="theme-navigation">
    <nav role="navigation">
        <ul aria-label="<@liferay.language key="site-pages" />" class="navbar-nav mr-auto" role="menubar">
            <#list nav_items as nav_item>
                <#assign
                     nav_item_attr_has_popup = ""
                    nav_item_css_class = "nav-item"
                    nav_item_layout = nav_item.getLayout()
                    nav_item_caret = ""
                    nav_item_counter = nav_item?counter
                />

                <#if nav_item.isSelected()>
                    <#assign
                        nav_item_attr_has_popup = "aria-haspopup='true'"
                        nav_item_css_class = "${nav_item_css_class} selected"
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

                <li class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
                    <a 
                        aria-labelledby="layout_${nav_item.getLayoutId()}" 
                        class="${nav_link_css_class}" ${nav_item_attr_has_popup} 
                        href="${nav_item.getURL()}" 
                        ${nav_item.getTarget()} 
                        role="menuitem"
                        >
                        <span>
                            <@liferay_theme["layout-icon"] layout=nav_item_layout /> 
                            ${nav_item.getName()?left_pad(20)[0..*20]}
                        </span> 
                        ${nav_item_caret}
                    </a>

                    <#if nav_item.hasChildren()>
                        <ul class="${child_menu_css_class}" role="menu">
                            <#list nav_item.getChildren() as nav_child>
                                <#assign
                                nav_child_css_class = "nav-item"
                                />

                                <#if nav_item.isSelected()>
                                <#assign
                                    nav_child_css_class = "nav-item selected"
                                />
                                </#if>

                                <li class="${nav_link_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
                                    <a aria-labelledby="layout_${nav_child.getLayoutId()}" class="${nav_link_css_class}" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()?left_pad(20)[0..*20]}</a>
                                </li>
                            </#list>
                        </ul>
                    </#if>
                </li>

                <#if nav_item_counter?matches("[9]$")>
                    <div class="break"></div>
                </#if>
            </#list>
        </ul>
    </nav>
</div>

<div>
	<@liferay.user_personal_bar />
</div>
