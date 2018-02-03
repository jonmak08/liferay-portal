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

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
<<<<<<< HEAD
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

=======
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.RemoteConfig;

>>>>>>> compatible
/**
 * @author Peter Yoo
 */
public class LocalGitSyncUtil {

<<<<<<< HEAD
	public static List<GitWorkingDirectory.Remote> getLocalGitRemotes(
		GitWorkingDirectory gitWorkingDirectory) {
=======
	public static void deleteCacheBranch(
			GitWorkingDirectory gitWorkingDirectory, String receiverUsername,
			String senderBranchName, String senderUsername,
			String senderBranchSHA, String upstreamBranchSHA)
		throws GitAPIException {

		List<RemoteConfig> localGitRemoteConfigs = null;

		try {
			localGitRemoteConfigs = getLocalGitRemoteConfigs(
				gitWorkingDirectory);

			deleteCacheBranch(
				getCacheBranchName(
					receiverUsername, senderUsername, senderBranchSHA,
					upstreamBranchSHA),
				gitWorkingDirectory, localGitRemoteConfigs);
		}
		finally {
			if (localGitRemoteConfigs != null) {
				try {
					gitWorkingDirectory.removeRemotes(localGitRemoteConfigs);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static List<RemoteConfig> getLocalGitRemoteConfigs(
			GitWorkingDirectory gitWorkingDirectory)
		throws GitAPIException {
>>>>>>> compatible

		List<String> localGitRemoteURLs = getLocalGitRemoteURLs(
			gitWorkingDirectory);

<<<<<<< HEAD
		List<GitWorkingDirectory.Remote> localGitRemotes = new ArrayList<>(
			localGitRemoteURLs.size());

		for (String localGitRemoteURL : localGitRemoteURLs) {
=======
		List<RemoteConfig> localGitRemoteConfigs = new ArrayList<>(
			localGitRemoteURLs.size());

		for (String localGitRemoteURL : localGitRemoteURLs) {
			String url = localGitRemoteURL.replace(
				"${username}", gitWorkingDirectory.getRepositoryUsername());

			url = url.replace(
				"${repository-name}", gitWorkingDirectory.getRepositoryName());

>>>>>>> compatible
			String localGitRemoteName =
				"local-git-remote-" +
					localGitRemoteURLs.indexOf(localGitRemoteURL);

<<<<<<< HEAD
			GitWorkingDirectory.Remote remote = gitWorkingDirectory.getRemote(
				localGitRemoteName);

			if ((remote == null) ||
				!localGitRemoteURL.equals(remote.getRemoteURL())) {

				remote = gitWorkingDirectory.addRemote(
					true, localGitRemoteName, localGitRemoteURL);
			}

			localGitRemotes.add(remote);
		}

		return localGitRemotes;
=======
			RemoteConfig remoteConfig = gitWorkingDirectory.getRemoteConfig(
				localGitRemoteName);

			if ((remoteConfig == null) ||
				!url.equals(GitWorkingDirectory.getRemoteURL(remoteConfig))) {

				remoteConfig = gitWorkingDirectory.addRemote(
					true, localGitRemoteName, url);
			}

			localGitRemoteConfigs.add(remoteConfig);
		}

		return localGitRemoteConfigs;
>>>>>>> compatible
	}

	public static String synchronizeToLocalGit(
			GitWorkingDirectory gitWorkingDirectory, String receiverUsername,
			String senderBranchName, String senderUsername,
			String senderBranchSHA, String upstreamBranchSHA)
<<<<<<< HEAD
		throws IOException {
=======
		throws GitAPIException, IOException {
>>>>>>> compatible

		return synchronizeToLocalGit(
			gitWorkingDirectory, receiverUsername, 0, senderBranchName,
			senderUsername, senderBranchSHA, upstreamBranchSHA);
	}

	protected static void cacheBranch(
<<<<<<< HEAD
		GitWorkingDirectory gitWorkingDirectory,
		GitWorkingDirectory.Branch localBranch,
		GitWorkingDirectory.Remote remote, long timestamp) {

		gitWorkingDirectory.pushToRemote(
			true, localBranch, localBranch.getName(), remote);

		gitWorkingDirectory.pushToRemote(
			true, localBranch,
			JenkinsResultsParserUtil.combine(
				localBranch.getName(), "-", Long.toString(timestamp)),
			remote);
	}

	protected static void cacheBranches(
		final GitWorkingDirectory gitWorkingDirectory,
		final GitWorkingDirectory.Branch localBranch,
		List<GitWorkingDirectory.Remote> localGitRemotes,
		final String upstreamUsername) {

		String localBranchName = localBranch.getName();
		GitWorkingDirectory.Branch currentBranch =
			gitWorkingDirectory.getCurrentBranch();

		if ((currentBranch == null) ||
			!localBranchName.equals(currentBranch.getName())) {

			gitWorkingDirectory.checkoutBranch(localBranch, "-f");
=======
			GitWorkingDirectory gitWorkingDirectory, String localBranchName,
			RemoteConfig remoteConfig, long timestamp)
		throws GitAPIException {

		gitWorkingDirectory.pushToRemote(true, remoteConfig);

		gitWorkingDirectory.pushToRemote(
			true,
			JenkinsResultsParserUtil.combine(
				localBranchName, "-", Long.toString(timestamp)),
			remoteConfig);
	}

	protected static void cacheBranches(
			final GitWorkingDirectory gitWorkingDirectory,
			final String localBranchName,
			List<RemoteConfig> localGitRemoteConfigs,
			final String upstreamBranchName, final String upstreamUsername)
		throws GitAPIException {

		if (!localBranchName.equals(gitWorkingDirectory.getCurrentBranch())) {
			gitWorkingDirectory.checkoutBranch(localBranchName, "-f");
>>>>>>> compatible
		}

		final long start = System.currentTimeMillis();

<<<<<<< HEAD
		final GitWorkingDirectory.Branch upstreamBranch =
			gitWorkingDirectory.getBranch(
				gitWorkingDirectory.getUpstreamBranchName(),
				gitWorkingDirectory.getRemote("upstream"));

		List<Callable<Object>> callables = new ArrayList<>();

		for (final GitWorkingDirectory.Remote localGitRemote :
				localGitRemotes) {

			Callable<Object> callable = new Callable<Object>() {

				@Override
				public Object call() {
					cacheBranch(
						gitWorkingDirectory, localBranch, localGitRemote,
						start);

					if (upstreamUsername.equals("liferay")) {
						GitWorkingDirectory.Branch localUpstreamBranch =
							gitWorkingDirectory.getBranch(
								upstreamBranch.getName(), null);

						gitWorkingDirectory.pushToRemote(
							true, localUpstreamBranch, upstreamBranch.getName(),
							localGitRemote);
					}

					return null;
				}

			};

			callables.add(callable);
		}

		ParallelExecutor<Object> parallelExecutor = new ParallelExecutor<>(
			callables, _threadPoolExecutor);

		parallelExecutor.execute();
=======
		ExecutorService executorService = Executors.newFixedThreadPool(
			localGitRemoteConfigs.size());

		for (final RemoteConfig localGitRemoteConfig : localGitRemoteConfigs) {
			executorService.execute(
				new Runnable() {

					@Override
					public void run() {
						try {
							cacheBranch(
								gitWorkingDirectory, localBranchName,
								localGitRemoteConfig, start);

							if (upstreamUsername.equals("liferay")) {
								gitWorkingDirectory.pushToRemote(
									true, upstreamBranchName,
									upstreamBranchName, localGitRemoteConfig);
							}
						}
						catch (GitAPIException gapie) {
							throw new RuntimeException(gapie);
						}
					}

				});
		}

		executorService.shutdown();

		try {
			executorService.awaitTermination(30, TimeUnit.MINUTES);
		}
		catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}
>>>>>>> compatible

		long duration = System.currentTimeMillis() - start;

		System.out.println(
			"Cache branches pushed up in " +
				JenkinsResultsParserUtil.toDurationString(duration));
	}

	protected static void checkoutUpstreamBranch(
<<<<<<< HEAD
		GitWorkingDirectory gitWorkingDirectory, String upstreamBranchSHA) {

		GitWorkingDirectory.Branch localUpstreamBranch =
			updateLocalUpstreamBranch(gitWorkingDirectory, upstreamBranchSHA);

		if (localUpstreamBranch != null) {
			gitWorkingDirectory.checkoutBranch(localUpstreamBranch);
		}
=======
			GitWorkingDirectory gitWorkingDirectory)
		throws GitAPIException {

		String upstreamBranchName = gitWorkingDirectory.getUpstreamBranchName();

		if (!gitWorkingDirectory.branchExists(upstreamBranchName, null)) {
			RemoteConfig upstreamRemoteConfig =
				gitWorkingDirectory.getRemoteConfig("upstream");

			updateLocalUpstreamBranch(
				gitWorkingDirectory, upstreamBranchName,
				gitWorkingDirectory.getBranchSHA(
					gitWorkingDirectory.getUpstreamBranchName(),
					upstreamRemoteConfig),
				upstreamRemoteConfig);
		}

		gitWorkingDirectory.checkoutBranch(upstreamBranchName);
>>>>>>> compatible
	}

	protected static void copyUpstreamRefsToHeads(
			GitWorkingDirectory gitWorkingDirectory)
		throws IOException {

		File gitDir = gitWorkingDirectory.getGitDirectory();

		File headsDir = new File(gitDir, "refs/heads");
		File upstreamDir = new File(gitDir, "refs/remotes/upstream-temp");

		for (File file : upstreamDir.listFiles()) {
			System.out.println(
				JenkinsResultsParserUtil.combine(
					"Copying ", headsDir.getPath(), " to ",
					upstreamDir.getPath()));
			JenkinsResultsParserUtil.copy(
				file, new File(headsDir, file.getName()));
		}
	}

<<<<<<< HEAD
	protected static void deleteExpiredCacheBranches(
		GitWorkingDirectory gitWorkingDirectory,
		GitWorkingDirectory.Remote remote, long timestamp) {
=======
	protected static void deleteCacheBranch(
			final String cacheBranchName,
			final GitWorkingDirectory gitWorkingDirectory,
			List<RemoteConfig> localGitRemoteConfigs)
		throws GitAPIException {

		long start = System.currentTimeMillis();

		ExecutorService executorService = Executors.newFixedThreadPool(
			localGitRemoteConfigs.size());

		for (final RemoteConfig localGitRemoteConfig : localGitRemoteConfigs) {
			executorService.execute(
				new Runnable() {

					@Override
					public void run() {
						try {
							deleteRemoteCacheBranch(
								cacheBranchName, gitWorkingDirectory,
								localGitRemoteConfig);
						}
						catch (GitAPIException gapie) {
							throw new RuntimeException(gapie);
						}
					}

				});
		}

		executorService.shutdown();

		try {
			executorService.awaitTermination(15, TimeUnit.MINUTES);
		}
		catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}

		long duration = System.currentTimeMillis() - start;

		System.out.println(
			"Expired cache branches deleted in " +
				JenkinsResultsParserUtil.toDurationString(duration));
	}

	protected static void deleteExpiredCacheBranches(
			final GitWorkingDirectory gitWorkingDirectory,
			List<RemoteConfig> localGitRemoteConfigs)
		throws GitAPIException {

		final long start = System.currentTimeMillis();

		ExecutorService executorService = Executors.newFixedThreadPool(
			localGitRemoteConfigs.size());

		for (final RemoteConfig localGitRemoteConfig : localGitRemoteConfigs) {
			executorService.execute(
				new Runnable() {

					@Override
					public void run() {
						try {
							deleteExpiredCacheBranches(
								gitWorkingDirectory, localGitRemoteConfig,
								start);
						}
						catch (GitAPIException gapie) {
							throw new RuntimeException(gapie);
						}
					}

				});
		}

		executorService.shutdown();

		try {
			executorService.awaitTermination(15, TimeUnit.MINUTES);
		}
		catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}

		long duration = System.currentTimeMillis() - start;

		System.out.println(
			"Expired cache branches deleted in " +
				JenkinsResultsParserUtil.toDurationString(duration));
	}

	protected static void deleteExpiredCacheBranches(
			GitWorkingDirectory gitWorkingDirectory, RemoteConfig remoteConfig,
			long timestamp)
		throws GitAPIException {
>>>>>>> compatible

		int branchCount = 0;
		int deleteCount = 0;
		long oldestBranchAge = Long.MIN_VALUE;

<<<<<<< HEAD
		Map<String, GitWorkingDirectory.Branch> remoteBranches =
			new HashMap<>();

		for (GitWorkingDirectory.Branch remoteBranch :
				gitWorkingDirectory.getRemoteBranches(null, remote)) {

			remoteBranches.put(remoteBranch.getName(), remoteBranch);
		}

		for (Map.Entry<String, GitWorkingDirectory.Branch> entry :
				remoteBranches.entrySet()) {

			GitWorkingDirectory.Branch remoteBranch = entry.getValue();

			Matcher matcher = _cacheTimestampBranchPattern.matcher(
				entry.getKey());
=======
		List<String> remoteRepositoryBranchNames = null;

		try {
			remoteRepositoryBranchNames =
				gitWorkingDirectory.getRemoteBranchNames(remoteConfig);
		}
		catch (GitAPIException gapie) {
			System.out.println(
				"Unable to get remote repository branch names from " +
					GitWorkingDirectory.getRemoteURL(remoteConfig));

			gapie.printStackTrace();

			return;
		}

		for (String remoteBranchName : remoteRepositoryBranchNames) {
			Matcher matcher = _cacheTimestampBranchPattern.matcher(
				remoteBranchName);
>>>>>>> compatible

			if (matcher.matches()) {
				branchCount++;

				long remoteBranchTimestamp = Long.parseLong(
					matcher.group("timestamp"));

				long branchAge = timestamp - remoteBranchTimestamp;

				if (branchAge > _BRANCH_EXPIRE_AGE_MILLIS) {
<<<<<<< HEAD
					GitWorkingDirectory.Branch remoteRepositoryBaseCacheBranch =
						remoteBranches.get(matcher.group("name"));

					if (remoteRepositoryBaseCacheBranch != null) {
						deleteRemoteRepositoryCacheBranch(
							gitWorkingDirectory,
							remoteRepositoryBaseCacheBranch);
					}

					deleteRemoteRepositoryCacheBranch(
						gitWorkingDirectory, remoteBranch);

					deleteCount++;
=======
					try {
						deleteRemoteRepositoryCacheBranch(
							gitWorkingDirectory, matcher.group("name"),
							remoteConfig);
						deleteRemoteRepositoryCacheBranch(
							gitWorkingDirectory, remoteBranchName,
							remoteConfig);

						deleteCount++;
					}
					catch (GitAPIException gapie) {
						System.out.println(
							JenkinsResultsParserUtil.combine(
								"Unable to delete cache branch ",
								remoteBranchName, " from ",
								GitWorkingDirectory.getRemoteURL(
									remoteConfig)));

						gapie.printStackTrace();
					}
>>>>>>> compatible
				}
				else {
					oldestBranchAge = Math.max(oldestBranchAge, branchAge);
				}
			}
		}

		System.out.println(
			JenkinsResultsParserUtil.combine(
				"Found ", Integer.toString(branchCount), " cache branches on ",
<<<<<<< HEAD
				remote.getRemoteURL(), " ", Integer.toString(deleteCount),
				" were deleted. ", Integer.toString(branchCount - deleteCount),
=======
				GitWorkingDirectory.getRemoteURL(remoteConfig), " ",
				Integer.toString(deleteCount), " were deleted. ",
				Integer.toString(branchCount - deleteCount),
>>>>>>> compatible
				" remain. The oldest branch is ",
				JenkinsResultsParserUtil.toDurationString(oldestBranchAge),
				" old."));
	}

<<<<<<< HEAD
	protected static void deleteExpiredCacheBranches(
		final GitWorkingDirectory gitWorkingDirectory,
		List<GitWorkingDirectory.Remote> localGitRemotes) {

		final long start = System.currentTimeMillis();

		List<Callable<Object>> callables = new ArrayList<>();

		for (final GitWorkingDirectory.Remote localGitRemote :
				localGitRemotes) {

			Callable<Object> callable = new Callable<Object>() {

				@Override
				public Object call() {
					deleteExpiredCacheBranches(
						gitWorkingDirectory, localGitRemote, start);

					return null;
				}

			};

			callables.add(callable);
		}

		ParallelExecutor<Object> parallelExecutor = new ParallelExecutor<>(
			callables, _threadPoolExecutor);

		parallelExecutor.execute();

		long duration = System.currentTimeMillis() - start;

		System.out.println(
			"Expired cache branches deleted in " +
				JenkinsResultsParserUtil.toDurationString(duration));
	}

	protected static void deleteLocalCacheBranches(
		String excludeBranchName, GitWorkingDirectory gitWorkingDirectory) {
=======
	protected static void deleteLocalCacheBranches(
			String excludeBranchName, GitWorkingDirectory gitWorkingDirectory)
		throws GitAPIException {
>>>>>>> compatible

		for (String localBranchName :
				gitWorkingDirectory.getLocalBranchNames()) {

<<<<<<< HEAD
			if (localBranchName.matches(_CACHE_BRANCH_REGEX) &&
				!localBranchName.equals(excludeBranchName)) {

				gitWorkingDirectory.deleteBranch(localBranchName, null);
=======
			if (localBranchName.matches(_cacheBranchRegex) &&
				!localBranchName.equals(excludeBranchName)) {

				gitWorkingDirectory.deleteLocalBranch(localBranchName);
>>>>>>> compatible
			}
		}
	}

	protected static void deleteRemoteCacheBranch(
<<<<<<< HEAD
		String cacheBranchName, GitWorkingDirectory gitWorkingDirectory,
		Map<String, GitWorkingDirectory.Branch> remoteBranches) {

		for (Map.Entry<String, GitWorkingDirectory.Branch> entry :
				remoteBranches.entrySet()) {

			String remoteBranchName = entry.getKey();
=======
			String cacheBranchName, GitWorkingDirectory gitWorkingDirectory,
			RemoteConfig remoteConfig)
		throws GitAPIException {

		for (String remoteBranchName :
				gitWorkingDirectory.getRemoteBranchNames(remoteConfig)) {
>>>>>>> compatible

			if (!remoteBranchName.startsWith(cacheBranchName)) {
				continue;
			}

<<<<<<< HEAD
			deleteRemoteRepositoryCacheBranch(
				gitWorkingDirectory, entry.getValue());
=======
			try {
				deleteRemoteRepositoryCacheBranch(
					gitWorkingDirectory, remoteBranchName, remoteConfig);
			}
			catch (GitAPIException gapie) {
				System.out.println(
					JenkinsResultsParserUtil.combine(
						"Unable to delete cache branch ", remoteBranchName,
						" from ",
						GitWorkingDirectory.getRemoteURL(remoteConfig)));

				gapie.printStackTrace();
			}
>>>>>>> compatible
		}
	}

	protected static void deleteRemoteRepositoryCacheBranch(
<<<<<<< HEAD
		GitWorkingDirectory gitWorkingDirectory,
		GitWorkingDirectory.Branch remoteBranch) {

		GitWorkingDirectory.Remote remote = remoteBranch.getRemote();

		if (gitWorkingDirectory.pushToRemote(true, null, remoteBranch)) {
			System.out.println(
				JenkinsResultsParserUtil.combine(
					"Deleted ", remoteBranch.getName(), " from ",
					remote.getName()));
=======
			GitWorkingDirectory gitWorkingDirectory, String remoteBranchName,
			RemoteConfig remoteConfig)
		throws GitAPIException {

		if (gitWorkingDirectory.pushToRemote(
				true, "", remoteBranchName, remoteConfig)) {

			System.out.println(
				JenkinsResultsParserUtil.combine(
					"Deleted ", remoteBranchName, " from ",
					GitWorkingDirectory.getRemoteURL(remoteConfig)));
>>>>>>> compatible
		}
		else {
			System.out.println(
				JenkinsResultsParserUtil.combine(
<<<<<<< HEAD
					"Unable to delete ", remoteBranch.getName(), " from ",
					remote.getName()));
=======
					"Unable to delete ", remoteBranchName, " from ",
					GitWorkingDirectory.getRemoteURL(remoteConfig)));
>>>>>>> compatible
		}
	}

	protected static String getCacheBranchName(
		String receiverUsername, String senderUsername, String senderSHA,
		String upstreamSHA) {

		return JenkinsResultsParserUtil.combine(
			"cache-", receiverUsername, "-", upstreamSHA, "-", senderUsername,
			"-", senderSHA);
	}

	protected static String getGitHubRemoteURL(
		String repositoryName, String userName) {

		return JenkinsResultsParserUtil.combine(
			"git@github.com:", userName, "/", repositoryName, ".git");
	}

	protected static List<String> getLocalGitRemoteURLs(
		GitWorkingDirectory gitWorkingDirectory) {

		Properties buildProperties;

		try {
			buildProperties = JenkinsResultsParserUtil.getBuildProperties();
		}
		catch (IOException ioe) {
			throw new RuntimeException("Unable to get build properties");
		}

		String gitCacheHostnamesPropertyValue = buildProperties.getProperty(
			"github.cache.hostnames");

		String[] gitCacheHostnames = gitCacheHostnamesPropertyValue.split(",");

		List<String> localGitRemoteURLs = new ArrayList<>(
			gitCacheHostnames.length);

		for (String gitCacheHostname : gitCacheHostnames) {
<<<<<<< HEAD
=======
			if (gitCacheHostname.startsWith("file:") ||
				gitCacheHostname.startsWith("http:")) {

				localGitRemoteURLs.add(gitCacheHostname);

				continue;
			}

>>>>>>> compatible
			localGitRemoteURLs.add(
				JenkinsResultsParserUtil.combine(
					"git@", gitCacheHostname, ":",
					gitWorkingDirectory.getRepositoryUsername(), "/",
					gitWorkingDirectory.getRepositoryName(), ".git"));
		}

<<<<<<< HEAD
		return validateLocalGitRemoteURLs(
			localGitRemoteURLs, gitWorkingDirectory);
	}

	protected static GitWorkingDirectory.Remote getRandomRemote(
		List<GitWorkingDirectory.Remote> remotes) {

		return remotes.get(
			JenkinsResultsParserUtil.getRandomValue(0, remotes.size() - 1));
	}

	protected static List<GitWorkingDirectory.Branch> getRemoteCacheBranches(
		GitWorkingDirectory gitWorkingDirectory,
		GitWorkingDirectory.Remote remote) {

		List<GitWorkingDirectory.Branch> remoteCacheBranches =
			new ArrayList<>();

		Map<String, GitWorkingDirectory.Branch> remoteBranches =
			new HashMap<>();

		for (GitWorkingDirectory.Branch remoteBranch :
				gitWorkingDirectory.getRemoteBranches(null, remote)) {

			remoteBranches.put(remoteBranch.getName(), remoteBranch);
		}

		for (Map.Entry<String, GitWorkingDirectory.Branch> entry :
				remoteBranches.entrySet()) {

			String remoteBranchName = entry.getKey();

			if (remoteBranchName.matches(_CACHE_BRANCH_REGEX)) {
				if (hasTimestampBranch(remoteBranchName, remoteBranches)) {
					remoteCacheBranches.add(entry.getValue());
				}
				else {
					deleteRemoteCacheBranch(
						remoteBranchName, gitWorkingDirectory, remoteBranches);
=======
		return localGitRemoteURLs;
	}

	protected static RemoteConfig getRandomRemoteConfig(
		List<RemoteConfig> remoteConfigs) {

		return remoteConfigs.get(
			JenkinsResultsParserUtil.getRandomValue(
				0, remoteConfigs.size() - 1));
	}

	protected static List<String> getRemoteCacheBranchNames(
			GitWorkingDirectory gitWorkingDirectory, RemoteConfig remoteConfig)
		throws GitAPIException {

		List<String> remoteCacheBranchNames = new ArrayList<>();

		List<String> remoteBranchNames =
			gitWorkingDirectory.getRemoteBranchNames(remoteConfig);

		for (String remoteBranchName : remoteBranchNames) {
			if (remoteBranchName.matches(_cacheBranchRegex)) {
				if (hasTimestampBranch(remoteBranchName, remoteBranchNames)) {
					remoteCacheBranchNames.add(remoteBranchName);
				}
				else {
					deleteRemoteCacheBranch(
						remoteBranchName, gitWorkingDirectory, remoteConfig);
>>>>>>> compatible
				}
			}
		}

<<<<<<< HEAD
		return remoteCacheBranches;
	}

	protected static boolean hasTimestampBranch(
		String cacheBranchName,
		Map<String, GitWorkingDirectory.Branch> remoteBranches) {

		for (String remoteBranchName : remoteBranches.keySet()) {
=======
		return remoteCacheBranchNames;
	}

	protected static boolean hasTimestampBranch(
		String cacheBranchName, List<String> remoteBranchNames) {

		for (String remoteBranchName : remoteBranchNames) {
>>>>>>> compatible
			Matcher matcher = _cacheTimestampBranchPattern.matcher(
				remoteBranchName);

			if (matcher.matches()) {
				return true;
			}
		}

		return false;
	}

	protected static boolean isBranchCached(
		String branchName, GitWorkingDirectory gitWorkingDirectory,
<<<<<<< HEAD
		List<GitWorkingDirectory.Remote> remotes) {

		for (GitWorkingDirectory.Remote remote : remotes) {
			if (gitWorkingDirectory.branchExists(branchName, remote)) {
				continue;
=======
		List<RemoteConfig> remoteConfigs) {

		for (RemoteConfig remoteConfig : remoteConfigs) {
			try {
				if (gitWorkingDirectory.branchExists(
						branchName, remoteConfig)) {

					continue;
				}
			}
			catch (GitAPIException gapie) {
				System.out.println(
					JenkinsResultsParserUtil.combine(
						"Unable to determine if branch ", branchName,
						" exists on ",
						GitWorkingDirectory.getRemoteURL(remoteConfig)));

				gapie.printStackTrace();
>>>>>>> compatible
			}

			return false;
		}

		return true;
	}

<<<<<<< HEAD
	protected static Map<GitWorkingDirectory.Remote, Boolean> pushToAllRemotes(
		final boolean force, final GitWorkingDirectory gitWorkingDirectory,
		final GitWorkingDirectory.Branch localBranch,
		final String remoteBranchName,
		final List<GitWorkingDirectory.Remote> remotes) {

		if (localBranch != null) {
			String localBranchName = localBranch.getName();
			GitWorkingDirectory.Branch currentBranch =
				gitWorkingDirectory.getCurrentBranch();

			if ((currentBranch == null) ||
				!localBranchName.equals(currentBranch.getName())) {

				gitWorkingDirectory.checkoutBranch(localBranch, "-f");
			}
=======
	protected static Map<RemoteConfig, Boolean> pushToAllRemotes(
			final boolean force, final GitWorkingDirectory gitWorkingDirectory,
			final String localBranchName, final String remoteBranchName,
			List<RemoteConfig> remoteConfigs)
		throws GitAPIException {

		if (!localBranchName.isEmpty() &&
			!localBranchName.equals(gitWorkingDirectory.getCurrentBranch())) {

			gitWorkingDirectory.checkoutBranch(localBranchName, "-f");
>>>>>>> compatible
		}

		final long start = System.currentTimeMillis();

<<<<<<< HEAD
		final Map<GitWorkingDirectory.Remote, Boolean> resultsMap =
			Collections.synchronizedMap(
				new HashMap<GitWorkingDirectory.Remote, Boolean>(
					remotes.size()));

		List<Callable<Boolean>> callables = new ArrayList<>();

		for (final GitWorkingDirectory.Remote remote : remotes) {
			Callable<Boolean> callable = new Callable<Boolean>() {

				@Override
				public Boolean call() {
					Boolean result = gitWorkingDirectory.pushToRemote(
						force, localBranch, remoteBranchName, remote);

					resultsMap.put(remote, result);

					return result;
				}

			};

			callables.add(callable);
		}

		ParallelExecutor<Boolean> parallelExecutor = new ParallelExecutor<>(
			callables, _threadPoolExecutor);

		parallelExecutor.execute();

		long duration = System.currentTimeMillis() - start;

		if (localBranch == null) {
			System.out.println(
				JenkinsResultsParserUtil.combine(
					"Deleted ", remoteBranchName, " on ",
					Integer.toString(remotes.size()), " git nodes in ",
					JenkinsResultsParserUtil.toDurationString(duration)));
		}
		else {
			System.out.println(
				JenkinsResultsParserUtil.combine(
					"Pushed ", localBranch.getName(), " to ", remoteBranchName,
					" on ", Integer.toString(remotes.size()), " git nodes in ",
					JenkinsResultsParserUtil.toDurationString(duration)));
		}
=======
		final Map<RemoteConfig, Boolean> resultsMap =
			Collections.synchronizedMap(
				new HashMap<RemoteConfig, Boolean>(remoteConfigs.size()));

		ExecutorService executorService = Executors.newFixedThreadPool(
			remoteConfigs.size());

		for (final RemoteConfig remoteConfig : remoteConfigs) {
			executorService.execute(
				new Runnable() {

					@Override
					public void run() {
						try {
							resultsMap.put(
								remoteConfig,
								gitWorkingDirectory.pushToRemote(
									force, localBranchName, remoteBranchName,
									remoteConfig));
						}
						catch (GitAPIException gapie) {
							System.out.println(
								JenkinsResultsParserUtil.combine(
									"Unable to push ", localBranchName, " to ",
									GitWorkingDirectory.getRemoteURL(
										remoteConfig),
									":", remoteBranchName));

							gapie.printStackTrace();
						}
					}

				});
		}

		executorService.shutdown();

		try {
			executorService.awaitTermination(30, TimeUnit.MINUTES);
		}
		catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}

		long duration = System.currentTimeMillis() - start;

		System.out.println(
			JenkinsResultsParserUtil.combine(
				"Pushed ", localBranchName, " to ", remoteBranchName, " on ",
				Integer.toString(remoteConfigs.size()), " git nodes in ",
				JenkinsResultsParserUtil.toDurationString(duration)));
>>>>>>> compatible

		return resultsMap;
	}

	protected static String synchronizeToLocalGit(
			GitWorkingDirectory gitWorkingDirectory, String receiverUsername,
			int retryCount, String senderBranchName, String senderUsername,
			String senderBranchSHA, String upstreamBranchSHA)
<<<<<<< HEAD
		throws IOException {
=======
		throws GitAPIException, IOException {
>>>>>>> compatible

		long start = System.currentTimeMillis();

		File repositoryDirectory = gitWorkingDirectory.getWorkingDirectory();

<<<<<<< HEAD
		GitWorkingDirectory.Branch originalBranch =
			gitWorkingDirectory.getCurrentBranch();

		if (originalBranch == null) {
			gitWorkingDirectory.reset("--hard");

			originalBranch = gitWorkingDirectory.getBranch(
				gitWorkingDirectory.getUpstreamBranchName(), null);

			if (originalBranch != null) {
				gitWorkingDirectory.checkoutBranch(originalBranch);
			}
		}
=======
		String originalBranchName = gitWorkingDirectory.getCurrentBranch();
>>>>>>> compatible

		System.out.println(
			JenkinsResultsParserUtil.combine(
				"Starting synchronization with local-git. Current repository ",
				"directory is ", repositoryDirectory.getPath(), ". Current ",
<<<<<<< HEAD
				"branch is ", originalBranch.getName(), "."));

		GitWorkingDirectory.Remote senderRemote = null;

		try {
			senderRemote = gitWorkingDirectory.addRemote(
				true, "sender-temp",
				getGitHubRemoteURL(
					gitWorkingDirectory.getRepositoryName(), senderUsername));
=======
				"branch is ", originalBranchName, "."));

		RemoteConfig senderRemoteConfig = null;
		RemoteConfig upstreamRemoteConfig = null;

		try {
			senderRemoteConfig = gitWorkingDirectory.addRemote(
				true, "sender-temp",
				getGitHubRemoteURL(
					gitWorkingDirectory.getRepositoryName(), senderUsername));
			upstreamRemoteConfig = gitWorkingDirectory.getRemoteConfig(
				"upstream");
>>>>>>> compatible

			boolean pullRequest = !upstreamBranchSHA.equals(senderBranchSHA);

			String cacheBranchName = getCacheBranchName(
				receiverUsername, senderUsername, senderBranchSHA,
				upstreamBranchSHA);

			String upstreamBranchName =
				gitWorkingDirectory.getUpstreamBranchName();

<<<<<<< HEAD
			List<GitWorkingDirectory.Remote> localGitRemotes = null;

			try {
				localGitRemotes = getLocalGitRemotes(gitWorkingDirectory);
=======
			List<RemoteConfig> localGitRemoteConfigs = null;

			try {
				localGitRemoteConfigs = getLocalGitRemoteConfigs(
					gitWorkingDirectory);
>>>>>>> compatible

				deleteLocalCacheBranches(cacheBranchName, gitWorkingDirectory);

				deleteExpiredCacheBranches(
<<<<<<< HEAD
					gitWorkingDirectory, localGitRemotes);

				if (isBranchCached(
						cacheBranchName, gitWorkingDirectory,
						localGitRemotes)) {
=======
					gitWorkingDirectory, localGitRemoteConfigs);

				if (isBranchCached(
						cacheBranchName, gitWorkingDirectory,
						localGitRemoteConfigs)) {
>>>>>>> compatible

					System.out.println(
						JenkinsResultsParserUtil.combine(
							"Cache branch ", cacheBranchName,
							" already exists"));

<<<<<<< HEAD
					GitWorkingDirectory.Remote localGitRemote = getRandomRemote(
						localGitRemotes);

					GitWorkingDirectory.Branch remoteCacheBranch =
						gitWorkingDirectory.getBranch(
							cacheBranchName, localGitRemote);

					gitWorkingDirectory.fetch(null, remoteCacheBranch);

					gitWorkingDirectory.deleteBranch(cacheBranchName, null);

					gitWorkingDirectory.createLocalBranch(
						cacheBranchName, true, remoteCacheBranch.getSHA());
=======
					RemoteConfig localGitRemoteConfig = getRandomRemoteConfig(
						localGitRemoteConfigs);

					gitWorkingDirectory.fetch(
						cacheBranchName, cacheBranchName, localGitRemoteConfig);
>>>>>>> compatible

					if (!gitWorkingDirectory.branchExists(
							upstreamBranchName, null)) {

						updateLocalUpstreamBranch(
<<<<<<< HEAD
							gitWorkingDirectory, upstreamBranchSHA);
					}

					updateCacheBranchTimestamp(
						cacheBranchName, gitWorkingDirectory, localGitRemotes);
=======
							gitWorkingDirectory, upstreamBranchName,
							upstreamBranchSHA, upstreamRemoteConfig);
					}

					updateCacheBranchTimestamp(
						cacheBranchName, gitWorkingDirectory,
						localGitRemoteConfigs);
>>>>>>> compatible

					return cacheBranchName;
				}

<<<<<<< HEAD
				GitWorkingDirectory.Branch localCacheBranch =
					gitWorkingDirectory.getBranch(cacheBranchName, null);

				if (localCacheBranch == null) {
					localCacheBranch = gitWorkingDirectory.createLocalBranch(
						cacheBranchName, true, null);
				}

				senderBranchName = senderBranchName.trim();

				gitWorkingDirectory.fetch(
					localCacheBranch,
					gitWorkingDirectory.getBranch(
						senderBranchName, senderRemote));

				updateLocalUpstreamBranch(
					gitWorkingDirectory, upstreamBranchSHA);
=======
				gitWorkingDirectory.fetch(
					cacheBranchName, senderBranchName, senderRemoteConfig);

				updateLocalUpstreamBranch(
					gitWorkingDirectory, upstreamBranchName, upstreamBranchSHA,
					upstreamRemoteConfig);
>>>>>>> compatible

				gitWorkingDirectory.createLocalBranch(
					cacheBranchName, true, senderBranchSHA);

				if (pullRequest) {
<<<<<<< HEAD
					gitWorkingDirectory.checkoutBranch(localCacheBranch);

					gitWorkingDirectory.rebase(
						true,
						gitWorkingDirectory.getBranch(upstreamBranchName, null),
						localCacheBranch);
				}

				cacheBranches(
					gitWorkingDirectory, localCacheBranch, localGitRemotes,
					"liferay");
=======
					gitWorkingDirectory.checkoutBranch(cacheBranchName);

					gitWorkingDirectory.rebase(
						true, upstreamBranchName, cacheBranchName);
				}

				cacheBranches(
					gitWorkingDirectory, cacheBranchName, localGitRemoteConfigs,
					upstreamBranchName, "liferay");
>>>>>>> compatible

				return cacheBranchName;
			}
			catch (Exception e) {
				if (retryCount == 1) {
					throw e;
				}

<<<<<<< HEAD
				localGitRemotes = null;
				senderRemote = null;
=======
				localGitRemoteConfigs = null;
				senderRemoteConfig = null;
>>>>>>> compatible

				System.out.println(
					"Synchronization with local-git failed. Retrying.");

				e.printStackTrace();

<<<<<<< HEAD
				gitWorkingDirectory.checkoutBranch(originalBranch);
=======
				gitWorkingDirectory.checkoutBranch(originalBranchName);
>>>>>>> compatible

				return synchronizeToLocalGit(
					gitWorkingDirectory, receiverUsername, retryCount + 1,
					senderBranchName, senderUsername, senderBranchSHA,
					upstreamBranchSHA);
			}
			finally {
<<<<<<< HEAD
				if (localGitRemotes != null) {
					try {
						gitWorkingDirectory.removeRemotes(localGitRemotes);
=======
				if (localGitRemoteConfigs != null) {
					try {
						gitWorkingDirectory.removeRemotes(
							localGitRemoteConfigs);
>>>>>>> compatible
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (gitWorkingDirectory.branchExists(
<<<<<<< HEAD
						originalBranch.getName(), null)) {

					gitWorkingDirectory.checkoutBranch(originalBranch);
				}
				else {
					checkoutUpstreamBranch(
						gitWorkingDirectory, upstreamBranchSHA);
				}

				gitWorkingDirectory.deleteBranch(cacheBranchName, null);
			}
		}
		finally {
			if (senderRemote != null) {
				try {
					gitWorkingDirectory.removeRemote(senderRemote);
=======
						originalBranchName, null)) {

					gitWorkingDirectory.checkoutBranch(originalBranchName);
				}
				else {
					checkoutUpstreamBranch(gitWorkingDirectory);
				}

				gitWorkingDirectory.deleteLocalBranch(cacheBranchName);
			}
		}
		finally {
			if (senderRemoteConfig != null) {
				try {
					gitWorkingDirectory.removeRemote(senderRemoteConfig);
>>>>>>> compatible
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}

			System.out.println(
				"Synchronization with local Git completed in " +
					JenkinsResultsParserUtil.toDurationString(
						System.currentTimeMillis() - start));
		}
	}

	protected static void updateCacheBranchTimestamp(
		final String cacheBranchName,
		final GitWorkingDirectory gitWorkingDirectory,
<<<<<<< HEAD
		List<GitWorkingDirectory.Remote> localGitRemotes) {

		long start = System.currentTimeMillis();

		GitWorkingDirectory.Remote localGitRemote = getRandomRemote(
			localGitRemotes);

		List<GitWorkingDirectory.Branch> remoteCacheBranches =
			getRemoteCacheBranches(gitWorkingDirectory, localGitRemote);

		boolean updated = false;

		for (GitWorkingDirectory.Branch remoteCacheBranch :
				remoteCacheBranches) {

			String remoteCacheBranchName = remoteCacheBranch.getName();

			Matcher matcher = _cacheTimestampBranchPattern.matcher(
				remoteCacheBranchName);

			if (remoteCacheBranchName.contains(cacheBranchName) &&
				matcher.matches()) {

				if (updated) {
					pushToAllRemotes(
						true, gitWorkingDirectory, null, remoteCacheBranchName,
						localGitRemotes);

					continue;
				}

				long currentTimestamp = System.currentTimeMillis();
				long existingTimestamp = Long.parseLong(
					matcher.group("timestamp"));

				if ((currentTimestamp - existingTimestamp) <
						(1000 * 60 * 60 * 24)) {

					return;
				}

				String newTimestampBranchName =
					JenkinsResultsParserUtil.combine(
						cacheBranchName, "-", Long.toString(currentTimestamp));

				System.out.println(
					JenkinsResultsParserUtil.combine(
						"\nUpdating cache branch timestamp from ",
						remoteCacheBranchName, "to ", newTimestampBranchName));

				System.out.println(
					JenkinsResultsParserUtil.combine(
						"Updating existing timestamp for branch ",
						remoteCacheBranchName, " to ", newTimestampBranchName));

				GitWorkingDirectory.Branch currentBranch =
					gitWorkingDirectory.getCurrentBranch();

				if (currentBranch == null) {
					currentBranch = gitWorkingDirectory.getBranch(
						gitWorkingDirectory.getUpstreamBranchName(), null);
				}

				GitWorkingDirectory.Branch newTimestampBranch =
					gitWorkingDirectory.createLocalBranch(
						newTimestampBranchName);

				gitWorkingDirectory.fetch(
					newTimestampBranch, remoteCacheBranch);

				try {
					pushToAllRemotes(
						true, gitWorkingDirectory, newTimestampBranch,
						newTimestampBranchName, localGitRemotes);
					pushToAllRemotes(
						true, gitWorkingDirectory, null, remoteCacheBranchName,
						localGitRemotes);

					updated = true;
				}
				finally {
					if ((currentBranch != null) &&
						gitWorkingDirectory.branchExists(
							currentBranch.getName(), null)) {

						gitWorkingDirectory.checkoutBranch(currentBranch);
					}
					else {
						checkoutUpstreamBranch(gitWorkingDirectory, null);
					}

					gitWorkingDirectory.deleteBranch(newTimestampBranch);
				}
			}
		}

		System.out.println(
			JenkinsResultsParserUtil.combine(
				"Cache branch timestamp updated in ",
				JenkinsResultsParserUtil.toDurationString(
					System.currentTimeMillis() - start)));
	}

	protected static GitWorkingDirectory.Branch updateLocalUpstreamBranch(
		GitWorkingDirectory gitWorkingDirectory, String upstreamBranchSHA) {

		String upstreamBranchName = gitWorkingDirectory.getUpstreamBranchName();

		GitWorkingDirectory.Branch remoteUpstreamBranch =
			gitWorkingDirectory.getBranch(
				upstreamBranchName, gitWorkingDirectory.getRemote("upstream"));

		GitWorkingDirectory.Branch localUpstreamBranch =
			gitWorkingDirectory.getBranch(upstreamBranchName, null);

		if (localUpstreamBranch == null) {
			localUpstreamBranch = gitWorkingDirectory.createLocalBranch(
				upstreamBranchName);

			gitWorkingDirectory.fetch(
				localUpstreamBranch, remoteUpstreamBranch);
		}

		String localUpstreamBranchSHA = localUpstreamBranch.getSHA();

		String remoteUpstreamBranchSHA = remoteUpstreamBranch.getSHA();

		if ((upstreamBranchSHA != null) &&
			!remoteUpstreamBranchSHA.equals(upstreamBranchSHA)) {

			remoteUpstreamBranchSHA = upstreamBranchSHA;
		}

		if (localUpstreamBranchSHA.equals(remoteUpstreamBranchSHA)) {
			return localUpstreamBranch;
		}
=======
		List<RemoteConfig> localGitRemoteConfigs) {

		long start = System.currentTimeMillis();

		RemoteConfig localGitRemoteConfig = getRandomRemoteConfig(
			localGitRemoteConfigs);

		try {
			List<String> remoteCacheBranchNames = getRemoteCacheBranchNames(
				gitWorkingDirectory, localGitRemoteConfig);

			boolean updated = false;

			for (String remoteCacheBranchName : remoteCacheBranchNames) {
				Matcher matcher = _cacheTimestampBranchPattern.matcher(
					remoteCacheBranchName);

				if (remoteCacheBranchName.contains(cacheBranchName) &&
					matcher.matches()) {

					if (updated) {
						pushToAllRemotes(
							true, gitWorkingDirectory, "",
							remoteCacheBranchName, localGitRemoteConfigs);

						continue;
					}

					long currentTimestamp = System.currentTimeMillis();
					long existingTimestamp = Long.parseLong(
						matcher.group("timestamp"));

					if ((currentTimestamp - existingTimestamp) <
							(1000 * 60 * 60 * 24)) {

						return;
					}

					String newTimestampBranchName =
						JenkinsResultsParserUtil.combine(
							cacheBranchName, "-",
							Long.toString(currentTimestamp));

					System.out.println(
						JenkinsResultsParserUtil.combine(
							"\nUpdating cache branch timestamp from ",
							remoteCacheBranchName, "to ",
							newTimestampBranchName));

					System.out.println(
						JenkinsResultsParserUtil.combine(
							"Updating existing timestamp for branch ",
							remoteCacheBranchName, " to ",
							newTimestampBranchName));

					String currentBranch =
						gitWorkingDirectory.getCurrentBranch();

					gitWorkingDirectory.fetch(
						newTimestampBranchName, remoteCacheBranchName,
						localGitRemoteConfig);

					gitWorkingDirectory.createLocalBranch(
						newTimestampBranchName, true,
						gitWorkingDirectory.getBranchSHA(
							remoteCacheBranchName, localGitRemoteConfig));

					try {
						pushToAllRemotes(
							true, gitWorkingDirectory, newTimestampBranchName,
							newTimestampBranchName, localGitRemoteConfigs);
						pushToAllRemotes(
							true, gitWorkingDirectory, "",
							remoteCacheBranchName, localGitRemoteConfigs);

						updated = true;
					}
					finally {
						if (gitWorkingDirectory.branchExists(
								currentBranch, null)) {

							gitWorkingDirectory.checkoutBranch(currentBranch);
						}
						else {
							checkoutUpstreamBranch(gitWorkingDirectory);
						}

						gitWorkingDirectory.deleteLocalBranch(
							newTimestampBranchName);
					}
				}
			}
		}
		catch (GitAPIException gapie) {
			System.out.println(
				JenkinsResultsParserUtil.combine(
					"Unable to update cache branch timestamp on branch ",
					cacheBranchName));

			gapie.printStackTrace();
		}

		System.out.println(
			"Cache branch timestamp updated in " +
				JenkinsResultsParserUtil.toDurationString(
					System.currentTimeMillis() - start));
	}

	protected static void updateLocalUpstreamBranch(
			GitWorkingDirectory gitWorkingDirectory, String upstreamBranchName,
			String upstreamBranchSHA, RemoteConfig upstreamRemoteConfig)
		throws GitAPIException {
>>>>>>> compatible

		gitWorkingDirectory.rebaseAbort();

		gitWorkingDirectory.clean();

<<<<<<< HEAD
		gitWorkingDirectory.reset("--hard");

		gitWorkingDirectory.fetch(null, remoteUpstreamBranch);

		String tempBranchName = "temp-" + System.currentTimeMillis();

		GitWorkingDirectory.Branch tempBranch = null;

		try {
			tempBranch = gitWorkingDirectory.createLocalBranch(
				tempBranchName, true, remoteUpstreamBranchSHA);

			gitWorkingDirectory.checkoutBranch(tempBranch, "-f");

			gitWorkingDirectory.deleteBranch(upstreamBranchName, null);

			localUpstreamBranch = gitWorkingDirectory.createLocalBranch(
				remoteUpstreamBranch.getName(), true, remoteUpstreamBranchSHA);

			gitWorkingDirectory.checkoutBranch(localUpstreamBranch);
		}
		finally {
			if (tempBranch != null) {
				gitWorkingDirectory.deleteBranch(tempBranch);
			}
		}

		return localUpstreamBranch;
	}

	protected static List<String> validateLocalGitRemoteURLs(
		List<String> localGitRemoteURLs,
		final GitWorkingDirectory gitWorkingDirectory) {

		List<Callable<String>> callables = new ArrayList<>();

		for (final String localGitRemoteURL : localGitRemoteURLs) {
			Callable<String> callable = new Callable<String>() {

				@Override
				public String call() {
					if (gitWorkingDirectory.isRemoteRepositoryAlive(
							localGitRemoteURL)) {

						return localGitRemoteURL;
					}

					return null;
				}

			};

			callables.add(callable);
		}

		ParallelExecutor<String> parallelExecutor = new ParallelExecutor<>(
			callables, _threadPoolExecutor);

		List<String> validatedLocalGitRemoteURLs = new ArrayList<>();

		for (String validatedLocalGitRemoteURL : parallelExecutor.execute()) {
			if (validatedLocalGitRemoteURL != null) {
				validatedLocalGitRemoteURLs.add(validatedLocalGitRemoteURL);
			}
		}

		return validatedLocalGitRemoteURLs;
=======
		gitWorkingDirectory.reset(null, ResetType.HARD);

		gitWorkingDirectory.fetch(null, upstreamRemoteConfig);

		String tempBranchName = "temp-" + System.currentTimeMillis();

		try {
			gitWorkingDirectory.createLocalBranch(
				tempBranchName, true, upstreamBranchSHA);

			gitWorkingDirectory.checkoutBranch(tempBranchName, "-f");

			gitWorkingDirectory.deleteLocalBranch(upstreamBranchName);

			gitWorkingDirectory.createLocalBranch(
				upstreamBranchName, true, upstreamBranchSHA);

			gitWorkingDirectory.checkoutBranch(upstreamBranchName);
		}
		finally {
			gitWorkingDirectory.deleteLocalBranch(tempBranchName);
		}
>>>>>>> compatible
	}

	private static final long _BRANCH_EXPIRE_AGE_MILLIS =
		1000 * 60 * 60 * 24 * 2;

<<<<<<< HEAD
	private static final String _CACHE_BRANCH_REGEX = ".*cache-.+-.+-.+-[^-]+";

	private static final Pattern _cacheTimestampBranchPattern = Pattern.compile(
		"(?<name>cache-[^-]+-[^-]+-[^-]+-[^-]+)-(?<timestamp>\\d+)");
	private static final ThreadPoolExecutor _threadPoolExecutor =
		JenkinsResultsParserUtil.getNewThreadPoolExecutor(5, true);
=======
	private static final String _cacheBranchRegex = ".*cache-.+-.+-.+-[^-]+";
	private static final Pattern _cacheTimestampBranchPattern = Pattern.compile(
		"(?<name>cache-.*)-(?<timestamp>\\d+)");
>>>>>>> compatible

}