<div class="welcome-container">
	<div class="welcome-content">
		<h5>
			<span class="theme-color-text">${welcome_subheader1}</span>
			${welcome_subheader2}
		</h5>

		<h1>
			${welcome_header}
		</h1>

		<div class="description-container">
			<div class="welcome-content">
				${welcome_content}
			</div>
		</div>

		<a class="button-main" href="#footer">MAKE RESERVATION</a>
	</div>

	<#assign
		has_twitter = show_twitter
		has_facebook = show_facebook
		has_linkedin = show_linkedin
	/>

	<div class="welcome-footer-container">
		<div class="welcome-footer-content">
			<div class="welcome-footer-social-media">
				<ul class="nav flex-row mx-auto">
					<li class="mx-2">
						<div id="twitter">
							<#if has_twitter>
								<a class="text-white" href=${twitter_link} target="_blank">
									<@clay["icon"] symbol="twitter" />
								</a>
							</#if>
						</div>
					</li>

					<li class="mx-2">
						<div id="facebook">
							<#if has_facebook>
								<a class="text-white" href=${facebook_link} target="_blank">
									<@clay["icon"] symbol="social-facebook" />
								</a>
							</#if>
						</div>
					</li>

					<li class="mx-2">
						<div id="linked-in">
							<#if has_linkedin>
								<a class="text-white" href=${linkedin_link} target="_blank">
									<@clay["icon"] symbol="social-linkedin" />
								</a>
							</#if>
						</div>
					</li>
				</ul>  
			</div>

			<div class="welcome-footer-address">
				<@clay["icon"] symbol="geolocation" />
				1400 Montefino Ave, Diamond Bar, CA 91765
			</div>
		</div>
	</div>
</div>