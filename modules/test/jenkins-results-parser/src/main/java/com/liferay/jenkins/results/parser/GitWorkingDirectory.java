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
=======
import com.jcraft.jsch.Session;

>>>>>>> compatible
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
=======
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CleanCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.DeleteBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.LsRemoteCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RebaseCommand;
import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryState;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig.Host;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.RemoteRefUpdate;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.URIish;
>>>>>>> compatible

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 * @author Peter Yoo
 */
public class GitWorkingDirectory {

<<<<<<< HEAD
	public static String getGitHubUserName(Remote remote) {
		String remoteURL = remote.getRemoteURL();
=======
	public static String getGitHubUserName(RemoteConfig remoteConfig)
		throws GitAPIException {

		String remoteURL = getRemoteURL(remoteConfig);
>>>>>>> compatible

		if (!remoteURL.contains("github.com")) {
			throw new IllegalArgumentException(
				JenkinsResultsParserUtil.combine(
<<<<<<< HEAD
					remote.getName(),
=======
					remoteConfig.getName(),
>>>>>>> compatible
					" does not point to a GitHub repository"));
		}

		String userName = null;

		if (remoteURL.startsWith("https://github.com/")) {
			userName = remoteURL.substring("https://github.com/".length());
		}
		else {
			userName = remoteURL.substring("git@github.com:".length());
		}

		return userName.substring(0, userName.indexOf("/"));
	}

	public GitWorkingDirectory(
<<<<<<< HEAD
			String upstreamBranchName, String workingDirectoryPath)
		throws IOException {

		this(upstreamBranchName, workingDirectoryPath, null);
	}

	public GitWorkingDirectory(
			String upstreamBranchName, String workingDirectoryPath,
			String repositoryName)
		throws IOException {

		_upstreamBranchName = upstreamBranchName;

		setWorkingDirectory(workingDirectoryPath);

		waitForIndexLock();

=======
			String upstreamBranchName, String workingDirectory)
		throws GitAPIException, IOException {

		this(upstreamBranchName, workingDirectory, null);
	}

	public GitWorkingDirectory(
			String upstreamBranchName, String workingDirectory,
			String repositoryName)
		throws GitAPIException, IOException {

		_upstreamBranchName = upstreamBranchName;

		setWorkingDirectory(workingDirectory);

		waitForIndexLock();

		FileRepositoryBuilder fileRepositoryBuilder =
			new FileRepositoryBuilder();

		fileRepositoryBuilder.setGitDir(_gitDirectory);
		fileRepositoryBuilder.setWorkTree(_workingDirectory);

		_repository = fileRepositoryBuilder.build();

		_git = new Git(_repository);

>>>>>>> compatible
		if ((repositoryName == null) || repositoryName.equals("")) {
			repositoryName = loadRepositoryName();
		}

		_repositoryName = repositoryName;

<<<<<<< HEAD
		if (_publicOnlyRepositoryNames.contains(_repositoryName)) {
			setUpstreamRemoteToPublicRepository();
		}
		else {
			if (_privateOnlyRepositoryNames.contains(_repositoryName)) {
				setUpstreamRemoteToPrivateRepository();
			}
			else {
				if (upstreamBranchName.equals("master")) {
					setUpstreamRemoteToPublicRepository();
				}
				else {
					setUpstreamRemoteToPrivateRepository();
				}
			}
		}

		_repositoryUsername = loadRepositoryUsername();
	}

	public Remote addRemote(
		boolean force, String remoteName, String remoteURL) {

		if (remoteExists(remoteName)) {
			if (force) {
				removeRemote(getRemote(remoteName));
			}
			else {
				throw new IllegalArgumentException(
=======
		_repositoryUsername = loadRepositoryUsername();
	}

	public RemoteConfig addRemote(
			boolean force, String remoteName, String remoteURL)
		throws GitAPIException {

		System.out.println(
			JenkinsResultsParserUtil.combine(
				"Adding remote ", remoteName, " with url: ", remoteURL));

		RemoteConfig remoteConfig = getRemoteConfig(remoteName);

		if (remoteConfig != null) {
			if (force) {
				removeRemote(remoteConfig);
			}
			else {
				throw new RuntimeException(
>>>>>>> compatible
					JenkinsResultsParserUtil.combine(
						"Remote ", remoteName, " already exists"));
			}
		}

<<<<<<< HEAD
		ExecutionResult executionResult = executeBashCommands(
			JenkinsResultsParserUtil.combine(
				"git remote add ", remoteName, " ", remoteURL));

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to add remote ", remoteName, "\n",
					executionResult.getStandardError()));
		}

		return getRemote(remoteName);
	}

	public boolean branchExists(String branchName, Remote remote) {
		if (getBranch(branchName, remote) != null) {
			return true;
		}

		return false;
	}

	public void checkoutBranch(Branch branch) {
		checkoutBranch(branch, "-f");
	}

	public void checkoutBranch(Branch branch, String options) {
=======
		Process process = null;

		try {
			process = JenkinsResultsParserUtil.executeBashCommands(
				true, _workingDirectory, 1000 * 10,
				JenkinsResultsParserUtil.combine(
					"git remote add ", remoteName, " ", remoteURL));
		}
		catch (InterruptedException | IOException e) {
			throw new RuntimeException("Unable to add remote " + remoteName, e);
		}

		if ((process != null) && (process.exitValue() != 0)) {
			try {
				System.out.println(
					JenkinsResultsParserUtil.readInputStream(
						process.getErrorStream()));
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}

			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to add remote ", remoteName));
		}

		_remoteConfigs = null;

		return getRemoteConfig(remoteName);
	}

	public boolean branchExists(String branchName, RemoteConfig remoteConfig)
		throws GitAPIException {

		List<String> branchNames = null;

		if (remoteConfig == null) {
			branchNames = getLocalBranchNames();
		}
		else {
			branchNames = getRemoteBranchNames(remoteConfig);
		}

		return branchNames.contains(branchName);
	}

	public void checkoutBranch(String branchName) throws GitAPIException {
		checkoutBranch(branchName, "-f");
	}

	public void checkoutBranch(String branchName, String options)
		throws GitAPIException {

		String currentBranchName = getCurrentBranch();

		List<String> localBranchNames = getLocalBranchNames();

		if (!branchName.contains("/") &&
			!localBranchNames.contains(branchName)) {

			throw new IllegalArgumentException(
				JenkinsResultsParserUtil.combine(
					"Unable to checkout ", branchName,
					" because it does not exist"));
		}

		if (currentBranchName.equals(branchName)) {
			System.out.println(branchName + " is already checked out");

			return;
		}

		System.out.println(
			JenkinsResultsParserUtil.combine(
				"The current branch is ", currentBranchName,
				". Checking out branch ", branchName, "."));

>>>>>>> compatible
		waitForIndexLock();

		StringBuilder sb = new StringBuilder();

		sb.append("git checkout ");

		if (options != null) {
			sb.append(options);
			sb.append(" ");
		}

<<<<<<< HEAD
		String branchName = branch.getName();

		sb.append(branchName);

		ExecutionResult executionResult = executeBashCommands(
			1, 1000 * 60 * 10, sb.toString());

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to checkout ", branchName, "\n",
					executionResult.getStandardError()));
=======
		sb.append(branchName);

		Process process = null;

		try {
			process = JenkinsResultsParserUtil.executeBashCommands(
				true, _workingDirectory, 1000 * 60 * 10, sb.toString());
		}
		catch (InterruptedException | IOException e) {
			throw new RuntimeException(
				"Unable to checkout branch " + branchName, e);
		}

		if ((process != null) && (process.exitValue() != 0)) {
			try {
				System.out.println(
					JenkinsResultsParserUtil.readInputStream(
						process.getErrorStream()));
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}

			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to checkout branch ", branchName));
>>>>>>> compatible
		}

		int timeout = 0;

		File headFile = new File(_gitDirectory, "HEAD");

<<<<<<< HEAD
		String expectedContent = JenkinsResultsParserUtil.combine(
			"ref: refs/heads/", branchName);
=======
		String expectedContent = null;

		if (!branchName.contains("/")) {
			expectedContent = JenkinsResultsParserUtil.combine(
				"ref: refs/heads/", branchName);
		}
		else {
			int i = branchName.indexOf("/");

			String remoteBranchName = branchName.substring(i + 1);

			String remoteName = branchName.substring(0, i);

			expectedContent = getBranchSHA(
				remoteBranchName, getRemoteConfig(remoteName));
		}
>>>>>>> compatible

