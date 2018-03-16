<#assign
	group_id = groupId?keep_before("2") + '1'
/>

<div class="${class.getData()}">

	<div class="article-preview-subject">
		${subject.getData()}
	</div>

	<img alt="${title.getData()}" class="article-preview-image" src="${image.getData()}" />

	<div class="article-preview-wrapper">

		<h3 class="article-preview-title">
			<a class="content-anchor" href="/group/template-${group_id}/11">${title.getData()}</a>
		</h3>

		<div class="article-preview-author">
			By ${author.getData()}
		</div>

		<div class="article-preview-text">
			${content.getData()}
		</div>
		
	</div>

</div>