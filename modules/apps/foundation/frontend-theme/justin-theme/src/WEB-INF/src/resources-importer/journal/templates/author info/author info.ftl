<#assign
	group_id = groupId?keep_before("2") + '1'
/>

<div class="author">
	
	<img alt="${name.getData()}" class="author-photo" src="${image.getData()}" />

	<div class="author-wrapper">

		<h1 class="author-name">
			<a class="content-anchor" href="/group/template-${group_id}/11">${name.getData()}</a>
		</h1>

		<h4 class="author-bio">
			${bio.getData()}
		</h4>

	</div>

</div>