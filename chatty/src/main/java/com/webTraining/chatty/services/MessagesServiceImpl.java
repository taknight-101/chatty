package com.webTraining.chatty.services;

import com.webTraining.chatty.models.Messages;
import com.webTraining.chatty.repositories.ChatRoomsMembersRepository;
import com.webTraining.chatty.repositories.ChatRoomsRepository;
import com.webTraining.chatty.repositories.MessagesRepository;
import com.webTraining.chatty.repositories.UsersRepository;
import com.webTraining.chatty.services.interfaces.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessagesServiceImpl implements MessagesService {


    @Autowired
    private UsersRepository userRepository ;

    @Autowired
    ChatRoomsMembersRepository chatRoomsMembersRepository ;

    @Autowired
    ChatRoomsRepository chatRoomsRepository ;


    @Autowired
    private MessagesRepository messageRepository;



    @Override
    public Messages create(Messages msg) {
        var from_user = userRepository.findById(msg.getFrom_user());
//		var from_user = msg.getFrom_user(); // this should be linked to the user table via the FK <from_user> automatically via JPA i guess :)



//		switch (msg.getType().getType_name()){
//			case "text" : msg.getType().setCode(MSG_TYPES.TEXT);
//			break ;
//			case "audio" : msg.getType().setCode(MSG_TYPES.AUDIO);
//			break ;
//			case "video" : msg.getType().setCode(MSG_TYPES.VIDEO);
//			break ;
//			case "photo" : msg.getType().setCode(MSG_TYPES.PHOTO);
//			break ;
//			case "url"  : msg.getType().setCode(MSG_TYPES.URL);
//			default:
//				msg.getType().setCode(MSG_TYPES.NOT_IMPLEMENTED);
//
//
//		}
        //TODO: till i fix the relation to the users table :) >>> i fixed it via the use of @Transient on msg's username field :) simply not mapping it to db :)
//		msg.setFrom_user_username(from_user.get().getUsername());

        msg.setFromUser(from_user.get());


        return messageRepository.save(msg) ;

    }

    @Override
    public List<Messages> list(int room_id) {
        var testi = messageRepository.findByRoomId(room_id) ;

        return messageRepository.findByRoomId(room_id);
//		return  messageRepository.findAll();
    }

    @Override
    public void leaveRoom(int member_id , int room_id) {
        var room_joining = chatRoomsMembersRepository.findByMemberIdAndRoomId(member_id , room_id) ;


        chatRoomsMembersRepository.delete(room_joining.get());
    }


    //TODO : use proper JPA cascade delete
    @Override
    public void deleteRoom( int room_id) {
        var to_delete_room_messages = messageRepository.findByRoomId( room_id) ;
        if (to_delete_room_messages != null) {
            messageRepository.deleteAll(to_delete_room_messages);

            var to_delete_room_memberships =   chatRoomsMembersRepository.findByRoomId(room_id) ;
            if (to_delete_room_memberships != null) {
                chatRoomsMembersRepository.deleteAll(to_delete_room_memberships.get());

            }

            var to_delete_room =   chatRoomsRepository.findById(room_id) ;
            if (to_delete_room != null) {
                chatRoomsRepository.delete(to_delete_room.get());

            }


        }


    }
}
