package br.com.goodideasolutions.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	
	@Id
	private Long id;
	private String authority;
	
	@ManyToMany
    @JoinTable( name = "users_roles",
        joinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "id_user", referencedColumnName = "idUser"))
    private List<User> users;
	
	@ManyToMany
    @JoinTable( name = "privileges_roles",
        joinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "id_privilege", referencedColumnName = "id"))
	private List<Privilege> privileges;
	
	public Role() {
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	
}
