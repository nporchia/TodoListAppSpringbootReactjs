package api.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.app.model.entity.user.User;
import api.app.model.entity.user.UserDTO;
import api.app.model.mapper.UserMapper;
import api.app.service.user.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@GetMapping("/users")
	public List<UserDTO> findAll() {

		// Retornará todos las Usuarioes de la DB
		List<User> users = userService.findAll();
		List<UserDTO> userDTOs = new ArrayList<>();

		for (User user : users) {
			userDTOs.add(UserMapper.convertToDTO(user));
		}

		return userDTOs;
	}
	@GetMapping("/users/{userId}")
	public ResponseEntity<?> getUsuario(@PathVariable int userId) {
		User user = userService.findById(userId);

		if (user == null) {
			String message = "Usuario not found with ID: " + userId;
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}

		UserDTO usuarioDTO = UserMapper.convertToDTO(user);
		return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> addUsuario(@RequestBody UserDTO userDTO) {
	    System.out.println(userDTO);
	    User user = UserMapper.convertToEntity(userDTO);

	    // Print the values of username and password
	    System.out.println("Username: " + user.getUsername());
	    System.out.println("Password: " + user.getPassword());

	    if (user.getUsername() != null && user.getPassword() != null) {
	        userService.save(user);

	        UserDTO nuevoUsuarioDTO = UserMapper.convertToDTO(user);
	        return new ResponseEntity<>(nuevoUsuarioDTO, HttpStatus.CREATED);
	    } else {
	        return new ResponseEntity<>("Required Files.", HttpStatus.BAD_REQUEST);
	    }
	}
	@PutMapping("/users/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody UserDTO userDTO) {
		User user = UserMapper.convertToEntity(userDTO);
		User userOld = userService.findById(userId);

		if (userOld == null) {
			String message = "Usuario not found with ID: " + userId;
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}

		
		userService.update(userId, user);

		UserDTO updatedUsuarioDTO = UserMapper.convertToDTO(user);

		return new ResponseEntity<>(updatedUsuarioDTO, HttpStatus.OK);
	}
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<String> deleteUsuario(@PathVariable int userId) {

		User user = userService.findById(userId);

		if (user == null) {
			String message = "User not found with ID: " + userId;
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}

		userService.deleteById(userId);

		String message = "User deleted [UserID = " + userId + "]";
		return new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
	}


}
