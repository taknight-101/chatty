package com.webTraining.chatty.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.apache.catalina.User;
import org.hibernate.boot.model.process.internal.UserTypeResolution;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ChatRooms {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String description;

    @ManyToMany
    @JoinTable(name ="chat_rooms_members",
            joinColumns = @JoinColumn(name ="room_id") ,
            inverseJoinColumns = @JoinColumn(name="member_id")
    )
    private Set<Users> users = new HashSet<>() ;

    public Set<Users> getUsers() {
        return users;
    }
    public void joinUser(Users user ) {
        this.users.add(user) ;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date created_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
