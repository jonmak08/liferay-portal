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

package com.liferay.portal.workflow.kaleo.definition.internal.deployment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
<<<<<<< HEAD
import com.liferay.portal.kernel.util.StringPool;
=======
>>>>>>> compatible
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.workflow.kaleo.KaleoWorkflowModelConverter;
import com.liferay.portal.workflow.kaleo.definition.Condition;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.NodeType;
import com.liferay.portal.workflow.kaleo.definition.State;
import com.liferay.portal.workflow.kaleo.definition.Task;
import com.liferay.portal.workflow.kaleo.definition.Transition;
import com.liferay.portal.workflow.kaleo.definition.deployment.WorkflowDeployer;
import com.liferay.portal.workflow.kaleo.definition.exception.KaleoDefinitionValidationException;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
<<<<<<< HEAD
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.service.KaleoConditionLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionVersionLocalService;
=======
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.service.KaleoConditionLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalService;
>>>>>>> compatible
import com.liferay.portal.workflow.kaleo.service.KaleoNodeLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTransitionLocalService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = WorkflowDeployer.class)
public class DefaultWorkflowDeployer implements WorkflowDeployer {

<<<<<<< HEAD
	/**
	 * @deprecated As of 1.0.0, replaced by {@link #deploy(String, String,
	 *             Definition, ServiceContext)}
	 * @review
	 */
	@Deprecated
=======
>>>>>>> compatible
	@Override
	public WorkflowDefinition deploy(
			String title, Definition definition, ServiceContext serviceContext)
		throws PortalException {

<<<<<<< HEAD
		return deploy(title, definition.getName(), definition, serviceContext);
	}

	@Override
	public WorkflowDefinition deploy(
			String title, String name, Definition definition,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoDefinition kaleoDefinition =
			_kaleoDefinitionLocalService.fetchKaleoDefinition(
				name, serviceContext);

		if (kaleoDefinition == null) {
			kaleoDefinition = _kaleoDefinitionLocalService.addKaleoDefinition(
				name, title, definition.getDescription(),
				definition.getContent(), 1, serviceContext);
=======
		KaleoDefinition kaleoDefinition =
			_kaleoDefinitionLocalService.fetchLatestKaleoDefinition(
				definition.getName(), serviceContext);

		if (kaleoDefinition == null) {
			kaleoDefinition = _kaleoDefinitionLocalService.addKaleoDefinition(
				definition.getName(), title, definition.getDescription(),
				definition.getContent(), definition.getVersion(),
				serviceContext);
>>>>>>> compatible
		}
		else {
			kaleoDefinition =
				_kaleoDefinitionLocalService.incrementKaleoDefinition(
<<<<<<< HEAD
					definition, name, title, serviceContext);
		}

		KaleoDefinitionVersion kaleoDefinitionVersion =
			_kaleoDefinitionVersionLocalService.getKaleoDefinitionVersion(
				kaleoDefinition.getCompanyId(), kaleoDefinition.getName(),
				getVersion(kaleoDefinition.getVersion()));

		long kaleoDefinitionVersionId =
			kaleoDefinitionVersion.getKaleoDefinitionVersionId();
=======
					definition, title, serviceContext);
		}

		long kaleoDefinitionId = kaleoDefinition.getKaleoDefinitionId();
>>>>>>> compatible

		Collection<Node> nodes = definition.getNodes();

		Map<String, KaleoNode> kaleoNodesMap = new HashMap<>();

		for (Node node : nodes) {
			KaleoNode kaleoNode = _kaleoNodeLocalService.addKaleoNode(
<<<<<<< HEAD
				kaleoDefinitionVersionId, node, serviceContext);
=======
				kaleoDefinitionId, node, serviceContext);
>>>>>>> compatible

			kaleoNodesMap.put(node.getName(), kaleoNode);

			NodeType nodeType = node.getNodeType();

