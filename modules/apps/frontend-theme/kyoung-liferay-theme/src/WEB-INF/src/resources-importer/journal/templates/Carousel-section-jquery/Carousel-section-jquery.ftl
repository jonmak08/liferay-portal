<section class="apps-screenshot">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="apps-screenshot-area">
                    <div class="title-area">
                        <h2 class="title">
                            <@liferay.language key="apps-screenshot" />
                        </h2>
                        <span class="title-dot"></span>

                        <div class="section-summary">
                            ${AppScreenshotSummary.getData()}
                        </div>
                    </div>

                    <div class="apps-screenshot-content">
                        <div class="apps-screenshot-slider">
                            <div class="card-carousel">
                                <#if AppScreenshot.getSiblings()?has_content>
                                    <#list AppScreenshot.getSiblings() as cur_AppScreenshot>
                                        <#if cur_AppScreenshot.getData()?? && cur_AppScreenshot.getData() !="">
                                            <div class="my-card">
                                                <img alt="${cur_AppScreenshot.getAttribute("alt")}" data-fileentryid="${cur_AppScreenshot.getAttribute("fileEntryId")}" src="${cur_AppScreenshot.getData()}" />
                                            </div>
                                        </#if>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>