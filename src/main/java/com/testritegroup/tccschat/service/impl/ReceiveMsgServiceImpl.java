package com.testritegroup.tccschat.service.impl;

import com.testritegroup.tccschat.common.SystemConst.Channel;
import com.testritegroup.tccschat.service.ReceiveMsgService;
import com.testritegroup.tccschat.service.SendMsgService;
import com.testritegroup.tccschat.vo.receive.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiveMsgServiceImpl implements ReceiveMsgService {



	@Autowired
	private SendMsgService sendMsgService;


	@Override
	public void customerFollow(Event event, Channel channel) throws Exception {

	}

	@Override
	public void customerUnfollow(Event event, Channel channel) throws Exception {

	}

	@Override
	public void processTextMessage(Event event, Channel channel) throws Exception {

	}

	@Override
	public void processSelectStoreAreaPostback(Event event, Channel channel, String storeId) throws Exception {

	}

	@Override
	public void processImageMessage(Event event, Channel channel) throws Exception {

	}

	@Override
	public void processAudioMessage(Event event, Channel channel) throws Exception {

	}

	@Override
	public void processVideoMessage(Event event, Channel channel) throws Exception {

	}

	@Override
	public void processFileMessage(Event event, Channel channel) throws Exception {

	}

	@Override
	public void processStickerMessage(Event event, Channel channel) throws Exception {

	}

	@Override
	public void processLocationMessage(Event event, Channel channel) throws Exception {

	}
}
