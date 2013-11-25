/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.tools.deploy;

import com.liferay.portal.kernel.deploy.Deployer;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.plugin.PluginPackageUtil;

import java.io.File;

import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class ThemeDeployerTest extends BaseDeployerTest {

	@Override
	public Deployer getDeployer() {
		return new ThemeDeployer();
	}

	@Test
	public void testProcessPluginPackageProperties() throws Exception {

		Map<String, String> filterMap = processPluginPackageProperties();

		Assert.assertNotNull("FilterMap must not be null.", filterMap);

		Assert.assertFalse("FilterMap must not be empty.", filterMap.isEmpty());

		File xmlFile = new File(
			getWebInfFolder(), "liferay-plugin-package.xml");

		validateLiferayPluginPackageXMLFile(xmlFile);

		xmlFile = new File(getWebInfFolder(),"liferay-look-and-feel.xml");

		validateLiferayLookAndFeelXMLFile(xmlFile);
	}

	protected Map<String, String> processPluginPackageProperties()
		throws Exception {

		String displayName = "test-theme";

		Properties properties = getLiferayPluginPackageProperties();

		PluginPackage pluginPackage =
			PluginPackageUtil.readPluginPackageProperties(
				displayName, properties);

		Assert.assertNotNull(pluginPackage);

		Assert.assertEquals("Test Theme EE", pluginPackage.getName());

		Deployer themeDeployer = getDeployer();

		Map<String, String> filterMap =
			themeDeployer.processPluginPackageProperties(
				getRootDeploymentFolder(), displayName, pluginPackage);

		Assert.assertNotNull("FilterMap must not be null.", filterMap);

		Assert.assertFalse("FilterMap must not be empty.", filterMap.isEmpty());

		return filterMap;
	}

	protected void validateLiferayLookAndFeelXMLFile(File xmlFile)
		throws Exception {

		Assert.assertTrue(
			"liferay-look-and-feel.xml must be created.", xmlFile.exists());

		String liferayLookAndFeelXML = FileUtil.read(xmlFile);

		Assert.assertNotNull(
			"XML file content must not be null.", liferayLookAndFeelXML);

		Document document = SAXReaderUtil.read(liferayLookAndFeelXML, true);

		Element rootElement = document.getRootElement();

		Element element = rootElement.element("theme");

		String value = element.attribute("name").getValue();

		Assert.assertEquals("Test Theme EE", value);

		value = element.attribute("id").getValue();

		Assert.assertNotNull(value);

		element = rootElement.element("compatibility");

		Assert.assertNotNull(element);

		Assert.assertNotNull(element.getTextTrim());
	}

}