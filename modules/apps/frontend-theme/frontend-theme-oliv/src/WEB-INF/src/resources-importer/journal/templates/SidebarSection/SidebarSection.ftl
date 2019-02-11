<div class="sidebar-section">
	<h4 class="sidebar-section-header">
		${SectionHeader.getData()}
	</h4>

	<#if TagName.getSiblings()?has_content>
		<#list TagName.getSiblings() as cur_TagName>
			<li class="sidebar-section-li ${SectionHeader.getData()}">
				<a class="sidebar-section-tag" href="${cur_TagName.TagLink.getData()}">${cur_TagName.getData()}</a>
			</li>
		</#list>
	</#if>
</div>