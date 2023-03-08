package com.webTraining.chatty.services.interfaces;

import com.webTraining.chatty.models.ChatRoomMember;
import com.webTraining.chatty.models.LoginRequest;
import com.webTraining.chatty.models.SignUpRequest;
import com.webTraining.chatty.models.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UsersService {

     List<Users> list() ;
     void create( Users user)  ;
        Users get(int id)  ;

       Users update( int id   , Users new_user)  ;


       void delete(int id)  ;

        ResponseEntity<?> registerUser( SignUpRequest signUpRequest)  ;

          ResponseEntity<?> joinRoom( ChatRoomMember crm)   ;

     ResponseEntity<?> authenticateUser(LoginRequest loginRequest) ;

}
