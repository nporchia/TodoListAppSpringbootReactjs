package api.app.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.app.model.dao.user.UserDAO;
import api.app.model.entity.user.User;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public List<User> findAll() {
		List<User> users = userDAO.findAll();
		return users;
	}

	@Override
	public User findById(int id) {
		User user = userDAO.findById(id);
		return user;
	}
	@Override
	public User findByUsuario(String username) {
		User user = userDAO.findByUser(username);
		return user;
	}
	@Override
	public void save(User user) {
		userDAO.save(user);
	}

	@Override
	public void update(int userId , User user) {
		User userExist = userDAO.findById(userId);

		if (userExist != null) {
			userExist.setUsername(user.getUsername());
			userExist.setPassword(user.getPassword());
			userExist.setTodos(user.getTodos());
			userDAO.save(userExist);
		}
	}

	@Override
	public void deleteById(int id) {
		userDAO.deleteById(id);
}

	@Override
	public User authenticate(String username, String password) {
		User user = userDAO.authenticate(username, password);
		return user;
	}
}