		while (true) {
			String headContent = null;

			try {
				headContent = JenkinsResultsParserUtil.read(headFile);
			}
			catch (IOException ioe) {
				throw new RuntimeException(
					"Unable to read file " + headFile.getPath(), ioe);
			}

			headContent = headContent.trim();

			if (headContent.equals(expectedContent)) {
				return;
			}

			System.out.println(
				JenkinsResultsParserUtil.combine(
					"HEAD file content is currently: ", headContent,
					". Waiting for branch to be updated."));

			JenkinsResultsParserUtil.sleep(5000);

			timeout++;

			if (timeout >= 59) {
<<<<<<< HEAD
				Branch currentBranch = getCurrentBranch();

				if ((currentBranch != null) &&
					branchName.equals(currentBranch.getName())) {

=======
				if (branchName.equals(getCurrentBranch())) {
>>>>>>> compatible
					return;
				}

				throw new RuntimeException(
					"Unable to checkout branch " + branchName);
			}
		}
	}

<<<<<<< HEAD
	public void cherryPick(Commit commit) {
		String cherryPickCommand = JenkinsResultsParserUtil.combine(
			"git cherry-pick " + commit.getSHA());

		ExecutionResult executionResult = executeBashCommands(
			cherryPickCommand);

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to cherry pick commit ", commit.getSHA(), "\n",
					executionResult.getStandardError()));
		}
	}

	public void clean() {
		clean(null);
	}

	public void clean(File workingDirectory) {
		if (workingDirectory == null) {
			workingDirectory = _workingDirectory;
		}

		ExecutionResult executionResult = executeBashCommands(
			1, 1000 * 60 * 10, "git clean -dfx");

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to clean repository\n",
					executionResult.getStandardError()));
		}
	}

	public void commitFileToCurrentBranch(String fileName, String message) {
		String commitCommand = JenkinsResultsParserUtil.combine(
			"git commit -m \"", message, "\" ", fileName);

		ExecutionResult executionResult = executeBashCommands(commitCommand);

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to commit file ", fileName, "\n",
					executionResult.getStandardError()));
		}
	}

	public void commitStagedFilesToCurrentBranch(String message) {
		String commitCommand = JenkinsResultsParserUtil.combine(
			"git commit -m \"", message, "\" ");

		ExecutionResult executionResult = executeBashCommands(commitCommand);

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to commit staged files", "\n",
					executionResult.getStandardError()));
		}
	}

	public Branch createLocalBranch(String branchName) {
		return createLocalBranch(branchName, false, null);
	}

	public Branch createLocalBranch(
		String branchName, boolean force, String startPoint) {

		Branch currentBranch = getCurrentBranch();

		Branch tempBranch = null;

		try {
			if (branchName.equals(currentBranch.getName())) {
				String tempBranchName = "temp-" + System.currentTimeMillis();

				tempBranch = createLocalBranch(tempBranchName);

				checkoutBranch(tempBranch);
			}

			StringBuilder sb = new StringBuilder();

			sb.append("git branch ");

			if (force) {
				sb.append("-f ");
			}

			sb.append(branchName);

			if (startPoint != null) {
				sb.append(" ");
				sb.append(startPoint);
			}

			ExecutionResult executionResult = executeBashCommands(
				sb.toString());

			if (executionResult.getExitValue() != 0) {
				throw new RuntimeException(
					JenkinsResultsParserUtil.combine(
						"Unable to create local branch ", branchName, " at ",
						startPoint, "\n", executionResult.getStandardError()));
			}
		}
		finally {
			if (tempBranch != null) {
				checkoutBranch(currentBranch);

				deleteBranch(tempBranch);
			}
		}

		return getBranch(branchName, null);
=======
	public void clean() throws GitAPIException {
		StoredConfig storedConfig = _repository.getConfig();

		boolean requireForce = storedConfig.getBoolean(
			"clean", "requireForce", true);

		if (requireForce == true) {
			updateConfig("clean", null, "requireForce", false);
		}

		try {
			CleanCommand cleanCommand = _git.clean();

			cleanCommand.setCleanDirectories(true);
			cleanCommand.setIgnore(true);

			System.out.println("Cleaning repository");

			cleanCommand.call();
		}
		finally {
			if (requireForce != false) {
				updateConfig("clean", null, "requireForce", null);
			}
		}
	}

	public void commitFileToCurrentBranch(String fileName, String message)
		throws GitAPIException {

		System.out.println("Committing file to current branch " + fileName);

		stageFileInCurrentBranch(fileName);

		commitStagedFilesToCurrentBranch(message);
	}

	public void commitStagedFilesToCurrentBranch(String message)
		throws GitAPIException {

		System.out.println("Committing staged files to current branch");

		CommitCommand commitCommand = _git.commit();

		commitCommand.setMessage(message);

		commitCommand.call();
	}

	public void createLocalBranch(String branchName) throws GitAPIException {
		createLocalBranch(branchName, false, null);
	}

	public void createLocalBranch(
			String branchName, boolean force, String startPoint)
		throws GitAPIException {

		System.out.println(
			JenkinsResultsParserUtil.combine(
				"Creating branch ", branchName, " at starting point ",
				startPoint));

		CreateBranchCommand createBranchCommand = _git.branchCreate();

		createBranchCommand.setForce(force);
		createBranchCommand.setName(branchName);

		if (startPoint != null) {
			createBranchCommand.setStartPoint(startPoint);
		}

		try {
			createBranchCommand.call();
		}
		catch (JGitInternalException jgie) {
			String errorMessage = jgie.getMessage();

			if (errorMessage.contains("FAST_FORWARD")) {
				return;
			}

			throw jgie;
		}
>>>>>>> compatible
	}

	public String createPullRequest(
			String body, String pullRequestBranchName, String receiverUserName,
			String title)
		throws IOException {

		JSONObject requestJSONObject = new JSONObject();

		requestJSONObject.put("base", _upstreamBranchName);
		requestJSONObject.put("body", body);
		requestJSONObject.put(
			"head", receiverUserName + ":" + pullRequestBranchName);
		requestJSONObject.put("title", title);

		String url = JenkinsResultsParserUtil.combine(
			"https://api.github.com/repos/", receiverUserName, "/",
			_repositoryName, "/pulls");

		JSONObject responseJSONObject = JenkinsResultsParserUtil.toJSONObject(
			url, requestJSONObject.toString());

		String pullRequestURL = responseJSONObject.getString("html_url");

		System.out.println("Created a pull request at " + pullRequestURL);

		return pullRequestURL;
	}

<<<<<<< HEAD
	public void deleteBranch(Branch branch) {
		if (!branchExists(branch.getName(), branch.getRemote())) {
			return;
		}

		if (branch.getRemote() != null) {
			pushToRemote(true, null, branch);

			return;
		}

		ExecutionResult executionResult = executeBashCommands(
			"git branch -f -D " + branch.getName());

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to delete local branch ", branch.getName(), "\n",
					executionResult.getStandardError()));
		}
	}

	public void deleteBranch(String branchName, Remote remote) {
		Branch branch = null;

		branch = getBranch(branchName, remote);

		if (branch != null) {
			deleteBranch(branch);
		}
	}

	public void fetch(Branch localBranch, boolean noTags, Branch remoteBranch) {
		if ((remoteBranch.getSHA() != null) &&
			localSHAExists(remoteBranch.getSHA())) {

			System.out.println(
				remoteBranch.getSHA() + " already exists in repository");

			if (localBranch != null) {
				createLocalBranch(
					localBranch.getName(), true, remoteBranch.getSHA());
			}

			return;
		}
		else {
			Remote remote = remoteBranch.getRemote();

			String remoteURL = remote.getRemoteURL();

			if (remoteURL.contains("github-dev.liferay.com")) {
				executeBashCommands("rm -f ~/.ssh/known_hosts");
			}

			if (remoteURL.contains("github.com:liferay/")) {
				remoteURL = remoteURL.replace(
					"github.com:liferay/", "github-dev.liferay.com:liferay/");

				Remote gitHubDevRemote = null;

				try {
					gitHubDevRemote = addRemote(
						true, "github-dev-remote", remoteURL);

					Branch localGitRemoteBranch = getBranch(
						remoteBranch.getName(), gitHubDevRemote);

					if (localGitRemoteBranch != null) {
						fetch(localBranch, noTags, localGitRemoteBranch);

						String upstreamBranchSHA = remoteBranch.getSHA();

						if (localSHAExists(upstreamBranchSHA)) {
							if (!upstreamBranchSHA.equals(
									localGitRemoteBranch.getSHA())) {

								createLocalBranch(
									localBranch.getName(), true,
									remoteBranch.getSHA());
							}

							return;
						}
					}
				}
				finally {
					if (gitHubDevRemote != null) {
						removeRemote(gitHubDevRemote);
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append("git fetch --progress -v -f ");

		if (noTags) {
			sb.append(" --no-tags ");
		}

		Remote remote = remoteBranch.getRemote();

		sb.append(remote.getName());

		String remoteBranchName = remoteBranch.getName();

		if ((remoteBranchName != null) && !remoteBranchName.isEmpty()) {
			sb.append(" ");
			sb.append(remoteBranch.getName());

			if (localBranch != null) {
				sb.append(":");
				sb.append(localBranch.getName());
			}
		}

		long start = System.currentTimeMillis();

		ExecutionResult executionResult = executeBashCommands(
			3, 1000 * 60 * 30, sb.toString());

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to fetch remote branch ", remoteBranch.getName(),
					"\n", executionResult.getStandardError()));
=======
	public void deleteLocalBranch(String localBranchName)
		throws GitAPIException {

		System.out.println("Deleting local branch " + localBranchName);

		DeleteBranchCommand deleteBranchCommand = _git.branchDelete();

		deleteBranchCommand.setBranchNames(localBranchName);
		deleteBranchCommand.setForce(true);

		deleteBranchCommand.call();
	}

	public void deleteRemoteBranch(
			String remoteBranchName, RemoteConfig remoteConfig)
		throws GitAPIException {

		String remoteURL = getRemoteURL(remoteConfig);

		System.out.println(
			JenkinsResultsParserUtil.combine(
				"Deleting remote branch ", remoteBranchName, " from ",
				remoteURL));

		pushToRemote(true, "", remoteBranchName, remoteConfig);
	}

	public void fetch(RefSpec refSpec, RemoteConfig remoteConfig)
		throws GitAPIException {

		StringBuilder sb = new StringBuilder();

		sb.append("git fetch --progress -v -f ");
		sb.append(getRemoteURL(remoteConfig));

		if (refSpec == null) {
			System.out.println(
				JenkinsResultsParserUtil.combine(
					"Fetching from ", getRemoteURL(remoteConfig)));

			List<RefSpec> fetchRefSpecs = remoteConfig.getFetchRefSpecs();

			for (RefSpec fetchRefSpec : fetchRefSpecs) {
				sb.append(" ");
				sb.append(fetchRefSpec.toString());
			}
		}
		else {
			System.out.println(
				JenkinsResultsParserUtil.combine(
					"Fetching from ", getRemoteURL(remoteConfig), " ",
					refSpec.toString()));

			sb.append(" ");
			sb.append(refSpec.toString());
		}

		int retries = 0;
		long start = System.currentTimeMillis();

		while (true) {
			try {
				Process process = JenkinsResultsParserUtil.executeBashCommands(
					true, getWorkingDirectory(), 1000 * 60 * 30, sb.toString());

				if ((process != null) && (process.exitValue() != 0)) {
					try {
						System.out.println(
							JenkinsResultsParserUtil.readInputStream(
								process.getErrorStream()));
					}
					catch (IOException ioe) {
						ioe.printStackTrace();
					}

					throw new RuntimeException("Unable to fetch");
				}

				if (process == null) {
					throw new RuntimeException("Process failed to run");
				}

				System.out.println(
					JenkinsResultsParserUtil.readInputStream(
						process.getInputStream()));
			}
			catch (Exception e) {
				if (retries < 3) {
					System.out.println(
						JenkinsResultsParserUtil.combine(
							"Fetch attempt ", Integer.toString(retries),
							" failed with an exception. ", e.getMessage(),
							"\nRetrying."));

					retries++;

					JenkinsResultsParserUtil.sleep(30000);
				}
				else {
					throw new RuntimeException(e);
				}
			}

			break;
>>>>>>> compatible
		}

		System.out.println(
			"Fetch completed in " +
				JenkinsResultsParserUtil.toDurationString(
					System.currentTimeMillis() - start));
	}

<<<<<<< HEAD
	public void fetch(Branch localBranch, Branch remoteBranch) {
		fetch(localBranch, true, remoteBranch);
	}

	public void fetch(Remote remote) {
		fetch(null, new Branch(this, null, remote, null));
	}

	public Branch getBranch(String branchName, Remote remote) {
		if (branchName.equals("HEAD") && (remote == null)) {
			ExecutionResult executionResult = executeBashCommands(
				"git rev-parse --abbrev-ref " + branchName);

			if (executionResult.getExitValue() != 0) {
				return null;
			}

			System.out.println(executionResult.getStandardOut());

			branchName = executionResult.getStandardOut();

			branchName = branchName.trim();

			if (branchName.isEmpty()) {
				return null;
			}

			return new Branch(this, branchName, null, getBranchSHA(branchName));
		}

		List<Branch> branches = getBranches(branchName, remote);

		for (Branch branch : branches) {
			if (branchName.equals(branch.getName())) {
				return branch;
			}
		}

		return null;
	}

	public List<Branch> getBranches(String branchName, Remote remote) {
		if (remote == null) {
			List<String> localBranchNames = getLocalBranchNames();

			List<Branch> localBranches = new ArrayList<>(
				localBranchNames.size());

			if (branchName != null) {
				if (localBranchNames.contains(branchName)) {
					localBranches.add(
						new Branch(
							this, branchName, null, getBranchSHA(branchName)));
				}

				return localBranches;
			}

			for (String localBranchName : localBranchNames) {
				localBranches.add(
					new Branch(
						this, localBranchName, null,
						getBranchSHA(localBranchName)));
			}

			return localBranches;
		}

		return getRemoteBranches(branchName, remote);
	}

	public List<String> getBranchNames(Remote remote) {
		if (remote == null) {
			return getLocalBranchNames();
		}

		return getRemoteBranchNames(remote);
	}

	public List<String> getBranchNamesContainingSHA(String sha) {
		ExecutionResult executionResult = executeBashCommands(
			1, 1000 * 60 * 2, "git branch --contains " + sha);

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to get branches with SHA ", sha, "\n",
					executionResult.getStandardError()));
		}

		String standardOut = executionResult.getStandardOut();

		if (standardOut.contains("no such commit")) {
			return Collections.emptyList();
		}

		String[] lines = standardOut.split("\n");

		List<String> branchNamesList = new ArrayList<>(lines.length - 1);

		for (String line : lines) {
			if (branchNamesList.size() == (lines.length - 1)) {
				break;
			}

			String branchName = line.trim();

			if (branchName.startsWith("* ")) {
				branchName = branchName.substring(2);
			}

			branchNamesList.add(branchName);
		}

		return branchNamesList;
	}

	public String getBranchSHA(String localBranchName) {
		ExecutionResult executionResult = executeBashCommands(
			1, 1000 * 60 * 2, "git rev-parse " + localBranchName);

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to determine SHA of branch ", localBranchName, "\n",
					executionResult.getStandardError()));
		}

		return executionResult.getStandardOut();
	}

	public String getBranchSHA(String branchName, Remote remote) {
		if (remote == null) {
			return getBranchSHA(branchName);
		}

		String command = JenkinsResultsParserUtil.combine(
			"git ls-remote -h ", remote.getName(), " ", branchName);

		ExecutionResult executionResult = executeBashCommands(
			1, 1000 * 60 * 10, command);

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to get remote branch SHA ", remote.toString(), " ",
					branchName, "\n", executionResult.getStandardError()));
		}

		String input = executionResult.getStandardOut();

		for (String line : input.split("\n")) {
			Matcher matcher = _gitLsRemotePattern.matcher(line);

			if (matcher.find()) {
				return matcher.group("sha");
			}
		}
