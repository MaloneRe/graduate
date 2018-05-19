package com.personalizednewsrecommendation.manager.controller;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;

/**
 * 这个是测试websocket
 * @ClassName MyWebSocketHandler
 * @Description TODO
 * @author zhanghui
 * @date May 7, 2018 4:16:32 PM
 */

public class MyWebSocketHandler implements WebSocketHandler{


    private static final Logger log = Logger.getLogger(MyWebSocketHandler.class);

    // 保存所有的用户session
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();
    // 连接 就绪时 
    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {

        log.info("connect websocket success.......");

        users.add(session);

    }
    // 处理信息
    @Override
    public void handleMessage(WebSocketSession session,
            WebSocketMessage<?> message) throws Exception {

        JSONObject gson = new JSONObject();

        // 将消息JSON格式通过Gson转换成Map
        // message.getPayload().toString() 获取消息具体内容
        Map<String, Object> msg = (Map<String, Object>) gson.toJSON(message.getPayload().toString());

        log.info("handleMessage......."+message.getPayload()+"..........."+msg);

//      session.sendMessage(message);

        // 处理消息 msgContent消息内容
        //ObjectMessage objectMessage = new ObjectMessage() ;
        TextMessage textMessage = new TextMessage(msg.get("msgContent").toString(), true);
        // 调用方法（发送消息给所有人）
        sendMsgToAllUsers(textMessage);
    }
    // 处理传输时异常
    @Override
    public void handleTransportError(WebSocketSession session,
            Throwable exception) throws Exception {
        // TODO Auto-generated method stub
    }
    // 关闭 连接时
    @Override
    public void afterConnectionClosed(WebSocketSession session,
            CloseStatus closeStatus) throws Exception {
        log.info("connect websocket closed.......");
        users.remove(session);

    }

    @Override
    public boolean supportsPartialMessages() {
        // TODO Auto-generated method stub
        return false;
    }

    // 给所有用户发送 信息
    public void sendMsgToAllUsers(WebSocketMessage<?> message) throws Exception{

        for (WebSocketSession user : users) {
            user.sendMessage(message);
        }

    }

}