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

package com.liferay.portal.kernel.io;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

import java.nio.ByteBuffer;

import java.util.Arrays;

/**
 * @author Tina Tian
<<<<<<< HEAD
 * @deprecated As of 7.0.0, with no direct replacement
 */
@Deprecated
=======
 */
>>>>>>> compatible
public class SerializableObjectWrapper implements Externalizable {

	public static <T> T unwrap(Object object) {
		if (!(object instanceof SerializableObjectWrapper)) {
			return (T)object;
		}

		SerializableObjectWrapper serializableWrapper =
			(SerializableObjectWrapper)object;

		if (serializableWrapper._serializable instanceof LazySerializable) {
			LazySerializable lazySerializable =
				(LazySerializable)serializableWrapper._serializable;

<<<<<<< HEAD
			Serializable serializable = lazySerializable.getSerializable();

			if (serializable == null) {
				return null;
			}

			serializableWrapper._serializable = serializable;
=======
			serializableWrapper._serializable =
				lazySerializable.getSerializable();
>>>>>>> compatible
		}

		return (T)serializableWrapper._serializable;
	}

	/**
	 * The empty constructor is required by {@link Externalizable}. Do not use
	 * this for any other purpose.
	 */
	public SerializableObjectWrapper() {
<<<<<<< HEAD
		_hashCode = 0;
=======
>>>>>>> compatible
	}

	public SerializableObjectWrapper(Serializable serializable) {
		_serializable = serializable;
<<<<<<< HEAD

		_hashCode = serializable.hashCode();
=======
>>>>>>> compatible
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SerializableObjectWrapper)) {
			return false;
		}

		SerializableObjectWrapper serializableWrapper =
			(SerializableObjectWrapper)object;

<<<<<<< HEAD
		if (_hashCode != serializableWrapper._hashCode) {
			return false;
		}

=======
>>>>>>> compatible
		if ((_serializable instanceof LazySerializable) &&
			(serializableWrapper._serializable instanceof LazySerializable)) {

			LazySerializable lazySerializable1 =
				(LazySerializable)_serializable;
			LazySerializable lazySerializable2 =
				(LazySerializable)serializableWrapper._serializable;

<<<<<<< HEAD
			if (Arrays.equals(
					lazySerializable1.getData(), lazySerializable2.getData())) {

				return true;
			}
=======
			return Arrays.equals(
				lazySerializable1.getData(), lazySerializable2.getData());
>>>>>>> compatible
		}

		if (_serializable instanceof LazySerializable) {
			LazySerializable lazySerializable = (LazySerializable)_serializable;

<<<<<<< HEAD
			Serializable serializable = lazySerializable.getSerializable();

			if (serializable == null) {
				return Arrays.equals(
					lazySerializable.getData(), serializableWrapper._getData());
			}

			_serializable = serializable;
=======
			_serializable = lazySerializable.getSerializable();
>>>>>>> compatible
		}

		if (serializableWrapper._serializable instanceof LazySerializable) {
			LazySerializable lazySerializable =
				(LazySerializable)serializableWrapper._serializable;

<<<<<<< HEAD
			Serializable serializable = lazySerializable.getSerializable();

			if (serializable == null) {
				return Arrays.equals(_getData(), lazySerializable.getData());
			}

			serializableWrapper._serializable = serializable;
=======
			serializableWrapper._serializable =
				lazySerializable.getSerializable();
>>>>>>> compatible
		}

		return _serializable.equals(serializableWrapper._serializable);
	}

	@Override
	public int hashCode() {
<<<<<<< HEAD
		return _hashCode;
=======
		if (_serializable instanceof LazySerializable) {
			LazySerializable lazySerializable = (LazySerializable)_serializable;

			_serializable = lazySerializable.getSerializable();
		}

		return _serializable.hashCode();
>>>>>>> compatible
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
<<<<<<< HEAD
		_hashCode = objectInput.readInt();

=======
>>>>>>> compatible
		byte[] data = new byte[objectInput.readInt()];

		objectInput.readFully(data);

		_serializable = new LazySerializable(data);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
<<<<<<< HEAD
		objectOutput.writeInt(_hashCode);

		byte[] data = _getData();

		objectOutput.writeInt(data.length);

		objectOutput.write(data, 0, data.length);
	}

	private byte[] _getData() {
		if (_serializable instanceof LazySerializable) {
			LazySerializable lazySerializable = (LazySerializable)_serializable;

			return lazySerializable.getData();
=======
		if (_serializable instanceof LazySerializable) {
			LazySerializable lazySerializable = (LazySerializable)_serializable;

			byte[] data = lazySerializable.getData();

			objectOutput.writeInt(data.length);

			objectOutput.write(data, 0, data.length);

			return;
>>>>>>> compatible
		}

		Serializer serializer = new Serializer();

		serializer.writeObject(_serializable);

		ByteBuffer byteBuffer = serializer.toByteBuffer();

<<<<<<< HEAD
		return byteBuffer.array();
=======
		objectOutput.writeInt(byteBuffer.remaining());

		objectOutput.write(
			byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
>>>>>>> compatible
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SerializableObjectWrapper.class);

<<<<<<< HEAD
	private int _hashCode;
=======
>>>>>>> compatible
	private volatile Serializable _serializable;

	private static class LazySerializable implements Serializable {

		public byte[] getData() {
			return _data;
		}

		public Serializable getSerializable() {
			Deserializer deserializer = new Deserializer(
				ByteBuffer.wrap(_data));

			try {
				return deserializer.readObject();
			}
			catch (ClassNotFoundException cnfe) {
				_log.error("Unable to deserialize object", cnfe);

				return null;
			}
		}

		private LazySerializable(byte[] data) {
			_data = data;
		}

		private final byte[] _data;

	}

}