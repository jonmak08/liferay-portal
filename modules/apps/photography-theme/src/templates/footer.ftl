<nav id="footer-recursive">
	<div class="container text-center">
		<div class="row">
			<div class="col-xs-12">
				<ul class="list-inline" role="menubar">
					<#foreach nav_item in nav_items>
						<li class="link-container" role="presentation">
							<a aria-labelledby="layout_${nav_item.getLayoutId()}" class="link" href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem">
								${nav_item.getName()}
							</a>
						</li>
					</#foreach>
				</ul>

				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
				</a>

				<div class="bottom-container">

					<#if dribbble>
						<a class="icon-container" href="${dribbbleUrl}">
							<i aria-hidden="true" class="icon-2x icon-dribbble social-media-icon social-media-icon--dribbble"></i>

							<h3 class="tooltip"><@liferay.language key="dribbble" /></h3>

						</a>
					</#if>

					<#if facebook>
						<a class="icon-container" href="${facebookUrl}">
							<i aria-hidden="true" class="icon-facebook facebook icon-2x social-media-icon social-media-icon--facebook"></i>

							<h3 class="tooltip"><@liferay.language key="facebook" /></h3>

						</a>
					</#if>

					<#if github>
						<a class="icon-container" href="${githubUrl}">
							<i aria-hidden="true" class="icon-2x icon-github  social-media-icon social-media-icon--github"></i>

							<h3 class="tooltip"><@liferay.language key="github" /></h3>

						</a>
					</#if>

					<#if instagram>
						<a class="icon-container" href="${instagramUrl}">
							<i aria-hidden="true" class="icon-2x icon-instagram social-media-icon social-media-icon--instagram"></i>

							<h3 class="tooltip"><@liferay.language key="instagram" /></h3>

						</a>
					</#if>

					<#if linkedin>
						<a class="icon-container" href="${linkedinUrl}">
							<i aria-hidden="true" class="icon-2x icon-linkedin social-media-icon social-media-icon--linkedin"></i>

							<h3 class="tooltip"><@liferay.language key="linkedin" /></h3>

						</a>
					</#if>

					<#if twitter>
						<a class="icon-container" href="${twitterUrl}">
							<i aria-hidden="true" class="icon-2x icon-twitter social-media-icon social-media-icon--twitter"></i>

							<h3 class="tooltip"><@liferay.language key="twitter" /></h3>

						</a>
					</#if>

					<#if youtube>
						<a class="icon-container" href="${youtubeUrl}">
							<i aria-hidden="true" class="icon-2x icon-youtube social-media-icon social-media-icon--youtube"></i>

							<h3 class="tooltip"><@liferay.language key="youtube" /></h3>

						</a>
					</#if>

				</div>

				<#if showContactInfo>
					<h4 id="contact">${email} | ${phone}</h4>
				</#if>

				<#if showCopyrightInfo>
					<h4>© ${year?string.yyyy} ${site_name}</h4>
				</#if>

				<#if showAttributionInfo>
					<h5><@liferay.language key="created-with-love" /></h5>
				</#if>

			</div>
		</div>
	</div>
</nav>
