package com.webTraining.chatty.services;

import com.webTraining.chatty.config.security.jwt.JwtUtils;
import com.webTraining.chatty.exceptions.BadRequestException;
import com.webTraining.chatty.exceptions.ERROR_MSGS;
import com.webTraining.chatty.exceptions.NotFoundException;
import com.webTraining.chatty.models.*;
import com.webTraining.chatty.repositories.ChatRoomsRepository;
import com.webTraining.chatty.repositories.UsersRepository;
import com.webTraining.chatty.services.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private ChatRoomsRepository chatRoomRepository;

    @Autowired
    JwtUtils jwtUtils;


    @Override
    public List<Users> list() {
        return userRepository.findAll();
    }

    @Override
    public void create(Users user) {
        userRepository.save(user);
    }

    @Override
    public Users get(int id) {
        var user = userRepository.findById(id) ;
        if (! user.isEmpty() ) {
            return  user.get() ;
        }
        return  null ;


    }

    @Override
    public Users update(int id, Users new_user) {
        var user = userRepository.findById(id);

        if (user.isPresent()) {
            Users usr = user.get();
            usr.setUsername(new_user.getUsername());
            usr.setPassword(new_user.getPassword());

            return userRepository.save(usr) ;


        } else {
            return  null ;


        }
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);

    }

    @Override
    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {


        var user_exists = userRepository.findByUsername(signUpRequest.getUsername()) ;
        if (user_exists.isPresent()){

            throw  new BadRequestException(ERROR_MSGS.USER_ALREADY_EXISTS);

        }
        Users user = new Users(
                signUpRequest.getUsername(),
                jwtUtils.hash(signUpRequest.getPassword())

        );
        Users newUser = userRepository.save(user);
        String jwt = jwtUtils.generateJwtToken(newUser);
        return ResponseEntity.ok(new JwtResponse(jwt, newUser.getId(), newUser.getUsername()));
    }

    @Override
    public ResponseEntity<?> joinRoom(ChatRoomMember crm) {
        var user = userRepository.findById(crm.getMember_id()) ;
        if (! user.isEmpty() ) {
            var usr = user.get();
            var room = chatRoomRepository.findById(crm.getRoom_id()) ;
            if (! room.isEmpty() ) {
                var cr = room.get();
                usr.joinChatRoom(cr);
                userRepository.save(usr); // i only wanna save it once :)

            }


            return ResponseEntity.ok(new JwtResponse(usr.getId(), usr.getUsername()  ,usr.getChatrooms().stream().map(cr -> cr.getId()).toList()));

        }

        throw new NotFoundException(ERROR_MSGS.USER_NOT_FOUND) ;
    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {



        var tmp = loginRequest.getUsername() ;
        var theUser = userRepository.findByUsername(loginRequest.getUsername());
        if (theUser.isPresent() && jwtUtils.verifyHash(loginRequest.getPassword(), theUser.get().getPassword())) {
            String jwt = jwtUtils.generateJwtToken(theUser.get());
            var test = theUser.get().getChatrooms().stream().map(cr -> cr.getId()).toList();

            return ResponseEntity.ok(new JwtResponse(jwt, theUser.get().getId(), theUser.get().getUsername()  ,theUser.get().getChatrooms().stream().map(cr -> cr.getId()).toList()));
        } else
                 throw new BadRequestException(ERROR_MSGS.LOGIN_ERROR) ;
    }
}
