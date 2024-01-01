package api.app.controller;



import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api.app.model.entity.todoobj.TodoObj;
import api.app.service.todo.TodoService;



@RestController
@RequestMapping("/api")
public class TodoController {
	
	@Autowired
	private TodoService todoService;


	@GetMapping("/todos")
	public List<TodoObj> findAll() {
		List<TodoObj> todos = todoService.findAll();
		return todos;
	}
	@GetMapping("/todos/{todoId}")
	public ResponseEntity<?> gettodo(@PathVariable int todoId) {
		TodoObj todo = todoService.findById(todoId);

		if (todo == null) {
			String mensaje = "Todo not found " + todoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}


		return new ResponseEntity<>(todo, HttpStatus.OK);
	}

	@PostMapping("/todos")
	public ResponseEntity<?> addUsuario(@RequestBody TodoObj todo) {
		
		if (todo.getTitle() != null && todo.getDescription() != null ) {
			todoService.save(todo);
			return new ResponseEntity<>(todo, HttpStatus.CREATED);
		} else {

	        return new ResponseEntity<>("The required fields are incomplete", HttpStatus.BAD_REQUEST);
	    }
		
	}

	@PutMapping("/todos/{todoId}")
	public ResponseEntity<?> updateUsuario(@PathVariable int todoId, @RequestBody TodoObj todo) {
		TodoObj todoOld = todoService.findById(todoId);

		if (todoOld == null) {
			String mensaje = "Todo Not found on this id" + todoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		todoService.update(todoId, todo);

		return new ResponseEntity<>(todo, HttpStatus.OK);
	}
	
	@DeleteMapping("/todo/{todoId}")
	public ResponseEntity<String> deleteUsuario(@PathVariable int todoId) {

		TodoObj todo = todoService.findById(todoId);

		if (todo == null) {
			String message = "Couldnt find this todo on this id " + todoId;
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}

		todoService.deleteById(todoId);


		String message = "Todo Deleted [TodoID = " + todoId + "]";
		return new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
	}


}