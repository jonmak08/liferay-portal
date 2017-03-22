<div class="architecture-wrapper">
    <#if ArchitectureImage.getData()?? && ArchitectureImage.getData() != "">
    	<img alt="${ArchitectureImage.getAttribute("alt")}" src="${ArchitectureImage.getData()}" />
    </#if>
</div>