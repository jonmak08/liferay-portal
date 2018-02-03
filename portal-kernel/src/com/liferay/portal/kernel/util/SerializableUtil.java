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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.io.ProtectedObjectInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author     Alexander Chow
 * @author     Igor Spasic
 * @deprecated As of 6.1.0, moved to {@link com.liferay.util.SerializableUtil}
 */
@Deprecated
public class SerializableUtil {

	public static Object clone(Object object) {
		Class<?> clazz = object.getClass();

		return deserialize(serialize(object), clazz.getClassLoader());
	}

	public static Object deserialize(byte[] bytes) {
<<<<<<< HEAD
		try (ObjectInputStream objectInputStream =
				new ProtectedObjectInputStream(
					new UnsyncByteArrayInputStream(bytes))) {
=======
		ObjectInputStream objectInputStream = null;

		try {
			objectInputStream = new ProtectedObjectInputStream(
				new UnsyncByteArrayInputStream(bytes));
>>>>>>> compatible

			return objectInputStream.readObject();
		}
		catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe);
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
<<<<<<< HEAD
	}

	public static Object deserialize(byte[] bytes, ClassLoader classLoader) {
		try (ObjectInputStream objectInputStream =
				new ProtectedClassLoaderObjectInputStream(
					new UnsyncByteArrayInputStream(bytes), classLoader)) {
=======
		finally {
			StreamUtil.cleanUp(objectInputStream);
		}
	}

	public static Object deserialize(byte[] bytes, ClassLoader classLoader) {
		ObjectInputStream objectInputStream = null;

		try {
			objectInputStream = new ProtectedClassLoaderObjectInputStream(
				new UnsyncByteArrayInputStream(bytes), classLoader);
>>>>>>> compatible

			return objectInputStream.readObject();
		}
		catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe);
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
<<<<<<< HEAD
	}

	public static byte[] serialize(Object object) {
		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				unsyncByteArrayOutputStream)) {
=======
		finally {
			StreamUtil.cleanUp(objectInputStream);
		}
	}

	public static byte[] serialize(Object object) {
		ObjectOutputStream objectOutputStream = null;

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		try {
			objectOutputStream = new ObjectOutputStream(
				unsyncByteArrayOutputStream);
>>>>>>> compatible

			objectOutputStream.writeObject(object);
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
<<<<<<< HEAD
=======
		finally {
			StreamUtil.cleanUp(objectOutputStream);
		}
>>>>>>> compatible

		return unsyncByteArrayOutputStream.toByteArray();
	}

}