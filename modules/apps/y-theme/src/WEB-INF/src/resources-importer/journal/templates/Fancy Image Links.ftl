<#--
Web content templates are used to lay out the fields defined in a web
content structure.

Please use the left panel to quickly add commonly used variables.
Autocomplete is also available and can be invoked by typing "${".
-->
<div class="centered-content-container">
    <div class="fancy-image-links-container">
        <#list image.getSiblings() as cur_image>
            <#assign link_all = link.getSiblings() />
            <#assign cur_link = link_all[cur_image?index] />
            <#assign text_all = text.getSiblings() />
            <#assign cur_text = text_all[cur_image?index] />
            <#assign blue_all = Texthlkf.getSiblings() />
            <#assign cur_blue = blue_all[cur_image?index] />
            <div class="mouseOverSquarePic squarePic">
                <a href="${cur_link.getFriendlyUrl()}">
                    <img style="width: 100%;" src="${cur_image.getData()}"/>
                    <div class="info-container">
                    <p>${cur_text.getData()}</p>
                    <p>${cur_blue.getData()}</p>
                    </div>
                </a>

            </div>
        </#list>
    </div>
</div>
