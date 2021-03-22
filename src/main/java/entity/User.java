package entity;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

	@Id
	private String id;

	@Column
	private String username;

	@Column
	private String name;

	@Column
	private String mail;

	@Column
	private int varsta;

	@Column
	private String password;

	@Column
	private boolean isAdmin;


	public User(String id, String username, String name, String mail, int varsta, String password, boolean isAdmin) {
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.varsta = varsta;
		this.password = password;
		this.username = username;
		this.isAdmin = isAdmin;
	}

	public User() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getVarsta() {
		return varsta;
	}

	public void setVarsta(int varsta) {
		this.varsta = varsta;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

}
