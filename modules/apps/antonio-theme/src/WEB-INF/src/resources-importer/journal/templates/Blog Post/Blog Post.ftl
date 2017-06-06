<div class="container blog">

    <#if Imagecnm6.getData()?? && Imagecnm6.getData() != "">
        <div class="blog-image col-md-6">
            <img data-fileentryid="${Imagecnm6.getAttribute("fileEntryId")}" alt="${Imagecnm6.getAttribute("alt")}" src="${Imagecnm6.getData()}" />
        </div>
        <div class="blog-content col-md-6">
            <div class="blog-title">
                <a href="#">${Title.getData()}</a>
            </div>
            <div class="blog-subtitle">
                ${Subittle.getData()}
            </div>
            <div class="blog-separator"></div>
            <div class="blog-entry">
                ${BlogEntry.getData()}
            </div>
        </div>
    <#else>
        <div class="blog-content col-md-12">
            <div class="blog-title">
                <a href="#">${Title.getData()}</a>
            </div>
            <div class="blog-subtitle">
                ${Subittle.getData()}
            </div>
            <div class="blog-separator"></div>
            <div class="blog-entry">
                ${BlogEntry.getData()}
            </div>
        </div>
    </#if>

</div>
