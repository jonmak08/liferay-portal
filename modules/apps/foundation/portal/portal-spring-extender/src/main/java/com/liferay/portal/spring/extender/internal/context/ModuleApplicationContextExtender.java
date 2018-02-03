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

package com.liferay.portal.spring.extender.internal.context;

import com.liferay.osgi.felix.util.AbstractExtender;
<<<<<<< HEAD
=======
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
>>>>>>> compatible
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBContext;
import com.liferay.portal.kernel.dao.db.DBManager;
import com.liferay.portal.kernel.dao.db.DBProcessContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.configuration.configurator.ServiceConfigurator;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.upgrade.UpgradeStep;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.spring.extender.internal.classloader.BundleResolverClassLoader;
=======
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.spring.extender.internal.classloader.BundleResolverClassLoader;
import com.liferay.portal.spring.extender.internal.configuration.SpringExtenderConfiguration;
>>>>>>> compatible
import com.liferay.portal.upgrade.registry.UpgradeStepRegistratorTracker;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.ArrayList;
import java.util.Dictionary;
<<<<<<< HEAD
import java.util.Hashtable;
import java.util.List;

import javax.sql.DataSource;

=======
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.apache.felix.dm.ComponentDeclaration;
import org.apache.felix.dm.ComponentDependencyDeclaration;
>>>>>>> compatible
import org.apache.felix.dm.DependencyManager;
import org.apache.felix.dm.ServiceDependency;
import org.apache.felix.utils.extender.Extension;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Miguel Pastor
 */
<<<<<<< HEAD
@Component(immediate = true)
public class ModuleApplicationContextExtender extends AbstractExtender {

