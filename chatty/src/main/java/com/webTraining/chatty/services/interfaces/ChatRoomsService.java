package com.webTraining.chatty.services.interfaces;

import com.webTraining.chatty.models.ChatRooms;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

public interface ChatRoomsService {
    List<ChatRooms> list()  ;
    ChatRooms get(int id)  ;
    ChatRooms update(int id,  ChatRooms new_chat_room) ;
    void delete( int id)  ;
    ChatRooms create(Principal principal ,  ChatRooms chat_room)  ;

}
