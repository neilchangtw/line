package com.testritegroup.tccschat.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.event.message.FileMessageContent;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.VideoMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.flex.component.Button.ButtonStyle;
import com.linecorp.bot.model.message.flex.component.FlexComponent;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.Carousel;
import com.linecorp.bot.model.message.flex.unit.FlexAlign;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.testritegroup.tccschat.common.SystemConst.Channel;
import com.testritegroup.tccschat.core.mapper.TbBasStoreMapper;
import com.testritegroup.tccschat.core.model.TbBasStore;
import com.testritegroup.tccschat.core.model.TbBasStoreExample;
import com.testritegroup.tccschat.service.SendMsgService;

@Service
public class SendMsgServiceImpl implements SendMsgService {

	@Autowired
	private LineMessagingClient lineMessagingClient;

	@Autowired
	private TbBasStoreMapper tbBasStoreMapper;

	@Override
	public void pushTextMessage(String toId, String text) throws Exception {

		TextMessage msg = new TextMessage(text); // Max character limit: 300
		PushMessage message = new PushMessage(toId, msg);
		lineMessagingClient.pushMessage(message);
	}

	@Override
	public void pushTextMessage(String toId, List<String> lstText) throws Exception {

		List<Message> lstMessage = new ArrayList<>();

		for (String text : lstText) {
			lstMessage.add(new TextMessage(text));
		}
		PushMessage message = new PushMessage(toId, lstMessage);
		lineMessagingClient.pushMessage(message);
	}

	@Override
	public void replyTextMessage(String replyToken, String text) throws Exception {

		TextMessage msg = new TextMessage(text); // Max character limit: 300
		ReplyMessage message = new ReplyMessage(replyToken, msg);
		lineMessagingClient.replyMessage(message);
	}

	@Override
	public void replyTextMessage(String replyToken, List<String> lstText) throws Exception {

		List<Message> lstMessage = new ArrayList<>();

		for (String text : lstText) {
			lstMessage.add(new TextMessage(text));
		}
		ReplyMessage message = new ReplyMessage(replyToken, lstMessage);
		lineMessagingClient.replyMessage(message);
	}

	@Override
	public void pushFlexMessage(String toId, String altText, Bubble bubble) throws Exception {

		FlexMessage msg = new FlexMessage(altText, bubble);
		PushMessage message = new PushMessage(toId, msg);
		lineMessagingClient.pushMessage(message);
	}

	@Override
	public void pushFlexMessage(String toId, String altText, List<Bubble> lstBubble) throws Exception {

		Carousel carousel = Carousel.builder().contents(lstBubble).build();
		FlexMessage msg = new FlexMessage(altText, carousel);
		PushMessage message = new PushMessage(toId, msg);
		lineMessagingClient.pushMessage(message);
	}

	@Override
	public void replyFlexMessage(String replyToken, String altText, Bubble bubble) throws Exception {

		FlexMessage msg = new FlexMessage(altText, bubble);
		ReplyMessage message = new ReplyMessage(replyToken, msg);
		lineMessagingClient.replyMessage(message);
	}

	@Override
	public void replyFlexMessage(String replyToken, String altText, List<Bubble> lstBubble) throws Exception {

		Carousel carousel = Carousel.builder().contents(lstBubble).build();
		FlexMessage msg = new FlexMessage(altText, carousel);
		ReplyMessage message = new ReplyMessage(replyToken, msg);
		lineMessagingClient.replyMessage(message);
	}

