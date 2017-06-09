<div class="col-md-10 col-md-offset-1 contact-container">
    <div class="col-md-6 geolocation">

        <#assign latitude = 0>
        	<#assign longitude = 0>

        	<#if (Geolocation42mw.getData() != "")>
        		<#assign geolocationJSONObject = jsonFactoryUtil.createJSONObject(Geolocation42mw.getData())>

        		<#assign latitude = geolocationJSONObject.getDouble("latitude")>
        		<#assign longitude = geolocationJSONObject.getDouble("longitude")>

        		<@liferay_map["map-display"]
        			geolocation=true
        			latitude=latitude
        			longitude=longitude
        			name="Geolocation42mw"
        		/>
        	</#if>

	</div>
	<div class="col-md-6 contact-us">
	    <header class="col-md-12">
	        <h2 class="contact-header">${ContactHeader.getData()}</h2>
	    </header>
	    <p class="col-md-12">${TextBoxheqz.getData()}</p>
	    <ul class="col-md-12 text-center">

	        <#if ContactInfo.getSiblings()?has_content>
            	<#list ContactInfo.getSiblings() as cur_ContactInfo>
            		<li class="contact-information">
            		    ${cur_ContactInfo.getData()}
            		</li>
            	</#list>
            </#if>

	    </ul>
	</div>
</div>
