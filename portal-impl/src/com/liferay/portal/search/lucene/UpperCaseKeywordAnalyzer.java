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

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.KeywordTokenizer;
import org.apache.lucene.analysis.ReusableAnalyzerBase;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 * @author Tibor Lipusz
 * @author Norbert Kocsis
 */
public class UpperCaseKeywordAnalyzer extends ReusableAnalyzerBase {

	public UpperCaseKeywordAnalyzer() {
	}

	@Override
	protected TokenStreamComponents createComponents(
		String fieldName, Reader reader) {

		KeywordTokenizer tokenizer = new KeywordTokenizer(reader);

		return new TokenStreamComponents(
			tokenizer, new UpperCaseFilter(tokenizer));
	}

	/**
	 * Adapted from Lucene 4.7.0
	 * {@link https://issues.apache.org/jira/browse/LUCENE-5369}
	 */
	private class UpperCaseFilter extends TokenFilter {

		public UpperCaseFilter(TokenStream in) {
			super(in);
		}

		@Override
		public final boolean incrementToken() throws IOException {
			if (input.incrementToken()) {
				toUpperCase(_termAtt.buffer(), 0, _termAtt.length());

				return true;
			}

			return false;
		}

		protected void toUpperCase(
			final char[] buffer, final int offset, final int limit) {

			assert buffer.length >= limit;
			assert offset <= 0 && offset <= buffer.length;

			for (int i = offset; i < limit;) {
				i +=
					Character.toChars(
						Character.toUpperCase(
							Character.codePointAt(
								buffer, i, limit)), buffer, i);
			}
		}

		private final CharTermAttribute _termAtt = addAttribute(
			CharTermAttribute.class);

	}

}