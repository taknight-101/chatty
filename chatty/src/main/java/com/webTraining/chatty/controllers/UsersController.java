package com.webTraining.chatty.controllers;
import com.webTraining.chatty.exceptions.ERROR_MSGS;
import com.webTraining.chatty.exceptions.InternalServerErrorException;
import com.webTraining.chatty.exceptions.NotFoundException;
import com.webTraining.chatty.models.*;
import com.webTraining.chatty.services.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {




@Autowired
private UsersService usersService ;

	@GetMapping
	public ResponseEntity<List<Users>> list() {

		return new ResponseEntity<>( usersService.list()  , HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody Users user) {

		usersService.create(user);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Users> get(@PathVariable("id") int id) {

		var result = usersService.get(id) ;
		if (result != null ){
			return new ResponseEntity<Users>(result  , HttpStatus.OK);

		}else {
			throw new NotFoundException(ERROR_MSGS.USER_NOT_FOUND) ;
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Users> update(@PathVariable("id") int id, @RequestBody Users new_user) {

		var result = usersService.update(id , new_user) ;

		if(result != null ){
			return new ResponseEntity<Users>(result  , HttpStatus.OK);

		}else {
			throw new NotFoundException(ERROR_MSGS.USER_NOT_FOUND) ;
		}



	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
		try {
				usersService.delete(id) ;
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw  new InternalServerErrorException(ERROR_MSGS.INTERNAL_SERVER_ERROR) ;

		}
	}


	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		return  usersService.authenticateUser(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {

		return  usersService.registerUser(signUpRequest);

	}


	@PostMapping("/joinRoom")
	public ResponseEntity<?> joinRoom(@RequestBody ChatRoomMember crm)  {

		return  usersService.joinRoom(crm) ;
	}




}
