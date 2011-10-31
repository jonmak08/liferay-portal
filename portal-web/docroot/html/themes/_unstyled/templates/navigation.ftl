<nav class="sort-pages modify-pages" id="navigation">
	<h1>
		<span><@liferay.language key="navigation" /></span>
	</h1>

	<ul>
		<#list nav_items as nav_item>
			<li>
				<#assign nav_item_class = "">
				<#if nav_item_index == 0 && !nav_item_has_next>
					<#assign nav_item_class = nav_item_class + " first last only">
				<#elseif nav_item_index == 0>
					<#assign nav_item_class = nav_item_class + " first">
				<#elseif !nav_item_has_next>
					<#assign nav_item_class = nav_item_class + " last">
				</#if>
				<#if nav_child.isSelected()>
					<#assign nav_item_class = nav_item_class + " selected">
				</#if>

				<a class="${nav_item_class}" href="${nav_item.getURL()}" ${nav_item.getTarget()}><span>${nav_item.icon()} ${nav_item.getName()}</span></a>

				<#if nav_item.hasChildren()>
					<ul class="child-menu">
						<#list nav_item.getChildren() as nav_child>
							<li>
								<#assign nav_child_class = "">
								<#if nav_item_index == 0 && !nav_item_has_next>
									<#assign nav_item_class = nav_child_class + " first last only">
								<#elseif nav_item_index == 0>
									<#assign nav_child_class = nav_child_class + " first">
								<#elseif !nav_item_has_next>
									<#assign nav_child_class = nav_child_class + " last">
								</#if>
								<#if nav_child.isSelected()>
									<#assign nav_child_class = nav_child_class + " selected">
								</#if>
								
								<a class="${nav_child_class}" href="${nav_child.getURL()}" ${nav_child.getTarget()}>${nav_child.getName()}</a>
							</li>
						</#list>
					</ul>
				</#if>
			</li>
		</#list>
	</ul>
</nav>