package com.webTraining.chatty.models ;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JwtResponse {
  private String authToken;
  private String type = "Bearer";
  private int id;
  private String username;

  private List<Integer> chat_rooms_id ;

  public JwtResponse(String authToken, int id, String username, List<Integer> chat_rooms_id) {
    this.id = id;
    this.authToken = authToken;
    this.username = username;
    this.chat_rooms_id = chat_rooms_id ;
  }

  public JwtResponse( int id, String username, List<Integer> chat_rooms_id) {
    this.id = id;
  
    this.username = username;
    this.chat_rooms_id = chat_rooms_id ;
  }

  public List<Integer> getChat_rooms_id() {
    return chat_rooms_id;
  }

  public void setChat_rooms_id(List<Integer> chat_rooms_id) {
    this.chat_rooms_id = chat_rooms_id;
  }

  public JwtResponse(String authToken, int id, String username) {
    this.id = id;
    this.authToken = authToken;
    this.username = username;
  }
  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public int getUser_id() {
    return id;
  }

  public void setUser_id(int id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
