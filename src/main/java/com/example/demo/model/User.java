package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="Users")
public class User {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;
	@Column(unique=true)
	private String username;
	@Column
	private String password;
	@Column
	private LocalDate registerDate;
	@Column
	private String token;
	@Column(unique = true)
	private String email;
	@Column
	private boolean activated=false;
	@Column
	private boolean banned=false;
	@Column
	@Enumerated(EnumType.STRING)
	private UserRole role= UserRole.USER;
	@OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="user_id")
	List<Image> images;
	
	
	
	public User() {}
	
	
	public User(String username, String password, LocalDate registerDate, String token, String email) {
		super();
		this.username = username;
		this.password = password;
		this.registerDate = registerDate;
		this.token = token;
		this.email = email;
	}


	public User(long id, String username, String password, LocalDate registerDate, String token, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.registerDate = registerDate;
		this.token = token;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isActivated() {
		return activated;
	}


	public void setActivated(boolean activated) {
		this.activated = activated;
	}


	public UserRole getRole() {
		return role;
	}


	public void setRole(UserRole role) {
		this.role = role;
	}


	public boolean isBanned() {
		return banned;
	}


	public void setBanned(boolean banned) {
		this.banned = banned;
	}


	public List<Image> getImages() {
		return images;
	}


	public void setImages(List<Image> images) {
		this.images = images;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", registerDate=" + registerDate
				+ ", token=" + token + ", email=" + email + "]";
	}
	

}
