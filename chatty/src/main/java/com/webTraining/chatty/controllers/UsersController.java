package com.webTraining.chatty.controllers;

import com.webTraining.chatty.models.*;
import com.webTraining.chatty.repositories.ChatRoomsRepository;
import com.webTraining.chatty.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.webTraining.chatty.config.security.jwt.JwtUtils ;
@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private ChatRoomsRepository chatRoomRepository;



	@Autowired
	JwtUtils jwtUtils;


	@GetMapping
	public List<Users> list() {
		return userRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody Users user) {

		userRepository.save(user);
	}

	@GetMapping("/{id}")
	public Users get(@PathVariable("id") int id) {
		var user = userRepository.findById(id) ;
		if (! user.isEmpty() ) {
			return  user.get() ;
		}
		throw new RuntimeException("USER NOT FOUND");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Users> update(@PathVariable("id") int id, @RequestBody Users new_user) {
		var user = userRepository.findById(id);

		if (user.isPresent()) {
			Users usr = user.get();
			usr.setUsername(new_user.getUsername());
			usr.setPassword(new_user.getPassword());

			return new ResponseEntity<Users>(userRepository.save(usr)  , HttpStatus.OK);


		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		var tmp = loginRequest.getUsername() ;
		var theUser = userRepository.findByUsername(loginRequest.getUsername());
		if (theUser.isPresent() && jwtUtils.verifyHash(loginRequest.getPassword(), theUser.get().getPassword())) {
			String jwt = jwtUtils.generateJwtToken(theUser.get());
		var test = theUser.get().getChatrooms().stream().map(cr -> cr.getId()).toList();

			return ResponseEntity.ok(new JwtResponse(jwt, theUser.get().getId(), theUser.get().getUsername()  ,theUser.get().getChatrooms().stream().map(cr -> cr.getId()).toList()));
		} else
			throw new RuntimeException("Wrong user name or password");
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {


		Users user = new Users(
				signUpRequest.getUsername(),
				jwtUtils.hash(signUpRequest.getPassword())

				);
		Users newUser = userRepository.save(user);
		String jwt = jwtUtils.generateJwtToken(newUser);
		return ResponseEntity.ok(new JwtResponse(jwt, newUser.getId(), newUser.getUsername()));
	}


	@PostMapping("/joinRoom")
	public Users joinRoom(@RequestBody ChatRoomMember crm)  {
		var user = userRepository.findById(crm.getMember_id()) ;
		if (! user.isEmpty() ) {
			var usr = user.get();
			var room = chatRoomRepository.findById(crm.getRoom_id()) ;
			if (! room.isEmpty() ) {
					var cr = room.get();
						usr.joinChatRoom(cr);
						userRepository.save(usr); // i only wanna save it once :)

			}

			return  user.get() ;
		}
		throw new RuntimeException("USER NOT FOUND");
	}




}
