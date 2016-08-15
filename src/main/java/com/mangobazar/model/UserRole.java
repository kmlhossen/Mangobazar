
package com.mangobazar.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Represent category table in the database.
 */
@Entity
@Table(name = "Role")
public class UserRole {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Name", nullable = false, unique = true)
	private String name;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "Roles_Privileges", joinColumns = @JoinColumn(name = "RoleId", referencedColumnName = "Id"), inverseJoinColumns = @JoinColumn(name = "PrivilegeId", referencedColumnName = "Id"))
	private Set<Privilege> privileges;

	public Long getId() {
		return id;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public String getName() {
		return name;
	}
}
