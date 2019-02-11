<div class="slider-container">
    <div class="slider-outer">
        <div class="slider-inner">
            <div class="slider-wrapper" id="slider-wrapper">

                <#if CarouselImage.getSiblings()?has_content>
                    <#list CarouselImage.getSiblings() as cur_CarouselImage>
                        <#if cur_CarouselImage.getData()?? && cur_CarouselImage.getData() != "">
                            <div class="slider-item">
                                <div class="slider-image">
                                    <img alt="${cur_CarouselImage.getAttribute("alt")}" class="thumbnail" data-fileentryid="${cur_CarouselImage.getAttribute("fileEntryId")}" src="${cur_CarouselImage.getData()}" />
                                </div>

                                <#if cur_CarouselImage.getAttribute("alt")?? && cur_CarouselImage.getAttribute("alt") != "">
                                    <h3 class="slider-title">
                                        <div class="slider-text">
                                            ${cur_CarouselImage.getAttribute("alt")}
                                        </div>
                                    </h3>
                                </#if>

                            </div>
                        </#if>
                    </#list>
                </#if>
            </div>
        </div>

        <div class="slider-controls">
            <div class="slider-buttons">
                <div class="slider-prev" id="slider-prev"></div>
                <div class="slider-next" id="slider-next"></div>
            </div>
        </div>
    </div>
</div>