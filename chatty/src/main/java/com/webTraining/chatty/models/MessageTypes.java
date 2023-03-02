package com.webTraining.chatty.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MessageTypes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String type_name;
	private MSG_TYPES code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }


    public MSG_TYPES getCode() {
        return code;
    }

    public void setCode(MSG_TYPES code) {
        this.code = code;
    }

    public MessageTypes(){

    }

    public MessageTypes(String type_name){
    this.type_name = type_name;
    }

    public MessageTypes(Integer id, String type_name, MSG_TYPES code) {
        this.id = id;
        this.type_name = type_name;
        this.code = code;
    }
}
