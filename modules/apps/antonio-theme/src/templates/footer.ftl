<footer class="site-footer">
	<div class="container">

		<#if addFooterSection>
			<section class="col-md-12 footer-content">
				<div class="footer-section text-center">
					<h3 class="footer-section-title">${footerSectionTitle}</h3>

					<div class="col-md-6 col-md-offset-3 footer-section-entry">
					${footerSectionEntry}
					</div>
				</div>
			</section>
		</#if>

		<div class="col-md-12 social-media text-center">
			<ul class="social-media-list">

				<#if socialMediaOne != "" && socialMediaOneLink != "">
					<li>
						<a aria-hidden="true" href="${socialMediaOneLink}" target="_blank"><span class="${socialMediaOne} social-media-icon"></span></a>
					</li>
				</#if>

				<#if socialMediaTwo !="" && socialMediaTwoLink != "">
					<li>
						<a aria-hidden="true" href="${socialMediaTwoLink}" target="_blank"><span class="${socialMediaTwo} social-media-icon"></span></a>
					</li>
				</#if>

				<#if socialMediaThree !="" && socialMediaThreeLink != "">
					<li>
						<a aria-hidden="true" href="${socialMediaThreeLink}" target="_blank"><span class="${socialMediaThree} social-media-icon"></span></a>
					</li>
				</#if>

				<#if socialMediaFour !="" && socialMediaFourLink != "">
					<li>
						<a aria-hidden="true" href="${socialMediaFourLink}" target="_blank"><span class="${socialMediaFour} social-media-icon"></span></a>
					</li>
				</#if>

				<#if socialMediaFive !="" && socialMediaFiveLink != "">
					<li>
						<a aria-hidden="true" href="${socialMediaFiveLink}" target="_blank"><span class="${socialMediaFive} social-media-icon"></span></a>
					</li>
				</#if>

			</ul>
		</div>

		<div class="col-md-12 text-center">
			<span class="contact-info">
				<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external" target="_blank">Liferay</a>
			</span>
		</div>
	</div>
</footer>
