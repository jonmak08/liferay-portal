<#if Imagefaa2.getSiblings()?has_content>
	<#list Imagefaa2.getSiblings() as cur_Imagefaa2>
		<#if cur_Imagefaa2.getData()?? && cur_Imagefaa2.getData() != "">
			<img alt="${cur_Imagefaa2.getAttribute("alt")}" src="${cur_Imagefaa2.getData()}" />
		</#if>
	</#list>
</#if>