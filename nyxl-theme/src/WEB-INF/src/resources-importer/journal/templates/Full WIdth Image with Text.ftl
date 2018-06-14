<style>
    section.container-fluid-1280 {
        max-width: none;
    }
    
    #column-1  {
        padding: 0;
    }
</style>

<div class="hero-image-container">
    <div class="hero-image">
        <#if image.getData()?? && image.getData() != "">
	<img data-fileentryid="${image.getAttribute("fileEntryId")}" alt="${image.getAttribute("alt")}" src="${image.getData()}" />
</#if>
    
        <div class="hero-image-content">
            <#if text.getSiblings()?has_content>
                <#list text.getSiblings() as cur_text> ${cur_text.getData()}</#list>
            </#if>
        </div>
    </div>
</div>