=======
	public void fetch(
			String localBranchName, String remoteBranchName,
			RemoteConfig remoteConfig)
		throws GitAPIException {

		RefSpec refSpec = new RefSpec(
			JenkinsResultsParserUtil.combine(
				"refs/heads/", remoteBranchName, ":", "refs/heads/",
				localBranchName));

		fetch(refSpec, remoteConfig);
	}

	public List<String> getBranchNamesContainingSHA(String sha) {
		String command = "git branch --contains " + sha;

		try {
			Process process = JenkinsResultsParserUtil.executeBashCommands(
				true, getWorkingDirectory(), 1000 * 60 * 2, command);

			String output = JenkinsResultsParserUtil.readInputStream(
				process.getInputStream());

			if (output.contains("no such commit")) {
				return Collections.emptyList();
			}

			System.out.println(output);

			String[] outputLines = output.split("\n");

			List<String> branchNamesList = new ArrayList<>(
				outputLines.length - 1);

			for (String outputLine : outputLines) {
				if (branchNamesList.size() == (outputLines.length - 1)) {
					break;
				}

				String branchName = outputLine.trim();

				if (branchName.startsWith("* ")) {
					branchName = branchName.substring(2);
				}

				branchNamesList.add(branchName);
			}

			return branchNamesList;
		}
		catch (InterruptedException | IOException e) {
			throw new RuntimeException(
				"Unable to find branches with SHA " + sha, e);
		}
	}

	public List<Ref> getBranchRefs() throws GitAPIException {
		ListBranchCommand listBranchCommand = _git.branchList();

		listBranchCommand.setListMode(ListMode.ALL);

		return listBranchCommand.call();
	}

	public String getBranchSHA(String branchName) throws GitAPIException {
		String command = "git rev-parse " + branchName;

		try {
			Process process = JenkinsResultsParserUtil.executeBashCommands(
				true, getWorkingDirectory(), 1000 * 60 * 2, command);

			String output = JenkinsResultsParserUtil.readInputStream(
				process.getInputStream());

			String firstLine = output.substring(0, output.indexOf("\n"));

			return firstLine.trim();
		}
		catch (InterruptedException | IOException e) {
			throw new RuntimeException(
				"Unable to get SHA of branch " + branchName);
		}
	}

	public String getBranchSHA(String branchName, RemoteConfig remoteConfig)
		throws GitAPIException {

		if (remoteConfig == null) {
			return getBranchSHA(branchName);
		}

		String remoteURL = getRemoteURL(remoteConfig);

		if (remoteURL.contains("git@github.com")) {
			return getGitHubBranchSHA(branchName, remoteConfig);
		}

		LsRemoteCommand lsRemoteCommand = Git.lsRemoteRepository();

		lsRemoteCommand.setHeads(true);
		lsRemoteCommand.setRemote(remoteURL);
		lsRemoteCommand.setTags(false);

		Collection<Ref> remoteRefs = lsRemoteCommand.call();

		for (Ref remoteRef : remoteRefs) {
			String completeBranchName = "refs/heads/" + branchName;

			if (completeBranchName.equals(remoteRef.getName())) {
				return remoteRef.getObjectId().getName();
			}
		}

		return null;
	}

	public String getCurrentBranch() {
		waitForIndexLock();

		try {
			return _repository.getBranch();
		}
		catch (IOException ioe) {
			throw new RuntimeException(
				"Unable to get current branch name from repository", ioe);
		}
	}

	public Git getGit() {
		return _git;
	}

	public File getGitDirectory() {
		return _gitDirectory;
	}

	public String getGitHubBranchSHA(
		String branchName, RemoteConfig remoteConfig) {

		String command = JenkinsResultsParserUtil.combine(
			"git ls-remote ", getRemoteURL(remoteConfig), " ", branchName);

		try {
			Process process = JenkinsResultsParserUtil.executeBashCommands(
				command);

			if (process.exitValue() != 0) {
				System.out.println(
					JenkinsResultsParserUtil.readInputStream(
						process.getErrorStream()));

				throw new RuntimeException(
					JenkinsResultsParserUtil.combine(
						"Unable to get branch sha for ", branchName, " on ",
						getRemoteURL(remoteConfig)));
			}

			String output = JenkinsResultsParserUtil.readInputStream(
				process.getInputStream());

			for (String line : output.split("\n")) {
				if (line.endsWith("refs/heads/" + branchName)) {
					return line.substring(0, line.indexOf("\t"));
				}
			}
		}
		catch (InterruptedException | IOException e) {
			throw new RuntimeException(e);
		}
>>>>>>> compatible

		return null;
	}

