package com.mangobazar.model;

import com.mangobazar.util.UserRole;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="User")
public class SystemUser {

    public SystemUser(){
        isActive = true;
        // default role
        roles.add(UserRole.CUSTOMER);
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

    @Column(name = "FirsName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Address")
    private String address;

    @Column(name = "ContactNo")
    private String contactNo;

    @Column(name = "Active")
    private boolean isActive;


    // Many to Many relation between user and role.
    @ElementCollection(targetClass = UserRole.class)
    @CollectionTable(name = "Role", joinColumns = @JoinColumn(name = "UserId"))
    @Column(name = "RoleName", nullable = false)
    private Set<UserRole> roles = new HashSet<>();


    /**
     * Returns list of roles associated with the user.
     * <p>
     * NOTES/ASSUMPTIONS: <BR>
     * None
     *
     * @return list of roles associated with the user.
     */
    @Enumerated(EnumType.STRING)
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

    /**
     * Sets user's encrypted password.
     * <p>
     * NOTES/ASSUMPTIONS: <BR>
     * None
     *
     * @param passwordHash user's encrypted password.
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

    public void setContactNo(boolean active) {
        this.isActive = active;
    }
}
