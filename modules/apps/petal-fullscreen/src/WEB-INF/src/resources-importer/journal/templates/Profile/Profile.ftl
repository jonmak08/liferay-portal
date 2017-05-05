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
		<#list name.getSiblings() as cur_name>
			<#assign
				cur_image = cur_name.image
				cur_bio   = cur_name.biography
			/>
			<#assign social_media = "" />
			<#assign
				cur_smn = cur_name.socialmedia1
				cur_smu = cur_name.socialmedia1.url1
			/>
			<#if cur_smn?? && cur_smu?? && cur_smn != "" && cur_smu != "">
			<#assign
				social_media = social_media + "<div class=\"image-container\"><a href=\"" +cur_smu.getData()+"\" target=\"_blank\"><i class=\"fa " + cur_smn.getData() + "\"></i></a></div>"
			/>
			</#if>
			<#assign
				cur_smn = cur_name.socialmedia2
				cur_smu = cur_name.socialmedia2.url2
			/>
			<#if cur_smn?? && cur_smu?? && cur_smn != "" && cur_smu != "">
			<#assign
				social_media = social_media + "<div class=\"image-container\"><a href=\"" +cur_smu.getData()+"\" target=\"_blank\"><i class=\"fa " + cur_smn.getData() + "\"></i></a></div>"
			/>
			</#if>
			<#assign
				cur_smn = cur_name.socialmedia3
				cur_smu = cur_name.socialmedia3.url3
			/>
			<#if cur_smn?? && cur_smu?? && cur_smn != "" && cur_smu != "">
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
