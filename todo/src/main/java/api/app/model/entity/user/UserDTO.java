package api.app.model.entity.user;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import api.app.model.entity.todoobj.TodoObj;

public class UserDTO {
	    public UserDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserDTO(Integer id, String username, String password) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;

		}

	    private Integer id;
	    private String username;
		@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	    private String password; 
	    private ArrayList<TodoObj> todos;
	    
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public ArrayList<TodoObj> getTodos() {
			return todos;
		}
		public void setTodos(ArrayList<TodoObj> todos) {
			this.todos = todos;
		}
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "UserDTO [id=" + id + ", username=" + username + ", password=" + password + ", todos=" + todos + "]";
		}
	
	}


