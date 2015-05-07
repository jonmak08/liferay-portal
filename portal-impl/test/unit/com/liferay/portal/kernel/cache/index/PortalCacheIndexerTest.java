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

package com.liferay.portal.kernel.cache.index;

import com.liferay.portal.cache.memory.MemoryPortalCache;
import com.liferay.portal.kernel.cache.CacheListener;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.concurrent.test.MappedMethodNameCallableInvocationHandler;
import com.liferay.portal.kernel.test.CodeCoverageAssertor;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;

import com.liferay.portal.util.RandomTestUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Preston Crary
 */
public class PortalCacheIndexerTest {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Before
	public void setUp() throws Exception {
		_portalCache = new MemoryPortalCache<TestIndexedCacheKey, String>(
			RandomTestUtil.randomString(), 10);

		_portalCacheIndexer =
			new PortalCacheIndexer<Long, TestIndexedCacheKey, String>(
				_portalCache);

		_cacheListener = ReflectionTestUtil.getFieldValue(
			_portalCache, "aggregatedCacheListener");

		ConcurrentMap<Long, TestIndexedCacheKey> map =
			ReflectionTestUtil.getFieldValue(
				_portalCacheIndexer, "_indexedCacheKeys");

		_mappedMethodNameCallableInvocationHandler =
			new MappedMethodNameCallableInvocationHandler(map, true);

		Object proxy = ProxyUtil.newProxyInstance(
			ClassLoader.getSystemClassLoader(),
			new Class<?>[] {ConcurrentMap.class},
			_mappedMethodNameCallableInvocationHandler);

		ReflectionTestUtil.setFieldValue(
			_portalCacheIndexer, "_indexedCacheKeys", proxy);
	}

	@Test
	public void testAddIndexedCacheKeyConcurrentPutDifferentKeys()
		throws ReflectiveOperationException {

		Callable<?> callable = new Callable<Object>() {

			@Override
			public Object call() {
				_portalCache.put(_INDEX_1_KEY_1, _VALUE);

				return null;
			}

		};

		_mappedMethodNameCallableInvocationHandler.putBeforeCallable(
			ConcurrentMap.class.getMethod(
				"putIfAbsent", Object.class, Object.class),
			callable);

		_portalCache.put(_INDEX_1_KEY_2, _VALUE);

		assertIndexCacheSynchronization();
	}

	@Test
	public void testAddIndexedCacheKeyConcurrentPutRemove()
		throws ReflectiveOperationException {

		_portalCache.put(_INDEX_1_KEY_1, _VALUE);

		Callable<?> beforePutIfAbsentCallable = new Callable<Object>() {

			@Override
			public Object call() {
				_portalCache.put(_INDEX_1_KEY_1, _VALUE);

				return null;
			}

		};

		_mappedMethodNameCallableInvocationHandler.putBeforeCallable(
			ConcurrentMap.class.getMethod(
				"putIfAbsent", Object.class, Object.class),
			beforePutIfAbsentCallable);

		Callable<?> beforeReplaceCallable = new Callable<Object>() {

			@Override
			public Object call() {
				_portalCache.remove(_INDEX_1_KEY_1);

				return null;
			}

		};

		_mappedMethodNameCallableInvocationHandler.putBeforeCallable(
			ConcurrentMap.class.getMethod(
				"replace", Object.class, Object.class, Object.class),
			beforeReplaceCallable);

		_portalCache.put(_INDEX_1_KEY_2, _VALUE);

		assertIndexCacheSynchronization();
	}

	@Test
	public void testAddIndexedCacheKeyPutSameKey() {
		_portalCache.put(_INDEX_1_KEY_1, _VALUE);
		_portalCache.put(_INDEX_1_KEY_1, _VALUE);

		assertIndexCacheSynchronization();
	}

	@Test
	public void testAddIndexedCacheKeyWithDifferentIndex() {
		_portalCache.put(_INDEX_1_KEY_1, _VALUE);
		_portalCache.put(_INDEX_2_KEY_3, _VALUE);

		assertIndexCacheSynchronization();
	}

	@Test
	public void testAddIndexedCacheKeyWithSameIndex() {
		_portalCache.put(_INDEX_1_KEY_1, _VALUE);
		_portalCache.put(_INDEX_1_KEY_2, _VALUE);

		assertIndexCacheSynchronization();
	}

	@Test
	public void testGetIndexedCacheKeysWithIndexKey() {
		_portalCache.put(_INDEX_1_KEY_1, _VALUE);

		Set<TestIndexedCacheKey> set = _portalCacheIndexer.getIndexedCacheKeys(
			_INDEX_1_KEY_1.getIndex());

		set.clear();

		assertIndexCacheSynchronization();
	}

	@Test
	public void testGetIndexedCacheKeysWithoutIndexKey() {
		_portalCacheIndexer.getIndexedCacheKeys(_INDEX_1_KEY_1.getIndex());

		assertIndexCacheSynchronization();
	}

	@Test
	public void testInit() {
		_portalCache = new MemoryPortalCache<TestIndexedCacheKey, String>(
			RandomTestUtil.randomString(), 10);

		_portalCache.put(_INDEX_1_KEY_1, _VALUE);

		_portalCacheIndexer =
			new PortalCacheIndexer<Long, TestIndexedCacheKey, String>(
				_portalCache);

		assertIndexCacheSynchronization();
	}

	@Test
	public void testNotifyEntryEvicted() {
		_portalCache.put(_INDEX_1_KEY_1, _VALUE);

		_portalCache.remove(_INDEX_1_KEY_1);
		_cacheListener.notifyEntryEvicted(_portalCache, _INDEX_1_KEY_1, _VALUE);

		assertIndexCacheSynchronization();
	}

