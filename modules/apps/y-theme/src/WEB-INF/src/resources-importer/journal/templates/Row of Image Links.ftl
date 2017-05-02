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
		    <#if cur_image.getData()?? && cur_image.getData() != "">
			    <#assign link_cur = link.getSiblings() />
			    <#assign cur_link = link_cur[cur_image?index] />
			    <div class="image-container">
			        <div class="mouseOverShadowPic">
			            <#if cur_link.getData()?? && cur_link.getData() != "">
    			            <a href="${cur_link.getFriendlyUrl()}">
	    		        </#if>
		    	            <img alt="${cur_image.getAttribute("alt")}" src="${cur_image.getData()}" />
			            <#if cur_link.getData()?? && cur_link.getData() != "">       
			                </a>
			            </#if>
			        </div>
			    </div>
		    </#if>
	    </#list>
    </#if>
    </div>
</div>
