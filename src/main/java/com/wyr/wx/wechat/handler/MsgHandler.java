package com.wyr.wx.wechat.handler;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.wyr.wx.wechat.builder.NewsBuilder;
import com.wyr.wx.wechat.builder.TextBuilder;
import com.wyr.wx.wechat.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService weixinService,
                                  WxSessionManager sessionManager) {

    if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
      //TODO 可以选择将消息保存到本地
    }

    //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
    try {
      if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
          && weixinService.getKefuService().kfOnlineList()
          .getKfOnlineList().size() > 0) {
        return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
            .fromUser(wxMessage.getToUser())
            .toUser(wxMessage.getFromUser()).build();
      }
    } catch (WxErrorException e) {
      e.printStackTrace();
    }
    
    if (wxMessage.getMsgType().equals(XmlMsgType.TEXT)) {
    	if(wxMessage.getContent().equals("胡歌")) {
    		WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
    		item.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530786472628&di=195cd69b957bbfe4b6fdc32334900b47&imgtype=0&src=http%3A%2F%2Fp1.qhimgs4.com%2Ft01b7c3f54acfc93bd0.jpg");
    		item.setDescription("李逍遥");
    		item.setTitle("胡歌");
    		item.setUrl("http://yrwu.free.ngrok.cc/mhubV2/web/index");
    		return new NewsBuilder().addArticle(item).build("胡歌", wxMessage, weixinService);
    	}else if(wxMessage.getContent().equals("刘亦菲")) {
    		WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
    		item.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530786833335&di=e52fc899f601341eb850b903ef53dcd5&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201304%2F19%2F20130419192418_z5Cia.thumb.700_0.jpeg");
    		item.setDescription("赵灵儿");
    		item.setTitle("刘亦菲");
    		item.setUrl("http://yrwu.free.ngrok.cc/mhubV2/web/index");
    		return new NewsBuilder().addArticle(item).build("刘亦菲", wxMessage, weixinService);
    	}else if(wxMessage.getContent().contains("仙剑")) {
    		List<WxMpXmlOutNewsMessage.Item> items = new ArrayList<>();
    		WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
    		item.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530786472628&di=195cd69b957bbfe4b6fdc32334900b47&imgtype=0&src=http%3A%2F%2Fp1.qhimgs4.com%2Ft01b7c3f54acfc93bd0.jpg");
    		item.setDescription("李逍遥");
    		item.setTitle("胡歌");
    		item.setUrl("http://yrwu.free.ngrok.cc/mhubV2/web/index");
    		items.add(item);
    		
    		WxMpXmlOutNewsMessage.Item item2 = new WxMpXmlOutNewsMessage.Item();
    		item2.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530786833335&di=e52fc899f601341eb850b903ef53dcd5&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201304%2F19%2F20130419192418_z5Cia.thumb.700_0.jpeg");
    		item2.setDescription("赵灵儿");
    		item2.setTitle("刘亦菲");
    		item2.setUrl("http://yrwu.free.ngrok.cc/mhubV2/web/index");
    		items.add(item2);
    		
    		return new NewsBuilder().articles(items).build("仙剑", wxMessage, weixinService);
    	}
    	
    	return new TextBuilder().build(wxMessage.getContent(), wxMessage, weixinService);
    }else if(wxMessage.getMsgType().equals(XmlMsgType.IMAGE)) {
    	return new TextBuilder().build("好图", wxMessage, weixinService);
    }
    
    
    //TODO 组装回复消息
    String content = JsonUtils.toJson(wxMessage);

    return new TextBuilder().build(content, wxMessage, weixinService);

  }

}
