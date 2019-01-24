<article id="post" class="hentry">
        <div class="entry-section">
            <a href="${templatesPath}" title="${Header.getData()}">
                <img width="520" height="347" class="small-thumb" alt="${Image94ms.getAttribute("alt")}" data-fileentryid="${Image94ms.getAttribute("fileEntryId")}" src="${Image94ms.getData()}">
            </a>
            <header class="entry-header blog-entry-header">
                <div class="entry-data">
                    <span class="posted-on">
                        <a href="${templatesPath}" rel="bookmark">
                            <time class="entry-date" datetime="">
<#assign Datefyr2_Data = getterUtil.getString(Datefyr2.getData())> <#if validator.isNotNull(Datefyr2_Data)> <#assign Datefyr2_DateObj = dateUtil.parseDate("yyyy-MM-dd", Datefyr2_Data, locale)> ${dateUtil.getDate(Datefyr2_DateObj, "dd MMM yyyy", locale)} </#if>
                            </time>
                        </a>
                    </span>
                    <span>/</span>
                    <span class="cat-links"><a href="http://oria.madalinm.com/category/city/" rel="category tag">City</a>, <a href="http://oria.madalinm.com/category/city-view/" rel="category tag">City View</a></span>
                </div>
                <h1 class="entry-title">
                    <a href="${templatesPath}" rel="bookmark">${Header.getData()}</a>
                </h1>
            </header>
            <div class="entry-content">
                <p>${Description.getData()}</p>
                <a class="read-more" href="${templatesPath}">Continue reading</a>
            </div>
        </div>
</article>