	@Activate
	protected void activate(BundleContext bundleContext) throws Exception {
		start(bundleContext);
=======
@Component(
	configurationPid = "com.liferay.portal.spring.extender.internal.configuration.SpringExtenderConfiguration",
	immediate = true
)
public class ModuleApplicationContextExtender extends AbstractExtender {

	@Activate
	protected void activate(
			BundleContext bundleContext, Map<String, Object> properties)
		throws Exception {

		start(bundleContext);

		SpringExtenderConfiguration springExtenderConfiguration =
			ConfigurableUtil.createConfigurable(
				SpringExtenderConfiguration.class, properties);

		long scanningInterval =
			springExtenderConfiguration.unavailableComponentScanningInterval();

		if (scanningInterval > 0) {
			_unavailableComponentScanningThread =
				new UnavailableComponentScanningThread(
					scanningInterval * Time.SECOND);

			_unavailableComponentScanningThread.start();
		}
>>>>>>> compatible
	}

	@Deactivate
	protected void deactivate(BundleContext bundleContext) throws Exception {
<<<<<<< HEAD
=======
		if (_unavailableComponentScanningThread != null) {
			_unavailableComponentScanningThread.interrupt();

			_unavailableComponentScanningThread.join();
		}

>>>>>>> compatible
		stop(bundleContext);
	}

	@Override
	protected void debug(Bundle bundle, String s) {
		if (_log.isDebugEnabled()) {
			_log.debug(s);
		}
	}

	@Override
	protected Extension doCreateExtension(Bundle bundle) throws Exception {
		Dictionary<String, String> headers = bundle.getHeaders();

		if (headers.get("Liferay-Spring-Context") == null) {
			return null;
		}

		return new ModuleApplicationContextExtension(bundle);
	}

	@Override
	protected void error(String s, Throwable throwable) {
		_log.error(s, throwable);
	}

	@Reference(
		target = "(&(bean.id=liferayDataSource)(original.bean=true))",
		unbind = "-"
	)
	protected void setDataSource(DataSource dataSource) {
	}

	@Reference(target = "(original.bean=true)", unbind = "-")
	protected void setInfrastructureUtil(
		InfrastructureUtil infrastructureUtil) {
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(target = "(original.bean=true)", unbind = "-")
	protected void setSaxReaderUtil(SAXReaderUtil saxReaderUtil) {
	}

	@Reference(target = "(original.bean=true)", unbind = "-")
	protected void setServiceConfigurator(
		ServiceConfigurator serviceConfigurator) {

		_serviceConfigurator = serviceConfigurator;
	}

	@Override
	protected void warn(Bundle bundle, String s, Throwable throwable) {
		if (_log.isWarnEnabled()) {
			_log.warn(s, throwable);
		}
	}

<<<<<<< HEAD
=======
	private static void _scanUnavailableComponents() {
		StringBundler sb = new StringBundler();

		for (DependencyManager dependencyManager :
				(List<DependencyManager>)
					DependencyManager.getDependencyManagers()) {

			BundleContext bundleContext = dependencyManager.getBundleContext();

			Bundle bundle = bundleContext.getBundle();

			Map<ComponentDeclaration, List<ComponentDependencyDeclaration>>
				unavailableComponentDeclarations = new HashMap<>();

			for (ComponentDeclaration componentDeclaration :
					(List<ComponentDeclaration>)
						dependencyManager.getComponents()) {

				if (componentDeclaration.getState() !=
						ComponentDeclaration.STATE_UNREGISTERED) {

					continue;
				}

				List<ComponentDependencyDeclaration>
					componentDependencyDeclarations =
						unavailableComponentDeclarations.computeIfAbsent(
							componentDeclaration, key -> new ArrayList<>());

				for (ComponentDependencyDeclaration
						componentDependencyDeclaration :
							componentDeclaration.getComponentDependencies()) {

					if (componentDependencyDeclaration.getState() ==
							ComponentDependencyDeclaration.
								STATE_UNAVAILABLE_REQUIRED) {

						componentDependencyDeclarations.add(
							componentDependencyDeclaration);
					}
				}
			}

			if (!unavailableComponentDeclarations.isEmpty()) {
				sb.append("Found unavailable component in bundle ");
				sb.append(bundle);
				sb.append(".\n");

				for (Entry<
						ComponentDeclaration,
						List<ComponentDependencyDeclaration>> entry :
							unavailableComponentDeclarations.entrySet()) {

					sb.append("\tComponent ");
					sb.append(entry.getKey());
					sb.append(" is unavailable due to missing required ");
					sb.append("dependencies: ");

					List<ComponentDependencyDeclaration>
						componentDependencyDeclarations = entry.getValue();

					for (int i = 0; i < componentDependencyDeclarations.size();
						i++) {

						if (i != 0) {
							sb.append(", ");
						}

						ComponentDependencyDeclaration
							componentDependencyDeclaration =
								componentDependencyDeclarations.get(i);

						sb.append(componentDependencyDeclaration);
					}

					sb.append(".");
				}
			}
		}

		if (sb.index() == 0) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"All Spring extender dependency manager components are " +
						"registered");
			}
		}
		else {
			if (_log.isWarnEnabled()) {
				_log.warn(sb.toString());
			}
		}
	}

>>>>>>> compatible
	private static final Log _log = LogFactoryUtil.getLog(
		ModuleApplicationContextExtender.class);

	private ServiceConfigurator _serviceConfigurator;
<<<<<<< HEAD
=======
	private Thread _unavailableComponentScanningThread;

	private static class UnavailableComponentScanningThread extends Thread {

		@Override
		public void run() {
			try {
				while (true) {
					sleep(_scanningInterval);

					_scanUnavailableComponents();
				}
			}
			catch (InterruptedException ie) {
				if (_log.isInfoEnabled()) {
					_log.info("Stopped scanning for unavailable components");
				}
			}
		}

		private UnavailableComponentScanningThread(long scanningInterval) {
			_scanningInterval = scanningInterval;

			setDaemon(true);
			setName("Spring Extender Unavailable Component Scanner");
		}

		private final long _scanningInterval;

	}
>>>>>>> compatible

	private class ModuleApplicationContextExtension implements Extension {

