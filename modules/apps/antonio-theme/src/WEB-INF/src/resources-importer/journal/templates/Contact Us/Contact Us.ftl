<div class="col-md-10 col-md-offset-1 contact-container">
	<div class="col-md-6 mapLocation">

		<#assign latitude = 0>
		<#assign longitude = 0>

		<#if (geolocation.getData() != "")>
			<#assign
				latitude = geolocationJSONObject.getDouble("latitude")
				longitude = geolocationJSONObject.getDouble("longitude")
				geolocationJSONObject = jsonFactoryUtil.createJSONObject(geolocation.getData())
			/>

			<@liferay_map["map-display"]
				latitude=latitude
				longitude=longitude
				geolocation=true
				name="mapLocation"
			/>
		</#if>

	</div>

	<div class="col-md-6 contact-us">
		<header class="col-md-12">
			<h2 class="contact-header">${contact-header.getData()}</h2>
		</header>

		<div class="col-md-12">${contact-paragraph.getData()}</div>

		<ul class="col-md-12 text-center">

			<#if contact-info.getSiblings()?has_content>
				<#list contact-info.getSiblings() as cur_contact-info>
					<li class="contact-information">
						${cur_contact-info.getData()}
					</li>
				</#list>
			</#if>

		</ul>
	</div>
</div>
