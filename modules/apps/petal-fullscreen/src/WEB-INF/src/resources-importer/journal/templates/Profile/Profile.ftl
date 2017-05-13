<div class="full-width-container paragraph-div">
	<div class="centered-content-container">
		<div class="centered-mini-content">
			<h3>${title.getData()}</h3>
		</div>
	</div>

	<div class="centered-content-container">
		<div class="centered-mini-content">
			${intro.getData()}
		</div>
	</div>
</div>

<div class="centered-content-container">
	<div class="metamorphic-container">
		<#list name.getSiblings() as cur_name>
			<div class="image-container vertical-content-container">
				<div class="flexbox-container">
					<div class="profile-picture-container">
						<#assign
							themePath = getterUtil.getString(request['theme-display']['path-theme-images'])
							cur_image = cur_name.image
							cur_bio = cur_name.biography
						/>
						<img src="${themePath}/${cur_image.getData()}" />
					</div>
				</div>

				<div class="full-width-container paragraph-div">
					<h4>${cur_name.getData()}</h4>
				</div>

				<div class="profile-biography-container">
					${cur_bio.getData()}
				</div>

				<div class="flexbox-container paragraph-div">
					<#if cur_name.social_media_1?? && cur_name.social_media_1.url_1?? && cur_name.social_media_1 != "" && cur_name.social_media_1.url_1 != "">
						<div class="image-container">
							<a href="${cur_name.social_media_1.url_1.getData()}" target="_blank"><i class="fa ${cur_name.social_media_1.getData()}"></i></a>
						</div>
					</#if>

					<#if cur_name.social_media_2?? && cur_name.social_media_2.url_2?? && cur_name.social_media_2 != "" && cur_name.social_media_2.url_2 != "">
						<div class="image-container">
							<a href="${cur_name.social_media_2.url_2.getData()}" target="_blank"><i class="fa ${cur_name.social_media_2.getData()}"></i></a>
						</div>
					</#if>

					<#if cur_name.social_media_3?? && cur_name.social_media_3.url_3?? && cur_name.social_media_3 != "" && cur_name.social_media_3.url_3 != "">
						<div class="image-container">
							<a href="${cur_name.social_media_3.url_3.getData()}" target="_blank"><i class="fa ${cur_name.social_media_3.getData()}"></i></a>
						</div>
					</#if>
				</div>
			</div>
		</#list>
	</div>
</div>
