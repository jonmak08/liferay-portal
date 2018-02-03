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

package com.liferay.portal.kernel.concurrent;

import com.liferay.petra.memory.FinalizeManager;
import com.liferay.portal.kernel.test.GCUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.test.rule.NewEnv;
import com.liferay.portal.kernel.test.rule.NewEnvTestRule;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.After;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class AsyncBrokerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
<<<<<<< HEAD
			CodeCoverageAssertor.INSTANCE, NewEnvTestRule.INSTANCE);
=======
			AspectJNewEnvTestRule.INSTANCE, CodeCoverageAssertor.INSTANCE);
>>>>>>> compatible

	@After
	public void tearDown() {
		System.clearProperty(_THREAD_ENABLED_KEY);
	}

	@Test
	public void testGetOpenBids() {
		AsyncBroker<String, String> asyncBroker = new AsyncBroker<>();

		Map<String, NoticeableFuture<String>> map = asyncBroker.getOpenBids();

		Assert.assertTrue(map.toString(), map.isEmpty());

		try {
			map.clear();

			Assert.fail();
		}
		catch (UnsupportedOperationException uoe) {
		}

		NoticeableFuture<String> noticeableFuture = asyncBroker.post(_KEY);

		Assert.assertEquals(map.toString(), 1, map.size());
		Assert.assertSame(noticeableFuture, map.get(_KEY));

		noticeableFuture.cancel(true);

		Assert.assertTrue(map.toString(), map.isEmpty());
	}

	@NewEnv(type = NewEnv.Type.CLASSLOADER)
	@Test
	public void testOrphanCancellation() throws InterruptedException {
		System.setProperty(_THREAD_ENABLED_KEY, StringPool.FALSE);

		AsyncBroker<String, String> asyncBroker = new AsyncBroker<>();

		NoticeableFuture<String> noticeableFuture = asyncBroker.post(_KEY);

		AtomicBoolean completedMarker = new AtomicBoolean();

		noticeableFuture.addFutureListener(future -> completedMarker.set(true));

		noticeableFuture = null;

		GCUtil.gc(true);

		ReflectionTestUtil.invoke(
			FinalizeManager.class, "_pollingCleanup", new Class<?>[0]);

<<<<<<< HEAD
		Assert.assertTrue(completedMarker.get());
=======
			String toString = noticeableFuture.toString();

			noticeableFuture = null;

			GCUtil.gc(true);

			ReflectionTestUtil.invoke(
				FinalizeManager.class, "_pollingCleanup", new Class<?>[0]);

			List<LogRecord> logRecords = captureHandler.getLogRecords();

			Assert.assertEquals(logRecords.toString(), 1, logRecords.size());

			LogRecord logRecord = logRecords.get(0);

			Assert.assertEquals(
				"Cancelled orphan noticeable future " + toString +
					" with key " + _KEY,
				logRecord.getMessage());
		}
	}

	@NewEnv(type = NewEnv.Type.CLASSLOADER)
	@Test
	public void testOrphanCancellationNotSupported() throws Exception {
		System.setProperty(_THREAD_ENABLED_KEY, StringPool.FALSE);

		try (CaptureHandler captureHandler =
				JDKLoggerTestUtil.configureJDKLogger(
					AsyncBroker.class.getName(), Level.SEVERE)) {

			AsyncBroker<String, String> asyncBroker = new AsyncBroker<>();

			asyncBroker.post(_KEY);

			GCUtil.gc(true);

			Field field = ReflectionTestUtil.getFieldValue(
				AsyncBroker.class, "_REFERENT_FIELD");

			field.setAccessible(false);

			ReflectionTestUtil.invoke(
				FinalizeManager.class, "_pollingCleanup", new Class<?>[0]);

			List<LogRecord> logRecords = captureHandler.getLogRecords();

			Assert.assertEquals(logRecords.toString(), 1, logRecords.size());

			LogRecord logRecord = logRecords.get(0);

			String message = logRecord.getMessage();

			Assert.assertTrue(
				message.startsWith("Unable to access referent of "));

			Throwable throwable = logRecord.getThrown();

			Assert.assertSame(
				IllegalAccessException.class, throwable.getClass());
		}
	}

	@AdviseWith(adviceClasses = ReflectionUtilAdvice.class)
	@NewEnv(type = NewEnv.Type.CLASSLOADER)
	@Test
	public void testPhantomReferenceResurrectionNotSupportedWithLog()
		throws ClassNotFoundException {

		testPhantomReferenceResurrectionNotSupported(true);
	}

	@AdviseWith(adviceClasses = ReflectionUtilAdvice.class)
	@NewEnv(type = NewEnv.Type.CLASSLOADER)
	@Test
	public void testPhantomReferenceResurrectionNotSupportedWithoutLog()
		throws ClassNotFoundException {

		testPhantomReferenceResurrectionNotSupported(false);
>>>>>>> compatible
	}

	@Test
	public void testPost() throws Exception {
		AsyncBroker<String, String> asyncBroker = new AsyncBroker<>();

		Map<String, DefaultNoticeableFuture<String>> defaultNoticeableFutures =
			ReflectionTestUtil.getFieldValue(
				asyncBroker, "_defaultNoticeableFutures");

		NoticeableFuture<String> noticeableFuture = asyncBroker.post(_KEY);

		Assert.assertEquals(
			defaultNoticeableFutures.toString(), 1,
			defaultNoticeableFutures.size());
		Assert.assertSame(noticeableFuture, defaultNoticeableFutures.get(_KEY));
		Assert.assertSame(noticeableFuture, asyncBroker.post(_KEY));
		Assert.assertEquals(
			defaultNoticeableFutures.toString(), 1,
			defaultNoticeableFutures.size());
		Assert.assertTrue(noticeableFuture.cancel(true));
		Assert.assertTrue(
			defaultNoticeableFutures.toString(),
			defaultNoticeableFutures.isEmpty());

		boolean[] newMarker = new boolean[1];

<<<<<<< HEAD
		noticeableFuture = asyncBroker.post(_KEY, newMarker);
=======
			Assert.assertEquals(logRecords.toString(), 1, logRecords.size());
>>>>>>> compatible

		Assert.assertNotNull(noticeableFuture);

		Assert.assertTrue(Arrays.toString(newMarker), newMarker[0]);
		Assert.assertSame(noticeableFuture, asyncBroker.post(_KEY, newMarker));
		Assert.assertFalse(Arrays.toString(newMarker), newMarker[0]);
		Assert.assertTrue(noticeableFuture.cancel(true));
		Assert.assertTrue(
			defaultNoticeableFutures.toString(),
			defaultNoticeableFutures.isEmpty());

		DefaultNoticeableFuture<String> defaultNoticeableFuture =
			new DefaultNoticeableFuture<>();

		Assert.assertNull(asyncBroker.post(_KEY, defaultNoticeableFuture));
		Assert.assertSame(
			defaultNoticeableFuture, defaultNoticeableFutures.get(_KEY));
		Assert.assertSame(defaultNoticeableFuture, asyncBroker.post(_KEY));

		Assert.assertEquals(
			defaultNoticeableFutures.toString(), 1,
			defaultNoticeableFutures.size());
		Assert.assertTrue(defaultNoticeableFuture.cancel(true));
		Assert.assertTrue(
			defaultNoticeableFutures.toString(),
			defaultNoticeableFutures.isEmpty());
	}

	@Test
	public void testTake() {
		AsyncBroker<String, String> asyncBroker = new AsyncBroker<>();

		Map<String, DefaultNoticeableFuture<String>> defaultNoticeableFutures =
			ReflectionTestUtil.getFieldValue(
				asyncBroker, "_defaultNoticeableFutures");

<<<<<<< HEAD
		Assert.assertTrue(
			defaultNoticeableFutures.toString(),
			defaultNoticeableFutures.isEmpty());
=======
		Assert.assertTrue(defaultNoticeableFutures.isEmpty());
>>>>>>> compatible

		Assert.assertNull(asyncBroker.take(_KEY));

		NoticeableFuture<String> noticeableFuture = asyncBroker.post(_KEY);

		Assert.assertEquals(
			defaultNoticeableFutures.toString(), 1,
			defaultNoticeableFutures.size());
		Assert.assertSame(noticeableFuture, defaultNoticeableFutures.get(_KEY));
		Assert.assertSame(noticeableFuture, asyncBroker.take(_KEY));
		Assert.assertTrue(
			defaultNoticeableFutures.toString(),
			defaultNoticeableFutures.isEmpty());
		Assert.assertNull(asyncBroker.take(_KEY));
		Assert.assertTrue(noticeableFuture.cancel(true));
	}

	@Test
	public void testTakeWithException() throws Exception {
		AsyncBroker<String, String> asyncBroker = new AsyncBroker<>();

		Map<String, DefaultNoticeableFuture<String>> defaultNoticeableFutures =
			ReflectionTestUtil.getFieldValue(
				asyncBroker, "_defaultNoticeableFutures");

		Assert.assertTrue(
			defaultNoticeableFutures.toString(),
			defaultNoticeableFutures.isEmpty());

		Exception exception = new Exception();

		Assert.assertFalse(asyncBroker.takeWithException(_KEY, exception));

		NoticeableFuture<String> noticeableFuture = asyncBroker.post(_KEY);

		Assert.assertEquals(
			defaultNoticeableFutures.toString(), 1,
			defaultNoticeableFutures.size());
		Assert.assertSame(noticeableFuture, defaultNoticeableFutures.get(_KEY));
		Assert.assertTrue(asyncBroker.takeWithException(_KEY, exception));

		try {
			noticeableFuture.get();

			Assert.fail();
		}
		catch (ExecutionException ee) {
			Assert.assertSame(exception, ee.getCause());
		}

		Assert.assertTrue(
			defaultNoticeableFutures.toString(),
			defaultNoticeableFutures.isEmpty());
		Assert.assertFalse(asyncBroker.takeWithException(_KEY, exception));
	}

	@Test
	public void testTakeWithResult() throws Exception {
		AsyncBroker<String, String> asyncBroker = new AsyncBroker<>();

		Map<String, DefaultNoticeableFuture<String>> defaultNoticeableFutures =
			ReflectionTestUtil.getFieldValue(
				asyncBroker, "_defaultNoticeableFutures");

<<<<<<< HEAD
		Assert.assertTrue(
			defaultNoticeableFutures.toString(),
			defaultNoticeableFutures.isEmpty());
=======
		Assert.assertTrue(defaultNoticeableFutures.isEmpty());
>>>>>>> compatible

		Assert.assertFalse(asyncBroker.takeWithResult(_KEY, _VALUE));

		NoticeableFuture<String> noticeableFuture = asyncBroker.post(_KEY);

		Assert.assertEquals(
			defaultNoticeableFutures.toString(), 1,
			defaultNoticeableFutures.size());
		Assert.assertSame(noticeableFuture, defaultNoticeableFutures.get(_KEY));
		Assert.assertTrue(asyncBroker.takeWithResult(_KEY, _VALUE));
		Assert.assertEquals(_VALUE, noticeableFuture.get());
		Assert.assertTrue(
			defaultNoticeableFutures.toString(),
			defaultNoticeableFutures.isEmpty());
		Assert.assertFalse(asyncBroker.takeWithResult(_KEY, _VALUE));
	}

