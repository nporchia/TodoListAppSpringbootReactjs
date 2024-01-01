package api.app.service.todo;

import java.util.List;

import api.app.model.entity.todoobj.TodoObj;

public interface TodoService {
	public List<TodoObj> findAll();
	
	public TodoObj findById(int id);

	public void save(TodoObj todo);
	
	public void update(int todoId, TodoObj todo);

	public void deleteById(int id);
}
