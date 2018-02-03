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

package com.liferay.portal.cluster.multiple.internal.jgroups;

<<<<<<< HEAD
import com.liferay.petra.string.CharPool;
import com.liferay.portal.cluster.multiple.internal.ClusterChannel;
import com.liferay.portal.cluster.multiple.internal.ClusterChannelFactory;
import com.liferay.portal.cluster.multiple.internal.ClusterReceiver;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
=======
import com.liferay.portal.cluster.multiple.internal.ClusterChannel;
import com.liferay.portal.cluster.multiple.internal.ClusterChannelFactory;
import com.liferay.portal.cluster.multiple.internal.ClusterReceiver;
import com.liferay.portal.cluster.multiple.internal.constants.ClusterPropsKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
>>>>>>> compatible
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.SocketUtil;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.StringBundler;
=======
import com.liferay.portal.kernel.util.StringUtil;
>>>>>>> compatible
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.net.InetAddress;
import java.net.NetworkInterface;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tina Tian
 */
@Component(immediate = true, service = ClusterChannelFactory.class)
public class JGroupsClusterChannelFactory implements ClusterChannelFactory {

	@Override
	public ClusterChannel createClusterChannel(
<<<<<<< HEAD
		String channleLogicName, String channelProperties, String clusterName,
		ClusterReceiver clusterReceiver) {

		return new JGroupsClusterChannel(
			channleLogicName, channelProperties, clusterName, clusterReceiver,
			_bindInetAddress);
=======
		String channelProperties, String clusterName,
		ClusterReceiver clusterReceiver) {

		return new JGroupsClusterChannel(
			channelProperties, clusterName, clusterReceiver, _bindInetAddress);
>>>>>>> compatible
	}

	@Override
	public InetAddress getBindInetAddress() {
		return _bindInetAddress;
	}

	@Override
	public NetworkInterface getBindNetworkInterface() {
		return _bindNetworkInterface;
	}

	@Activate
	@Modified
	protected synchronized void activate(Map<String, Object> properties) {
		if (!GetterUtil.getBoolean(
				_props.get(PropsKeys.CLUSTER_LINK_ENABLED))) {

			return;
		}

<<<<<<< HEAD
		initSystemProperties(
			_props.getArray(PropsKeys.CLUSTER_LINK_CHANNEL_SYSTEM_PROPERTIES));

		initBindAddress(
			GetterUtil.getString(
				_props.get(PropsKeys.CLUSTER_LINK_AUTODETECT_ADDRESS)));
=======
		String[] channelSystemPropertiesArray = null;

		String channelSystemProperties = GetterUtil.getString(
			properties.get(ClusterPropsKeys.CHANNEL_SYSTEM_PROPERTIES));

		if (Validator.isNull(channelSystemProperties)) {
			channelSystemPropertiesArray = _props.getArray(
				PropsKeys.CLUSTER_LINK_CHANNEL_SYSTEM_PROPERTIES);
		}
		else {
			channelSystemPropertiesArray = StringUtil.split(
				channelSystemProperties);
		}

		initSystemProperties(channelSystemPropertiesArray);

		String autodetectAddress = GetterUtil.getString(
			properties.get(ClusterPropsKeys.AUTODETECT_ADDRESS));

		if (Validator.isNull(autodetectAddress)) {
			autodetectAddress = GetterUtil.getString(
				_props.get(PropsKeys.CLUSTER_LINK_AUTODETECT_ADDRESS));
		}

		initBindAddress(autodetectAddress);
>>>>>>> compatible
	}

	protected void initBindAddress(String autodetectAddress) {
		if (Validator.isNull(autodetectAddress)) {
			return;
		}

		String host = autodetectAddress;
		int port = 80;

		int index = autodetectAddress.indexOf(CharPool.COLON);

		if (index != -1) {
			host = autodetectAddress.substring(0, index);
			port = GetterUtil.getInteger(
				autodetectAddress.substring(index + 1), port);
		}

		if (_log.isInfoEnabled()) {
			_log.info(
<<<<<<< HEAD
				StringBundler.concat(
					"Autodetecting JGroups outgoing IP address and interface ",
					"for ", host, ":", String.valueOf(port)));
=======
				"Autodetecting JGroups outgoing IP address and interface for " +
					host + ":" + port);
>>>>>>> compatible
		}

		try {
			SocketUtil.BindInfo bindInfo = SocketUtil.getBindInfo(host, port);

			_bindInetAddress = bindInfo.getInetAddress();

			_bindNetworkInterface = bindInfo.getNetworkInterface();
		}
		catch (IOException e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to detect bind address for JGroups, using " +
						"loopback");

				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
			}

			_bindInetAddress = InetAddress.getLoopbackAddress();

			try {
				_bindNetworkInterface = NetworkInterface.getByInetAddress(
					_bindInetAddress);
			}
			catch (IOException ie) {
				_log.error("Unable to bind to lopoback interface", ie);
			}
		}

		if (_log.isInfoEnabled()) {
			String hostAddress = _bindInetAddress.getHostAddress();
			String name = _bindNetworkInterface.getName();

			_log.info(
<<<<<<< HEAD
				StringBundler.concat(
					"Setting JGroups outgoing IP address to ", hostAddress,
					" and interface to ", name));
=======
				"Setting JGroups outgoing IP address to " + hostAddress +
					" and interface to " + name);
>>>>>>> compatible
		}
	}

	protected void initSystemProperties(String[] channelSystemPropertiesArray) {
		for (String channelSystemProperty : channelSystemPropertiesArray) {
			int index = channelSystemProperty.indexOf(CharPool.COLON);

			if (index == -1) {
				continue;
			}

			String key = channelSystemProperty.substring(0, index);
			String value = channelSystemProperty.substring(index + 1);

			System.setProperty(key, value);

			if (_log.isDebugEnabled()) {
				_log.debug(
<<<<<<< HEAD
					StringBundler.concat(
						"Setting system property {key=", key, ", value=", value,
						"}"));
=======
					"Setting system property {key=" + key + ", value=" + value +
						"}");
>>>>>>> compatible
			}
		}
	}

	@Reference(unbind = "-")
	protected void setProps(Props props) {
		_props = props;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JGroupsClusterChannelFactory.class);

	private InetAddress _bindInetAddress;
	private NetworkInterface _bindNetworkInterface;
	private Props _props;

}