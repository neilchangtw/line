package com.testritegroup.tccschat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.testritegroup.tccschat.common.SystemConst.Channel;
import com.testritegroup.tccschat.service.ReceiveMsgService;
import com.testritegroup.tccschat.service.SendMsgService;
import com.testritegroup.tccschat.util.GsonUtils;
import com.testritegroup.tccschat.vo.receive.Event;
import com.testritegroup.tccschat.vo.receive.EventWrapper;

@Scope("request")
@RestController
@RequestMapping("/receiveMessage")
public class ReceiveMsgController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private GsonUtils gsonUtils;

	@Autowired
	private ReceiveMsgService receiveMsgService;

	@Autowired
	private SendMsgService sendMsgService;

	@GetMapping(value = "test/{value}")
	@ResponseBody
	public String test(@PathVariable("value") String value) {

		try {
			logger.info("value :{}", value);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}

	@PostMapping(value = "/callback/{channelId}")
	public void webhook(@RequestBody EventWrapper events, @PathVariable("channelId") String channelId) {

		try {

			logger.info("events: {}", gsonUtils.toJson(events));
			
			Channel channel = Channel.valueOf(channelId); // 通路
			logger.info("channel: {}", channel.getValue());

			for (Event event : events.getEvents()) {

				logger.info("event type: {}", event.getType());

				// 當event為message時進入此case執行，其他event(如follow、unfollow、leave等)
				// https://developers.line.biz/en/docs/messaging-api/receiving-messages/#webhook-event-types
				switch (event.getType()) {

				// 加入好友 / 解除封鎖
				case "follow":
					receiveMsgService.customerFollow(event, channel);
					break;

				// 已被封鎖
				case "unfollow":
					receiveMsgService.customerUnfollow(event, channel);
					break;

				// Indicates that the user performed a postback action. You can reply to this
				// event.
				case "postback":

					String replyToken = event.getReplyToken();
					String data = event.getPostback().getData();

					if (data.startsWith("SelectArea=")) {
						data = data.replace("SelectArea=", "");
						sendMsgService.replySelectStorePostback(replyToken, data, channel);

					} else if (data.startsWith("SelectStore=")) {
							data = data.replace("SelectStore=", "");
//							// 紀錄並指派門店
							receiveMsgService.processSelectStoreAreaPostback(event, channel, data);
					}
					break;

					// 傳送訊息
				case "message":

//					logger.info("message type: {}", event.getMessage().getType());

					switch (event.getMessage().getType()) {

					case "text":
						receiveMsgService.processTextMessage(event, channel);
						break;
					case "image":
						receiveMsgService.processImageMessage(event, channel);
						break;
					case "audio":
						receiveMsgService.processAudioMessage(event, channel);
						break;
					case "video":
						receiveMsgService.processVideoMessage(event, channel);
						break;
					case "file":
						receiveMsgService.processFileMessage(event, channel);
						break;
					case "sticker":
						receiveMsgService.processStickerMessage(event, channel);
						break;
					case "location":
						receiveMsgService.processLocationMessage(event, channel);
						break;
					}
					break;

				// Indicates that the user finished watching the video message with the
				// specified trackingId sent from the LINE Official account. You can reply to
				// this event.
				case "videoPlayComplete":
					break;
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
