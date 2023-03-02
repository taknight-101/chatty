package com.webTraining.chatty.repositories;

import com.webTraining.chatty.models.Message;
import com.webTraining.chatty.models.Messages;
import com.webTraining.chatty.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MessagesRepository extends JpaRepository<Messages, Integer> {
    public List<Messages> findByRoomId(Integer room_id);


}
