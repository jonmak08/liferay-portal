<div class="architecture-wrapper3">
    <div class="Image3">
        <#if Image3.getData()?? && Image3.getData() != "">
        	<img alt="${Image3.getAttribute("alt")}" src="${Image3.getData()}" />
        </#if>
    </div>
    
    <div class="Image4">
        <#if Image4.getData()?? && Image4.getData() != "">
        	<img alt="${Image4.getAttribute("alt")}" src="${Image4.getData()}" />
        </#if>
    </div>
</div>