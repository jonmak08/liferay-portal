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