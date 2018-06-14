<script>

document.getElementById("portlet_com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_HcUvPg6i4Toj").addEventListener('click', function(event) {
	var parent = event.target.parentElement;

	if (event.target.className === 'profile-full') {
		 if (parent.nodeName === 'PICTURE') {
			 parent = parent.parentElement;
		 }
		event.target.classList.toggle('hidden');
		parent.firstElementChild.classList.toggle('hidden');
	}

	if (event.target.className === 'profile') {
		event.target.classList.toggle('hidden');
		if (parent.lastElementChild.nodeName === 'PICTURE') {
			return parent.lastElementChild.childNodes[1].classList.toggle('hidden')
		}
		parent.lastElementChild.classList.toggle('hidden');
	}
})

</script>

<div class="team-container">
    <div class="team-content">
        <div class="player-card" id="${handle.getData()}">
        	<img data-fileentryid="${image.getAttribute("fileEntryId")}" alt="${image.getAttribute("alt")}" class="profile hidden" src="${image.getData()}" />
        	<img data-fileentryid="${imageFull.getAttribute("fileEntryId")}" alt="${imageFull.getAttribute("alt")}" class="profile-full" src="${imageFull.getData()}" />
        </div>

       <div class="player-content-top">
            <p class="player-handle">${handle.getData()}</p>
            <h1 class="player-number">#${number.getData()}</h1>
        </div>
        
        <div class="player-content-bottom ${position.getData()}">
            <p class="player-position">Position: ${position.getData()}</p>
            <p class="player-name">Name: ${name.getData()}</p>
        </div>
    </div>
</div>