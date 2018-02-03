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

package com.liferay.portlet.documentlibrary.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author     Bruno Farache
 * @author     Alexander Chow
 * @deprecated As of 7.0.0, moved to {@link
 *             com.liferay.document.library.kernel.document.conversion.DocumentConversionUtil}
 */
@Deprecated
public class DocumentConversionUtil {

	public static File convert(
			String id, InputStream inputStream, String sourceExtension,
			String targetExtension)
		throws IOException {

		return com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.convert(
				id, inputStream, sourceExtension, targetExtension);
	}

	public static void disconnect() {
		com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.disconnect();
	}

	public static String[] getConversions(String extension) {
		return com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.getConversions(extension);
	}

	public static String getFilePath(String id, String targetExtension) {
		return com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.getFilePath(id, targetExtension);
	}

	public static boolean isComparableVersion(String extension) {
<<<<<<< HEAD
		return com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.isComparableVersion(extension);
=======
		boolean enabled = false;

		String periodAndExtension = StringPool.PERIOD.concat(extension);

		for (int i = 0; i < _COMPARABLE_FILE_EXTENSIONS.length; i++) {
			if (StringPool.STAR.equals(_COMPARABLE_FILE_EXTENSIONS[i]) ||
				periodAndExtension.equals(_COMPARABLE_FILE_EXTENSIONS[i])) {

				enabled = true;

				break;
			}
		}

		if (!enabled) {
			return false;
		}

		if (extension.equals("css") || extension.equals("htm") ||
			extension.equals("html") || extension.equals("js") ||
			extension.equals("txt") || extension.equals("xml")) {

			return true;
		}

		try {
			if (isEnabled() && isConvertBeforeCompare(extension)) {
				return true;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
>>>>>>> compatible
	}

	public static boolean isConvertBeforeCompare(String extension) {
		return com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.isConvertBeforeCompare(extension);
	}

	public static boolean isEnabled() {
<<<<<<< HEAD
		return com.liferay.document.library.kernel.document.conversion.
			DocumentConversionUtil.isEnabled();
=======
		try {
			return PrefsPropsUtil.getBoolean(
				PropsKeys.OPENOFFICE_SERVER_ENABLED,
				PropsValues.OPENOFFICE_SERVER_ENABLED);
		}
		catch (Exception e) {
		}

		return false;
	}

	private DocumentConversionUtil() {
		_populateConversionsMap("drawing");
		_populateConversionsMap("presentation");
		_populateConversionsMap("spreadsheet");
		_populateConversionsMap("text");
	}

	private File _convert(
			String id, InputStream inputStream, String sourceExtension,
			String targetExtension)
		throws IOException {

		if (!isEnabled()) {
			return null;
		}

		sourceExtension = _fixExtension(sourceExtension);
		targetExtension = _fixExtension(targetExtension);

		_validate(targetExtension, id);

		String fileName = getFilePath(id, targetExtension);

		File file = new File(fileName);

		if (PropsValues.OPENOFFICE_CACHE_ENABLED && file.exists()) {
			return file;
		}

		DocumentFormatRegistry documentFormatRegistry =
			new DefaultDocumentFormatRegistry();

		DocumentFormat inputDocumentFormat =
			documentFormatRegistry.getFormatByFileExtension(sourceExtension);
		DocumentFormat outputDocumentFormat =
			documentFormatRegistry.getFormatByFileExtension(targetExtension);

		if (inputDocumentFormat == null) {
			throw new SystemException(
				"Conversion is not supported from ." + sourceExtension);
		}
		else if (!inputDocumentFormat.isImportable()) {
			throw new SystemException(
				"Conversion is not supported from " +
					inputDocumentFormat.getName());
		}
		else if (outputDocumentFormat == null) {
			throw new SystemException(
				"Conversion is not supported from " +
					inputDocumentFormat.getName() + " to ." + targetExtension);
		}
		else if (!inputDocumentFormat.isExportableTo(outputDocumentFormat)) {
			throw new SystemException(
				"Conversion is not supported from " +
					inputDocumentFormat.getName() + " to " +
						outputDocumentFormat.getName());
		}

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		DocumentConverter documentConverter = _getDocumentConverter();

		documentConverter.convert(
			inputStream, inputDocumentFormat, unsyncByteArrayOutputStream,
			outputDocumentFormat);

		FileUtil.write(
			file, unsyncByteArrayOutputStream.unsafeGetByteArray(), 0,
			unsyncByteArrayOutputStream.size());

		return file;
	}

	private void _disconnect() {
		if (_openOfficeConnection != null) {
			_openOfficeConnection.disconnect();
		}
	}

	private String _fixExtension(String extension) {
		if (extension.equals("htm")) {
			extension = "html";
		}

		return extension;
	}

	private String[] _getConversions(String extension) {
		extension = _fixExtension(extension);

		String[] conversions = _conversionsMap.get(extension);

		if (conversions == null) {
			conversions = _DEFAULT_CONVERSIONS;
		}
		else {
			if (ArrayUtil.contains(conversions, extension)) {
				List<String> conversionsList = new ArrayList<>();

				for (int i = 0; i < conversions.length; i++) {
					String conversion = conversions[i];

					if (!conversion.equals(extension)) {
						conversionsList.add(conversion);
					}
				}

				conversions = conversionsList.toArray(
					new String[conversionsList.size()]);
			}
		}

		return conversions;
	}

	private DocumentConverter _getDocumentConverter() {
		if ((_openOfficeConnection != null) && (_documentConverter != null)) {
			return _documentConverter;
		}

		String host = PrefsPropsUtil.getString(
			PropsKeys.OPENOFFICE_SERVER_HOST);
		int port = PrefsPropsUtil.getInteger(
			PropsKeys.OPENOFFICE_SERVER_PORT,
			PropsValues.OPENOFFICE_SERVER_PORT);

		if (_isRemoteOpenOfficeHost(host)) {
			_openOfficeConnection = new SocketOpenOfficeConnection(host, port);

			_documentConverter = new StreamOpenOfficeDocumentConverter(
				_openOfficeConnection);
		}
		else {
			_openOfficeConnection = new SocketOpenOfficeConnection(port);

			_documentConverter = new OpenOfficeDocumentConverter(
				_openOfficeConnection);
		}

		return _documentConverter;
	}

	private boolean _isRemoteOpenOfficeHost(String host) {
		if (Validator.isNotNull(host) && !host.equals(_LOCALHOST_IP) &&
			!host.startsWith(_LOCALHOST)) {

			return true;
		}
		else {
			return false;
		}
	}

	private void _populateConversionsMap(String documentFamily) {
		Filter filter = new Filter(documentFamily);

		DocumentFormatRegistry documentFormatRegistry =
			new DefaultDocumentFormatRegistry();

		String[] sourceExtensions = PropsUtil.getArray(
			PropsKeys.OPENOFFICE_CONVERSION_SOURCE_EXTENSIONS, filter);
		String[] targetExtensions = PropsUtil.getArray(
			PropsKeys.OPENOFFICE_CONVERSION_TARGET_EXTENSIONS, filter);

		for (String sourceExtension : sourceExtensions) {
			List<String> conversions = new SortedArrayList<>();

			DocumentFormat sourceDocumentFormat =
				documentFormatRegistry.getFormatByFileExtension(
					sourceExtension);

			if (sourceDocumentFormat == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("Invalid source extension " + sourceExtension);
				}

				continue;
			}

			for (String targetExtension : targetExtensions) {
				DocumentFormat targetDocumentFormat =
					documentFormatRegistry.getFormatByFileExtension(
						targetExtension);

				if (targetDocumentFormat == null) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Invalid target extension " + targetDocumentFormat);
					}

					continue;
				}

				if (sourceDocumentFormat.isExportableTo(targetDocumentFormat)) {
					conversions.add(targetExtension);
				}
			}

			if (conversions.isEmpty()) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"There are no conversions supported from " +
							sourceExtension);
				}
			}
			else {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Conversions supported from " + sourceExtension +
							" to " + conversions);
				}

				_conversionsMap.put(
					sourceExtension,
					conversions.toArray(new String[conversions.size()]));
			}
		}
>>>>>>> compatible
	}

}