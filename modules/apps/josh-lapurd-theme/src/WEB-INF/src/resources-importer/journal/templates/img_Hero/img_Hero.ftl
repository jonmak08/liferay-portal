<div class="row">
    <div class="hero">
        <#if img_Hero.getData()?? && img_Hero.getData() != "">
            <img alt="${img_Hero.getAttribute("alt")}" src="${img_Hero.getData()}" />
        </#if>
    </div>
</div>