<div class="header-featured-area">
    <div class="container">
        <div class="header-featured-img">
            <#if Imagee3k2.getData()?? && Imagee3k2.getData() !="">
				<img alt="${Imagee3k2.getAttribute("alt")}" data-fileentryid="${Imagee3k2.getAttribute("fileEntryId")}" src="${Imagee3k2.getData()}" />
			</#if>
        </div>

        <div class="header-featured-content">
            <h1>
                ${WelcomeSummaryTitle.getData()}
            </h1>

            <div class="header-featured-summary">
                ${WelcomeSummary.getData()}
            </div>

            <div class="app-download-area">
                <h4>
					<@liferay.language key="download-the-app" />
				</h4>

                <#if WelcomeDownloadText.getSiblings()?has_content>
                    <#list WelcomeDownloadText.getSiblings() as cur_WelcomeDownloadText>
						<a class="social-btn" href="#">
							<span class="fa ${cur_WelcomeDownloadText.WelcomeDownloadLogo.getData()}"></span>
							<span>${cur_WelcomeDownloadText.getData()}</span>
						</a>
					</#list>
                </#if>
            </div>
        </div>
    </div>
</div>