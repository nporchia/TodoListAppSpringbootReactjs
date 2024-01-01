package api.app.model.entity.user;

import api.app.model.entity.todoobj.TodoObj;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//Import statements...

@Entity
public class User {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;

 @Column(nullable = false, unique = true)
 private String username;

 @Column(nullable = false)
 private String password;

 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 private List<TodoObj> todos;

 // Constructors, getter, and setter methods...

 public User() {
     // Default constructor
 }

 public User(Integer id, String username, String password) {
     this.id = id;
     this.username = username;
     this.password = password;

 }

 // Getter and setter methods...

 public Integer getId() {
     return id;
 }

 public void setId(Integer id) {
     this.id = id;
 }

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

 public List<TodoObj> getTodos() {
     return todos;
 }

 public void setTodos(List<TodoObj> todos) {
     this.todos = todos;
 }
}
