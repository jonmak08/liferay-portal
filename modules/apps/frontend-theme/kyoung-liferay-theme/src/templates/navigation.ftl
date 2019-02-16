<nav class="${nav_css_class}" id="navigation" role="navigation">
	<h1 class="hide-accessible"><@liferay.language key="navigation" /></h1>

	<ul aria-label="<@liferay.language key="site-pages" />" role="menubar">
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
				<#if nav_item.hasChildren()>
					<a aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}<i class="fa fa-angle-down"></i></span></a>
                    <#else>
					<a aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span></a>
                </#if>
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
                            <#if nav_child.hasChildren()>
								<a aria-labelledby="layout_${nav_child.getLayoutId()}" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}<span class="fa fa-angle-down"></span></a>
                                <#else>
                                <a aria-labelledby="layout_${nav_child.getLayoutId()}" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
							</li>
                            </#if>
                            <#if nav_child.hasChildren()>
					<ul class="child-menu" role="menu">
						<#list nav_child.getChildren() as nav_grandchild>
							<#assign
								nav_grandchild_css_class = ""
							/>

							<#if nav_item.isSelected()>
								<#assign
									nav_child_css_class = "selected"
								/>
							</#if>

							<li class="${nav_grandchild_css_class}" id="layout_${nav_grandchild.getLayoutId()}" role="presentation">
                            <#if nav_grandchild.hasChildren()>
								<a aria-labelledby="layout_${nav_grandchild.getLayoutId()}" href="${nav_grandchild.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_grandchild.getName()}<i class="fa fa-angle-down"></i></a>
                            <#else>
								<a aria-labelledby="layout_${nav_grandchild.getLayoutId()}" href="${nav_grandchild.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_grandchild.getName()}</a>
							</li>
                            </#if>
						</#list>
					</ul>
				</#if>
						</#list>
					</ul>
				</#if>
			</li>
		</#list>
	</ul>
</nav>