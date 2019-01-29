<div class="oria-slider">
    <div class="slider-inner">
        <div class="owl-wrapper-outer">
            <div class="owl-wrapper">
                <#if Imageh2jy.getSiblings()?has_content>
                    <#list Imageh2jy.getSiblings() as cur_Imageh2jy>
                        <div class="owl-item">
                            <div class="slide ${(cur_Imageh2jy?counter == 1)?then('active', '')} item">
                                <img alt="${cur_Imageh2jy.getAttribute("alt")}" data-fileentryid="${cur_Imageh2jy.getAttribute("fileEntryId")}" src="${cur_Imageh2jy.getData()}" />
                            </div>
                            <h3 class="slide-title">
                                <a href="www.google.com">${cur_Imageh2jy.getAttribute("alt")}</a>
                            </h3>
                        </div>
                    </#list>
                </#if>
            </div>
        </div>
        <div class="owl-controls">
            <div class="owl-buttons">
                <div class="owl-prev"></div>
                <div class="owl-next"></div>
            </div>
        </div>
    </div>
</div>