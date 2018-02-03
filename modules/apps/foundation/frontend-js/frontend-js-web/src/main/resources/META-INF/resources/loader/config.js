(function() {
	var LiferayAUI = Liferay.AUI;

	var combine = LiferayAUI.getCombine();

	window.__CONFIG__ = {
		basePath: '',
		combine: combine,
		reportMismatchedAnonymousModules: 'warn',
		url: combine ? LiferayAUI.getComboPath() : themeDisplay.getPortalURL()
	};

	if (!combine) {
		__CONFIG__.defaultURLParams = {
			languageId: themeDisplay.getLanguageId()
		};
	}

	__CONFIG__.maps = Liferay.MAPS;

	__CONFIG__.modules = Liferay.MODULES;

	__CONFIG__.paths = Liferay.PATHS;

	__CONFIG__.namespace = 'Liferay';

<<<<<<< HEAD
	__CONFIG__.explainResolutions = Liferay.EXPLAIN_RESOLUTIONS;

=======
>>>>>>> compatible
	__CONFIG__.exposeGlobal = Liferay.EXPOSE_GLOBAL;
}());