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
			<h2 class="contactHeader">${contactHeader.getData()}</h2>
		</header>

		<div class="col-md-12">${contactParagraph.getData()}</div>

		<ul class="col-md-12 text-center">

			<#if contactInfo.getSiblings()?has_content>
				<#list contactInfo.getSiblings() as cur_contactInfo>
					<li class="contactInformation">
						${cur_contactInfo.getData()}
					</li>
				</#list>
			</#if>

		</ul>
	</div>
</div>
