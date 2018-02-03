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

package com.liferay.portal.workflow.kaleo.runtime.internal.graph.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
<<<<<<< HEAD
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageListener;
=======
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSenderFactory;
>>>>>>> compatible
import com.liferay.portal.workflow.kaleo.runtime.constants.KaleoRuntimeDestinationNames;
import com.liferay.portal.workflow.kaleo.runtime.graph.GraphWalker;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
=======
import org.osgi.service.component.annotations.Activate;
>>>>>>> compatible
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = {"destination.name=" + KaleoRuntimeDestinationNames.KALEO_GRAPH_WALKER},
	service = MessageListener.class
)
public class PathElementMessageListener extends BaseMessageListener {

<<<<<<< HEAD
=======
	@Activate
	protected void activate() {
		_singleDestinationMessageSender =
			_singleDestinationMessageSenderFactory.
				createSingleDestinationMessageSender(
					KaleoRuntimeDestinationNames.KALEO_GRAPH_WALKER);
	}

>>>>>>> compatible
	@Override
	protected void doReceive(Message message) throws Exception {
		PathElement pathElement = (PathElement)message.getPayload();

		List<PathElement> remainingPathElements = new ArrayList<>();

		_graphWalker.follow(
			pathElement.getStartNode(), pathElement.getTargetNode(),
			remainingPathElements, pathElement.getExecutionContext());

		for (PathElement remainingPathElement : remainingPathElements) {
<<<<<<< HEAD
			message = new Message();

			message.setPayload(remainingPathElement);

			_messageBus.sendMessage(
				KaleoRuntimeDestinationNames.KALEO_GRAPH_WALKER, message);
=======
			_singleDestinationMessageSender.send(remainingPathElement);
>>>>>>> compatible
		}
	}

	@Reference
	private GraphWalker _graphWalker;

<<<<<<< HEAD
	@Reference
	private MessageBus _messageBus;
=======
	private SingleDestinationMessageSender _singleDestinationMessageSender;

	@Reference
	private SingleDestinationMessageSenderFactory
		_singleDestinationMessageSenderFactory;
>>>>>>> compatible

}