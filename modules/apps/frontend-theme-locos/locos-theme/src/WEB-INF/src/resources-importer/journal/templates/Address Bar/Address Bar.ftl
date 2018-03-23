<div class="company-info">
	<#if address.getData()?? && address.getData() != "">
		<div class="full-address">
			<div class="address">${address.getData()}</div>
			<div class="city-state-zip">
				<span class="city">${city.getData()}</span>, 
				<span class="state">${state.getData()}</span> 
				<span class="zip">${zip.getData()}</span>
			</div>
			<div class="country-name">${country_name.getData()}</div>
		</div>
	</#if>
	<#if ph_number.getData()?? && ph_number.getData() != "" && country_code.getData()?? && country_code.getData() != "">
		<div class="phone">
			+${country_code.getData()} ${ph_number.getData()}
		</div>
	</#if>
</div>