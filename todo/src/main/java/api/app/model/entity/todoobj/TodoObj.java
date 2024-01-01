package api.app.model.entity.todoobj;

import api.app.model.entity.user.User;
import jakarta.persistence.*;

@Entity
public class TodoObj {
    public TodoObj() {
        // Constructor sin argumentos requerido por Hibernate
        this.completed = false;
    }

    public TodoObj(String title, String description) {
		super();
		this.title = title;
		this.description = description;
		this.completed = false;

	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String title;
    
    @Column
    private String description;
    @Column
    private String category;
    @Column
    private boolean completed;

    @ManyToOne
    private User user;  // Reference to the User

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
