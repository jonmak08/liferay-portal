<#--
Web content templates are used to lay out the fields defined in a web
content structure.

Please use the left panel to quickly add commonly used variables.
Autocomplete is also available and can be invoked by typing "${".
-->
<div class="centered-content-container">
    <div class="fancy-image-links-container">
        <#list image.getSiblings() as cur_image>
            <div class="mouse-over-square-pic square-pic">
                <a href="${cur_image.link.getFriendlyUrl()}">
                    <div class="info-container">
                        <div class="paragraph-div info-container-text">${cur_image.text.getData()}</div>
                        <div class="blue-button small-button">${cur_image.bluebuttontext.getData()}</div>
                    </div>
                    <#assign themePath = getterUtil.getString(request['theme-display']['path-theme-images']) />
                    <img class="container-content" src="${themePath}/${cur_image.getData()}"/>
                </a>
            </div>
        </#list>
    </div>
</div>
