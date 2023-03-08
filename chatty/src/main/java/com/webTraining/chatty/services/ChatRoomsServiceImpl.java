package com.webTraining.chatty.services;


import com.webTraining.chatty.models.ChatRooms;
import com.webTraining.chatty.repositories.ChatRoomsRepository;
import com.webTraining.chatty.repositories.UsersRepository;
import com.webTraining.chatty.services.interfaces.ChatRoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ChatRoomsServiceImpl implements ChatRoomsService {

    @Autowired
    private ChatRoomsRepository chatRoomsRepository;

    @Autowired
    private UsersRepository userRepository;



    @Override
    public List<ChatRooms> list() {
        return chatRoomsRepository.findAll();
    }

    @Override
    public ChatRooms get(int id) {
        var chat_room = chatRoomsRepository.findById(id) ;
        if (! chat_room.isEmpty() ) {
            return  chat_room.get() ;
        }
        throw new RuntimeException("ROOM NOT FOUND");
    }

    @Override
    public ChatRooms update(int id, ChatRooms new_chat_room) {
        var chat_room = chatRoomsRepository.findById(id);

        if (chat_room.isPresent()) {
            ChatRooms cr = chat_room.get();
            cr.setName(new_chat_room.getName());
            cr.setDescription(new_chat_room.getDescription());
            cr.setCreated_at(new java.sql.Date(System.currentTimeMillis()));
            return chatRoomsRepository.save(cr) ;


        } else {
            return  null ;
        }
    }

    @Override
    public void delete(int id) {
         chatRoomsRepository.deleteById(id);

    }

    @Override
    public ChatRooms create(Principal principal, ChatRooms chat_room) {
//        String username = principal.getName();

//        var theUser = userRepository.findByUsername(username);

        //work
        // return ResponseEntity.ok(new JwtResponse(theUser.get().getId(), theUser.get().getUsername()  ,theUser.get().getChatrooms().stream().map(cr -> cr.getId()).toList()));


        return chatRoomsRepository.save(chat_room);
    }
}
