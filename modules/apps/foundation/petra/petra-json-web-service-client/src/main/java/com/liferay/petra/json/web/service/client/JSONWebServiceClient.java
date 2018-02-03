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

package com.liferay.petra.json.web.service.client;

import aQute.bnd.annotation.ProviderType;

<<<<<<< HEAD
import com.fasterxml.jackson.databind.Module;

import java.security.KeyStore;

import java.util.List;
=======
import java.security.KeyStore;

>>>>>>> compatible
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@ProviderType
public interface JSONWebServiceClient {

	public void destroy();

	public String doDelete(String url, Map<String, String> parameters)
<<<<<<< HEAD
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;
=======
		throws JSONWebServiceTransportException;
>>>>>>> compatible

	public String doDelete(
			String url, Map<String, String> parameters,
			Map<String, String> headers)
<<<<<<< HEAD
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doDelete(String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doGet(String url, Map<String, String> parameters)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;
=======
		throws JSONWebServiceTransportException;

	public String doGet(String url, Map<String, String> parameters)
		throws JSONWebServiceTransportException;
>>>>>>> compatible

	public String doGet(
			String url, Map<String, String> parameters,
			Map<String, String> headers)
<<<<<<< HEAD
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doGet(String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public <V, T> List<V> doGetToList(
			Class<T> clazz, String url, Map<String, String> parameters,
			Map<String, String> headers)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public <V, T> List<V> doGetToList(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public <T> T doGetToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public String doPost(String url, Map<String, String> parameters)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;
=======
		throws JSONWebServiceTransportException;

	public String doPost(String url, Map<String, String> parameters)
		throws JSONWebServiceTransportException;
>>>>>>> compatible

	public String doPost(
			String url, Map<String, String> parameters,
			Map<String, String> headers)
<<<<<<< HEAD
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doPost(String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doPostAsJSON(String url, Object object)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public String doPostAsJSON(String url, String json)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doPostAsJSON(
			String url, String json, Map<String, String> headers)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public <T> T doPostToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public String doPut(String url, Map<String, String> parameters)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;
=======
		throws JSONWebServiceTransportException;

	public String doPostAsJSON(String url, String json)
		throws JSONWebServiceTransportException;

	public String doPostAsJSON(
			String url, String json, Map<String, String> headers)
		throws JSONWebServiceTransportException;

	public String doPut(String url, Map<String, String> parameters)
		throws JSONWebServiceTransportException;
>>>>>>> compatible

	public String doPut(
			String url, Map<String, String> parameters,
			Map<String, String> headers)
<<<<<<< HEAD
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public String doPut(String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;
=======
		throws JSONWebServiceTransportException;
>>>>>>> compatible

	public String getHostName();

	public int getHostPort();

	public String getProtocol();

<<<<<<< HEAD
	public void registerModule(Module module);

=======
>>>>>>> compatible
	public void resetHttpClient();

	public void setHostName(String hostName);

	public void setHostPort(int hostPort);

	public void setKeyStore(KeyStore keyStore);

	public void setLogin(String login);

<<<<<<< HEAD
	public void setOAuthAccessSecret(String oAuthAccessSecret);

	public void setOAuthAccessToken(String oAuthAccessToken);

	public void setOAuthConsumerKey(String oAuthConsumerKey);

	public void setOAuthConsumerSecret(String oAuthConsumerSecret);

=======
>>>>>>> compatible
	public void setPassword(String password);

	public void setProtocol(String protocol);

}