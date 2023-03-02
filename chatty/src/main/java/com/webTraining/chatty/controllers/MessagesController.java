package com.webTraining.chatty.controllers ;

import com.webTraining.chatty.models.MSG_TYPES;
import com.webTraining.chatty.models.Message;
import com.webTraining.chatty.models.Messages;
import com.webTraining.chatty.models.Users;
import com.webTraining.chatty.repositories.MessagesRepository;
import com.webTraining.chatty.repositories.UsersRepository;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
	@RequestMapping("/api/msgs")
	public class MessagesController {

	@Autowired
	private UsersRepository userRepository ;



	@Autowired
	private MessagesRepository messageRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Messages create(@RequestBody Messages msg) {

			var from_user = userRepository.findById(msg.getFrom_user());
		/// hmmmmm!!
		switch (msg.getType().getType_name()){
			case "text" : msg.getType().setCode(MSG_TYPES.TEXT);
			break ;
			case "audio" : msg.getType().setCode(MSG_TYPES.AUDIO);
			break ;
			case "video" : msg.getType().setCode(MSG_TYPES.VIDEO);
			break ;
			case "photo" : msg.getType().setCode(MSG_TYPES.PHOTO);
			break ;
			case "url"  : msg.getType().setCode(MSG_TYPES.URL);
			default:
				msg.getType().setCode(MSG_TYPES.NOT_IMPLEMENTED);


		}
		//TODO: till i fix the relation to the users table :)
		msg.setFrom_user_username(from_user.get().getUsername());

		return messageRepository.save(msg);
	}



	@GetMapping("/{room_id}")
	public List<Messages> list(@PathVariable("room_id") int room_id) {
		return messageRepository.findByRoomId(room_id);
//		return  messageRepository.findAll();
	}

//
//
//
//	@GetMapping("/{id}")
//	public Users get(@PathVariable("id") int id) {
//		var user = userRepository.findById(id) ;
//		if (! user.isEmpty() ) {
//			return  user.get() ;
//		}
//		throw new RuntimeException("USER NOT FOUND");
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<Users> update(@PathVariable("id") int id, @RequestBody Users new_user) {
//		var user = userRepository.findById(id);
//
//		if (user.isPresent()) {
//			Users usr = user.get();
//			usr.setUsername(new_user.getUsername());
//			usr.setPassword(new_user.getPassword());
//
//			return new ResponseEntity<Users>(userRepository.save(usr)  , HttpStatus.OK);
//
//
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
//		try {
//			userRepository.deleteById(id);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}


}
