package com.webTraining.chatty.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ChatRoomsMembers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    @Column(name="room_id")
    private Integer roomId ;


    @Column(name="member_id")
    private Integer memberId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoom_id() {
        return roomId;
    }

    public void setRoom_id(Integer room_id) {
        this.roomId = room_id;
    }

    public Integer getMember_id() {
        return memberId;
    }

    public void setMember_id(Integer member_id) {
        this.memberId = member_id;
    }
}
