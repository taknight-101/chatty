package com.webTraining.chatty.repositories ;

import com.webTraining.chatty.models.ChatRooms;
import com.webTraining.chatty.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatRoomsRepository extends JpaRepository<ChatRooms, Integer> {


}