<<<<<<< HEAD
=======
	protected void testPhantomReferenceResurrectionNotSupported(boolean withLog)
		throws ClassNotFoundException {

		Throwable throwable = new Throwable();

		ReflectionUtilAdvice.setDeclaredFieldThrowable(throwable);

		Level level = Level.OFF;

		if (withLog) {
			level = Level.WARNING;
		}

		try (CaptureHandler captureHandler =
				JDKLoggerTestUtil.configureJDKLogger(
					AsyncBroker.class.getName(), level)) {

			Class.forName(
				AsyncBroker.class.getName(), true,
				AsyncBroker.class.getClassLoader());

			List<LogRecord> logRecords = captureHandler.getLogRecords();

			if (withLog) {
				Assert.assertEquals(
					logRecords.toString(), 1, logRecords.size());

				LogRecord logRecord = logRecords.get(0);

				Assert.assertEquals(
					"Cancellation of orphaned noticeable futures is disabled " +
						"because the JVM does not support phantom reference " +
							"resurrection",
					logRecord.getMessage());
				Assert.assertSame(throwable, logRecord.getThrown());
			}
			else {
				Assert.assertTrue(logRecords.isEmpty());
			}

			ReflectionUtilAdvice.setDeclaredFieldThrowable(null);

			Assert.assertNull(
				ReflectionTestUtil.getFieldValue(
					AsyncBroker.class, "_REFERENT_FIELD"));
		}
	}

>>>>>>> compatible
	private static final String _KEY = "testKey";

	private static final String _THREAD_ENABLED_KEY =
		FinalizeManager.class.getName() + ".thread.enabled";

	private static final String _VALUE = "testValue";

}