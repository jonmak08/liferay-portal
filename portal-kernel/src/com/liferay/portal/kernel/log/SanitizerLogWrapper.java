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

package com.liferay.portal.kernel.log;

<<<<<<< HEAD
import com.liferay.petra.string.CharPool;
=======
import com.liferay.portal.kernel.util.CharPool;
>>>>>>> compatible
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SystemProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Polesovsky
 * @author Raymond Augé
 */
public class SanitizerLogWrapper extends LogWrapper {

	public static Log allowCRLF(Log log) {
		if (!(log instanceof SanitizerLogWrapper)) {
			return log;
		}

		SanitizerLogWrapper sanitizerLogWrapper = (SanitizerLogWrapper)log;

		sanitizerLogWrapper = new SanitizerLogWrapper(
			sanitizerLogWrapper.getWrappedLog());

		sanitizerLogWrapper._allowCRLF = true;

		return sanitizerLogWrapper;
	}

	public static void init() {
		if (!_LOG_SANITIZER_ENABLED) {
			return;
		}

		_logSanitizerEscapeHTMLEnabled = GetterUtil.getBoolean(
			SystemProperties.get(PropsKeys.LOG_SANITIZER_ESCAPE_HTML_ENABLED));

		_logSanitizerReplacementCharacter = (char)GetterUtil.getInteger(
			SystemProperties.get(
				PropsKeys.LOG_SANITIZER_REPLACEMENT_CHARACTER));

<<<<<<< HEAD
		for (int i = 0; i < _WHITELIST_CHARACTERS.length; i++) {
			_WHITELIST_CHARACTERS[i] = 1;
=======
		for (int i = 0; i < _whitelistCharacters.length; i++) {
			_whitelistCharacters[i] = 1;
>>>>>>> compatible
		}

		int[] whitelistCharacters = GetterUtil.getIntegerValues(
			StringUtil.split(
				SystemProperties.get(
					PropsKeys.LOG_SANITIZER_WHITELIST_CHARACTERS)));

		for (int whitelistCharacter : whitelistCharacters) {
			if ((whitelistCharacter >= 0) &&
<<<<<<< HEAD
				(whitelistCharacter < _WHITELIST_CHARACTERS.length)) {

				_WHITELIST_CHARACTERS[whitelistCharacter] = 0;
=======
				(whitelistCharacter < _whitelistCharacters.length)) {

				_whitelistCharacters[whitelistCharacter] = 0;
>>>>>>> compatible
			}
			else {
				System.err.println(
					"Unable to register log whitelist character " +
						whitelistCharacter);
			}
		}
	}

	public static boolean isEnabled() {
		return _LOG_SANITIZER_ENABLED;
	}

	public SanitizerLogWrapper(Log log) {
		super(log);

		setLogWrapperClassName(SanitizerLogWrapper.class.getName());
	}

	@Override
	public void debug(Object msg) {
		super.debug(sanitize(msg));
	}

	@Override
	public void debug(Object msg, Throwable t) {
		super.debug(sanitize(msg), sanitize(t));
	}

	@Override
	public void debug(Throwable t) {
		super.debug(sanitize(t));
	}

	@Override
	public void error(Object msg) {
		super.error(sanitize(msg));
	}

	@Override
	public void error(Object msg, Throwable t) {
		super.error(sanitize(msg), sanitize(t));
	}

	@Override
	public void error(Throwable t) {
		super.error(sanitize(t));
	}

	@Override
	public void fatal(Object msg) {
		super.fatal(sanitize(msg));
	}

	@Override
	public void fatal(Object msg, Throwable t) {
		super.fatal(sanitize(msg), sanitize(t));
	}

	@Override
	public void fatal(Throwable t) {
		super.fatal(sanitize(t));
	}

	@Override
	public void info(Object msg) {
		super.info(sanitize(msg));
	}

	@Override
	public void info(Object msg, Throwable t) {
		super.info(sanitize(msg), sanitize(t));
	}

	@Override
	public void info(Throwable t) {
		super.info(sanitize(t));
	}

	@Override
	public void trace(Object msg) {
		super.trace(sanitize(msg));
	}

	@Override
	public void trace(Object msg, Throwable t) {
		super.trace(sanitize(msg), sanitize(t));
	}

	@Override
	public void trace(Throwable t) {
		super.trace(sanitize(t));
	}

	@Override
	public void warn(Object msg) {
		super.warn(sanitize(msg));
	}

	@Override
	public void warn(Object msg, Throwable t) {
		super.warn(sanitize(msg), sanitize(t));
	}

	@Override
	public void warn(Throwable t) {
		super.warn(sanitize(t));
	}

	protected String sanitize(Object obj) {
		if (obj == null) {
			return null;
		}

		String message = obj.toString();

		return sanitize(message, message);
	}

	protected String sanitize(String message, String defaultResult) {
		if (message == null) {
			return null;
		}

		char[] chars = message.toCharArray();
		boolean hasCRLF = false;
		boolean hasLessThanCharacter = false;
		boolean sanitized = false;

		for (int i = 0; i < chars.length; i++) {
			int c = chars[i];

			if (_allowCRLF &&
				((c == CharPool.NEW_LINE) || (c == CharPool.RETURN))) {

				hasCRLF = true;

				continue;
			}

<<<<<<< HEAD
			if ((c >= 0) && (c < _WHITELIST_CHARACTERS.length) &&
				(_WHITELIST_CHARACTERS[c] != 0)) {
=======
			if ((c >= 0) && (c < _whitelistCharacters.length) &&
				(_whitelistCharacters[c] != 0)) {
>>>>>>> compatible

				chars[i] = _logSanitizerReplacementCharacter;
				sanitized = true;
			}

			if (c == CharPool.LESS_THAN) {
				hasLessThanCharacter = true;
			}
		}

		boolean escapeHTML = false;

		if (_logSanitizerEscapeHTMLEnabled && hasLessThanCharacter) {
			escapeHTML = true;
		}

		if (sanitized || escapeHTML || hasCRLF) {
			String sanitizedMessage = new String(chars);

			if (escapeHTML) {
				sanitizedMessage = StringUtil.replace(
					sanitizedMessage, CharPool.LESS_THAN, _LESS_THAN_ESCAPED);
			}

			if (sanitized) {
				sanitizedMessage = sanitizedMessage.concat(_SANITIZED);
			}

			if (hasCRLF) {
				sanitizedMessage = CRLF_WARNING.concat(sanitizedMessage);
			}

			return sanitizedMessage;
		}

		return defaultResult;
	}

	protected Throwable sanitize(Throwable throwable) {
		List<Throwable> throwables = new ArrayList<>();

		Throwable tempThrowable = throwable;

		while (tempThrowable != null) {
			throwables.add(tempThrowable);

			tempThrowable = tempThrowable.getCause();
		}

		Throwable resultThrowable = null;

		boolean sanitized = false;

		for (int i = throwables.size() - 1; i > -1; i--) {
			Throwable curThrowable = throwables.get(i);

			String message = curThrowable.toString();

			String sanitizedMessage = sanitize(message, null);

			if (!sanitized && (sanitizedMessage == null)) {
				resultThrowable = curThrowable;

				continue;
			}

			if (sanitizedMessage == null) {
				sanitizedMessage = message;
			}

			sanitized = true;

			resultThrowable = new LogSanitizerException(
				sanitizedMessage, curThrowable.getStackTrace(),
				resultThrowable);
		}

		return resultThrowable;
	}

	protected static final String CRLF_WARNING =
		"SanitizerLogWrapper warning: Following message contains CRLF " +
			"characters\n";

	private static final String _LESS_THAN_ESCAPED = "&lt;";

	private static final boolean _LOG_SANITIZER_ENABLED = GetterUtil.getBoolean(
		SystemProperties.get(PropsKeys.LOG_SANITIZER_ENABLED));

	private static final String _SANITIZED = " [Sanitized]";

<<<<<<< HEAD
	private static final int[] _WHITELIST_CHARACTERS = new int[128];

	private static boolean _logSanitizerEscapeHTMLEnabled;
	private static char _logSanitizerReplacementCharacter = CharPool.UNDERLINE;
=======
	private static boolean _logSanitizerEscapeHTMLEnabled;
	private static char _logSanitizerReplacementCharacter = CharPool.UNDERLINE;
	private static final int[] _whitelistCharacters = new int[128];
>>>>>>> compatible

	private boolean _allowCRLF;

}