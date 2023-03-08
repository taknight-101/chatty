package com.webTraining.chatty.services.interfaces;

import com.webTraining.chatty.models.Messages;


import java.util.List;

public interface MessagesService {

     Messages create( Messages msg)  ;
     List<Messages> list ( int room_id);
     void leaveRoom ( int member_id , int room_id);
     void deleteRoom( int room_id) ;

}
