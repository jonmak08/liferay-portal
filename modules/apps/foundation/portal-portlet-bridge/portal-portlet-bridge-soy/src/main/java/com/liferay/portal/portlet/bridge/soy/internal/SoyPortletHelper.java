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

package com.liferay.portal.portlet.bridge.soy.internal;

<<<<<<< HEAD
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import com.liferay.portal.kernel.portlet.Route;
import com.liferay.portal.kernel.portlet.Router;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCCommandCache;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.InputStream;

import java.net.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletException;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
=======
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.template.soy.utils.SoyJavaScriptRenderer;

import java.net.URL;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
>>>>>>> compatible

/**
 * @author Bruno Basto
 */
public class SoyPortletHelper {

<<<<<<< HEAD
	public SoyPortletHelper(
			Bundle bundle, MVCCommandCache mvcRenderCommandCache,
			FriendlyURLMapper friendlyURLMapper)
		throws Exception {

		_bundle = bundle;
		_mvcRenderCommandCache = mvcRenderCommandCache;
		_friendlyURLMapper = friendlyURLMapper;

		_jsonSerializer = JSONFactoryUtil.createJSONSerializer();
		_routerJavaScriptTPL = getRouterJavaScriptTPL();
	}

	public String getJavaScriptLoaderModule(String mvcCommandName)
		throws Exception {

		String loaderModule = _javaScriptLoaderModulesMap.get(mvcCommandName);

		if (loaderModule != null) {
			return loaderModule;
		}

		String controllerName = getJavaScriptControllerName(mvcCommandName);

		String packageName = getJavaScriptPackageName(mvcCommandName);

		if (packageName == null) {
			throw new Exception("Unable to get package name");
		}

		if (!controllerName.startsWith(StringPool.SLASH)) {
			packageName = packageName.concat(StringPool.SLASH);
		}

		loaderModule = packageName.concat(controllerName);

		_javaScriptLoaderModulesMap.put(mvcCommandName, loaderModule);

		return loaderModule;
	}

	public String getRouterJavaScript(
			String elementId, String portletId, String portletNamespace,
			String portletWrapperId, Template template)
		throws Exception {

		Set<String> mvcRenderCommandNames = getMVCRenderCommandNames();

		String mvcRenderCommandNamesString = _jsonSerializer.serialize(
			mvcRenderCommandNames);

		template.remove("element");

		String contextString = _jsonSerializer.serializeDeep(template);

		List<Map<String, Object>> friendlyURLRoutes = getFriendlyURLRoutes();

		String friendlyURLRoutesString = _jsonSerializer.serializeDeep(
			friendlyURLRoutes);

		return StringUtil.replace(
			_routerJavaScriptTPL,
			new String[] {
				"$ELEMENT_ID", "$MVC_RENDER_COMMAND_NAMES", "$PORTLET_ID",
				"$PORTLET_NAMESPACE", "$PORTLET_WRAPPER_ID", "$CONTEXT",
				"$FRIENDLY_URL_ROUTES", "$FRIENDLY_URL_MAPPING",
				"$FRIENDLY_URL_PREFIX"
			},
			new String[] {
				elementId, mvcRenderCommandNamesString, portletId,
				portletNamespace, portletWrapperId, contextString,
				friendlyURLRoutesString, getFriendlyURLMapping(),
				String.valueOf(isCheckMappingWithPrefix())
			});
	}

	public String serializeTemplate(Template template) {
		return _jsonSerializer.serializeDeep(template);
	}

	protected String getFriendlyURLMapping() {
		if (_friendlyURLMapper == null) {
			return StringPool.BLANK;
		}

		return _friendlyURLMapper.getMapping();
	}

	protected List<Map<String, Object>> getFriendlyURLRoutes() {
		List<Map<String, Object>> routesMapping = new ArrayList<>();

		if (_friendlyURLMapper != null) {
			Router router = _friendlyURLMapper.getRouter();

			List<Route> routes = router.getRoutes();

			for (Route route : routes) {
				Map<String, Object> mapping = new HashMap<>();

				mapping.put(
					"implicitParameters", route.getImplicitParameters());
				mapping.put(
					"overriddenParameters", route.getOverriddenParameters());
				mapping.put("pattern", route.getPattern());

				routesMapping.add(mapping);
			}
		}

		return routesMapping;
	}

