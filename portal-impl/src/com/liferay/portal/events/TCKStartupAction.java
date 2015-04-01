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
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.servlet.filters.invoker.FilterMapping;
import com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterConfig;
import com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterHelper;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.servlet.filters.tck.TCKAutoLoginFilter;
import com.liferay.portal.struts.StrutsActionRegistryUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;
import java.io.OutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import java.nio.charset.Charset;

import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 *
 * @author Matthew Tambara
 */

public class TCKStartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		ServletContext servletContext = ServletContextPool.get(
			PortalUtil.getPathContext());

		Filter filter = new TCKAutoLoginFilter();

		FilterConfig filterConfig = new InvokerFilterConfig(
			servletContext, _FILTER_NAME, Collections.EMPTY_MAP);

		try {
			filter.init(filterConfig);
		}
		catch (ServletException se) {
			throw new ActionException(se);
		}

		InvokerFilterHelper invokerFilterHelper =
			(InvokerFilterHelper)servletContext.getAttribute(
				InvokerFilterHelper.class.getName());

		invokerFilterHelper.registerFilter(_FILTER_NAME, filter);

		invokerFilterHelper.registerFilterMapping(
			new FilterMapping(
				filter, filterConfig, Collections.singletonList("/*"),
				Collections.EMPTY_LIST), _FILTER_NAME, false);

		StrutsActionRegistryUtil.register(_PATH, new TCKStrutsAction());

		FutureTask<Void> futureTask = new FutureTask<Void>(
			new HandshakeServerCallable());

		Thread handshakeServerThread = new Thread(
			futureTask, "Handshake server thread");

		handshakeServerThread.setDaemon(true);

		handshakeServerThread.start();
	}

	private static final String _FILTER_NAME = "TCK Auto Login Filter";

	private static final String _PATH = "/portal/tck";

	private static Log _log = LogFactoryUtil.getLog(TCKStartupAction.class);

	private static class HandshakeServerCallable implements Callable<Void> {

		@Override
		public Void call() throws IOException {
			long startTime = System.currentTimeMillis();

			for (String servletContextName : PropsUtil.getArray(
				_SERVLET_CONTEXT_NAMES)) {

				_waitForDeployment(
					servletContextName, startTime,
					GetterUtil.getInteger(
						PropsUtil.get(_TIMEOUT)) * Time.SECOND);
			}

			ServerSocket serverSocket =
				new ServerSocket(
					GetterUtil.getInteger(
						PropsUtil.get(_HANDSHAKE_SERVER_PORT)));

			try {
				serverSocket.setSoTimeout(100);

				while (!Thread.interrupted()) {
					Socket socket = serverSocket.accept();

					OutputStream outputStream = socket.getOutputStream();

					try {
						outputStream.write(
							"Portlet TCK Bridge is ready".getBytes(
								Charset.defaultCharset()));
					}
					catch (SocketTimeoutException ste) {
					}
					finally {
						outputStream.close();

						socket.close();
					}
				}
			}
			finally {
				serverSocket.close();
			}

			return null;
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
		}

		private static final String _SERVLET_CONTEXT_NAMES =
			"servlet.context.names";

		private static final String _TIMEOUT = "tck.handshake.timeout";

		private static final String _HANDSHAKE_SERVER_PORT =
			"handshake.server.port";

	}

}