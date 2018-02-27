<style>
	.about-you-portlet {
		color: #FFF;
		padding: 100px 0;
		text-align: center;
	}

	.about-you-portlet h1 {
		font-weight: bold;
		display: inline-block;
		text-transform: uppercase;
	}

	.about-you-portlet h4 {
		font-weight: bold;
		margin-bottom: 75px;
		text-transform: uppercase;
	}

	.about-you-portlet img {
		display: inline-block;
		max-width: 500px !important;
	}

	.about-you-portlet p {
		color: #939393;
		display: inline-block;
		margin-bottom: 10px;
		max-width: 500px;
		text-align: left;
	}

	.about-you-portlet .action-group {
		padding: 0 15px;
		vertical-align: top;
		margin-bottom: 50px;
	}

	.about-you-portlet .line {
		background: #FD0;
		display: block;
		height: 3px;
		margin: 10px auto;
		width: 40px;
	}
</style>

<div class="about-you-portlet">
	<h1>
	    ${header.getData()}
	    <span class="line"></span>
	</h1>

	<h4>${header.subheader.getData()}</h4>

	<p class="action-group">${content.getData()}</p>

	<img class="action-group" data-fileentryid="${image.getAttribute("fileEntryId")}" alt="${image.getAttribute("alt")}" src="/documents/87902/0/starman.jpg/84d75b42-c6e8-f405-a61b-c41b1d4e46ae" />
</div>