<div class="services-wrapper">
	<h2>${OurServicesHeading.getData()}</h2>

		<div class="services-flex">

			<div class="services">
				<div class="icon-container">
					<a class="fa fa-mobile-alt services-icons" href="${ServiceLink01.getFriendlyUrl()}" id="service-icon01"></a>
				</div>

				<h3>${ServiceType01.getData()}</h3>

				<p>${Service02Description.getData()}</p>
				<a class="arrow-link" href="${ServiceLink01.getFriendlyUrl()}"><@liferay.language key="read-more" /></a>
			</div>

			<div class="services">
				<div class="icon-container">
					<a class="fa-wordpress fab services-icons" href="${ServiceLink02.getFriendlyUrl()}" id="service-icon02"></a>
				</div>

				<h3>${ServiceType02.getData()}</h3>

				<p>${Service02Description.getData()}</p>
				<a class="arrow-link" href="${ServiceLink02.getFriendlyUrl()}"><@liferay.language key="read-more" /></a>
			</div>

			<div class="services">
				<div class="icon-container">
					<a class="fa-facebook-f fab services-icons" href="${ServiceLink03.getFriendlyUrl()}" id="service-icon03"></a>
				</div>

				<h3>${ServiceType03.getData()}</h3>

				<p>${Service03Description.getData()}</p>
				<a class="arrow-link" href="${ServiceLink03.getFriendlyUrl()}"><@liferay.language key="read-more" /></a>
			</div>
		</div>
	<hr>
</div>