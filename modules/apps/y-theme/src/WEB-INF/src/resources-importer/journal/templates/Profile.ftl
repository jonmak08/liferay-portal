<#--
Web content templates are used to lay out the fields defined in a web
content structure.

Please use the left panel to quickly add commonly used variables.
Autocomplete is also available and can be invoked by typing "${".
-->
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
		<#list image.getSiblings() as cur_image>
			<#assign 
				name_cur = name.getSiblings() 
				cur_name = name_cur[cur_image?index] 
				bio_cur = biography.getSiblings() 
				cur_bio = bio_cur[cur_image?index] 
			/>
			<#assign social_media = "" />
			<#assign
				soc_cur = socialmedianame1.getSiblings()
				cur_smn = soc_cur[cur_image?index]
				soc_cur = socialmediaurl1.getSiblings()
				cur_smu = soc_cur[cur_image?index]
			/>            
			<#if cur_smn != "" && cur_smu != "" >
				<#assign 
					social_media = social_media + "<div class=\"image-container\"><a href=\"" +cur_smu.getData()+"\" target=\"_blank\"><i class=\"fa " + cur_smn.getData() + "\"></i></a></div>"
			/>
			</#if>            
			<#assign
				soc_cur = socialmedianame2.getSiblings()
				cur_smn = soc_cur[cur_image?index]
				soc_cur = socialmediaurl2.getSiblings()
				cur_smu = soc_cur[cur_image?index]
			/>            
			<#if cur_smn != "" && cur_smu != "" >
				<#assign 
					social_media = social_media + "<div class=\"image-container\"><a href=\"" +cur_smu.getData()+"\" target=\"_blank\"><i class=\"fa " + cur_smn.getData() + "\"></i></a></div>"
				/>
			</#if>            
			<#assign
				soc_cur = socialmedianame3.getSiblings()
				cur_smn = soc_cur[cur_image?index]
				soc_cur = socialmediaurl3.getSiblings()
				cur_smu = soc_cur[cur_image?index]
			/>            
			<#if cur_smn != "" && cur_smu != "" >
				<#assign 
					social_media = social_media + "<div class=\"image-container\"><a href=\"" +cur_smu.getData()+"\" target=\"_blank\"><i class=\"fa " + cur_smn.getData() + "\"></i></a></div>"
				/>
			</#if>
			<div class="vertical-content-container image-container">
				<div class="flexbox-container">
					<div class="profile-picture-container">
						<img alt="${cur_image.getAttribute("alt")}" src="${cur_image.getData()}" />
					</div>
				</div>
				<div class="full-width-container paragraph-div">
					<h4>${cur_name.getData()}</h4>
				</div>
				<div class="profile-biography-container">
					${cur_bio.getData()}
				</div>
				<div class="flexbox-container paragraph-div">
					<#if social_media != "">
						${social_media}
					<#else>
						&nbsp;
					</#if>
				</div>
			</div>
		</#list>
	</div>
</div>
