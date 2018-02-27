<style>
	.features-portlet {
		text-align: center;
		padding: 150px 0;
	}

	.features-portlet h4 {
		display: inline-block;
		font-weight: bold;
		text-transform: uppercase;
		width: fit-content;
	}

	.features-portlet img {
		border: 10px solid #DDD;
		display: block;
		height: 225px;
		margin: 0 auto;
		transition: border-color 0.25s;
		width: 225px;
	}

	.features-portlet img:hover {
		border-color: #FD0;
		cursor: pointer;
	}

	.features-portlet span {
		display: inline-block;
		margin: 0 5px;
		text-align: center;
		vertical-align: top;
		width: 275px;
	}

	.features-portlet .line {
		background: #FD0;
		height: 2px;
		margin: 10px 0;
		width: 80%;
	}
</style>

<div class='features-portlet'>
		<span class="action-group">
			<img data-fileentryid="${image1.getAttribute("fileEntryId")}" alt="${image1.getAttribute("alt")}" src="${image1.getData()}" />
			<h4>
				${image1.header1.getData()}
				<span class='line'></span>
			</h4>

			<p>${image1.summary1.getData()}</p>
		</span>

		<span class="action-group">
			<img data-fileentryid="${image2.getAttribute("fileEntryId")}" alt="${image2.getAttribute("alt")}" src="${image2.getData()}" />
			<h4>
				${image2.header2.getData()}
				<span class='line'></span>
			</h4>

			<p>${image2.summary2.getData()}</p>
		</span>

		<span class="action-group">
			<img data-fileentryid="${image3.getAttribute("fileEntryId")}" alt="${image3.getAttribute("alt")}" src="${image3.getData()}" />
			<h4>
				${image3.header3.getData()}
				<span class='line'></span>
			</h4>

			<p>${image3.summary3.getData()}</p>
		</span>

		<span class="action-group">
			<img data-fileentryid="${image4.getAttribute("fileEntryId")}" alt="${image4.getAttribute("alt")}" src="${image4.getData()}" />
			<h4>
				${image4.header4.getData()}
				<span class='line'></span>
			</h4>

			<p>${image4.summary4.getData()}</p>
		</span>
</div>