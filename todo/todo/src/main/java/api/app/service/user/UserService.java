package api.app.service.user;

import java.util.List;

import api.app.model.entity.user.User;

public interface UserService {
	public List<User> findAll();
	public User authenticate(String user,String password);
	public User findById(int id);

	public void save(User user);
	
	public void update(int userId, User user);

	public void deleteById(int id);
	public User findByUsuario(String user);
}
