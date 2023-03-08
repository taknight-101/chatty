package com.webTraining.chatty.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.sql.Date;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Messages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;



    @Transient
    private String from_user_username ;

    public String getFrom_user_username() {
        return from_user_username;
    }

    public void setFrom_user_username(String from_user_username) {
        this.from_user_username = from_user_username;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="id")
	private MessageTypes type;



    @Transient
    private Integer from_user ;
    private Integer to_user ;

    @Column(name = "room_id")
    private Integer roomId ;

    private String content ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date created_at;

    public Integer getFrom_user() {
        return from_user;
    }

    public void setFrom_user(Integer from_user) {
        this.from_user = from_user;
    }

    public Integer getTo_user() {
        return to_user;
    }

    public void setTo_user(Integer to_user) {
        this.to_user = to_user;
    }

    @ManyToOne
    @JoinColumn(name = "from_user")
    private Users fromUser ;

    //TODO: use JPA for proper cascade delete :)
//    @ManyToOne
//    @JoinColumn(name = "room_id")
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private ChatRooms chatRoom ;



    public Users getFromUser() {
        return fromUser;
    }

    public void setFromUser(Users fromUser) {
        this.fromUser = fromUser;
    }
//
//    @ManyToOne
//    @JoinColumn(name = "to_user")
//    private Users to_user ;
//
//    public Users getFrom_user() {
//        return from_user;
//    }
//
//    public void setFrom_user(Users from_user) {
//        this.from_user = from_user;
//    }
//
//    public Users getTo_user() {
//        return to_user;
//    }
//
//    public void setTo_user(Users to_user) {
//        this.to_user = to_user;
//    }
//    @ManyToOne
//    @JoinColumn(name ="from_user")
//    private Users from_user ;
//    @ManyToOne
//    @JoinColumn(name ="to_user")
//    private Users to_user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MessageTypes getType() {
        return type;
    }

    public void setType(MessageTypes type) {
        this.type = type;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }




    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

}


