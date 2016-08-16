package com.mangobazar.model;

import java.util.Date;
import java.util.HashSet;
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



@Entity
@Table(name = "User")
public class SystemUser {

	public SystemUser() {
		isActive = true;
		roles= new HashSet<UserRole>();
	}

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Email", nullable = false, unique = true)
	private String email;

	// stores the password hash
	@Column(name = "Password", nullable = false)
	private String password;

	@Column(name = "FirsName", nullable = false)
	private String firstName;

	@Column(name = "LastName", nullable = false)
	private String lastName;

	@Column(name = "Address", nullable = false)
	private String address;

	@Column(name = "ContactNo", nullable = false)
	private String contactNo;

	@Column(name = "Active", nullable = false)
	private boolean isActive;

	@Column(name = "LastLogOut")
	private Date lastLogOut;

	// Many to Many relation between user and role.
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "Users_Roles", joinColumns = @JoinColumn(name = "UserId", referencedColumnName = "Id"), inverseJoinColumns = @JoinColumn(name = "RoleId", referencedColumnName = "Id"))
	private Set<UserRole> roles;

	/**
	 * Returns list of roles associated with the user.
	 * <p>
	 * NOTES/ASSUMPTIONS: <BR>
	 * None
	 *
	 * @return list of roles associated with the user.
	 */
	public Set<UserRole> getRoles() {
		return roles;
	}

	public Long getId() {
		return id;
	}

	/**
	 * Returns user email address, used as username.
	 * <p>
	 * NOTES/ASSUMPTIONS: <BR>
	 * None
	 *
	 * @return user's email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns user encrypted password.
	 * <p>
	 * NOTES/ASSUMPTIONS: <BR>
	 * None
	 *
	 * @return user's encrypted password.
	 */
	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getContactNo() {
		return contactNo;
	}

	/**
	 * Returns user active or not.
	 * <p>
	 * NOTES/ASSUMPTIONS: <BR>
	 * None
	 *
	 * @return true if user is active else false.
	 */
	public boolean isActive() {
		return isActive;
	}

	public Date getLastLogOut() {
		return lastLogOut;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets user's encrypted password.
	 * <p>
	 * NOTES/ASSUMPTIONS: <BR>
	 * None
	 *
	 * @param passwordHash
	 *            user's encrypted password.
	 */
	public void setPassword(String passwordHash) {
		password = passwordHash;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	public void setActive(boolean active) {
		this.isActive = active;
	}

	public void setLastLogOut(Date lastLogOut) {
		this.lastLogOut = lastLogOut;
	}
}
