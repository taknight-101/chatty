package com.webTraining.chatty.config.security.services ;

import java.util.Collection;
import java.util.Objects;

import com.webTraining.chatty.models.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  //ahmed
  private int id;
//
//  private String firstName;
//
//  private String lastName;

  private String userName;

  @JsonIgnore
  private String password;

  public UserDetailsImpl(int id,  String userName, String password) {
    this.id = id;

    this.userName = userName;
    this.password = password;
  }

  public static UserDetailsImpl build(Users user) {
    return new UserDetailsImpl(
        user.getId(),
        user.getUsername(),
        user.getPassword()) ;

  }

  public int getId() {
    return id;
  }

//  public String getFirstName() {
//    return firstName;
//  }
//
//  public String getLastName() {
//    return lastName;
//  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }
}
