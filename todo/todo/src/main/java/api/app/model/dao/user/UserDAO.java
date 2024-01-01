package api.app.model.dao.user;

import java.util.List;

import api.app.model.entity.user.User;

public interface UserDAO {
	public List<User> findAll();
	
	public User authenticate(String user,String password);
	
	public User findById(int id);
	
	public void save(User user);

	public void deleteById(int id);

	public User findByUser(String username);
}
