<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title}</title>

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="portal-popup ${css_class}">
		<button id="modalBtn" class="button">Open Modal</button>



<@liferay_util["include"] page=content_include />

<@liferay_util["include"] page=bottom_ext_include />

</body>

</html>