	@Test
	public void testNotifyEntryExpired() {
		_portalCache.put(_INDEX_1_KEY_1, _VALUE);

		_portalCache.remove(_INDEX_1_KEY_1);
		_cacheListener.notifyEntryExpired(_portalCache, _INDEX_1_KEY_1, _VALUE);

		assertIndexCacheSynchronization();
	}

	@Test
	public void testNotifyEntryRemoved() {
		_portalCache.put(_INDEX_1_KEY_1, _VALUE);

		_portalCache.remove(_INDEX_1_KEY_1);

		assertIndexCacheSynchronization();
	}

	@Test
	public void testNotifyEntryUpdated() {
		_portalCache.put(_INDEX_1_KEY_1, _VALUE);

		_portalCache.put(_INDEX_1_KEY_1, _VALUE);

		assertIndexCacheSynchronization();
	}

	@Test
	public void testNotifyRemoveAll() {
		_portalCache.put(_INDEX_1_KEY_1, _VALUE);
		_portalCache.put(_INDEX_1_KEY_2, _VALUE);
		_portalCache.put(_INDEX_2_KEY_3, _VALUE);

		_portalCache.removeAll();

		assertIndexCacheSynchronization();
	}

	@Test
	public void testRemoveIndexedCacheKeyConcurrentPut()
		throws ReflectiveOperationException {

		_portalCache.put(_INDEX_1_KEY_1, _VALUE);

		Callable<?> callable = new Callable<Object>() {

			@Override
			public Object call() throws Exception {
				_portalCache.put(_INDEX_1_KEY_2, _VALUE);

				return null;
			}

		};

		_mappedMethodNameCallableInvocationHandler.putBeforeCallable(
			ConcurrentMap.class.getMethod("remove", Object.class, Object.class),
			callable);

		_portalCache.remove(_INDEX_1_KEY_1);

		assertIndexCacheSynchronization();
	}

	@Test
	public void testRemoveIndexedCacheKeyConcurrentRemove()
		throws ReflectiveOperationException {

		_portalCache.put(_INDEX_1_KEY_1, _VALUE);
		_portalCache.put(_INDEX_1_KEY_2, _VALUE);

		Callable<?> callable = new Callable<Object>() {

			@Override
			public Object call() throws Exception {
				_portalCache.remove(_INDEX_1_KEY_1);

				return null;
			}

		};

		_mappedMethodNameCallableInvocationHandler.putBeforeCallable(
			ConcurrentMap.class.getMethod(
				"replace", Object.class, Object.class, Object.class),
			callable);

		_portalCache.remove(_INDEX_1_KEY_2);

		assertIndexCacheSynchronization();
	}

	@Test
	public void testRemoveIndexedCacheKeysWithIndex() {
		_portalCache.put(_INDEX_1_KEY_1, _VALUE);

		_portalCacheIndexer.removeIndexedCacheKeys(_INDEX_1_KEY_1.getIndex());

		assertIndexCacheSynchronization();
	}

	@Test
	public void testRemoveIndexedCacheKeysWithoutIndex() {
		_portalCache.put(_INDEX_1_KEY_1, _VALUE);

		_portalCacheIndexer.removeIndexedCacheKeys(_INDEX_2_KEY_3.getIndex());

		assertIndexCacheSynchronization();
	}

	@Test
	public void testRemoveIndexedCacheKeyWithKey() {
		_portalCache.put(_INDEX_1_KEY_1, _VALUE);
		_portalCache.put(_INDEX_1_KEY_2, _VALUE);

		_portalCache.remove(_INDEX_1_KEY_1);

		assertIndexCacheSynchronization();
	}

	@Test
	public void testRemoveIndexedCacheKeyWithoutKey() {
		_portalCache.put(_INDEX_1_KEY_1, _VALUE);

		_portalCache.remove(_INDEX_1_KEY_2);

		assertIndexCacheSynchronization();
	}

	protected void assertIndexCacheSynchronization() {
		Set<TestIndexedCacheKey> keys = new HashSet<TestIndexedCacheKey>(
			_portalCache.getKeys());

		Set<Long> indexes = new HashSet<Long>();

		for (TestIndexedCacheKey key : keys) {
			indexes.add(key.getIndex());
		}

		Set<TestIndexedCacheKey> indexedCacheKeys =
			new HashSet<TestIndexedCacheKey>();

		for (Long index : indexes) {
			indexedCacheKeys.addAll(
				_portalCacheIndexer.getIndexedCacheKeys(index));
		}

		Assert.assertEquals(keys, indexedCacheKeys);
	}

	private static final TestIndexedCacheKey _INDEX_1_KEY_1 =
		new TestIndexedCacheKey(1L, 1L);

	private static final TestIndexedCacheKey _INDEX_1_KEY_2 =
		new TestIndexedCacheKey(1L, 2L);

	private static final TestIndexedCacheKey _INDEX_2_KEY_3 =
		new TestIndexedCacheKey(2L, 3L);

	private static final String _VALUE = "VALUE";

	private CacheListener<TestIndexedCacheKey, String> _cacheListener;
	private MappedMethodNameCallableInvocationHandler
		_mappedMethodNameCallableInvocationHandler;
	private PortalCache<TestIndexedCacheKey, String> _portalCache;
	private PortalCacheIndexer<Long, TestIndexedCacheKey, String>
		_portalCacheIndexer;

}