<<<<<<< HEAD
	public Branch getCurrentBranch() {
		waitForIndexLock();

		return getBranch("HEAD", null);
	}

	public String getGitConfigProperty(String gitConfigPropertyName) {
		ExecutionResult executionResult = executeBashCommands(
			"git config " + gitConfigPropertyName);

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to read Git config property ",
					gitConfigPropertyName, "\n",
					executionResult.getStandardError()));
		}

		String configProperty = executionResult.getStandardOut();

		if (configProperty != null) {
			configProperty = configProperty.trim();
		}

		if ((configProperty == null) || configProperty.isEmpty()) {
			return null;
		}

		return configProperty;
	}

	public Boolean getGitConfigPropertyBoolean(
		String gitConfigPropertyName, Boolean defaultValue) {

		String gitConfigProperty = getGitConfigProperty(gitConfigPropertyName);

		if (gitConfigProperty == null) {
			if (defaultValue != null) {
				return defaultValue;
			}

			return null;
		}

		return Boolean.parseBoolean(gitConfigProperty);
	}

	public File getGitDirectory() {
		return _gitDirectory;
	}

	public String getGitHubFileURL(Branch branch, File file) {
		return getGitHubFileURL(
			branch.getName(), branch.getRemote(), file, true);
	}

	public String getGitHubFileURL(
		String branchName, Remote branchRemote, File file, boolean verify) {

		String relativePath = JenkinsResultsParserUtil.getPathRelativeTo(
			file, getWorkingDirectory());

		String remoteURL = branchRemote.getRemoteURL();

		if (!remoteURL.contains("git@github.com:")) {
			throw new RuntimeException(
				remoteURL + " does not point to a GitHub repository");
		}

		if (verify) {
			String command = JenkinsResultsParserUtil.combine(
				"git cat-file -e ", branchRemote.getName(), "/", branchName,
				" ", relativePath);

			ExecutionResult executionResult = executeBashCommands(command);

			if (executionResult.getExitValue() != 0) {
				throw new RuntimeException(
					JenkinsResultsParserUtil.combine(
						relativePath, " does not exist in ",
						branchRemote.getName(), "/", branchName));
			}
		}

		return JenkinsResultsParserUtil.combine(
			"https://github.com/", getGitHubUserName(branchRemote), "/",
			getRepositoryName(), "/tree/", branchName, "/", relativePath);
	}

	public Remote getRemote(String name) {
		Map<String, Remote> remotes = getRemotes();

		name = name.trim();

		Remote remote = remotes.get(name);

		if ((remote == null) && name.equals("upstream")) {
			JenkinsResultsParserUtil.sleep(1000);

			remotes = getRemotes();

			return remotes.get(name);
		}

		return remote;
	}

	public Set<String> getRemoteNames() {
		Map<String, Remote> remotes = getRemotes();

		return remotes.keySet();
	}

	public Map<String, Remote> getRemotes() {
		Map<String, Remote> remotes = new HashMap<>();

		int retries = 0;

		String standardOut = null;

		while (true) {
			if (retries > 1) {
				return remotes;
			}

			ExecutionResult executionResult = executeBashCommands(
				"git remote -v");

			if (executionResult.getExitValue() != 0) {
				throw new RuntimeException(
					JenkinsResultsParserUtil.combine(
						"Unable to get list of remotes\n",
						executionResult.getStandardError()));
			}

			standardOut = executionResult.getStandardOut();

			standardOut = standardOut.trim();

			if (!standardOut.isEmpty()) {
				break;
			}

			retries++;

			JenkinsResultsParserUtil.sleep(1000);
		}

		String[] lines = standardOut.split("\n");

		Arrays.sort(lines);

		int x = 0;

		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];

			if (line == null) {
				continue;
			}

			line = line.trim();

			if (line.isEmpty()) {
				continue;
			}

			x = i;

			break;
		}

		lines = Arrays.copyOfRange(lines, x, lines.length);

		try {
			StringBuilder sb = new StringBuilder();

			sb.append("Found remotes: ");

			for (int i = 0; i < lines.length; i = i + 2) {
				Remote remote = new Remote(
					this, Arrays.copyOfRange(lines, i, i + 2));

				if (i > 0) {
					sb.append(", ");
				}

				sb.append(remote.getName());

				remotes.put(remote.getName(), remote);
			}

			System.out.println(sb);
		}
		catch (Throwable t) {
			System.out.println("Unable to parse remotes\n" + standardOut);

			throw t;
		}

		return remotes;
