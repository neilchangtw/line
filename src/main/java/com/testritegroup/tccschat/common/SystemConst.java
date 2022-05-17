package com.testritegroup.tccschat.common;

public class SystemConst {

	public enum Channel {
		
		TLW("A0"), // TLW特力屋
		HOLA("B0"); // HOLA和樂

		private String value;

		Channel(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum PostbackData {
		
		// 區域卡牌
		SelectAreaNorth, // 北部
		SelectAreaCentral, // 中部
		SelectAreaSouth, // 南部
		SelectAreaEast; // 東部
	}
}
