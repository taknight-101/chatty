package com.webTraining.chatty.controllers ;


import com.webTraining.chatty.models.ChatRooms;
import com.webTraining.chatty.services.interfaces.ChatRoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class ChatRoomsController {



	@Autowired
	private ChatRoomsService chatRoomsService ;

	@GetMapping
	public ResponseEntity<List<ChatRooms>> list() {

		return  new ResponseEntity<>(chatRoomsService.list()  , HttpStatus.OK);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ChatRooms>  create( Principal principal ,  @RequestBody ChatRooms chat_room) {
		return  new ResponseEntity<>(  chatRoomsService.create( null  , chat_room) , HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ChatRooms>  get(@PathVariable("id") int id) {
		return  new ResponseEntity<>(  chatRoomsService.get(id) , HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ChatRooms>  update(@PathVariable("id") int id, @RequestBody ChatRooms new_chat_room) {

				var result =chatRoomsService.update(id, new_chat_room) ;
				if (result!= null){
					return new ResponseEntity<>(result  , HttpStatus.OK);

				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);

				}


	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
		try {
			chatRoomsService.delete(id) ;
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
