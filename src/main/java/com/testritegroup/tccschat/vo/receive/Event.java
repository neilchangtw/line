package com.testritegroup.tccschat.vo.receive;

public class Event {

	private String replyToken;
	private String type;
	private Source source;
	private String timestamp;
	private String mode;
	private String webhookEventId;
	private DeliveryContext deliveryContext;
	// =====
	private Message message;
	private Postback postback;

	public String getReplyToken() {
		return replyToken;
	}

	public void setReplyToken(String replyToken) {
		this.replyToken = replyToken;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Postback getPostback() {
		return postback;
	}

	public void setPostback(Postback postback) {
		this.postback = postback;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getWebhookEventId() {
		return webhookEventId;
	}

	public void setWebhookEventId(String webhookEventId) {
		this.webhookEventId = webhookEventId;
	}

	public DeliveryContext getDeliveryContext() {
		return deliveryContext;
	}

	public void setDeliveryContext(DeliveryContext deliveryContext) {
		this.deliveryContext = deliveryContext;
	}

}
