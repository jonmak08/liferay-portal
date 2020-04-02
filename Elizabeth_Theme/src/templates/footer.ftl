<footer class="footer">
	<div class="section-container">  
		<div class="footer-section">
			<h4 class="section-footer-heading">
				<span class="theme-color-text">${section_one_title}</span>
			</h4>
			
			<div class="section-one-content">
				${section_one_content}
			</div>
		</div>
	</div>

	<div class="section-container">
		<div class="footer-section">
			<h4 class="section-footer-heading">
				<span class="theme-color-text">${section_two_title}</span>
			</h4>
			
			<div id="location">
				<a class="text-white" target="_blank">
					<@clay["icon"] symbol="geolocation" />
					${section_two_address}
				</a>
			</div>

			<div id="phone">
				<a class="text-white" target="_blank">
					<@clay["icon"] symbol="phone" />
					${section_two_phone}
				</a>
			</div>

			<div id="email">
				<a class="text-white" target="_blank">
					<@clay["icon"] symbol="envelope-closed" />
					<a href="mailto:${section_two_mail}">
						${section_two_mail}
					</a>
				</a>
			</div>
		</div>
	</div>
	
	<div class="section-container">
		<div class="footer-section">
			<h4 class="section-footer-heading">
				<span class="theme-color-text">${section_three_title}</span>
			</h4>

			<div>
				${section_three_content}
			</div>
		</div>
	</div>
</footer>