=======
	public List<String> getLocalBranchNames() throws GitAPIException {
		List<Ref> allLocalBranchRefs = new ArrayList<>();

		for (Ref branchRef : getBranchRefs()) {
			String branchName = branchRef.getName();

			if (branchName.startsWith("refs/heads")) {
				allLocalBranchRefs.add(branchRef);
			}
		}

		return toShortNameList(allLocalBranchRefs);
	}

	public List<String> getRemoteBranchNames(RemoteConfig remoteConfig)
		throws GitAPIException {

		LsRemoteCommand lsRemoteCommand = Git.lsRemoteRepository();

		lsRemoteCommand.setHeads(true);
		lsRemoteCommand.setRemote(getRemoteURL(remoteConfig));
		lsRemoteCommand.setTags(false);

		List<String> remoteBranchNames = toShortNameList(
			lsRemoteCommand.call());

		Collections.sort(remoteBranchNames);

		return remoteBranchNames;
	}

	public RemoteConfig getRemoteConfig(String remoteName)
		throws GitAPIException {

		if (remoteName.equals("upstream")) {
			return _getUpstreamRemoteConfig();
		}

		return _getRemoteConfig(remoteName);
	}

	public List<RemoteConfig> getRemoteConfigs() throws GitAPIException {
		if (_remoteConfigs != null) {
			return _remoteConfigs;
		}

		try {
			_remoteConfigs = RemoteConfig.getAllRemoteConfigs(
				_repository.getConfig());
		}
		catch (URISyntaxException urise) {
			throw new RuntimeException(urise);
		}

		return _remoteConfigs;
	}

	public Set<String> getRemoteNames() throws GitAPIException {
		List<RemoteConfig> remoteConfigs = getRemoteConfigs();

		Set<String> remoteNames = new HashSet<>(remoteConfigs.size());

		for (RemoteConfig remoteConfig : remoteConfigs) {
			remoteNames.add(remoteConfig.getName());
		}

		return remoteNames;
>>>>>>> compatible
	}

	public String getRepositoryName() {
		return _repositoryName;
	}

	public String getRepositoryUsername() {
		return _repositoryUsername;
	}

	public String getUpstreamBranchName() {
		return _upstreamBranchName;
	}

	public File getWorkingDirectory() {
		return _workingDirectory;
	}

