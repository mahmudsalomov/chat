package websocket.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import websocket.chat.model.ChatMessage;

import java.util.Objects;

@Controller
public class ChatController {

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
//        System.out.println(chatMessage);
////        chatMessage.setIp((String) headerAccessor.getSessionAttributes().get("ip"));
//        System.out.println("Header: "+headerAccessor.getSessionAttributes());
//        System.out.println("Header: "+headerAccessor.getSessionAttributes().get("ip"));
//        System.out.println("Header: "+headerAccessor.getMessageHeaders());
        return chatMessage;
    }
    

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
//        System.out.println(headerAccessor.getSessionAttributes().get("ip"));
        chatMessage.setIp(String.valueOf (headerAccessor.getSessionAttributes().get("ip")));
//        System.out.println(chatMessage);
        return chatMessage;
    }

}
