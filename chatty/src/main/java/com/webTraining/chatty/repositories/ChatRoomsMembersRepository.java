package com.webTraining.chatty.repositories;

import com.webTraining.chatty.models.ChatRoomMember;
import com.webTraining.chatty.models.ChatRooms;
import com.webTraining.chatty.models.ChatRoomsMembers;
import com.webTraining.chatty.models.Users;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ChatRoomsMembersRepository extends JpaRepository<ChatRoomsMembers, Integer> {

    public Optional<ChatRoomsMembers> findByMemberId(Integer member_id);

//    @Query("select crm.member_id from ChatRoomMembers crm where crm.id = :user_id")
//    List<Object> get_member_rooms( @Param("user_id")  Integer user_id);

}