			if (nodeType.equals(NodeType.TASK)) {
				Task task = (Task)node;

				_kaleoTaskLocalService.addKaleoTask(
<<<<<<< HEAD
					kaleoDefinitionVersionId, kaleoNode.getKaleoNodeId(), task,
=======
					kaleoDefinitionId, kaleoNode.getKaleoNodeId(), task,
>>>>>>> compatible
					serviceContext);
			}
			else if (nodeType.equals(NodeType.CONDITION)) {
				Condition condition = (Condition)node;

				_kaleoConditionLocalService.addKaleoCondition(
<<<<<<< HEAD
					kaleoDefinitionVersionId, kaleoNode.getKaleoNodeId(),
					condition, serviceContext);
=======
					kaleoDefinitionId, kaleoNode.getKaleoNodeId(), condition,
					serviceContext);
>>>>>>> compatible
			}
		}

		for (Node node : nodes) {
			KaleoNode kaleoNode = kaleoNodesMap.get(node.getName());

			for (Transition transition : node.getOutgoingTransitionsList()) {
<<<<<<< HEAD
				Node sourceNode = transition.getSourceNode();

				KaleoNode sourceKaleoNode = kaleoNodesMap.get(
					sourceNode.getName());

				if (sourceKaleoNode == null) {
					throw new KaleoDefinitionValidationException.
						MustSetSourceNode(sourceNode.getName());
				}

				Node targetNode = transition.getTargetNode();

				KaleoNode targetKaleoNode = kaleoNodesMap.get(
					targetNode.getName());

				if (targetKaleoNode == null) {
					throw new KaleoDefinitionValidationException.
						MustSetTargetNode(targetNode.getName());
				}

				_kaleoTransitionLocalService.addKaleoTransition(
					kaleoNode.getKaleoDefinitionVersionId(),
=======
				KaleoNode sourceKaleoNode = kaleoNodesMap.get(
					transition.getSourceNode().getName());

				if (sourceKaleoNode == null) {
					throw new KaleoDefinitionValidationException.
						MustSetSourceNode(transition.getSourceNode().getName());
				}

				KaleoNode targetKaleoNode = kaleoNodesMap.get(
					transition.getTargetNode().getName());

				if (targetKaleoNode == null) {
					throw new KaleoDefinitionValidationException.
						MustSetTargetNode(transition.getTargetNode().getName());
				}

				_kaleoTransitionLocalService.addKaleoTransition(
					kaleoNode.getKaleoDefinitionId(),
>>>>>>> compatible
					kaleoNode.getKaleoNodeId(), transition, sourceKaleoNode,
					targetKaleoNode, serviceContext);
			}
		}

		State initialState = definition.getInitialState();

		if (initialState == null) {
			throw new KaleoDefinitionValidationException.
				MustSetInitialStateNode();
		}

		String startKaleoNodeName = initialState.getName();

		KaleoNode kaleoNode = kaleoNodesMap.get(startKaleoNodeName);

<<<<<<< HEAD
		long kaleoDefinitionId = kaleoDefinition.getKaleoDefinitionId();

		_kaleoDefinitionLocalService.activateKaleoDefinition(
			kaleoDefinitionId, kaleoDefinitionVersionId,
			kaleoNode.getKaleoNodeId(), serviceContext);
=======
		_kaleoDefinitionLocalService.activateKaleoDefinition(
			kaleoDefinitionId, kaleoNode.getKaleoNodeId(), serviceContext);
>>>>>>> compatible

		return _kaleoWorkflowModelConverter.toWorkflowDefinition(
			kaleoDefinition);
	}

<<<<<<< HEAD
	protected String getVersion(int version) {
		return version + StringPool.PERIOD + 0;
	}

=======
>>>>>>> compatible
	@Reference
	private KaleoConditionLocalService _kaleoConditionLocalService;

	@Reference
	private KaleoDefinitionLocalService _kaleoDefinitionLocalService;

	@Reference
<<<<<<< HEAD
	private KaleoDefinitionVersionLocalService
		_kaleoDefinitionVersionLocalService;

	@Reference
=======
>>>>>>> compatible
	private KaleoNodeLocalService _kaleoNodeLocalService;

	@Reference
	private KaleoTaskLocalService _kaleoTaskLocalService;

	@Reference
	private KaleoTransitionLocalService _kaleoTransitionLocalService;

	@Reference
	private KaleoWorkflowModelConverter _kaleoWorkflowModelConverter;

}