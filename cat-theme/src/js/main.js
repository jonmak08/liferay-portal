initApp = (() => {
	let body = document.querySelector("body");
	let darkBackgroundElement = document.querySelector(".dark--background");
	let menuHeader = document.querySelector(".menu-title");
	let menuIcon = document.querySelector(".menu-icon");
	let parallax = document.querySelector(".parallax");
	let addSelected = document.querySelector(".add-selected");
	let sideNav = document.querySelectorAll(".control-menu-icon");

	document.addEventListener("DOMContentLoaded", function(){
			detectSideNav(body);

		addSelected.addEventListener('click', () => selectNav(addSelected));
	});
	
	menuHeader.addEventListener("click", () => body.classList.toggle("nav-active"));
	menuIcon.addEventListener("click", () => body.classList.toggle("nav-active"));

	sideNav.forEach(controlIcon => {
		controlIcon.addEventListener("click", () => 
		toggleSideNav(body));
	})

	window.addEventListener("scroll", function () {
		debounce(scrollParallax(parallax));

		debounce(changeHamburgerColor(darkBackgroundElement));
	}, false);
})();

changeHamburgerColor = element => {
	let hamburgerIcon = document.querySelectorAll(".menu-icon--line")
	let hamburgerMenuText = document.querySelector(".menu-title")
	let navigationBackground = document.querySelector(".navigation")

	if (!isInViewport(element)) {
		navigationBackground.classList.add("navigation--scroll")

		hamburgerMenuText.classList.add("menu-title--dark");

		hamburgerIcon.forEach(line => {
			line.classList.add("menu-icon--line--dark");
		})
	} else {
		navigationBackground.classList.remove("navigation--scroll")

		hamburgerMenuText.classList.remove("menu-title--dark");

		hamburgerIcon.forEach(line => {
			line.classList.remove("menu-icon--line--dark");
		})
	}
}

debounce = fn => {
	let timeout;

	return () => {
		if (timeout) {
			window.cancelAnimationFrame(timeout);
		}

		timeout = window.requestAnimationFrame(function () {
			fn.apply(this, arguments);
		});
	}
};

detectSideNav = element => {
	if(element.classList.contains("open") || element.classList.contains("open-admin-panel")) {
		document.getElementById("navigation").classList.add("liferay-nav-active");
	}
}

isInViewport = element => {
	const bounding = element.getBoundingClientRect();
		return (bounding.top >= 0 || bounding.bottom >= 0);
};

scrollParallax = element => {
	if (isInViewport(element)) {
		let offset = window.pageYOffset;

		element.style.backgroundPositionY = offset * .08 + "rem";
	}
};

toggleSideNav = (element) => {
	setTimeout(() => {
		if(element.classList.contains("open") || element.classList.contains("open-admin-panel")) {
			document.getElementById("navigation").classList.add("liferay-nav-active");
		} else {
			document.getElementById("navigation").classList.remove("liferay-nav-active");
		}
	}, 1)
}