<section id="feature">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="feature-area">
                    <div class="title-area">
                        <h2 class="title">
                            <@liferay.language key="our-app-features" />
                        </h2>
                        <span class="title-dot"></span>
                        <#if validator.isNotNull(AppFeatureSectionSummary.getData())>
                            <p>${AppFeatureSectionSummary.getData()}</p>
                            <#else>
                                <p></p>
                        </#if>
                    </div>
                    <div class="feature-content">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="feature-content-left">
                                    <#if AppFeatureImage.getData()?? && AppFeatureImage.getData() !="">
                                    <img alt="${AppFeatureImage.getAttribute("alt")}" class="profile-img" data-fileentryid="${AppFeatureImage.getAttribute("fileEntryId")}" src="${AppFeatureImage.getData()}" />
                                    </#if>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="feature-content-right">
                                    <#if AppFeatureTitle.getSiblings()?has_content>
                                        <#list AppFeatureTitle.getSiblings() as cur_AppFeatureTitle>
                                            <div class="media">
                                                <div class="media-left">
                                                    <button class="btn feature-btn" type="button"> <span class="fa ${cur_AppFeatureTitle.Text4pxl.AppFeatureLogoFA.getData()}" aria-hidden="true"></span> </button>
                                                </div>
                                                <div class="media-body">
                                                    <h3 class="media-heading">${cur_AppFeatureTitle.getData()}</h3>
                                                    <p>${cur_AppFeatureTitle.Text4pxl.getData()}</p>
                                                </div>
                                            </div>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>