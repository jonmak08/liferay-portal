<div class="col-md-10 col-md-offset-1 blog">
    <div class="blog-preview-image col-md-6" style="background-image: url(${BlogImageUrl.getData()}); background-position: center center; background-size: cover"></div>
    <div class="blog-content col-md-6">
        <h2 class="blog-preview-title">
            <a href="${LinkToBlog.getFriendlyUrl()}">
            	${Title.getData()}
            </a>
        </h2>
        <span class="blog-preview-subtitle">
            ${Subittle.getData()}
        </span>
        <div class="blog-separator"></div>
        <p class="blog-preview-entry">
            ${BlogEntry.getData()}
        </p>
        <div class="blog-preview-author">
            by <span>${Author.getData()}<span>
        </div>
    </div>
</div>
