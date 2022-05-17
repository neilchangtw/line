package com.testritegroup.tccschat.service;

import com.testritegroup.tccschat.common.SystemConst.Channel;
import com.testritegroup.tccschat.vo.receive.Event;

public interface ReceiveMsgService {

	/**
	 * 加入好友 / 解除封鎖
	 * 
	 * @param event
	 * @param channel
	 * @throws Exception
	 */
	public void customerFollow(Event event, Channel channel) throws Exception;

	/**
	 * 已被封鎖
	 * 
	 * @param event
	 * @param channel
	 * @throws Exception
	 */
	public void customerUnfollow(Event event, Channel channel) throws Exception;

	/**
	 * 處理Text訊息
	 * 
	 * @param event
	 * @param channel
	 * @throws Exception
	 */
	public void processTextMessage(Event event, Channel channel) throws Exception;

	/**
	 * 處理選擇門市Postback
	 * 
	 * @param event
	 * @param channel
	 * @param storeId
	 * @throws Exception
	 */
	public void processSelectStoreAreaPostback(Event event, Channel channel, String storeId) throws Exception;

	/**
	 * 處理Image訊息
	 * 
	 * @param event
	 * @param channel
	 * @throws Exception
	 */
	public void processImageMessage(Event event, Channel channel) throws Exception;

	/**
	 * 處理Audio訊息
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void processAudioMessage(Event event, Channel channel) throws Exception;

	/**
	 * 處理Video訊息
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void processVideoMessage(Event event, Channel channel) throws Exception;

	/**
	 * 處理File訊息
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void processFileMessage(Event event, Channel channel) throws Exception;

	/**
	 * 處理Sticker訊息
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void processStickerMessage(Event event, Channel channel) throws Exception;

	/**
	 * 處理Location訊息
	 * 
	 * @param event
	 * @param channel
	 * @throws Exception
	 */
	public void processLocationMessage(Event event, Channel channel) throws Exception;
}
