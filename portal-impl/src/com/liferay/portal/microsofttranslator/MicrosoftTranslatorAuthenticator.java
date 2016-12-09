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

package com.liferay.portal.microsofttranslator;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Hugo Huijser
 */
public class MicrosoftTranslatorAuthenticator {

	public MicrosoftTranslatorAuthenticator() {
		init(true);
	}

	public MicrosoftTranslatorAuthenticator(String clientSecret) {
		_clientSecret = clientSecret;

		init(true);
	}

	public String getAccessToken() {
		init(false);

		return _accessToken;
	}

	public String getError() {
		return _error;
	}

	public void init(boolean manual) {
		if (manual || isStale()) {
			doInit();
		}
	}

	protected void doInit() {
		_accessToken = null;
		_error = null;

		if (Validator.isNull(_clientSecret)) {
			_clientSecret = PropsValues.MICROSOFT_TRANSLATOR_CLIENT_SECRET;
		}

		try {
			Http.Options options = new Http.Options();

			options.addHeader(HttpHeaders.CONTENT_LENGTH, "0");
			options.addHeader("Ocp-Apim-Subscription-Key", _clientSecret);

			options.setLocation(_URL);

			options.setPost(true);

			String content = HttpUtil.URLtoString(options);

			Http.Response response = options.getResponse();

			int responseCode = response.getResponseCode();

			if (responseCode != HttpServletResponse.SC_OK) {
				_error = content;

				if (_log.isInfoEnabled()) {
					_log.info("Unable to initialize access token: " + _error);
				}
			}
			else {
				_accessToken = content;
			}

			if (_log.isInfoEnabled() && (_accessToken != null)) {
				_log.info("Access token " + _accessToken);
			}

			_initTime = System.currentTimeMillis();
		}
		catch (Exception e) {
			if (_log.isInfoEnabled()) {
				_log.info("Unable to initialize authentication token", e);
			}
		}
	}

	protected boolean isStale() {
		if ((_initTime + _EXPIRE_TIME) > System.currentTimeMillis()) {
			return false;
		}
		else {
			return true;
		}
	}

	private static final long _EXPIRE_TIME = 10 * Time.MINUTE;

	private static final String _URL =
		"https://api.cognitive.microsoft.com/sts/v1.0/issueToken";

	private static Log _log = LogFactoryUtil.getLog(
		MicrosoftTranslatorAuthenticator.class);

	private String _accessToken;
	private String _clientSecret;
	private String _error;
	private long _initTime;

}