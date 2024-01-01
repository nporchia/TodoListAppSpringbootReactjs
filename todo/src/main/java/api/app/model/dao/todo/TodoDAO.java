package api.app.model.dao.todo;

import java.util.List;

import api.app.model.entity.todoobj.TodoObj;

public interface TodoDAO {
	public List<TodoObj> findAll();
	
	public TodoObj findById(int id);
	
	public void save(TodoObj todo);

	public void deleteById(int id);
}
