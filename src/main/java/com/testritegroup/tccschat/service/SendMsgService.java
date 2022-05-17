package com.testritegroup.tccschat.service;

import java.util.List;

import com.linecorp.bot.model.message.flex.container.Bubble;
import com.testritegroup.tccschat.common.SystemConst.Channel;

public interface SendMsgService {

	/**
	 * push text message
	 * 
	 * @param toId
	 * @param text
	 * @throws Exception
	 */
	public void pushTextMessage(String toId, String text) throws Exception;

	/**
	 * push text message
	 * 
	 * @param toId
	 * @param lstText
	 * @throws Exception
	 */
	public void pushTextMessage(String toId, List<String> lstText) throws Exception;

	/**
	 * reply text message
	 * 
	 * @param replyToken
	 * @param text
	 * @throws Exception
	 */
	public void replyTextMessage(String replyToken, String text) throws Exception;

	/**
	 * reply text message
	 * 
	 * @param replyToken
	 * @param lstText
	 * @throws Exception
	 */
	public void replyTextMessage(String replyToken, List<String> lstText) throws Exception;

	/**
	 * push flex message
	 * 
	 * @param toId
	 * @param altText
	 * @param bubble
	 * @throws Exception
	 */
	public void pushFlexMessage(String toId, String altText, Bubble bubble) throws Exception;

	/**
	 * push flex message
	 * 
	 * @param toId
	 * @param altText
	 * @param lstBubble
	 * @throws Exception
	 */
	public void pushFlexMessage(String toId, String altText, List<Bubble> lstBubble) throws Exception;

	/**
	 * reply flex message
	 * 
	 * @param replyToken
	 * @param altText
	 * @param bubble
	 * @throws Exception
	 */
	public void replyFlexMessage(String replyToken, String altText, Bubble bubble) throws Exception;

	/**
	 * reply flex message
	 * 
	 * @param replyToken
	 * @param altText
	 * @param lstBubble
	 * @throws Exception
	 */
	public void replyFlexMessage(String replyToken, String altText, List<Bubble> lstBubble) throws Exception;

	/**
	 * reply選擇所在地區
	 * 
	 * @param replyToken
	 * @param channel
	 * @throws Exception
	 */
	public void replySelectAreaPostback(String replyToken) throws Exception;

	/**
	 * reply選擇門店
	 * 
	 * @param replyToken
	 * @param area
	 * @param channel
	 * @throws Exception
	 */
	public void replySelectStorePostback(String replyToken, String area, Channel channel) throws Exception;

	/**
	 * 傳送Image
	 * 
	 * @param toId
	 * @param mediaId
	 * @throws Exception
	 */
	public void pushImageMessage(String toId, String mediaId) throws Exception;

	/**
	 * 傳送Video
	 * 
	 * @param toId
	 * @param mediaId
	 * @throws Exception
	 */
	public void pushVideoMessage(String toId, String mediaId) throws Exception;

}
