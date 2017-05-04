<#--
Web content templates are used to lay out the fields defined in a web
content structure.

Please use the left panel to quickly add commonly used variables.
Autocomplete is also available and can be invoked by typing "${".
-->
<div class="centered-content-container">
    <div class="flexbox-container">
        <#if image.getSiblings()?has_content>
	        <#list image.getSiblings() as cur_image>
	            <div class="image-container">
	                <div class="mouseOverShadowPic">
	                    <#if cur_image.link.getFriendlyUrl()?? && cur_image.link.getFriendlyUrl() != "">
	                        <a href="${cur_image.link.getFriendlyUrl()}">
	                    </#if>
		                <img alt="${cur_image.getAttribute("alt")}" src="${cur_image.getData()}" />
		                <#if cur_image.link.getFriendlyUrl()?? && cur_image.link.getFriendlyUrl() != "">
	                        </a>
	                    </#if>
	                </div>
		        </div>
	        </#list>
        </#if>
    </div>
</div>
