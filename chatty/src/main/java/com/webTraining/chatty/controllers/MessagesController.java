package com.webTraining.chatty.controllers ;
import com.webTraining.chatty.models.*;
import com.webTraining.chatty.services.interfaces.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/msgs")
public class MessagesController {

@Autowired
private MessagesService messagesService  ;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Messages>   create(@RequestBody Messages msg) {
		return new ResponseEntity<>( messagesService.create(msg)  , HttpStatus.OK);


	}



	@GetMapping("/{room_id}")
	public  ResponseEntity<List<Messages>> list(@PathVariable("room_id") int room_id) {
		return new ResponseEntity<>( messagesService.list(room_id)  , HttpStatus.OK);


	}


	@GetMapping("/leaveRoom/{member_id}/{room_id}")
	public void leaveRoom(@PathVariable("member_id") int member_id, @PathVariable("room_id") int room_id) {
		 messagesService.leaveRoom(member_id ,room_id);
	}

	@GetMapping("/deleteRoom/{room_id}")
	public void leaveRoom(@PathVariable("room_id") int room_id) {
		messagesService.deleteRoom(room_id);
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
