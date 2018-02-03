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

package com.liferay.css.builder;

<<<<<<< HEAD
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import com.liferay.css.builder.internal.util.CSSBuilderUtil;
import com.liferay.css.builder.internal.util.FileUtil;
=======
import com.liferay.portal.kernel.regex.PatternFactory;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.tools.ArgumentsUtil;
>>>>>>> compatible
import com.liferay.rtl.css.RTLCSSConverter;
import com.liferay.sass.compiler.SassCompiler;
import com.liferay.sass.compiler.SassCompilerException;
import com.liferay.sass.compiler.jni.internal.JniSassCompiler;
import com.liferay.sass.compiler.ruby.internal.RubySassCompiler;

import java.io.File;
import java.io.IOException;

<<<<<<< HEAD
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

=======
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.tools.ant.DirectoryScanner;

>>>>>>> compatible
/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Eduardo Lundgren
 * @author Shuyang Zhou
 * @author David Truong
<<<<<<< HEAD
 * @author Christopher Bryan Boyd
=======
>>>>>>> compatible
 */
public class CSSBuilder implements AutoCloseable {

	public static void main(String[] args) throws Exception {
<<<<<<< HEAD
		CSSBuilderArgs cssBuilderArgs = new CSSBuilderArgs();

		JCommander jCommander = new JCommander(cssBuilderArgs);

		try {
			File jarFile = FileUtil.getJarFile();

			if (jarFile.isFile()) {
				jCommander.setProgramName("java -jar " + jarFile.getName());
			}
			else {
				jCommander.setProgramName(CSSBuilder.class.getName());
			}

			jCommander.parse(args);

			if (cssBuilderArgs.isHelp()) {
				_printHelp(jCommander);
			}
			else {
				try (CSSBuilder cssBuilder = new CSSBuilder(cssBuilderArgs)) {
					cssBuilder.execute();
				}
			}
		}
		catch (ParameterException pe) {
			System.err.println(pe.getMessage());

			_printHelp(jCommander);

			System.exit(1);
		}
	}

	public CSSBuilder(CSSBuilderArgs cssBuilderArgs) throws Exception {
		_cssBuilderArgs = cssBuilderArgs;

		File importDir = _cssBuilderArgs.getImportDir();

		if (importDir != null) {
			if (importDir.isFile()) {
				importDir = _unzipImport(importDir);

				_cleanImportDir = true;
			}
			else {
				_cleanImportDir = false;
			}

			_importDirName = importDir.getCanonicalPath();
		}
		else {
			_cleanImportDir = false;
			_importDirName = null;
		}

		List<String> rtlExcludedPathRegexps =
			_cssBuilderArgs.getRtlExcludedPathRegexps();

		_rtlExcludedPathPatterns = new Pattern[rtlExcludedPathRegexps.size()];

		for (int i = 0; i < rtlExcludedPathRegexps.size(); i++) {
			_rtlExcludedPathPatterns[i] = Pattern.compile(
				rtlExcludedPathRegexps.get(i));
		}

		_initSassCompiler(_cssBuilderArgs.getSassCompilerClassName());
=======
		Map<String, String> arguments = ArgumentsUtil.parseArguments(args);

		List<String> dirNames = new ArrayList<>();

		String dirName = GetterUtil.getString(
			arguments.get("sass.dir"), CSSBuilderArgs.DIR_NAME);

		if (Validator.isNotNull(dirName)) {
			dirNames.add(dirName);
		}
		else {
			for (int i = 0;; i++) {
				dirName = arguments.get("sass.dir." + i);

				if (Validator.isNotNull(dirName)) {
					dirNames.add(dirName);
				}
				else {
					break;
				}
			}
		}

		boolean appendCssImportTimestamps = GetterUtil.getBoolean(
			arguments.get("sass.append.css.import.timestamps"),
			CSSBuilderArgs.APPEND_CSS_IMPORT_TIMESTAMPS);
		String docrootDirName = GetterUtil.getString(
			arguments.get("sass.docroot.dir"), CSSBuilderArgs.DOCROOT_DIR_NAME);
		boolean generateSourceMap = GetterUtil.getBoolean(
			arguments.get("sass.generate.source.map"));
		String outputDirName = GetterUtil.getString(
			arguments.get("sass.output.dir"), CSSBuilderArgs.OUTPUT_DIR_NAME);

		String portalCommonPath = arguments.get("sass.portal.common.path");

		if (Validator.isNull(portalCommonPath)) {
			portalCommonPath = arguments.get("sass.portal.common.dir");
		}

		int precision = GetterUtil.getInteger(
			arguments.get("sass.precision"), CSSBuilderArgs.PRECISION);
		String[] rtlExcludedPathRegexps = StringUtil.split(
			arguments.get("sass.rtl.excluded.path.regexps"));
		String sassCompilerClassName = arguments.get(
			"sass.compiler.class.name");

		try (CSSBuilder cssBuilder = new CSSBuilder(
				appendCssImportTimestamps, docrootDirName, generateSourceMap,
				outputDirName, portalCommonPath, precision,
				rtlExcludedPathRegexps, sassCompilerClassName)) {

			cssBuilder.execute(dirNames);
		}
		catch (Exception e) {
			ArgumentsUtil.processMainException(arguments, e);
		}
	}

