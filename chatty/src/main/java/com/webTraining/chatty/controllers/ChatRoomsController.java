package com.webTraining.chatty.controllers ;

import com.webTraining.chatty.models.ChatRooms;
import com.webTraining.chatty.models.Users;
import com.webTraining.chatty.repositories.ChatRoomsRepository;
import com.webTraining.chatty.repositories.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class ChatRoomsController {
	@Autowired
	private ChatRoomsRepository chatRoomsRepository;


	@GetMapping
	public List<ChatRooms> list() {
		return chatRoomsRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ChatRooms create(@RequestBody ChatRooms chat_room) {

		return chatRoomsRepository.save(chat_room);
	}

	@GetMapping("/{id}")
	public ChatRooms get(@PathVariable("id") int id) {
		var chat_room = chatRoomsRepository.findById(id) ;
		if (! chat_room.isEmpty() ) {
			return  chat_room.get() ;
		}
		throw new RuntimeException("ROOM NOT FOUND");
	}

	@PutMapping("/{id}")
	public ResponseEntity<ChatRooms> update(@PathVariable("id") int id, @RequestBody ChatRooms new_chat_room) {
		var chat_room = chatRoomsRepository.findById(id);

		if (chat_room.isPresent()) {
			ChatRooms cr = chat_room.get();
			cr.setName(new_chat_room.getName());
			cr.setDescription(new_chat_room.getDescription());
			cr.setCreated_at(new java.sql.Date(System.currentTimeMillis()));
			return new ResponseEntity<>(chatRoomsRepository.save(cr)  , HttpStatus.OK);


		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
		try {
			chatRoomsRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