	protected String getJavaScriptControllerName(String mvcCommandName)
		throws PortletException {

		String controllerName = _javaScriptLoaderModulesMap.get(mvcCommandName);
=======
	public SoyPortletHelper(Bundle bundle) throws Exception {
		_bundle = bundle;
		_moduleName = getModuleName();
		_soyJavaScriptRenderer = new SoyJavaScriptRenderer();
	}

	public String getPortletJavaScript(
		Template template, String path, String portletNamespace,
		Set<String> additionalRequiredModules) {

		if (_moduleName == null) {
			return StringPool.BLANK;
		}

		Set<String> requiredModules = getRequiredModules(
			path, additionalRequiredModules);

		return _soyJavaScriptRenderer.getJavaScript(
			template, portletNamespace, requiredModules);
	}

	public String getTemplateNamespace(String path) {
		return path.concat(".render");
	}

	protected String getControllerName(String path) {
		String controllerName = _controllersMap.get(path);
>>>>>>> compatible

		if (controllerName != null) {
			return controllerName;
		}

<<<<<<< HEAD
		Bundle bundle = getMVCCommandBundle(mvcCommandName);

		String filePath = getJavaScriptFilePath(bundle, mvcCommandName);

		if (filePath.endsWith(".js")) {
			filePath = StringUtil.replace(filePath, ".js", StringPool.BLANK);
		}

		controllerName = StringUtil.replace(
			filePath, _RESOURCES_PATH, StringPool.BLANK);

		_javaScriptLoaderModulesMap.put(mvcCommandName, controllerName);
=======
		URL url = _bundle.getEntry(
			"/META-INF/resources/".concat(path).concat(".es.js"));

		if (url != null) {
			controllerName = path.concat(".es");
		}
		else {
			controllerName = path.concat(".soy");
		}

		_controllersMap.put(path, controllerName);
>>>>>>> compatible

		return controllerName;
	}

<<<<<<< HEAD
	protected String getJavaScriptFilePath(Bundle bundle, String mvcCommandName)
		throws PortletException {

		String resourcesPath = _RESOURCES_PATH;

		if (!mvcCommandName.startsWith(StringPool.SLASH)) {
			resourcesPath = resourcesPath.concat(StringPool.SLASH);
		}

		String filePath = resourcesPath.concat(mvcCommandName).concat(".js");

		if (bundle.getEntry(filePath) != null) {
			return filePath;
		}

		filePath = resourcesPath.concat(mvcCommandName).concat(".es.js");

		if (bundle.getEntry(filePath) != null) {
			return filePath;
		}

		filePath = resourcesPath.concat(mvcCommandName).concat(".soy");

		if (bundle.getEntry(filePath) != null) {
			return filePath;
		}

		throw new PortletException(
			"Unable to get controller for " + mvcCommandName);
	}

	protected String getJavaScriptPackageName(String path) throws Exception {
		JSONObject jsonObject = getPackageJSONObject(path);

		if (jsonObject == null) {
			return null;
		}
=======
	protected String getModuleName() throws Exception {
		URL url = _bundle.getEntry("package.json");

		if (url == null) {
			return null;
		}

		String json = StringUtil.read(url.openStream());

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(json);
>>>>>>> compatible

		String moduleName = jsonObject.getString("name");

		if (Validator.isNull(moduleName)) {
			return null;
		}

<<<<<<< HEAD
		String moduleVersion = jsonObject.getString("version");

		if (Validator.isNull(moduleVersion)) {
			return moduleName;
		}

		return moduleName.concat(StringPool.AT).concat(moduleVersion);
	}

	protected Bundle getMVCCommandBundle(String mvcCommandName)
		throws PortletException {

		MVCCommand mvcRenderCommand = null;

		if (Validator.isNull(mvcCommandName)) {
			mvcRenderCommand = MVCRenderCommand.EMPTY;
		}
		else {
			mvcRenderCommand = _mvcRenderCommandCache.getMVCCommand(
				mvcCommandName);
		}

		if (mvcRenderCommand == MVCRenderCommand.EMPTY) {
			return _bundle;
		}

		return FrameworkUtil.getBundle(mvcRenderCommand.getClass());
	}

	protected Set<String> getMVCRenderCommandNames() {
		MVCCommandCache mvcRenderCommandCache = _mvcRenderCommandCache;

		return mvcRenderCommandCache.getMVCCommandNames();
	}

	protected JSONObject getPackageJSONObject(String path) throws Exception {
		Bundle bundle = getMVCCommandBundle(path);

		URL url = bundle.getEntry("package.json");

		if (url == null) {
			return null;
		}

		String json = StringUtil.read(url.openStream());

		return JSONFactoryUtil.createJSONObject(json);
	}

	protected String getRouterJavaScriptTPL() throws Exception {
		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/router.js.tpl");

		return StringUtil.read(inputStream);
	}

	protected boolean isCheckMappingWithPrefix() {
		if (_friendlyURLMapper == null) {
			return false;
		}

		return _friendlyURLMapper.isCheckMappingWithPrefix();
	}

	private static final String _RESOURCES_PATH = "/META-INF/resources";

	private final Bundle _bundle;
	private final FriendlyURLMapper _friendlyURLMapper;
	private final Map<String, String> _javaScriptLoaderModulesMap =
		new HashMap<>();
	private final JSONSerializer _jsonSerializer;
	private final MVCCommandCache _mvcRenderCommandCache;
	private final String _routerJavaScriptTPL;
=======
		return moduleName;
	}

	protected Set<String> getRequiredModules(
		String path, Set<String> additionalRequiredModules) {

		if (_moduleName == null) {
			return Collections.emptySet();
		}

		Set<String> requiredModules = new LinkedHashSet<>();

		String controllerName = getControllerName(path);

		requiredModules.add(
			_moduleName.concat(StringPool.SLASH).concat(controllerName));

		requiredModules.addAll(additionalRequiredModules);

		return requiredModules;
	}

	private final Bundle _bundle;
	private final Map<String, String> _controllersMap = new HashMap<>();
	private final String _moduleName;
	private final SoyJavaScriptRenderer _soyJavaScriptRenderer;
>>>>>>> compatible

}