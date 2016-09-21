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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

/**
 * @author Brian Wing Shun Chan
 */
public class ServerDetector {

	public static final String GERONIMO_ID = "geronimo";

	public static final String GLASSFISH_ID = "glassfish";

	public static final String JBOSS_ID = "jboss";

	public static final String JETTY_ID = "jetty";

	public static final String JONAS_ID = "jonas";

	public static final String OC4J_ID = "oc4j";

	public static final String RESIN_ID = "resin";

	public static final String TOMCAT_ID = "tomcat";

	public static final String WEBLOGIC_ID = "weblogic";

	public static final String WEBSPHERE_ID = "websphere";

	public static final String WILDFLY_ID = "wildfly";

	public static ServerDetector getInstance() {
		return new ServerDetector();
	}

	public static String getServerId() {
		return StringUtil.toLowerCase(_serverType.toString());
	}

	public static void init(String serverId) {

		_serverType = ServerType.valueOf(StringUtil.toUpperCase(serverId));
		_init();
	}

	public static boolean isGeronimo() {
		if (_serverType == ServerType.GERONIMO) {
			return true;
		}

		return false;
	}

	public static boolean isGlassfish() {
		if (_serverType == ServerType.GLASSFISH) {
			return true;
		}

		return false;
	}

	public static boolean isJBoss() {
		if (_serverType == ServerType.JBOSS) {
			return true;
		}

		return false;
	}

	public static boolean isJBoss5() {
		if (_serverType == ServerType.JBOSS5) {
			return true;
		}

		return false;
	}

	public static boolean isJBoss7() {
		if (_serverType == ServerType.JBOSS7) {
			return true;
		}

		return false;
	}

	public static boolean isJetty() {
		if (_serverType == ServerType.JETTY) {
			return true;
		}

		return false;
	}

	public static boolean isJOnAS() {
		if (_serverType == ServerType.JONAS) {
			return true;
		}

		return false;
	}

	public static boolean isOC4J() {
		if (_serverType == ServerType.OC4J) {
			return true;
		}

		return false;
	}

	public static boolean isResin() {
		if (_serverType == ServerType.RESIN) {
			return true;
		}

		return false;
	}

	public static boolean isSupportsComet() {
		return _supportsComet;
	}

	public static boolean isSupportsHotDeploy() {
		return _supportsHotDeploy;
	}

	public static boolean isTomcat() {
		if (_serverType == ServerType.TOMCAT) {
			return true;
		}

		return false;
	}

	public static boolean isWebLogic() {
		if (_serverType == ServerType.WEBLOGIC) {
			return true;
		}

		return false;
	}

	public static boolean isWebSphere() {
		if (_serverType == ServerType.WEBSPHERE) {
			return true;
		}

		return false;
	}

	public static boolean isWildfly() {
		if (_serverType == ServerType.WILDFLY) {
			return true;
		}

		return false;
	}

	public static void setSupportsHotDeploy(boolean supportsHotDeploy) {
		_supportsHotDeploy = supportsHotDeploy;

		if (_log.isInfoEnabled()) {
			if (supportsHotDeploy) {
				_log.info("Server supports hot deploy");
			}
			else {
				_log.info("Server does not support hot deploy");
			}
		}
	}

	private static boolean _detect(String className) {
		try {
			ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

			systemClassLoader.loadClass(className);

			return true;
		}
		catch (ClassNotFoundException cnfe) {
			if (ServerDetector.class.getResource(className) != null) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	private static boolean _hasSystemProperty(String key) {
		String value = System.getProperty(key);

		if (value != null) {
			return true;
		}
		else {
			return false;
		}
	}

	private static void _init() {
		if (_hasSystemProperty("org.apache.geronimo.home.dir")) {
			_serverType = ServerType.GERONIMO;
		}
		else if (_hasSystemProperty("com.sun.aas.instanceRoot")) {
			_serverType = ServerType.GLASSFISH;
		}
		else if (_hasSystemProperty("jboss.home.dir")) {
			_serverType = ServerType.JBOSS;

			if (_isJBoss5()) {
				_serverType = ServerType.JBOSS5;
			}
			else {
				_serverType = ServerType.JBOSS7;
			}
		}
		else if (_hasSystemProperty("jonas.base")) {
			_serverType = ServerType.JONAS;
		}
		else if (_detect("oracle.oc4j.util.ClassUtils")) {
			_serverType = ServerType.OC4J;
		}
		else if (_hasSystemProperty("resin.home")) {
			_serverType = ServerType.RESIN;
		}
		else if (_detect("/weblogic/Server.class")) {
			_serverType = ServerType.WEBLOGIC;
		}
		else if (_detect("/com/ibm/websphere/product/VersionInfo.class")) {
			_serverType = ServerType.WEBSPHERE;
		}
		else if (_hasSystemProperty("jboss.home.dir")) {
			_serverType = ServerType.WILDFLY;
		}

		if (_serverType == null) {
			if (_hasSystemProperty("jetty.home")) {
				_serverType = ServerType.JETTY;
			}
			else if (_hasSystemProperty("catalina.base")) {
				_serverType = ServerType.TOMCAT;
			}
		}

		if (System.getProperty("external-properties") == null) {
			if (_log.isInfoEnabled()) {
				if (_serverType != null) {
					_log.info(
						"Detected server " +
							StringUtil.toLowerCase(_serverType.toString()));
				}
				else {
					_log.info("No server detected");
				}
			}
		}

		/*if (_serverId == null) {
			throw new RuntimeException("Server is not supported");
		}*/
	}

	private static boolean _isJBoss5() {
		try {
			for (MBeanServer mBeanServer :
					MBeanServerFactory.findMBeanServer(null)) {

				String defaultDomain = GetterUtil.getString(
					mBeanServer.getDefaultDomain(), "jboss");

				if (defaultDomain.equals("jboss")) {
					ObjectName objectName = new ObjectName(
						"jboss.system:type=Server");

					String version = (String)mBeanServer.getAttribute(
						objectName, "VersionNumber");

					if (version.startsWith("5")) {
						return true;
					}

					return false;
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(ServerDetector.class);

	private static ServerType _serverType;

	static {
		_init();
	}

	private static boolean _supportsComet;
	private static boolean _supportsHotDeploy;

	private enum ServerType {

		GERONIMO, GLASSFISH, JBOSS, JBOSS5, JBOSS7, JETTY, JONAS, OC4J, RESIN,
		TOMCAT, WEBLOGIC, WEBSPHERE, WILDFLY;
	}

}