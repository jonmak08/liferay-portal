<div class="col-md-10 col-md-offset-1 blog">
	<div class="blog-preview-image col-md-6" style="background-image: url(${blog-image-url.getData()}); background-position: center center; background-size: cover"></div>

	<div class="blog-content col-md-6">
		<h2 class="blog-preview-title">
			<a href="${link-to-blog.getFriendlyUrl()}">
				${Title.getData()}
			</a>
		</h2>

		<span class="blog-preview-subtitle">
			${Subittle.getData()}
		</span>

		<div class="blog-separator"></div>

		<div class="blog-preview-entry">
			${blog-entry.getData()}
		</div>

		<div class="blog-preview-author">
			<@liferay.language key="by" /> <span>${Author.getData()}<span>
		</div>
	</div>
</div>
