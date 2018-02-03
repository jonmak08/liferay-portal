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

package com.liferay.portal.cache.io;

import com.liferay.portal.kernel.io.Deserializer;
import com.liferay.portal.kernel.io.Serializer;
<<<<<<< HEAD
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
=======
import com.liferay.portal.kernel.util.AggregateClassLoader;
>>>>>>> compatible

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.nio.ByteBuffer;

/**
<<<<<<< HEAD
 * @author Tina Tian
 */
=======
 * @author     Tina Tian
 * @deprecated As of 2.2.0, replaced by {@link
 *             com.liferay.portal.kernel.io.SerializableObjectWrapper}
 */
@Deprecated
>>>>>>> compatible
public class SerializableObjectWrapper implements Serializable {

	public static <T> T unwrap(Object object) {
		if (!(object instanceof SerializableObjectWrapper)) {
			return (T)object;
		}

		SerializableObjectWrapper serializableWrapper =
			(SerializableObjectWrapper)object;

		return (T)serializableWrapper._serializable;
	}

	public SerializableObjectWrapper(Serializable serializable) {
		_serializable = serializable;
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

		return _serializable.equals(serializableWrapper._serializable);
	}

	@Override
	public int hashCode() {
		return _serializable.hashCode();
	}

	private void readObject(ObjectInputStream objectInputStream)
<<<<<<< HEAD
		throws IOException {

		byte[] data = new byte[objectInputStream.readInt()];

		objectInputStream.readFully(data);

		Deserializer deserializer = new Deserializer(ByteBuffer.wrap(data));

		try {
			_serializable = deserializer.readObject();
		}
		catch (ClassNotFoundException cnfe) {
			_log.error("Unable to deserialize object", cnfe);
=======
		throws ClassNotFoundException, IOException {

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		currentThread.setContextClassLoader(_classLoader);

		try {
			int size = objectInputStream.readInt();

			byte[] data = new byte[size];

			objectInputStream.readFully(data);

			Deserializer deserializer = new Deserializer(ByteBuffer.wrap(data));

			_serializable = deserializer.readObject();
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
>>>>>>> compatible
		}
	}

	private void writeObject(ObjectOutputStream objectOutputStream)
		throws IOException {

<<<<<<< HEAD
		Serializer serializer = new Serializer();

		serializer.writeObject(_serializable);

		ByteBuffer byteBuffer = serializer.toByteBuffer();

		objectOutputStream.writeInt(byteBuffer.remaining());

		objectOutputStream.write(
			byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SerializableObjectWrapper.class);
=======
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		currentThread.setContextClassLoader(_classLoader);

		try {
			Serializer serializer = new Serializer();

			serializer.writeObject(_serializable);

			ByteBuffer byteBuffer = serializer.toByteBuffer();

			objectOutputStream.writeInt(byteBuffer.remaining());
			objectOutputStream.write(
				byteBuffer.array(), byteBuffer.position(),
				byteBuffer.remaining());
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	private static final ClassLoader _classLoader;

	static {
		Thread currentThread = Thread.currentThread();

		_classLoader = AggregateClassLoader.getAggregateClassLoader(
			currentThread.getContextClassLoader(),
			SerializableObjectWrapper.class.getClassLoader());
	}
>>>>>>> compatible

	private Serializable _serializable;

}