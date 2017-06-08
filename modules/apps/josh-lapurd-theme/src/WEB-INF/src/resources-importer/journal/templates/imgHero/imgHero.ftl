<div class="row">
    <div class="hero">
        <#if imgHero.getData()?? && imgHero.getData() != "">
            <img alt="${imgHero.getAttribute("alt")}" src="${imgHero.getData()}" />
        </#if>
    </div>
</div>