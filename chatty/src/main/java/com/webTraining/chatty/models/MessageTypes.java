package com.webTraining.chatty.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MessageTypes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer type_name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getType_name() {
        return type_name;
    }

    public void setType_name(Integer type_name) {
        this.type_name = type_name;
    }

    public MessageTypes(){

    }

    public MessageTypes(Integer type_name){
    this.type_name = type_name;
    }

    public MessageTypes(Integer id, Integer type_name) {
        this.id = id;
        this.type_name = type_name;

    }
}
