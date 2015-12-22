<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<div class="alert alert-default">
	<strong class="lead">Taglibs used: </strong>

	<span class="badge badge-primary">aui:nav-bar</span>

	<span class="badge badge-primary">aui:nav</span>

	<span class="badge badge-primary">aui:nav-item</span>
</div>

<aui:nav-bar>
	<aui:nav collapsible="true" cssClass="navbar-nav">
		<aui:nav-item href="#" label="Link" />

		<aui:nav-item href="#" label="Active Link" selected="true" />

		<aui:nav-item dropdown="true" label="Dropdown">
			<aui:nav-item href="#" label="Dropdown Test" />

			<aui:nav-item href="#" label="Dropdown Item 2" />

			<aui:nav-item href="#" label="Dropdown Item 3" />
		</aui:nav-item>
	</aui:nav>
</aui:nav-bar>

<br />

<h3>Lexicon View</h3>

<aui:nav-bar markupView="lexicon">
	<aui:nav collapsible="true" cssClass="navbar-nav">
		<aui:nav-item href="#" label="Link" />

		<aui:nav-item href="#" label="Active Link" selected="true" />

		<aui:nav-item dropdown="true" label="Dropdown">
			<aui:nav-item href="#" label="Dropdown Test" />

			<aui:nav-item href="#" label="Dropdown Item 2" />

			<aui:nav-item href="#" label="Dropdown Item 3" />
		</aui:nav-item>
	</aui:nav>
</aui:nav-bar>