	public CSSBuilder(
			boolean appendCssImportTimestamps, String docrootDirName,
			boolean generateSourceMap, String outputDirName,
			String portalCommonPath, int precision,
			String[] rtlExcludedPathRegexps, String sassCompilerClassName)
		throws Exception {

		File portalCommonDir = new File(portalCommonPath);

		if (portalCommonDir.isFile()) {
			portalCommonDir = _unzipPortalCommon(portalCommonDir);

			_cleanPortalCommonDir = true;
		}
		else {
			_cleanPortalCommonDir = false;
		}

		_appendCssImportTimestamps = appendCssImportTimestamps;
		_docrootDirName = docrootDirName;
		_generateSourceMap = generateSourceMap;
		_outputDirName = outputDirName;
		_portalCommonDirName = portalCommonDir.getCanonicalPath();
		_precision = precision;
		_rtlExcludedPathPatterns = PatternFactory.compile(
			rtlExcludedPathRegexps);

		_initSassCompiler(sassCompilerClassName);
	}

	public CSSBuilder(
			String docrootDirName, boolean generateSourceMap,
			String outputDirName, String portalCommonPath, int precision,
			String[] rtlExcludedPathRegexps, String sassCompilerClassName)
		throws Exception {

		this(
			true, docrootDirName, generateSourceMap, outputDirName,
			portalCommonPath, precision, rtlExcludedPathRegexps,
			sassCompilerClassName);
>>>>>>> compatible
	}

