<#--
Web content templates are used to lay out the fields defined in a web
content structure.

Please use the left panel to quickly add commonly used variables.
Autocomplete is also available and can be invoked by typing "${".
-->
<div class="centered-content-container">
    <div class="fancy-image-links-container">
        <#list image.getSiblings() as cur_image>
            <div class="mouseOverSquarePic squarePic">
                <a href="${cur_image.link.getFriendlyUrl()}">
                    <div class="info-container">
                        <div class="paragraph-div info-container-text">${cur_image.text.getData()}</div>
                        <div class="bluebutton small-button">${cur_image.bluebuttontext.getData()}</div>
                    </div>
                    <img class="container-content" src="${cur_image.getData()}"/>
                </a>
            </div>
        </#list>
    </div>
</div>
