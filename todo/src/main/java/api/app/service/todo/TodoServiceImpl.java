package api.app.service.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.app.model.dao.todo.TodoDAO;
import api.app.model.entity.todoobj.TodoObj;
	@Service
	public class TodoServiceImpl implements TodoService {

			@Autowired(required=true) 
			private TodoDAO todoDAO;

			@Override
			public List<TodoObj> findAll() {
				List<TodoObj> todos = todoDAO.findAll();
				return todos;
			}

			@Override
			public TodoObj findById(int id) {
				TodoObj todo = todoDAO.findById(id);
				return todo;
			}
			@Override
			public void save(TodoObj todo) {
				todoDAO.save(todo);
			}

			@Override
			public void update(int todoId , TodoObj todo) {
				TodoObj todoExist = todoDAO.findById(todoId);

				if (todoExist != null) {
					todoExist.setTitle(todo.getTitle());
					todoExist.setDescription(todo.getDescription());
					todoExist.setFecha(todo.getFecha());
					todoExist.setUser(todo.getUser());
					todoExist.setCompleted(todo.isCompleted());
					todoDAO.save(todoExist);
				}
			}

			@Override
			public void deleteById(int id) {
				todoDAO.deleteById(id);
		}

	}