<<<<<<< HEAD
	public boolean isRemoteRepositoryAlive(String remoteURL) {
		String command = JenkinsResultsParserUtil.combine(
			"git ls-remote -h ", remoteURL, " HEAD");

		ExecutionResult executionResult = executeBashCommands(
			1, 1000 * 60 * 10, command);

		if (executionResult.getExitValue() != 0) {
			System.out.println("Unable to connect to " + remoteURL);

			return false;
		}

		System.out.println(remoteURL + " is alive");

		return true;
	}

	public boolean localSHAExists(String sha) {
		String command = "git cat-file -t " + sha;

		ExecutionResult executionResult = executeBashCommands(
			1, 1000 * 60 * 3, command);

		if (executionResult.getExitValue() == 0) {
			return true;
		}

		return false;
	}

	public String log(int num) {
		return log(num, null);
	}

	public String log(int num, File file) {
		for (int i = 0; i < 5; i++) {
			try {
				String gitLog = _log(num, file, "%H %s");

				gitLog = gitLog.replaceAll(
					"Finished executing Bash commands.", "");

				String[] gitLogItems = gitLog.split("\n");

				for (String gitLogItem : gitLogItems) {
					if (!gitLogItem.matches("([0-9a-f]{40}) (.*)")) {
						throw new RuntimeException("Unable to run: git log");
					}
				}

				return gitLog;
			}
			catch (RuntimeException re) {
				re.printStackTrace();

				JenkinsResultsParserUtil.sleep(1000);
			}
		}

		throw new RuntimeException("Unable to run: git log");
	}

	public boolean pushToRemote(boolean force, Branch remoteBranch) {
		Branch currentBranch = getCurrentBranch();

		if (currentBranch == null) {
			Remote remote = remoteBranch.getRemote();

			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to push current branch to remote branch ",
					remoteBranch.getName(), " on ", remote.getName(),
					" because the current branch is invalid"));
		}

		return pushToRemote(force, currentBranch, remoteBranch);
	}

	public boolean pushToRemote(
		boolean force, Branch localBranch, Branch remoteBranch) {

		return pushToRemote(
			force, localBranch, remoteBranch.getName(),
			remoteBranch.getRemote());
	}

	public boolean pushToRemote(
		boolean force, Branch localBranch, String remoteBranchName,
		Remote remote) {

		String localBranchName = "";

		if (localBranch != null) {
			localBranchName = localBranch._name;
		}

		StringBuilder sb = new StringBuilder();

		sb.append("git push ");

		if (force) {
			sb.append("-f ");
		}

		sb.append(remote.getName());
		sb.append(" ");
		sb.append(localBranchName);
		sb.append(":");
		sb.append(remoteBranchName);

		try {
			executeBashCommands(1, 1000 * 60 * 10, sb.toString());
		}
		catch (RuntimeException re) {
			return false;
		}

		return true;
	}

	public boolean pushToRemote(boolean force, Remote remote) {
		Branch currentBranch = getCurrentBranch();

		return pushToRemote(
			force, currentBranch, currentBranch.getName(), remote);
	}

	public void rebase(
		boolean abortOnFail, Branch sourceBranch, Branch targetBranch) {

		String rebaseCommand = JenkinsResultsParserUtil.combine(
			"git rebase ", sourceBranch.getName(), " ", targetBranch.getName());

		ExecutionResult executionResult = executeBashCommands(
			1, 1000 * 60 * 10, rebaseCommand);

		if (executionResult.getExitValue() != 0) {
			if (abortOnFail) {
				rebaseAbort();
			}

			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to rebase ", targetBranch.getName(), " to ",
					sourceBranch.getName(), "\n",
					executionResult.getStandardError()));
		}
	}

	public void rebaseAbort() {
		rebaseAbort(true);
	}

	public void rebaseAbort(boolean ignoreFailure) {
		ExecutionResult executionResult = executeBashCommands(
			"git rebase --abort");

		if (!ignoreFailure && (executionResult.getExitValue() != 0)) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to abort rebase\n",
					executionResult.getStandardError()));
		}
	}

	public boolean remoteExists(String remoteName) {
		if (getRemote(remoteName) != null) {
			return true;
		}

		return false;
	}

	public void removeRemote(Remote remote) {
		if ((remote == null) || !remoteExists(remote.getName())) {
			return;
		}

		ExecutionResult executionResult = executeBashCommands(
			"git remote rm " + remote.getName());

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to remove remote ", remote.getName(), "\n",
					executionResult.getStandardError()));
		}
	}

	public void removeRemotes(List<Remote> remotes) {
		for (Remote remote : remotes) {
			removeRemote(remote);
		}
	}

	public void reset(String options) {
		String command = "git reset " + options;

		ExecutionResult executionResult = executeBashCommands(
			2, 1000 * 60 * 2, command);

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to reset\n", executionResult.getStandardError()));
		}
	}

	public void stageFileInCurrentBranch(String fileName) {
		String command = "git stage " + fileName;

		ExecutionResult result = executeBashCommands(command);

		if (result.getExitValue() != 0) {
			throw new RuntimeException("Unable to stage file " + fileName);
		}
	}

	public String status() {
		for (int i = 0; i < 5; i++) {
			try {
				String gitStatus = _status();

				gitStatus = gitStatus.replaceAll(
					"Finished executing Bash commands.", "");

				if (!gitStatus.startsWith("On branch")) {
					throw new RuntimeException("Unable to run: git status");
				}

				return gitStatus;
			}
			catch (RuntimeException re) {
				re.printStackTrace();

				JenkinsResultsParserUtil.sleep(1000);
			}
		}

		throw new RuntimeException("Unable to run: git status");
	}

	public static class Branch {

		public String getName() {
			return _name;
		}

		public Remote getRemote() {
			return _remote;
		}

		public String getSHA() {
			return _sha;
		}

		private Branch(
			GitWorkingDirectory gitWorkingDirectory, String name, Remote remote,
			String sha) {

			_name = name;
			_remote = remote;

			if ((name != null) && (sha == null)) {
				_sha = gitWorkingDirectory.getBranchSHA(name, remote);
			}
			else {
				_sha = sha;
			}
		}

		private final String _name;
		private final Remote _remote;
		private final String _sha;

	};

	public static class Remote implements Comparable<Remote> {

		@Override
		public int compareTo(Remote otherGitRemote) {
			int result = _name.compareTo(otherGitRemote._name);

			if (result != 0) {
				return result;
			}

			return _fetchRemoteURL.compareTo(otherGitRemote._fetchRemoteURL);
		}

		public GitWorkingDirectory getGitWorkingDirectory() {
			return _gitWorkingDirectory;
		}

		public String getName() {
			return _name;
		}

		public String getPushRemoteURL() {
			if (_pushRemoteURL != null) {
				return _pushRemoteURL;
			}

			return _fetchRemoteURL;
		}

		public String getRemoteURL() {
			return _fetchRemoteURL;
		}

		public String toString() {
			return JenkinsResultsParserUtil.combine(
				getName(), " (", getRemoteURL(), ")");
		}

		private Remote(
			GitWorkingDirectory gitWorkingDirectory,
			String[] remoteInputLines) {

			_gitWorkingDirectory = gitWorkingDirectory;

			if (remoteInputLines.length != 2) {
				throw new IllegalArgumentException(
					"\"remoteInputLines\" must be an array of 2 strings");
			}

			if (remoteInputLines[0].equals(remoteInputLines[1])) {
				throw new IllegalArgumentException(
					JenkinsResultsParserUtil.combine(
						"\"remoteInputLines[0]\" and ",
						"\"remoteInputLines[1]\" are identical: ",
						remoteInputLines[0]));
			}

			if ((remoteInputLines[0] == null) ||
				(remoteInputLines[1] == null)) {

				throw new IllegalArgumentException(
					"Neither \"remoteInputLines[0]\" nor " +
						"\"remoteInputLines[1]\" may be NULL: " +
							Arrays.toString(remoteInputLines));
			}

			String name = null;
			String fetchRemoteURL = null;
			String pushRemoteURL = null;

			for (String remoteInputLine : remoteInputLines) {
				Matcher matcher = _remotePattern.matcher(remoteInputLine);

				if (!matcher.matches()) {
					throw new IllegalArgumentException(
						"Invalid Git remote input line " + remoteInputLine);
				}

				if (name == null) {
					name = matcher.group("name");
				}

				String remoteURL = matcher.group("remoteURL");
				String type = matcher.group("type");

				if ((fetchRemoteURL == null) && type.equals("fetch")) {
					fetchRemoteURL = remoteURL;
				}

				if ((pushRemoteURL == null) && type.equals("push")) {
					pushRemoteURL = remoteURL;
				}
			}

			_fetchRemoteURL = fetchRemoteURL;
			_name = name;
			_pushRemoteURL = pushRemoteURL;
		}

		private static final Pattern _remotePattern = Pattern.compile(
			JenkinsResultsParserUtil.combine(
				"(?<name>[^\\s]+)[\\s]+(?<remoteURL>[^\\s]+)[\\s]+\\(",
				"(?<type>[^\\s]+)\\)"));

		private final String _fetchRemoteURL;
		private final GitWorkingDirectory _gitWorkingDirectory;
		private final String _name;
		private final String _pushRemoteURL;

	}

	protected ExecutionResult executeBashCommands(
		int maxRetries, long timeout, String... commands) {

		Process process = null;

		int retries = 0;

		while (retries < maxRetries) {
			try {
				retries++;

				process = JenkinsResultsParserUtil.executeBashCommands(
					true, _workingDirectory, timeout, commands);

				break;
			}
			catch (InterruptedException | IOException | TimeoutException e) {
				if (retries == maxRetries) {
					throw new RuntimeException(
						"Unable to execute bash commands: " +
							Arrays.toString(commands),
						e);
				}
				else {
					System.out.println("Fetch attempt failed retrying... ");
					e.printStackTrace();
				}
			}
		}

		String standardErr = "";

		try {
			standardErr = JenkinsResultsParserUtil.readInputStream(
				process.getErrorStream());
		}
		catch (IOException ioe) {
			standardErr = "";
		}

		String standardOut = "";

		try {
			standardOut = JenkinsResultsParserUtil.readInputStream(
				process.getInputStream());
		}
		catch (IOException ioe) {
			throw new RuntimeException(
				"Unable to read process input stream", ioe);
		}

		return new ExecutionResult(
			process.exitValue(), standardErr.trim(), standardOut.trim());
	}

	protected ExecutionResult executeBashCommands(String... commands) {
		return executeBashCommands(1, 1000 * 30, commands);
	}

	protected List<String> getLocalBranchNames() {
		ExecutionResult executionResult = executeBashCommands(
			"git for-each-ref refs/heads --format=\"%(refname)\"");

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to get local branch names\n",
					executionResult.getStandardError()));
		}

		String standardOut = executionResult.getStandardOut();

		return toShortNameList(Arrays.asList(standardOut.split("\n")));
	}

	protected File getRealGitDirectory(File gitFile) {
		String gitFileContent;
		try {
			gitFileContent = JenkinsResultsParserUtil.read(gitFile);
		}
		catch (IOException ioe) {
			throw new RuntimeException(
				"Real .git directory could not be found", ioe);
		}

		for (String line : gitFileContent.split("\n")) {
			Matcher matcher = _gitDirectoryPathPattern.matcher(line);

			if (!matcher.find()) {
				continue;
			}

			return new File(matcher.group(1));
		}

		throw new RuntimeException(
			"Real Git directory could not be found in " + gitFile.getPath());
	}

	protected List<Branch> getRemoteBranches(String branchName, Remote remote) {
		String command = null;

		if (branchName != null) {
			command = JenkinsResultsParserUtil.combine(
				"git ls-remote -h ", remote.getName(), " ", branchName);
		}
		else {
			command = JenkinsResultsParserUtil.combine(
				"git ls-remote -h ", remote.getName());
		}

		ExecutionResult executionResult = executeBashCommands(
			1, 1000 * 60 * 10, command);

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to get remote branches from ", remote.toString(),
					"\n", executionResult.getStandardError()));
		}

		String input = executionResult.getStandardOut();

		List<Branch> branches = new ArrayList<>();

		for (String line : input.split("\n")) {
			Matcher matcher = _gitLsRemotePattern.matcher(line);

			if (matcher.find()) {
				branches.add(
					new Branch(
						this, matcher.group("name"), remote,
						matcher.group("sha")));
			}
		}

		System.out.println(
			"getRemoteBranches found " + branches.size() + " branches.");

		return branches;
	}

	protected List<String> getRemoteBranchNames(Remote remote) {
		ExecutionResult executionResult = executeBashCommands(
			1, 1000 * 60 * 10,
			JenkinsResultsParserUtil.combine(
				"git ls-remote -h ", remote.getName()));

		if (executionResult.getExitValue() != 0) {
			throw new RuntimeException(
				JenkinsResultsParserUtil.combine(
					"Unable to get remote branches from ", remote.getName(),
					"\n", executionResult.getStandardError()));
		}

		String input = executionResult.getStandardOut();

		List<String> branchNames = new ArrayList<>();

		for (String line : input.split("\n")) {
			Matcher matcher = _gitLsRemotePattern.matcher(line);

			if (matcher.find()) {
				branchNames.add(matcher.group("name"));
			}
		}

		return branchNames;
	}

	protected String loadRepositoryName() {
		Remote remote = getRemote("upstream");

		String remoteURL = remote.getRemoteURL();
=======
	public boolean pushToRemote(boolean force, RemoteConfig remoteConfig)
		throws GitAPIException {

		return pushToRemote(force, getCurrentBranch(), remoteConfig);
	}

	public boolean pushToRemote(
			boolean force, String remoteBranchName, RemoteConfig remoteConfig)
		throws GitAPIException {

		return pushToRemote(
			force, getCurrentBranch(), remoteBranchName, remoteConfig);
	}

	public boolean pushToRemote(
			boolean force, String remoteBranchName, String remoteURL)
		throws GitAPIException {

		RemoteConfig remoteConfig = null;

		try {
			remoteConfig = addRemote(true, "temp", remoteURL);

			return pushToRemote(force, remoteBranchName, remoteConfig);
		}
		finally {
			removeRemote(remoteConfig);
		}
	}

	public boolean pushToRemote(
			boolean force, String localBranchName, String remoteBranchName,
			RemoteConfig remoteConfig)
		throws GitAPIException {

		String remoteURL = getRemoteURL(remoteConfig);

		System.out.println(
			JenkinsResultsParserUtil.combine(
				"Pushing ", localBranchName, " to ", remoteURL, " ",
				remoteBranchName));

		PushCommand pushCommand = null;

		synchronized (_git) {
			pushCommand = _git.push();
		}

		String remoteRefName = "refs/heads/" + remoteBranchName;

		RefSpec refSpec = new RefSpec(
			JenkinsResultsParserUtil.combine(
				localBranchName, ":", remoteRefName));

		synchronized (pushCommand) {
			pushCommand.setForce(force);
			pushCommand.setRefSpecs(refSpec);
			pushCommand.setRemote(remoteURL);

			for (PushResult pushResult : pushCommand.call()) {
				for (RemoteRefUpdate remoteRefUpdate :
						pushResult.getRemoteUpdates()) {

					if ((remoteRefUpdate != null) &&
						(remoteRefUpdate.getStatus() !=
							RemoteRefUpdate.Status.OK)) {

						System.out.println(
							JenkinsResultsParserUtil.combine(
								"Unable to push ", localBranchName, " to ",
								getRemoteURL(remoteConfig),
								".\nPush response: ",
								remoteRefUpdate.toString()));

						return false;
					}
				}
			}

			return true;
		}
	}

	public void rebase(
			boolean abortOnFail, String sourceBranchName,
			String targetBranchName)
		throws GitAPIException {

		String rebaseCommand = JenkinsResultsParserUtil.combine(
			"git rebase ", sourceBranchName, " ", targetBranchName);

		String sourceBranchSHA = getBranchSHA(sourceBranchName);

		System.out.println(
			JenkinsResultsParserUtil.combine(
				"Rebasing ", sourceBranchName, "(", sourceBranchSHA, ") to ",
				targetBranchName));

		try {
			Process process = JenkinsResultsParserUtil.executeBashCommands(
				true, getWorkingDirectory(), 1000 * 60 * 10, rebaseCommand);

			if ((process != null) && (process.exitValue() != 0)) {
				try {
					System.out.println(
						JenkinsResultsParserUtil.readInputStream(
							process.getErrorStream()));
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}

				throw new RuntimeException("Unable to rebase");
			}

			if (process != null) {
				System.out.println(
					JenkinsResultsParserUtil.readInputStream(
						process.getInputStream()));
			}

			int i = 0;

			while (i < 10) {
				List<String> branchNamesContainingSourceBranchSHA =
					getBranchNamesContainingSHA(sourceBranchSHA);

				if (!branchNamesContainingSourceBranchSHA.contains(
						targetBranchName)) {

					i++;

					JenkinsResultsParserUtil.sleep(1000 * 30);

					continue;
				}

				break;
			}
		}
		catch (Exception e) {
			RepositoryState repositoryState = _repository.getRepositoryState();

			try {
				throw new RuntimeException(
					JenkinsResultsParserUtil.combine(
						"Unable to rebase ", targetBranchName, " to ",
						sourceBranchName, ". Repository is in the ",
						repositoryState.toString(), " state."),
					e);
			}
			finally {
				if (abortOnFail) {
					rebaseAbort();
				}
			}
		}
	}

	public void rebaseAbort() throws GitAPIException {
		if (!_rebaseRepositoryStates.contains(
				_repository.getRepositoryState())) {

			return;
		}

		RebaseCommand rebaseCommand = _git.rebase();

		rebaseCommand.setOperation(RebaseCommand.Operation.ABORT);

		System.out.println(
			"Aborting rebase " + RebaseCommand.Operation.ABORT.toString());

		rebaseCommand.call();
	}

	public boolean remoteExists(String remoteName) throws GitAPIException {
		Set<String> remoteNames = getRemoteNames();

		return remoteNames.contains(remoteName);
	}

	public void removeRemote(RemoteConfig remoteConfig) {
		try {
			if (!remoteExists(remoteConfig.getName())) {
				return;
			}

			System.out.println("Removing remote " + remoteConfig.getName());

			Process process = null;

			try {
				process = JenkinsResultsParserUtil.executeBashCommands(
					true, _workingDirectory, 1000 * 60,
					"git remote rm " + remoteConfig.getName());
			}
			catch (InterruptedException | IOException | RuntimeException e) {
				throw new RuntimeException(
					"Unable to remove remote " + remoteConfig.getName(), e);
			}

			if ((process != null) && (process.exitValue() != 0)) {
				try {
					System.out.println(
						JenkinsResultsParserUtil.readInputStream(
							process.getErrorStream()));
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}

				throw new RuntimeException(
					JenkinsResultsParserUtil.combine(
						"Unable to remove remote", remoteConfig.getName()));
			}

			if (_remoteConfigs.contains(remoteConfig)) {
				_remoteConfigs.remove(remoteConfig);
			}
			else {
				_remoteConfigs = null;
			}
		}
		catch (GitAPIException gapie) {
			gapie.printStackTrace();
		}
	}

	public void removeRemotes(List<RemoteConfig> remoteConfigs) {
		for (RemoteConfig remoteConfig : remoteConfigs) {
			removeRemote(remoteConfig);
		}
	}

	public void reset(String ref, ResetCommand.ResetType resetType)
		throws GitAPIException {

		if ((ref != null) && (ref.equals("head") || ref.equals("HEAD"))) {
			ref = null;
		}

		ResetCommand resetCommand = _git.reset();

		resetCommand.setMode(resetType);

		if (ref != null) {
			resetCommand.setRef(ref);
		}
		else {
			ref = Constants.HEAD;
		}

		System.out.println(
			JenkinsResultsParserUtil.combine(
				"Resetting ", resetType.toString(), " to ", ref));

		resetCommand.call();
	}

	public void stageFileInCurrentBranch(String fileName)
		throws GitAPIException {

		AddCommand addCommand = _git.add();

		addCommand.addFilepattern(fileName);

		System.out.println("Staging file in current branch " + fileName);

		addCommand.call();
	}

	public void updateConfig(
		String section, String subsection, String name, Object value) {

		StoredConfig storedConfig = _repository.getConfig();

		if (value == null) {
			storedConfig.unset(section, subsection, name);
		}

		if (value instanceof Boolean) {
			storedConfig.setBoolean(section, subsection, name, (Boolean)value);
		}

		if (value instanceof String) {
			storedConfig.setString(section, subsection, name, (String)value);
		}

		try {
			storedConfig.save();
		}
		catch (IOException ioe) {
			throw new RuntimeException("Unable to save configuration change");
		}
	}

	protected static String getRemoteURL(RemoteConfig remoteConfig) {
		List<URIish> uris = remoteConfig.getURIs();

		URIish uri = uris.get(0);

		return uri.toString();
	}

	protected String loadRepositoryName() throws GitAPIException {
		String remoteURL = getRemoteURL(_getRemoteConfig("upstream"));
>>>>>>> compatible

		int x = remoteURL.lastIndexOf("/") + 1;
		int y = remoteURL.indexOf(".git");

		String repositoryName = remoteURL.substring(x, y);

		if (repositoryName.equals("liferay-jenkins-tools-private")) {
			return repositoryName;
		}

		if ((repositoryName.equals("liferay-plugins-ee") ||
			 repositoryName.equals("liferay-portal-ee")) &&
			!_upstreamBranchName.contains("ee-") &&
			!_upstreamBranchName.contains("-private")) {

			repositoryName = repositoryName.replace("-ee", "");
		}

		if (repositoryName.contains("-private") &&
			!_upstreamBranchName.contains("-private")) {

			repositoryName = repositoryName.replace("-private", "");
		}

		return repositoryName;
	}

<<<<<<< HEAD
	protected String loadRepositoryUsername() {
		Remote remote = getRemote("upstream");

		String remoteURL = remote.getRemoteURL();
=======
	protected String loadRepositoryUsername() throws GitAPIException {
		String remoteURL = getRemoteURL(_getRemoteConfig("upstream"));
>>>>>>> compatible

		int x = remoteURL.indexOf(":") + 1;
		int y = remoteURL.indexOf("/");

		return remoteURL.substring(x, y);
	}

<<<<<<< HEAD
	protected void setUpstreamRemoteToPrivateRepository() {
		Remote upstreamRemote = getRemote("upstream");

		String remoteURL = upstreamRemote.getRemoteURL();

		String repositoryName = getRepositoryName();

		if (repositoryName.endsWith("-ee")) {
			if (!remoteURL.contains("-ee")) {
				remoteURL = remoteURL.replace(".git", "-ee.git");

				addRemote(true, "upstream", remoteURL);
			}
		}

		if (repositoryName.endsWith("-private")) {
			if (!remoteURL.contains("-private")) {
				remoteURL = remoteURL.replace(".git", "-private.git");

				addRemote(true, "upstream", remoteURL);
			}
		}
	}

	protected void setUpstreamRemoteToPublicRepository() {
		Remote upstreamRemote = getRemote("upstream");

		String remoteURL = upstreamRemote.getRemoteURL();

		if (remoteURL.contains("-ee") || remoteURL.contains("-private")) {
			remoteURL = remoteURL.replace("-ee", "");
			remoteURL = remoteURL.replace("-private", "");

			addRemote(true, "upstream", remoteURL);
		}
	}

	protected void setWorkingDirectory(String workingDirectoryPath)
		throws IOException {

		_workingDirectory = new File(workingDirectoryPath);
=======
	protected void setWorkingDirectory(String workingDirectory)
		throws GitAPIException, IOException {

		_workingDirectory = new File(workingDirectory);
>>>>>>> compatible

		if (!_workingDirectory.exists()) {
			throw new FileNotFoundException(
				_workingDirectory.getPath() + " is unavailable");
		}

<<<<<<< HEAD
		_gitDirectory = new File(workingDirectoryPath, ".git");

		if (_gitDirectory.isFile()) {
			_gitDirectory = getRealGitDirectory(_gitDirectory);
		}
=======
		_gitDirectory = new File(workingDirectory, ".git");
>>>>>>> compatible

		if (!_gitDirectory.exists()) {
			throw new FileNotFoundException(
				_gitDirectory.getPath() + " is unavailable");
		}
	}

<<<<<<< HEAD
	protected List<String> toShortNameList(List<String> fullNameList) {
		List<String> shortNames = new ArrayList<>(fullNameList.size());

		for (String fullName : fullNameList) {
			shortNames.add(fullName.substring(fullName.lastIndexOf("/") + 1));
=======
	protected List<String> toShortNameList(Collection<Ref> refs) {
		List<String> shortNames = new ArrayList<>(refs.size());

		for (Ref ref : refs) {
			String refName = ref.getName();

			shortNames.add(refName.substring(refName.lastIndexOf("/") + 1));
>>>>>>> compatible
		}

		return shortNames;
	}

	protected void waitForIndexLock() {
		int retries = 0;

		File file = new File(_gitDirectory, "index.lock");

		while (file.exists()) {
			System.out.println("Waiting for index.lock to be cleared.");

			JenkinsResultsParserUtil.sleep(5000);

			retries++;

			if (retries >= 24) {
				file.delete();
			}
		}
	}

<<<<<<< HEAD
	protected class ExecutionResult {

		public int getExitValue() {
			return _exitValue;
		}

		public String getStandardError() {
			return _standardError;
		}

		public String getStandardOut() {
			return _standardOut;
		}

		protected ExecutionResult(
			int exitValue, String standardError, String standardOut) {

			_exitValue = exitValue;
			_standardError = standardError;

			if (standardOut.endsWith("\nFinished executing Bash commands.")) {
				_standardOut = standardOut.substring(
					0,
					standardOut.indexOf("\nFinished executing Bash commands."));
			}
			else {
				_standardOut = standardOut;
			}
		}

		private final int _exitValue;
		private final String _standardError;
		private final String _standardOut;

	};

	private static List<String> _getBuildPropertyAsList(String key) {
		try {
			return JenkinsResultsParserUtil.getBuildPropertyAsList(key);
		}
		catch (IOException ioe) {
			throw new RuntimeException(
				"Unable to get build property " + key, ioe);
		}
	}

	private String _log(int num, File file, String format) {
		StringBuilder sb = new StringBuilder();

		sb.append("git log -n ");
		sb.append(num);
		sb.append(" --pretty=format:'");
		sb.append(format);
		sb.append("'");

		if (file != null) {
			sb.append(" ");

			try {
				sb.append(file.getCanonicalPath());
			}
			catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}

		ExecutionResult result = executeBashCommands(sb.toString());

		if (result.getExitValue() != 0) {
			throw new RuntimeException("Unable to run: git log");
		}

		return result.getStandardOut();
	}

	private String _status() {
		String command = "git status";

		ExecutionResult result = executeBashCommands(command);

		if (result.getExitValue() != 0) {
			throw new RuntimeException("Unable to run: git status");
		}

		return result.getStandardOut();
	}

	private static final Pattern _gitDirectoryPathPattern = Pattern.compile(
		"gitdir\\: (.*\\.git)");
	private static final Pattern _gitLsRemotePattern = Pattern.compile(
		"(?<sha>[^\\s]{40}+)[\\s]+refs/heads/(?<name>[^\\s]+)");
	private static final List<String> _privateOnlyRepositoryNames =
		_getBuildPropertyAsList(
			"git.working.directory.private.only.repository.names");
	private static final List<String> _publicOnlyRepositoryNames =
		_getBuildPropertyAsList(
			"git.working.directory.public.only.repository.names");

	private File _gitDirectory;
=======
	private RemoteConfig _getRemoteConfig(String remoteName)
		throws GitAPIException {

		List<RemoteConfig> remoteConfigs = getRemoteConfigs();

		for (RemoteConfig remoteConfig : remoteConfigs) {
			if (remoteName.equals(remoteConfig.getName())) {
				return remoteConfig;
			}
		}

		return null;
	}

	private RemoteConfig _getUpstreamPublicRemoteConfig()
		throws GitAPIException {

		RemoteConfig upstreamPublicRemoteConfig = _getRemoteConfig(
			"upstream-public");

		if (upstreamPublicRemoteConfig != null) {
			return upstreamPublicRemoteConfig;
		}

		String upstreamRemoteURL = getRemoteURL(_getRemoteConfig("upstream"));

		upstreamRemoteURL = upstreamRemoteURL.replace("-ee", "");
		upstreamRemoteURL = upstreamRemoteURL.replace("-private", "");

		return addRemote(true, "upstream-public", upstreamRemoteURL);
	}

	private RemoteConfig _getUpstreamRemoteConfig() throws GitAPIException {
		RemoteConfig upstreamRemoteConfig = _getRemoteConfig("upstream");

		String upstreamRemoteURL = getRemoteURL(upstreamRemoteConfig);

		if (upstreamRemoteURL.contains(_repositoryName + ".git")) {
			return upstreamRemoteConfig;
		}

		return _getUpstreamPublicRemoteConfig();
	}

	private static final List<RepositoryState> _rebaseRepositoryStates =
		Arrays.asList(
			RepositoryState.REBASING, RepositoryState.REBASING_INTERACTIVE,
			RepositoryState.REBASING_MERGE, RepositoryState.REBASING_REBASING);

	static {
		JschConfigSessionFactory jschConfigSessionFactory =
			new JschConfigSessionFactory() {

				@Override
				protected void configure(Host host, Session session) {
					session.setConfig("StrictHostKeyChecking", "no");
				}

			};

		SshSessionFactory.setInstance(jschConfigSessionFactory);
	}

	private final Git _git;
	private File _gitDirectory;
	private List<RemoteConfig> _remoteConfigs;
	private final Repository _repository;
>>>>>>> compatible
	private final String _repositoryName;
	private final String _repositoryUsername;
	private final String _upstreamBranchName;
	private File _workingDirectory;

}