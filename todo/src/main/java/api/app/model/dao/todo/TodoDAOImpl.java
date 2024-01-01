package api.app.model.dao.todo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.app.model.entity.todoobj.TodoObj;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class TodoDAOImpl implements TodoDAO{
	
	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public List<TodoObj> findAll() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<TodoObj> getQuery = currentSession.createQuery("from TodoObj", TodoObj.class);
		List<TodoObj> usuarios = getQuery.getResultList();
		
		return usuarios;
	}

	@Transactional
	public TodoObj findById(int id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		TodoObj todo = currentSession.get(TodoObj.class, id);
		
		return todo;
	}
	@Transactional
	public TodoObj findByUser(String name) {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<TodoObj> getQuery = currentSession.createQuery("from TodoObj where todo =: todo", TodoObj.class);
		getQuery.setParameter("todo", name);
		TodoObj todo = getQuery.uniqueResult();

		return todo;
	}
	@Transactional
	public void save(TodoObj todo) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(todo);
	}


	@Transactional
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<?> deleteQuery = currentSession.createQuery("delete from TodoObj where id=: idTodo", TodoObj.class);
		deleteQuery.setParameter("idTodo", id);
		deleteQuery.executeUpdate();
		
	}
}
