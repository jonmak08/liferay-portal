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

package com.liferay.jenkins.results.parser;

<<<<<<< HEAD
import java.io.IOException;
import java.io.PrintStream;

import java.util.Arrays;
=======
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
>>>>>>> compatible

/**
 * @author Peter Yoo
 */
public class SecurePrintStream extends PrintStream {

<<<<<<< HEAD
	public SecurePrintStream(PrintStream printStream) {
		super(printStream, true);

		_printStream = printStream;
=======
	public static SecurePrintStream getInstance() {
		if (_securePrintStream == null) {
			try {
				_securePrintStream = new SecurePrintStream(
					new SecurePrintStreamByteArrayOutputStream());
			}
			catch (UnsupportedEncodingException uee) {
				throw new RuntimeException(uee);
			}
		}

		return _securePrintStream;
>>>>>>> compatible
	}

	@Override
	public PrintStream append(char c) {
<<<<<<< HEAD
		_printStream.append(c);

		return this;
=======
		if (_suspendFlush) {
			return _tempPrintStream.append(c);
		}

		return super.append(c);
>>>>>>> compatible
	}

	@Override
	public PrintStream append(CharSequence charSequence) {
<<<<<<< HEAD
		String redactedString = _redact(charSequence.toString());

		if (redactedString != null) {
			_printStream.append(redactedString);
		}
		else {
			_printStream.append(charSequence);
		}

		return this;
=======
		if (_suspendFlush) {
			return _tempPrintStream.append(charSequence);
		}

		return super.append(charSequence);
>>>>>>> compatible
	}

	@Override
	public PrintStream append(CharSequence charSequence, int start, int end) {
<<<<<<< HEAD
		return append(charSequence.subSequence(start, end));
=======
		if (_suspendFlush) {
			return _tempPrintStream.append(charSequence, start, end);
		}

		return super.append(charSequence, start, end);
>>>>>>> compatible
	}

	@Override
	public void flush() {
<<<<<<< HEAD
		_printStream.flush();
=======
		if (_suspendFlush) {
			return;
		}

		synchronized (this) {
			ByteArrayOutputStream byteArrayOutputStream =
				new ByteArrayOutputStream();

			try {
				_tempPrintStream = new PrintStream(byteArrayOutputStream);

				_suspendFlush = true;

				String content =
					_securePrintStreamByteArrayOutputStream.toString();

				content = JenkinsResultsParserUtil.redact(content);

				_systemOutPrintStream.print(content);
			}
			finally {
				_securePrintStreamByteArrayOutputStream.reset();

				_suspendFlush = false;

				try {
					_securePrintStreamByteArrayOutputStream.write(
						byteArrayOutputStream.toByteArray());
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
				finally {
					byteArrayOutputStream.reset();
				}

				_tempPrintStream.close();
			}
		}
>>>>>>> compatible
	}

	@Override
	public void print(boolean b) {
<<<<<<< HEAD
		_printStream.print(b);
=======
		if (_suspendFlush) {
			_tempPrintStream.print(b);

			return;
		}

		super.print(b);
>>>>>>> compatible
	}

	@Override
	public void print(char c) {
<<<<<<< HEAD
		_printStream.print(c);
=======
		if (_suspendFlush) {
			_tempPrintStream.print(c);

			return;
		}

		super.print(c);
>>>>>>> compatible
	}

	@Override
	public void print(char[] chars) {
<<<<<<< HEAD
		String redactedString = _redact(new String(chars));

		if (redactedString != null) {
			_printStream.print(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.print(chars);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.print(chars);
=======
		super.print(chars);
>>>>>>> compatible
	}

	@Override
	public void print(double d) {
<<<<<<< HEAD
		String redactedString = _redact(Double.toString(d));

		if (redactedString != null) {
			_printStream.print(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.print(d);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.print(d);
=======
		super.print(d);
>>>>>>> compatible
	}

	@Override
	public void print(float f) {
<<<<<<< HEAD
		String redactedString = _redact(Float.toString(f));

		if (redactedString != null) {
			_printStream.print(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.print(f);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.print(f);
=======
		super.print(f);
>>>>>>> compatible
	}

	@Override
	public void print(int i) {
<<<<<<< HEAD
		String redactedString = _redact(Integer.toString(i));

		if (redactedString != null) {
			_printStream.print(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.print(i);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.print(i);
=======
		super.print(i);
>>>>>>> compatible
	}

	@Override
	public void print(long l) {
<<<<<<< HEAD
		String redactedString = _redact(Long.toString(l));

		if (redactedString != null) {
			_printStream.println(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.print(l);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.print(l);
=======
		super.print(l);
>>>>>>> compatible
	}

	@Override
	public void print(Object object) {
<<<<<<< HEAD
		if (object == null) {
			_printStream.print("null");
		}

		String redactedString = _redact(object.toString());

		if (redactedString != null) {
			_printStream.print(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.print(object);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.print(object);
=======
		super.print(object);
>>>>>>> compatible
	}

	@Override
	public void print(String string) {
<<<<<<< HEAD
		String redactedString = _redact(string);

		if (redactedString != null) {
			_printStream.print(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.print(string);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.print(string);
=======
		super.print(string);
>>>>>>> compatible
	}

	@Override
	public void println() {
<<<<<<< HEAD
		_printStream.println();
=======
		if (_suspendFlush) {
			_tempPrintStream.println();

			return;
		}

		super.println();
>>>>>>> compatible
	}

	@Override
	public void println(boolean b) {
<<<<<<< HEAD
		_printStream.println(b);
=======
		if (_suspendFlush) {
			_tempPrintStream.println(b);

			return;
		}

		super.println(b);
>>>>>>> compatible
	}

	@Override
	public void println(char c) {
<<<<<<< HEAD
		_printStream.println(c);
=======
		if (_suspendFlush) {
			_tempPrintStream.println(c);

			return;
		}

		super.println(c);
>>>>>>> compatible
	}

	@Override
	public void println(char[] chars) {
<<<<<<< HEAD
		String redactedString = _redact(new String(chars));

		if (redactedString != null) {
			_printStream.println(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.println(chars);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.println(chars);
=======
		super.println(chars);
>>>>>>> compatible
	}

	@Override
	public void println(double d) {
<<<<<<< HEAD
		String redactedString = _redact(Double.toString(d));

		if (redactedString != null) {
			_printStream.println(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.println(d);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.println(d);
=======
		super.println(d);
>>>>>>> compatible
	}

	@Override
	public void println(float f) {
<<<<<<< HEAD
		String redactedString = _redact(Float.toString(f));

		if (redactedString != null) {
			_printStream.println(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.println(f);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.println(f);
=======
		super.println(f);
>>>>>>> compatible
	}

	@Override
	public void println(int i) {
<<<<<<< HEAD
		String redactedString = _redact(Integer.toString(i));

		if (redactedString != null) {
			_printStream.println(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.println(i);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.println(i);
=======
		super.println(i);
>>>>>>> compatible
	}

	@Override
	public void println(long l) {
<<<<<<< HEAD
		String redactedString = _redact(Long.toString(l));

		if (redactedString != null) {
			_printStream.println(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.println(l);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.println(l);
=======
		super.println(l);
>>>>>>> compatible
	}

	@Override
	public void println(Object object) {
<<<<<<< HEAD
		if (object == null) {
			_printStream.println("null");
		}

		String redactedString = _redact(object.toString());

		if (redactedString != null) {
			_printStream.println(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.println(object);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.println(object);
=======
		super.println(object);
>>>>>>> compatible
	}

	@Override
	public void println(String string) {
<<<<<<< HEAD
		String redactedString = _redact(string);

		if (redactedString != null) {
			_printStream.println(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.println(string);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.println(string);
=======
		super.println(string);
>>>>>>> compatible
	}

	@Override
	public void write(byte[] bytes) throws IOException {
<<<<<<< HEAD
		String redactedString = _redact(new String(bytes));

		if (redactedString != null) {
			_printStream.write(redactedString.getBytes());
=======
		if (_suspendFlush) {
			_tempPrintStream.write(bytes);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.write(bytes);
=======
		super.write(bytes);
>>>>>>> compatible
	}

	@Override
	public void write(byte[] buffer, int offset, int length) {
<<<<<<< HEAD
		String redactedString = _redact(
			new String(Arrays.copyOfRange(buffer, offset, offset + length)));

		if (redactedString != null) {
			_printStream.print(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.write(buffer, offset, length);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.write(buffer, offset, length);
=======
		super.write(buffer, offset, length);
>>>>>>> compatible
	}

	@Override
	public void write(int b) {
<<<<<<< HEAD
		String redactedString = _redact(Integer.toString(b));

		if (redactedString != null) {
			_printStream.print(redactedString);
=======
		if (_suspendFlush) {
			_tempPrintStream.write(b);
>>>>>>> compatible

			return;
		}

<<<<<<< HEAD
		_printStream.write(b);
	}

	private String _redact(String string) {
		if (string == null) {
			return null;
		}

		String redactedString = JenkinsResultsParserUtil.redact(string);

		if (string.equals(redactedString)) {
			return null;
		}

		return redactedString;
	}

	private final PrintStream _printStream;
=======
		super.write(b);
	}

	private SecurePrintStream(
			SecurePrintStreamByteArrayOutputStream
				securePrintStreamByteArrayOutputStream)
		throws UnsupportedEncodingException {

		super(securePrintStreamByteArrayOutputStream, true);

		_securePrintStreamByteArrayOutputStream =
			securePrintStreamByteArrayOutputStream;

		_securePrintStreamByteArrayOutputStream.setSecurePrintStream(this);

		_systemOutPrintStream = System.out;
	}

	private static SecurePrintStream _securePrintStream;

	private final SecurePrintStreamByteArrayOutputStream
		_securePrintStreamByteArrayOutputStream;
	private boolean _suspendFlush;
	private final PrintStream _systemOutPrintStream;
	private PrintStream _tempPrintStream;

	private static class SecurePrintStreamByteArrayOutputStream
		extends ByteArrayOutputStream {

		@Override
		public void flush() throws IOException {
			super.flush();

			if (_securePrintStream != null) {
				_securePrintStream.flush();
			}
		}

		public void setSecurePrintStream(SecurePrintStream securePrintStream) {
			_securePrintStream = securePrintStream;
		}

		private SecurePrintStream _securePrintStream;

	}
>>>>>>> compatible

}