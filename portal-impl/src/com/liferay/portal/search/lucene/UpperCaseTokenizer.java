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

import org.apache.lucene.analysis.CharTokenizer;
import org.apache.lucene.util.AttributeSource;
import org.apache.lucene.util.Version;

/**
 * @author Norbert Kocsis
 */
public class UpperCaseTokenizer extends CharTokenizer {

	public UpperCaseTokenizer(Version matchVersion, Reader in) {
		super(matchVersion, in);
	}

	public UpperCaseTokenizer(
			Version matchVersion, AttributeSource source, Reader in) {

		super(matchVersion, source, in);
	}

	public UpperCaseTokenizer(
			Version matchVersion, AttributeFactory factory, Reader in) {

		super(matchVersion, factory, in);
	}

	@Deprecated
	public UpperCaseTokenizer(Reader in) {
		super(Version.LUCENE_30, in);
	}

	@Deprecated
	public UpperCaseTokenizer(AttributeSource source, Reader in) {
		super(Version.LUCENE_30, source, in);
	}

	@Deprecated
	public UpperCaseTokenizer(AttributeFactory factory, Reader in) {
		super(Version.LUCENE_30, factory, in);
	}

	@Override
	protected boolean isTokenChar(int c) {
		return true;
	}

	@Override
	protected int normalize(int c) {
		return Character.toUpperCase(c);
	}

}