		public ModuleApplicationContextExtension(Bundle bundle) {
			_bundle = bundle;

			_dependencyManager = new DependencyManager(
				bundle.getBundleContext());
		}

		@Override
		public void destroy() throws Exception {
			for (ServiceRegistration<UpgradeStep>
					upgradeStepServiceRegistration :
						_upgradeStepServiceRegistrations) {

				upgradeStepServiceRegistration.unregister();
			}

			if (_component != null) {
				_dependencyManager.remove(_component);
			}
		}

		public String getSQLTemplateString(String templateName)
			throws UpgradeException {

			URL resource = _bundle.getResource("/META-INF/sql/" + templateName);

			if (resource == null) {
				if (_log.isDebugEnabled()) {
					_log.debug("Unable to locate SQL template " + templateName);
				}

				return null;
			}

			try (InputStream inputStream = resource.openStream()) {
				return StringUtil.read(inputStream);
			}
			catch (IOException ioe) {
				throw new UpgradeException(
					"Unable to read SQL template " + templateName, ioe);
			}
		}

		@Override
		public void start() throws Exception {
			_component = _dependencyManager.createComponent();

			BundleContext bundleContext =
				ModuleApplicationContextExtender.this.getBundleContext();

			Bundle bundle = bundleContext.getBundle();

			_component.setImplementation(
				new ModuleApplicationContextRegistrator(
					_bundle, bundle, _serviceConfigurator));

			ClassLoader classLoader = new BundleResolverClassLoader(
				_bundle, bundle);

			List<ContextDependency> contextDependencies =
				_processServiceReferences(_bundle);

			for (ContextDependency contextDependency : contextDependencies) {
				ServiceDependency serviceDependency =
					_dependencyManager.createServiceDependency();

				serviceDependency.setRequired(true);

				Class<?> serviceClass = Class.forName(
					contextDependency.getServiceClassName(), false,
					classLoader);

				serviceDependency.setService(
					serviceClass, contextDependency.getFilterString());

				_component.add(serviceDependency);
			}

			Dictionary<String, String> headers = _bundle.getHeaders();

			String requireSchemaVersion = headers.get(
				"Liferay-Require-SchemaVersion");

			if (Validator.isNull(requireSchemaVersion)) {
				_generateReleaseInfo();
			}

			_dependencyManager.add(_component);

			_upgradeStepServiceRegistrations = _processInitialUpgrade(
				classLoader);
		}

		private void _generateReleaseInfo() {
			ServiceDependency serviceDependency =
				_dependencyManager.createServiceDependency();

			serviceDependency.setRequired(true);

			serviceDependency.setService(
				Release.class,
<<<<<<< HEAD
				StringBundler.concat(
					"(&(release.bundle.symbolic.name=",
					_bundle.getSymbolicName(), ")(release.schema.version=",
					String.valueOf(_bundle.getVersion()), "))"));
=======
				"(&(release.bundle.symbolic.name=" + _bundle.getSymbolicName() +
					")(release.schema.version=" + _bundle.getVersion() + "))");
>>>>>>> compatible

			_component.add(serviceDependency);
		}

<<<<<<< HEAD
		private List<ServiceRegistration<UpgradeStep>> _processInitialUpgrade(
			ClassLoader classLoader) {
=======
		private List<ServiceRegistration<UpgradeStep>>
			_processInitialUpgrade(ClassLoader classLoader) {
>>>>>>> compatible

			Dictionary<String, String> headers = _bundle.getHeaders();

			String upgradeToSchemaVersion = GetterUtil.getString(
				headers.get("Liferay-Require-SchemaVersion"),
				headers.get("Bundle-Version"));

			Dictionary<String, Object> properties = new Hashtable<>();

			try {
				Configuration configuration =
					ConfigurationFactoryUtil.getConfiguration(
						classLoader, "service");

				String buildNumber = configuration.get("build.number");

				if (buildNumber != null) {
					properties.put("build.number", buildNumber);
				}
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Unable to read service.properties for bundle " +
							_bundle.getSymbolicName());
				}
			}