	@Override
	public void close() throws Exception {
<<<<<<< HEAD
		if (_cleanImportDir) {
			FileUtil.deltree(Paths.get(_importDirName));
=======
		if (_cleanPortalCommonDir) {
			_deltree(_portalCommonDirName);
>>>>>>> compatible
		}

		_sassCompiler.close();
	}

<<<<<<< HEAD
	public void execute() throws Exception {
		List<String> fileNames = new ArrayList<>();

		File baseDir = _cssBuilderArgs.getBaseDir();

		if (!baseDir.exists()) {
			throw new IOException("Directory " + baseDir + " does not exist");
		}

		for (String dirName : _cssBuilderArgs.getDirNames()) {
			List<String> sassFileNames = _collectSassFiles(dirName, baseDir);

			fileNames.addAll(sassFileNames);
		}

		if (fileNames.isEmpty()) {
			System.out.println("There are no files to compile");

			return;
=======
	public void execute(List<String> dirNames) throws Exception {
		List<String> fileNames = new ArrayList<>();

		for (String dirName : dirNames) {
			_collectSassFiles(fileNames, dirName, _docrootDirName);
>>>>>>> compatible
		}

		for (String fileName : fileNames) {
			long startTime = System.currentTimeMillis();

			_parseSassFile(fileName);

			System.out.println(
				"Parsed " + fileName + " in " +
<<<<<<< HEAD
					String.valueOf(System.currentTimeMillis() - startTime) +
						"ms");
=======
					(System.currentTimeMillis() - startTime) + "ms");
>>>>>>> compatible
		}
	}

	public boolean isRtlExcludedPath(String filePath) {
		for (Pattern pattern : _rtlExcludedPathPatterns) {
			Matcher matcher = pattern.matcher(filePath);

			if (matcher.matches()) {
				return true;
			}
		}

		return false;
	}

<<<<<<< HEAD
	private static void _printHelp(JCommander jCommander) throws Exception {
		jCommander.usage();
	}

	private List<String> _collectSassFiles(String dirName, File baseDir)
		throws Exception {

		List<String> fileNames = new ArrayList<>();

		String basedir = String.valueOf(new File(baseDir, dirName));

		String[] scssFiles = _getScssFiles(basedir);

		if (!_isModified(basedir, scssFiles)) {
			long oldestSassModifiedTime = _getOldestModifiedTime(
				basedir, scssFiles);

			String[] scssFragments = _getScssFragments(basedir);

			long newestFragmentModifiedTime = _getNewestModifiedTime(
				basedir, scssFragments);

			if (oldestSassModifiedTime > newestFragmentModifiedTime) {
				return fileNames;
			}
		}

		for (String fileName : scssFiles) {
=======
	private void _collectSassFiles(
			List<String> fileNames, String dirName, String docrootDirName)
		throws Exception {

		DirectoryScanner directoryScanner = new DirectoryScanner();

		String basedir = docrootDirName.concat(dirName);

		directoryScanner.setBasedir(basedir);

		directoryScanner.setExcludes(
			new String[] {
				"**\\_*.scss", "**\\_diffs\\**", "**\\.sass-cache*\\**",
				"**\\.sass_cache_*\\**", "**\\_sass_cache_*\\**",
				"**\\_styled\\**", "**\\_unstyled\\**", "**\\css\\aui\\**",
				"**\\tmp\\**"
			});
		directoryScanner.setIncludes(new String[] {"**\\*.scss"});

		directoryScanner.scan();

		String[] fileNamesArray = directoryScanner.getIncludedFiles();

		if (!_isModified(basedir, fileNamesArray)) {
			return;
		}

		for (String fileName : fileNamesArray) {
>>>>>>> compatible
			if (fileName.contains("_rtl")) {
				continue;
			}

			fileNames.add(_normalizeFileName(dirName, fileName));
		}
<<<<<<< HEAD

		return fileNames;
	}

	private long _getNewestModifiedTime(String baseDir, String[] fileNames) {
		Stream<String> stream = Stream.of(fileNames);

		return stream.map(
			fileName -> Paths.get(baseDir, fileName)
		).map(
			FileUtil::getLastModifiedTime
		).max(
			Comparator.naturalOrder()
		).orElse(
			Long.MIN_VALUE
		);
	}

	private long _getOldestModifiedTime(String baseDir, String[] fileNames) {
		Stream<String> stream = Stream.of(fileNames);

		return stream.map(
			fileName -> Paths.get(baseDir, fileName)
		).map(
			FileUtil::getLastModifiedTime
		).min(
			Comparator.naturalOrder()
		).orElse(
			Long.MIN_VALUE
		);
=======
	}

	private void _deltree(String dirName) throws IOException {
		Files.walkFileTree(
			Paths.get(dirName),
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult postVisitDirectory(
						Path dirPath, IOException ioe)
					throws IOException {

					Files.delete(dirPath);

					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(
						Path path, BasicFileAttributes basicFileAttributes)
					throws IOException {

					Files.delete(path);

					return FileVisitResult.CONTINUE;
				}

			});
>>>>>>> compatible
	}

	private String _getRtlCss(String fileName, String css) throws Exception {
		String rtlCss = css;

		try {
			if (_rtlCSSConverter == null) {
				_rtlCSSConverter = new RTLCSSConverter();
			}

			rtlCss = _rtlCSSConverter.process(rtlCss);
		}
		catch (Exception e) {
			System.out.println(
<<<<<<< HEAD
				"Unable to generate RTL version for " + fileName + ", " +
					e.getMessage());
=======
				"Unable to generate RTL version for " + fileName +
					StringPool.COMMA_AND_SPACE + e.getMessage());
>>>>>>> compatible
		}

		return rtlCss;
	}

<<<<<<< HEAD
	private String[] _getScssFiles(String baseDir) throws IOException {
		String[] includes = {"**/*.scss"};

		String[] excludes = Arrays.copyOf(_EXCLUDES, _EXCLUDES.length + 1);

		excludes[excludes.length - 1] = "**/_*.scss";

		return FileUtil.getFilesFromDirectory(baseDir, includes, excludes);
	}

	private String[] _getScssFragments(String baseDir) throws IOException {
		String[] includes = {"**/_*.scss"};

		return FileUtil.getFilesFromDirectory(baseDir, includes, _EXCLUDES);
	}

	private void _initSassCompiler(String sassCompilerClassName)
		throws Exception {

		int precision = _cssBuilderArgs.getPrecision();

		if ((sassCompilerClassName == null) ||
			sassCompilerClassName.isEmpty() ||
=======
	private void _initSassCompiler(String sassCompilerClassName)
		throws Exception {

		if (Validator.isNull(sassCompilerClassName) ||
>>>>>>> compatible
			sassCompilerClassName.equals("jni")) {

			try {
				System.setProperty("jna.nosys", Boolean.TRUE.toString());

<<<<<<< HEAD
				_sassCompiler = new JniSassCompiler(precision);
=======
				_sassCompiler = new JniSassCompiler(_precision);
>>>>>>> compatible

				System.out.println("Using native Sass compiler");
			}
			catch (Throwable t) {
				System.out.println(
					"Unable to load native compiler, falling back to Ruby");

<<<<<<< HEAD
				_sassCompiler = new RubySassCompiler(precision);
=======
				_sassCompiler = new RubySassCompiler(_precision);
>>>>>>> compatible
			}
		}
		else {
			try {
<<<<<<< HEAD
				_sassCompiler = new RubySassCompiler(precision);
=======
				_sassCompiler = new RubySassCompiler(_precision);
>>>>>>> compatible

				System.out.println("Using Ruby Sass compiler");
			}
			catch (Exception e) {
				System.out.println(
					"Unable to load Ruby compiler, falling back to native");

				System.setProperty("jna.nosys", Boolean.TRUE.toString());

<<<<<<< HEAD
				_sassCompiler = new JniSassCompiler(precision);
=======
				_sassCompiler = new JniSassCompiler(_precision);
>>>>>>> compatible
			}
		}
	}

	private boolean _isModified(String dirName, String[] fileNames)
		throws Exception {

		for (String fileName : fileNames) {
			if (fileName.contains("_rtl")) {
				continue;
			}

			fileName = _normalizeFileName(dirName, fileName);

			File file = new File(fileName);
			File cacheFile = CSSBuilderUtil.getOutputFile(
<<<<<<< HEAD
				fileName, _cssBuilderArgs.getOutputDirName());
=======
				fileName, _outputDirName);
>>>>>>> compatible

			if (file.lastModified() != cacheFile.lastModified()) {
				return true;
			}
		}

		return false;
	}

	private String _normalizeFileName(String dirName, String fileName) {
<<<<<<< HEAD
		fileName = dirName + "/" + fileName;

		fileName = fileName.replace('\\', '/');
		fileName = fileName.replace("//", "/");
=======
		fileName = StringUtil.replace(
			dirName + StringPool.SLASH + fileName,
			new String[] {StringPool.BACK_SLASH, StringPool.DOUBLE_SLASH},
			new String[] {StringPool.SLASH, StringPool.SLASH});
>>>>>>> compatible

		return fileName;
	}

	private String _parseSass(String fileName) throws SassCompilerException {
<<<<<<< HEAD
		File sassFile = new File(_cssBuilderArgs.getBaseDir(), fileName);

		Path path = sassFile.toPath();

		String filePath = path.toString();
=======
		String filePath = _docrootDirName.concat(fileName);
>>>>>>> compatible

		String cssBasePath = filePath;

		int pos = filePath.lastIndexOf("/css/");

		if (pos >= 0) {
			cssBasePath = filePath.substring(0, pos + 4);
		}
		else {
			pos = filePath.lastIndexOf("/resources/");

			if (pos >= 0) {
				cssBasePath = filePath.substring(0, pos + 10);
			}
		}

		String css = _sassCompiler.compileFile(
<<<<<<< HEAD
			filePath, _importDirName + File.pathSeparator + cssBasePath,
			_cssBuilderArgs.isGenerateSourceMap(), filePath + ".map");

		return css;
	}

	private void _parseSassFile(String fileName) throws Exception {
		File file = new File(_cssBuilderArgs.getBaseDir(), fileName);
=======
			filePath, _portalCommonDirName + File.pathSeparator + cssBasePath,
			_generateSourceMap, filePath + ".map");

		return CSSBuilderUtil.parseStaticTokens(css);
	}

	private void _parseSassFile(String fileName) throws Exception {
		File file = new File(_docrootDirName, fileName);
>>>>>>> compatible

		if (!file.exists()) {
			return;
		}

		String ltrContent = _parseSass(fileName);

		_writeOutputFile(fileName, ltrContent, false);

		if (isRtlExcludedPath(fileName)) {
			return;
		}

		String rtlContent = _getRtlCss(fileName, ltrContent);

		String rtlCustomFileName = CSSBuilderUtil.getRtlCustomFileName(
			fileName);

<<<<<<< HEAD
		File rtlCustomFile = new File(
			_cssBuilderArgs.getBaseDir(), rtlCustomFileName);
=======
		File rtlCustomFile = new File(_docrootDirName, rtlCustomFileName);
>>>>>>> compatible

		if (rtlCustomFile.exists()) {
			rtlContent += _parseSass(rtlCustomFileName);
		}

		_writeOutputFile(fileName, rtlContent, true);
	}

<<<<<<< HEAD
	private File _unzipImport(File importFile) throws IOException {
		Path portalCommonCssDirPath = Files.createTempDirectory(
			"cssBuilderImport");

		try (ZipFile zipFile = new ZipFile(importFile)) {
=======
	private File _unzipPortalCommon(File portalCommonFile) throws IOException {
		Path portalCommonCssDirPath = Files.createTempDirectory(
			"portalCommonCss");

		try (ZipFile zipFile = new ZipFile(portalCommonFile)) {
>>>>>>> compatible
			Enumeration<? extends ZipEntry> enumeration = zipFile.entries();

			while (enumeration.hasMoreElements()) {
				ZipEntry zipEntry = enumeration.nextElement();

				String name = zipEntry.getName();

				if (name.endsWith("/") ||
					!name.startsWith("META-INF/resources/")) {

					continue;
				}

				name = name.substring(19);

				Path path = portalCommonCssDirPath.resolve(name);

				Files.createDirectories(path.getParent());

				Files.copy(
					zipFile.getInputStream(zipEntry), path,
					StandardCopyOption.REPLACE_EXISTING);
			}
		}

		return portalCommonCssDirPath.toFile();
	}

<<<<<<< HEAD
	private void _writeOutputFile(String fileName, String content, boolean rtl)
		throws Exception {

		if (_cssBuilderArgs.isAppendCssImportTimestamps()) {
=======
	private void _write(File file, String content) throws Exception {
		File parentFile = file.getParentFile();

		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}

		Path path = Paths.get(file.toURI());

		Files.write(path, content.getBytes(StringPool.UTF8));
	}

	private void _writeOutputFile(String fileName, String content, boolean rtl)
		throws Exception {

		if (_appendCssImportTimestamps) {
>>>>>>> compatible
			content = CSSBuilderUtil.parseCSSImports(content);
		}

		String outputFileName;

<<<<<<< HEAD
		boolean absoluteOutputDir = false;
		String outputFileDirName = _cssBuilderArgs.getOutputDirName();

		if (FileUtil.isAbsolute(outputFileDirName)) {
			absoluteOutputDir = true;
			outputFileDirName = "";
		}

=======
>>>>>>> compatible
		if (rtl) {
			String rtlFileName = CSSBuilderUtil.getRtlCustomFileName(fileName);

			outputFileName = CSSBuilderUtil.getOutputFileName(
<<<<<<< HEAD
				rtlFileName, outputFileDirName, "");
		}
		else {
			outputFileName = CSSBuilderUtil.getOutputFileName(
				fileName, outputFileDirName, "");
		}

		File outputFile;

		if (absoluteOutputDir) {
			outputFile = new File(
				_cssBuilderArgs.getOutputDirName(), outputFileName);
		}
		else {
			outputFile = new File(_cssBuilderArgs.getBaseDir(), outputFileName);
		}

		FileUtil.write(outputFile, content);

		File file = new File(_cssBuilderArgs.getBaseDir(), fileName);
=======
				rtlFileName, _outputDirName, StringPool.BLANK);
		}
		else {
			outputFileName = CSSBuilderUtil.getOutputFileName(
				fileName, _outputDirName, StringPool.BLANK);
		}

		File outputFile = new File(_docrootDirName, outputFileName);

		_write(outputFile, content);

		File file = new File(_docrootDirName, fileName);
>>>>>>> compatible

		outputFile.setLastModified(file.lastModified());
	}

<<<<<<< HEAD
	private static final String[] _EXCLUDES = {
		"**/_diffs/**", "**/.sass-cache*/**", "**/.sass_cache_*/**",
		"**/_sass_cache_*/**", "**/_styled/**", "**/_unstyled/**",
		"**/css/aui/**", "**/tmp/**"
	};

	private static RTLCSSConverter _rtlCSSConverter;

	private final boolean _cleanImportDir;
	private final CSSBuilderArgs _cssBuilderArgs;
	private final String _importDirName;
=======
	private static RTLCSSConverter _rtlCSSConverter;

	private final boolean _appendCssImportTimestamps;
	private final boolean _cleanPortalCommonDir;
	private final String _docrootDirName;
	private final boolean _generateSourceMap;
	private final String _outputDirName;
	private final String _portalCommonDirName;
	private final int _precision;
>>>>>>> compatible
	private final Pattern[] _rtlExcludedPathPatterns;
	private SassCompiler _sassCompiler;

}