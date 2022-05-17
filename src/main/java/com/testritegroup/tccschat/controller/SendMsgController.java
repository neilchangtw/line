package com.testritegroup.tccschat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testritegroup.tccschat.common.SystemConst.Channel;
import com.testritegroup.tccschat.service.SendMsgService;
import com.testritegroup.tccschat.vo.receive.Event;
import com.testritegroup.tccschat.vo.receive.EventWrapper;
import com.testritegroup.tccschat.vo.send.PushMediaMessage;
import com.testritegroup.tccschat.vo.send.PushTextMessage;

@Scope("request")
@RestController
@RequestMapping("/sendMessage")
public class SendMsgController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private SendMsgService sendMsgService;

	@PostMapping(value = "/pushTextMessage")
	public void pushTextMessage(@RequestBody PushTextMessage pushTextMessage) {

		try {
			sendMsgService.pushTextMessage(pushTextMessage.getToId(), pushTextMessage.getText());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/pushMediaMessage")
	public void pushMediaMessage(@RequestBody PushMediaMessage pushMediaMessage) {

		try {

			String mediaType = pushMediaMessage.getMediaType();

			switch (mediaType) {

			case "image":
				sendMsgService.pushImageMessage(pushMediaMessage.getToId(), pushMediaMessage.getMediaId());
				break;
				
			case "video":
				sendMsgService.pushVideoMessage(pushMediaMessage.getToId(), pushMediaMessage.getMediaId());
				break;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
