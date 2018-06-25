<script>
	document.body.classList.add('js-loading');

	function showPage() {
		document.body.classList.remove('js-loading');
	}

	window.addEventListener('load', showPage);
</script>

<style>
.js-loading *,
.js-loading *:before,
.js-loading *:after {
	animation-play-state: paused !important;
}

html body {
	padding-bottom: 0;
}

body::before, body::after {
	content: none;
}

section.container-fluid-1280 {
	max-width: inherit;
}

#content {
	padding: 0;
}

#content::before {
	animation: barberpole-body-case 1.5s ease-in-out 10;
	background-image: linear-gradient(45deg, #171C38 25%, #0F57EA 25%, #0F57EA 50%, #171C38 50%, #171C38 75%, #0F57EA 75%, #0F57EA);
	background-size: 150px 150px;
	content: '';
	height: 51vh;
	position: absolute;
	top: 51%;
	transform: translateY(-100%);
	width: 50%;
	z-index: -1;
}

#content::after {
	animation: barberpole-body-case 1.5s ease-in-out 10;
	background-image: linear-gradient(45deg, #171C38 25%, #0F57EA 25%, #0F57EA 50%, #171C38 50%, #171C38 75%, #0F57EA 75%, #0F57EA);
	background-size: 150px 150px;
	content: '';
	height: 51vh;
	position: absolute;
	top: 51%;
	transform: rotateX(180deg);
	width: 50%;
	z-index: -1;
}

#main-content::before {
	animation: barberpole-body-case 1.5s ease-in-out 10;
	animation-direction: reverse;
	background-image: linear-gradient(-45deg, #0F57EA 25%, #171C38 25%, #171C38 50%, #0F57EA 50%, #0F57EA 75%, #171C38 75%, #171C38);
	background-size: 150px 150px;
	content: '';
	height: 51vh;
	position: absolute;
	right: 0;
	top: 51%;
	transform: translateY(-100%);
	width: 50%;
	z-index: -1;
}

#main-content::after {
	animation: barberpole-body-case 1.5s ease-in-out 10;
	animation-direction: reverse;
	background-image: linear-gradient(-45deg, #0F57EA 25%, #171C38 25%, #171C38 50%, #0F57EA 50%, #0F57EA 75%, #171C38 75%, #171C38);
	background-size: 150px 150px;
	content: '';
	height: 51vh;
	position: absolute;
	right: 0;
	top: 51%;
	transform: rotateX(180deg);
	width: 50%;
	z-index: -1;
}

.portlet-hello-world {
	display: flex;
	flex-direction: column;
	height: 67.5vh;
	justify-content: center;
	margin: 0;
	padding: 5%;
}

.welcome-container {
	height: 50vh;
}
</style>

<div class="portlet-hello-world">
	<div class="welcome-container">
		<div class="welcome-content">
			<h1>${cityname.getData()}</h1>
			<h4>${teamname.getData()}</h4>
		</div>
	</div>
</div>