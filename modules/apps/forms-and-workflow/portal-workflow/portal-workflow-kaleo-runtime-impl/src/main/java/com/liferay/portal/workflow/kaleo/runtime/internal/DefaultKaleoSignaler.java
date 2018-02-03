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

package com.liferay.portal.workflow.kaleo.runtime.internal;

import com.liferay.portal.kernel.exception.PortalException;
<<<<<<< HEAD
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
=======
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSenderFactory;
>>>>>>> compatible
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.KaleoSignaler;
<<<<<<< HEAD
import com.liferay.portal.workflow.kaleo.runtime.constants.KaleoRuntimeDestinationNames;
=======
>>>>>>> compatible
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;
import com.liferay.portal.workflow.kaleo.runtime.internal.node.NodeExecutorFactory;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutor;
import com.liferay.portal.workflow.kaleo.runtime.util.ExecutionContextHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 */
@Transactional(
	isolation = Isolation.PORTAL, propagation = Propagation.SUPPORTS,
	rollbackFor = {Exception.class}
)
public class DefaultKaleoSignaler
	extends BaseKaleoBean implements KaleoSignaler {

<<<<<<< HEAD
=======
	public void afterPropertiesSet() {
		_singleDestinationMessageSender =
			_singleDestinationMessageSenderFactory.
				createSingleDestinationMessageSender(_destinationName);
	}

	public void setDestinationName(String destinationName) {
		_destinationName = destinationName;
	}

>>>>>>> compatible
	@Override
	public void signalEntry(
			String transitionName, ExecutionContext executionContext)
		throws PortalException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		executionContext.setTransitionName(transitionName);

		PathElement startPathElement = new PathElement(
			null, kaleoInstanceToken.getCurrentKaleoNode(), executionContext);

<<<<<<< HEAD
		_sendPathElement(startPathElement);
=======
		_singleDestinationMessageSender.send(startPathElement);
>>>>>>> compatible
	}

	@Override
	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRED,
		rollbackFor = {Exception.class}
	)
	public void signalExecute(
			KaleoNode currentKaleoNode, ExecutionContext executionContext)
		throws PortalException {

		NodeExecutor nodeExecutor = _nodeExecutorFactory.getNodeExecutor(
			currentKaleoNode.getType());

		List<PathElement> remainingPathElements = new ArrayList<>();

		nodeExecutor.execute(
			currentKaleoNode, executionContext, remainingPathElements);

		_executionContextHelper.checkKaleoInstanceComplete(executionContext);

		for (PathElement remainingPathElement : remainingPathElements) {
<<<<<<< HEAD
			_sendPathElement(remainingPathElement);
=======
			_singleDestinationMessageSender.send(remainingPathElement);
>>>>>>> compatible
		}
	}

	@Override
	public void signalExit(
			String transitionName, ExecutionContext executionContext)
		throws PortalException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		executionContext.setTransitionName(transitionName);

		KaleoNode currentKaleoNode = kaleoInstanceToken.getCurrentKaleoNode();

		PathElement pathElement = new PathElement(
			currentKaleoNode, null, executionContext);

<<<<<<< HEAD
		_sendPathElement(pathElement);
	}

	private void _sendPathElement(PathElement pathElement) {
		Message message = new Message();

		message.setPayload(pathElement);

		_messageBus.sendMessage(
			KaleoRuntimeDestinationNames.KALEO_GRAPH_WALKER, message);
	}
=======
		_singleDestinationMessageSender.send(pathElement);
	}

	private String _destinationName;
>>>>>>> compatible

	@ServiceReference(type = ExecutionContextHelper.class)
	private ExecutionContextHelper _executionContextHelper;

<<<<<<< HEAD
	@ServiceReference(type = MessageBus.class)
	private MessageBus _messageBus;

	@ServiceReference(type = NodeExecutorFactory.class)
	private NodeExecutorFactory _nodeExecutorFactory;

=======
	@ServiceReference(type = NodeExecutorFactory.class)
	private NodeExecutorFactory _nodeExecutorFactory;

	private SingleDestinationMessageSender _singleDestinationMessageSender;

	@ServiceReference(type = SingleDestinationMessageSenderFactory.class)
	private SingleDestinationMessageSenderFactory
		_singleDestinationMessageSenderFactory;

>>>>>>> compatible
}