			properties.put("upgrade.initial.database.creation", "true");

			return UpgradeStepRegistratorTracker.register(
				ModuleApplicationContextExtender.this.getBundleContext(),
				_bundle.getSymbolicName(), "0.0.0", upgradeToSchemaVersion,
				properties,
				new UpgradeStep() {

					@Override
					public String toString() {
						return "Initial Database Creation";
					}

					@Override
					public void upgrade(DBProcessContext dbProcessContext)
						throws UpgradeException {

						DBContext dbContext = dbProcessContext.getDBContext();

						DBManager dbManager = dbContext.getDBManager();

						DB db = dbManager.getDB();

						String tablesSQL = getSQLTemplateString("tables.sql");
						String sequencesSQL = getSQLTemplateString(
							"sequences.sql");
						String indexesSQL = getSQLTemplateString("indexes.sql");

<<<<<<< HEAD
						if (tablesSQL != null) {
							try {
								db.runSQLTemplateString(tablesSQL, true, true);
							}
							catch (Exception e) {
								throw new UpgradeException(
									StringBundler.concat(
										"Bundle ", String.valueOf(_bundle),
										" has invalid content in ",
										"tables.sql:\n", tablesSQL),
									e);
							}
						}

						if (sequencesSQL != null) {
							try {
								db.runSQLTemplateString(
									sequencesSQL, true, true);
							}
							catch (Exception e) {
								throw new UpgradeException(
									StringBundler.concat(
										"Bundle ", String.valueOf(_bundle),
										" has invalid content in ",
										"sequences.sql:\n", sequencesSQL),
									e);
							}
						}

						if (indexesSQL != null) {
							try {
								db.runSQLTemplateString(indexesSQL, true, true);
							}
							catch (Exception e) {
								throw new UpgradeException(
									StringBundler.concat(
										"Bundle ", String.valueOf(_bundle),
										" has invalid content in ",
										"indexes.sql:\n", indexesSQL),
									e);
							}
=======
						try {
							if (tablesSQL != null) {
								db.runSQLTemplateString(tablesSQL, true, true);
							}

							if (sequencesSQL != null) {
								db.runSQLTemplateString(
									sequencesSQL, true, true);
							}

							if (indexesSQL != null) {
								db.runSQLTemplateString(indexesSQL, true, true);
							}
						}
						catch (Exception e) {
							throw new UpgradeException(e);
>>>>>>> compatible
						}
					}

				});
		}

		private List<ContextDependency> _processServiceReferences(Bundle bundle)
			throws IOException {

			List<ContextDependency> contextDependencies = new ArrayList<>();

			URL url = bundle.getEntry("OSGI-INF/context/context.dependencies");

			if (url == null) {
				return contextDependencies;
			}

			List<String> lines = new ArrayList<>();

			StringUtil.readLines(url.openStream(), lines);

			for (String line : lines) {
				if (Validator.isNull(line)) {
					continue;
				}

				line = line.trim();

				String[] array = line.split(" ");

				String filterString = "";

				if (array.length > 1) {
					filterString = array[1];
				}

				contextDependencies.add(
					new ContextDependency(array[0], filterString));
			}

			return contextDependencies;
		}

		private final Bundle _bundle;
		private org.apache.felix.dm.Component _component;
		private final DependencyManager _dependencyManager;
		private List<ServiceRegistration<UpgradeStep>>
			_upgradeStepServiceRegistrations;

		private class ContextDependency {

			public ContextDependency(
				String serviceClassName, String filterString) {

				this.serviceClassName = serviceClassName;

				if (!filterString.equals("")) {
					this.filterString = filterString;
				}
			}

			public String getFilterString() {
				return filterString;
			}

			public String getServiceClassName() {
				return serviceClassName;
			}

			protected String filterString;
			protected final String serviceClassName;

		}

	}

}