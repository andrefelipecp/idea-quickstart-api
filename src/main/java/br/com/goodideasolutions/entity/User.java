package br.com.goodideasolutions.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.hateoas.ResourceSupport;

@Entity
public class User extends ResourceSupport implements Serializable{

	private static final long serialVersionUID = -8100355107249565015L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUser;
	
	private String username;
	
	private String password;
	
	@ManyToMany
    @JoinTable( name = "users_roles",
        joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "idUser"), 
        inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"))
	private List<Role> roles;
	
	public User() {
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
}