	@Override
	public void replySelectAreaPostback(String replyToken) throws Exception {

		// 發區域卡牌
		// heroBlock
//		Image heroBlock = Image.builder()
//				.url(new URI("https://scdn.line-apps.com/n/channel_devcenter/img/fx/01_1_cafe.png"))
//				.size(ImageSize.FULL_WIDTH).aspectRatio(ImageAspectRatio.R16TO9).aspectMode(ImageAspectMode.Cover)
//				.build();

		// bodyBlock
		List<FlexComponent> bodyContents = new ArrayList<>();
		Map<String, String> mapArea = new LinkedHashMap<>();
		mapArea.put("North", "北部");
		mapArea.put("Central", "中部");
		mapArea.put("South", "南部");
		mapArea.put("East", "東部");

		bodyContents.add(Text.builder().text("請選擇所在地區").align(FlexAlign.CENTER).build());

		for (Iterator<Map.Entry<String, String>> entries = mapArea.entrySet().iterator(); entries.hasNext();) {
			Map.Entry<String, String> entry = entries.next();
			System.out.println(entry.getKey() + " : " + entry.getValue());

			Button button = Button.builder().style(ButtonStyle.SECONDARY)
					.action(new PostbackAction(entry.getValue(), "SelectArea=" + entry.getKey(), entry.getValue()))
					.build();
			bodyContents.add(button);
		}

		Box bodyBlock = Box.builder().layout(FlexLayout.VERTICAL).spacing("5px").contents(bodyContents).build();

		// footerBlock
		Box footerBlock = Box.builder().layout(FlexLayout.VERTICAL).contents().build();

//		Bubble bubble = Bubble.builder().hero(heroBlock).body(bodyBlock).footer(footerBlock).build();
		Bubble bubble = Bubble.builder().body(bodyBlock).footer(footerBlock).build();
		this.replyFlexMessage(replyToken, "請選擇所在地區", bubble);
	}

	@Override
	public void replySelectStorePostback(String replyToken, String area, Channel channel) throws Exception {

		// 發門店卡牌
		TbBasStoreExample example = new TbBasStoreExample();
		TbBasStoreExample.Criteria criteria = example.createCriteria();
		criteria.andChannelIdEqualTo(channel.getValue());
		criteria.andAreaEqualTo(area);
		example.setOrderByClause(" store_sort, store_id ");

		List<TbBasStore> lstStore = tbBasStoreMapper.selectByExample(example);

		if (!lstStore.isEmpty()) {

			List<Bubble> lstBubble = new ArrayList<>();
			List<FlexComponent> bodyContents = new ArrayList<>();

			for (int i = 0; i < lstStore.size(); i++) {

				TbBasStore store = lstStore.get(i);

				if (i % 3 == 0) {

					if (i != 0) {
						// add to lstBubble
						Box bodyBlock = Box.builder().layout(FlexLayout.VERTICAL).spacing("5px").contents(bodyContents)
								.build();
						Box footerBlock = Box.builder().layout(FlexLayout.VERTICAL).contents().build();
						Bubble bubble = Bubble.builder().body(bodyBlock).footer(footerBlock).build();
						lstBubble.add(bubble);
					}

					// heroBlock
//					Image heroBlock = Image.builder()
//							.url(new URI("https://scdn.line-apps.com/n/channel_devcenter/img/fx/01_1_cafe.png"))
//							.size(ImageSize.FULL_WIDTH).aspectRatio(ImageAspectRatio.R16TO9).aspectMode(ImageAspectMode.Cover)
//							.build();

					bodyContents = new ArrayList<>();
					bodyContents.add(Text.builder().text("請選擇喜愛門店").align(FlexAlign.CENTER).build());

				}
				Button button = Button.builder().style(ButtonStyle.SECONDARY)
						.action(new PostbackAction(store.getStoreName(), "SelectStore=" + store.getStoreId(),
								store.getStoreName()))
						.build();
				bodyContents.add(button);
			}

			// add to lstBubble
			Box bodyBlock = Box.builder().layout(FlexLayout.VERTICAL).spacing("5px").contents(bodyContents).build();

			// footerBlock
			Box footerBlock = Box.builder().layout(FlexLayout.VERTICAL).contents().build();
//			Bubble bubble = Bubble.builder().hero(heroBlock).body(bodyBlock).footer(footerBlock).build();
			Bubble bubble = Bubble.builder().body(bodyBlock).footer(footerBlock).build();
			lstBubble.add(bubble);

			this.replyFlexMessage(replyToken, "請選擇喜愛門店", lstBubble);
		}
	}

	@Override
	public void pushImageMessage(String toId, String mediaId) throws Exception {
		
		ImageMessage msg = ImageMessage.builder().originalContentUrl(new URI("")).build();
		PushMessage pushMessage = new PushMessage(toId, msg);
		lineMessagingClient.pushMessage(pushMessage);
	}

	@Override
	public void pushVideoMessage(String toId, String mediaId) throws Exception {
		
		VideoMessage msg = VideoMessage.builder().originalContentUrl(new URI("")).build();
		PushMessage pushMessage = new PushMessage(toId, msg);
		lineMessagingClient.pushMessage(pushMessage);
	}
}
