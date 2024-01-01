package api.app.model.dao.user;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import api.app.model.entity.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	@Transactional
	public List<User> findAll() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<User> getQuery = currentSession.createQuery("from User", User.class);
		List<User> usuarios = getQuery.getResultList();
		
		return usuarios;
	}

	@Override
	@Transactional
	public User findById(int id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		User user = currentSession.get(User.class, id);
		
		return user;
	}
	@Override
	@Transactional
	public User findByUser(String username) {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<User> getQuery = currentSession.createQuery("from User where user =: user", User.class);
		getQuery.setParameter("user", username);
		User user = getQuery.uniqueResult();

		return user;
	}
	@Override
	@Transactional
	public void save(User user) {
		Session currentSession = entityManager.unwrap(Session.class);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());

		user.setPassword(hashedPassword);

		currentSession.persist(user);
	}


	@Override
	@Transactional
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);

		@SuppressWarnings("deprecation")
		Query<?> deleteQuery = currentSession.createQuery("delete from User where id=:idUser");
		deleteQuery.setParameter("idUser", id);
		deleteQuery.executeUpdate();
		
	}

	@Override
	@Transactional
	public User authenticate(String username, String password) {
	    Session currentSession = entityManager.unwrap(Session.class);
	    Query<User> getQuery = currentSession.createQuery("FROM User WHERE username = :username", User.class);
	    getQuery.setParameter("username", username);
	    User user = getQuery.uniqueResult();

	    if (user != null && checkPassword(password, user.getPassword())) {
	        return user;
	    } else {
	        return null;
	    }
	}

	private boolean checkPassword(String password, String passwordDB) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    boolean passwordMatch = passwordEncoder.matches(password, passwordDB);
	    return passwordMatch;
	}
}
