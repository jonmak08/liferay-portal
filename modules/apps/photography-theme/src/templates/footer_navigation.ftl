
<nav id="footer-recursive">
	<div class="container text-center">
		<div class="row">
			<div class="col-xs-12">

				<ul class="list-inline" role="menubar">
					<#foreach nav_item in nav_items>
						<li class="link-ctn" role="presentation">
							<a class="link" aria-labelledby="layout_${nav_item.getLayoutId()}" href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem">
								${nav_item.getName()}
							</a>
						</li>
					</#foreach>
				</ul>

				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
				</a>

				<div class="bottom-ctn">

					<#if facebook>
						<a class="icon-ctn" href="${facebookUrl}">
							<i aria-hidden="true" class="icon-facebook icon-2x social-media-icon social-media-icon--facebook facebook"></i>
              <h3 class="tooltip"><@liferay.language key="facebook" /></h3>
						</a>
					</#if>

					<#if twitter>
						<a class="icon-ctn" href="${twitterUrl}">
							<i aria-hidden="true" class="icon-twitter icon-2x social-media-icon social-media-icon--twitter"></i>
              <h3 class="tooltip"><@liferay.language key="twitter" /></h3>
						</a>
					</#if>

					<#if instagram>
						<a class="icon-ctn" href="${instagramUrl}">
							<i aria-hidden="true" class="icon-instagram icon-2x social-media-icon social-media-icon--instagram"></i>
              <h3 class="tooltip"><@liferay.language key="instagram" /></h3>
						</a>
					</#if>

					<#if github>
						<a class="icon-ctn" href="${githubUrl}">
							<i aria-hidden="true" class="icon-github icon-2x social-media-icon social-media-icon--github"></i>
              <h3 class="tooltip"><@liferay.language key="github" /></h3>
						</a>
					</#if>

					<#if youtube>
						<a class="icon-ctn" href="${youtubeUrl}">
							<i aria-hidden="true" class="icon-youtube icon-2x social-media-icon social-media-icon--youtube"></i>
              <h3 class="tooltip"><@liferay.language key="youtube" /></h3>
						</a>
					</#if>

					<#if linkedin>
						<a class="icon-ctn" href="${linkedinUrl}">
							<i aria-hidden="true" class="icon-linkedin icon-2x social-media-icon social-media-icon--linkedin"></i>
              <h3 class="tooltip"><@liferay.language key="linkedin" /></h3>
						</a>
					</#if>

					<#if dribbble>
						<a class="icon-ctn" href="${dribbbleUrl}">
							<i aria-hidden="true" class="icon-dribbble icon-2x social-media-icon social-media-icon--dribbble"></i>
              <h3 class="tooltip"><@liferay.language key="dribbble" /></h3>
						</a>
					</#if>

				</div>

				<h4 id="contact">${email} | ${phone}</h4>

				<h4>© ${year?string.yyyy} ${site_name}</h4>

				<h5><@liferay.language key="created-with-love" /></h5>

			</div>
		</div>
	</div>
</nav>
