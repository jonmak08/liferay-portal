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

package com.liferay.portal.search.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.ReusableAnalyzerBase;
import org.apache.lucene.util.Version;

/**
 * @author Norbert Kocsis
 */
public class UpperCaseAnalyzer extends ReusableAnalyzerBase {

	public UpperCaseAnalyzer(Version matchVersion) {
		this.matchVersion = matchVersion;
	}

	@Deprecated
	public UpperCaseAnalyzer() {
		this(Version.LUCENE_30);
	}

	@Override
	protected TokenStreamComponents createComponents(
		String fieldName, Reader reader) {

		return new TokenStreamComponents(
			new UpperCaseTokenizer(matchVersion, reader));
	}

	private Version matchVersion;

}