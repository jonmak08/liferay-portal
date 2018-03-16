<#assign
	group_id = groupId?keep_before("2") + '1'
/>

<div class="full-article-preview">

	<img alt="${title.getData()}" class="full-article-image" src="${image.getData()}" />

	<div class="full-article-preview-box">

		<div class="full-article-preview-subject">
			${subject.getData()}
		</div>

		<h1 class="full-article-title">
			<a class="content-anchor" href="/group/template-${group_id}/11">
				${title.getData()}
			</a>
		</h1>

		<div class="full-article-preview-author">
			By ${author.getData()}
		</div>

		<div class="full-article-subtitle">
			${subtitle.getData()}
		</div>
		
	</div>

</div>