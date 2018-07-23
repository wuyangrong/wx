package com.wyr.wx.wechat.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;

public class NewsBuilder extends AbstractBuilder {
	
	private List<WxMpXmlOutNewsMessage.Item> articles = new ArrayList<>();

	public NewsBuilder addArticle(WxMpXmlOutNewsMessage.Item... items) {
		Collections.addAll(this.articles, items);
		return this;
	}

	public NewsBuilder articles(List<WxMpXmlOutNewsMessage.Item> articles) {
		this.articles = articles;
		return this;
	}

	@Override
	public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
		
		return WxMpXmlOutMessage.NEWS()
				.fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
				.articles(articles)
				.build();
	}

}
