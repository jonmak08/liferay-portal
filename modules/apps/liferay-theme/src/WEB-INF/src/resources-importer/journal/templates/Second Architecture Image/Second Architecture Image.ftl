<div class="architecture-wrapper2">    
    <div class="Image2">
        <#if ArchitectureImage2.getData()?? && ArchitectureImage2.getData() != "">
        	<img alt="${ArchitectureImage2.getAttribute("alt")}" src="${ArchitectureImage2.getData()}" />
        </#if>
    </div>
    
    <div class="Image2Text">
        ${ArchitectureImage2Text.getData()}
    </div>
</div>