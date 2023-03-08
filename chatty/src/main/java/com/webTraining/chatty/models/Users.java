package com.webTraining.chatty.models;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;
	private String password;


    @ManyToMany
    @JoinTable(name ="chat_rooms_members",
    joinColumns = @JoinColumn(name ="member_id") ,
            inverseJoinColumns = @JoinColumn(name="room_id")
    )
    @JsonIgnore
    private Set<ChatRooms> chatrooms = new HashSet<>() ;

    public Set<ChatRooms> getChatrooms() {
        return chatrooms;
    }

    public void joinChatRoom(ChatRooms chatroom) {
        this.chatrooms.add(chatroom) ;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date created_at;

    public Users() {
    }
    public Users(Integer id){
        this.id = id ;
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
