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

package com.liferay.portal.events;

import com.liferay.portal.action.TCKStrutsAction;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.servlet.filters.invoker.FilterMapping;
import com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterConfig;
import com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterHelper;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.ThreadUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.servlet.filters.tck.TCKAutoLoginFilter;
import com.liferay.portal.struts.StrutsActionRegistryUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import java.nio.charset.Charset;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author Matthew Tambara
 */
public class TCKHandshakeAction extends SimpleAction {

	@Override
	public void run(String[] ids) {
		Thread handshakeServerThread = new Thread(
			new HandshakeServerRunnable(), "Handshake server thread");

		handshakeServerThread.setDaemon(true);

		handshakeServerThread.start();
	}

	private static Log _log = LogFactoryUtil.getLog(TCKHandshakeAction.class);

	private static class HandshakeServerRunnable implements Runnable {

		@Override
		public void run() {
			long startTime = System.currentTimeMillis();

			ServerSocket serverSocket = null;

			try {
				serverSocket = new ServerSocket(
					_PORTLET_TCK_BRIDGE_HANDSHAKE_PORT);

				serverSocket.setSoTimeout(100);

				while (!Thread.interrupted()) {
					Socket socket = null;

					UnsyncBufferedReader unsyncBufferedReader = null;

					OutputStream outputStream = null;

					try {
						socket = serverSocket.accept();

						unsyncBufferedReader = new UnsyncBufferedReader(
							new InputStreamReader(socket.getInputStream()));

						Matcher matcher = _COMMAND_PATTERN.matcher(
							unsyncBufferedReader.readLine());

						if (!matcher.find()) {
							continue;
						}

						String command = matcher.group(1);

						if (command.equals("activate")) {
							_activate();

							for (String servletContextName : StringUtil.split(
									matcher.group(2))) {

								_waitForDeployment(
									servletContextName, startTime,
									_PORTLET_TCK_BRIDGE_HANDSHAKE_TIMEOUT *
										Time.SECOND);
							}
						}
						else if (command.equals("deactivate")) {
							_deactivate();
						}

						outputStream = socket.getOutputStream();

						outputStream.write(_RESPONSE);
					}
					catch (SocketTimeoutException ste) {
					}
					finally {
						try {
							if (outputStream != null) {
								outputStream.close();
							}

							if (unsyncBufferedReader != null) {
								unsyncBufferedReader.close();
							}

							if (socket != null) {
								socket.close();
							}
						}
						catch (IOException ioe) {
							_log.error(ioe, ioe);
						}
					}
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
			finally {
				if (serverSocket != null) {
					try {
						serverSocket.close();
					}
					catch (IOException ioe) {
						throw new RuntimeException(ioe);
					}
				}
			}
		}

		private void _activate() throws ServletException {
			ServletContext servletContext = ServletContextPool.get(
				PortalUtil.getPathContext());

			Filter filter = new TCKAutoLoginFilter();

			FilterConfig filterConfig = new InvokerFilterConfig(
				servletContext, _FILTER_NAME,
				Collections.<String, String>emptyMap());

			filter.init(filterConfig);

			InvokerFilterHelper invokerFilterHelper =
				(InvokerFilterHelper)servletContext.getAttribute(
					InvokerFilterHelper.class.getName());

			invokerFilterHelper.registerFilter(_FILTER_NAME, filter);

			invokerFilterHelper.registerFilterMapping(
				new FilterMapping(
					filter, filterConfig, Collections.singletonList("/*"),
					Collections.<String>emptyList()), _FILTER_NAME, false);

			StrutsActionRegistryUtil.register(_PATH, new TCKStrutsAction());
		}

		private static void _deactivate() {
			ServletContext servletContext = ServletContextPool.get(
				PortalUtil.getPathContext());

			InvokerFilterHelper invokerFilterHelper =
				(InvokerFilterHelper)servletContext.getAttribute(
					InvokerFilterHelper.class.getName());

			StrutsActionRegistryUtil.unregister(_PATH);

			invokerFilterHelper.registerFilter(_FILTER_NAME, null);
		}

		private void _waitForDeployment(
			String servletContextName, long startTime, long timeout) {

			while ((System.currentTimeMillis() - startTime) < timeout) {
				ServletContext serviceContext = ServletContextPool.get(
					servletContextName);

				if (serviceContext == null) {
					try {
						Thread.sleep(100);
					}
					catch (InterruptedException ie) {
					}
				}
				else {
					return;
				}
			}

			_log.error("Timeout on waiting " + servletContextName);

			_log.error(ThreadUtil.threadDump());
		}

		private static final Pattern _COMMAND_PATTERN = Pattern.compile(
			"\\?(\\w+)=(.*)\\s");

		private static final String _FILTER_NAME = "TCK Auto Login Filter";

		private static final String _PATH = "/portal/tck";

		private static final byte[] _RESPONSE = "HTTP/1.1 200 OK".getBytes(
			Charset.defaultCharset());

		private static final int _PORTLET_TCK_BRIDGE_HANDSHAKE_PORT =
			GetterUtil.getInteger(
				PropsUtil.get("portlet.tck.bridge.handshake.port"));

		private static final long _PORTLET_TCK_BRIDGE_HANDSHAKE_TIMEOUT =
			GetterUtil.getLong(
				PropsUtil.get("portlet.tck.bridge.handshake.timeout"));

	}

}