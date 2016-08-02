package com.mangobazar.model;

import com.mangobazar.util.UserRole;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="User")
public class SystemUser {

    public SystemUser(){
        m_Active = true;
        m_Roles.add(UserRole.CUSTOMER);
    }

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long m_Id;

    @Column(name = "Email", nullable = false, unique = true)
    private String m_Email;

    // stores the password hash
    @Column(name = "Password", nullable = false)
    private String m_Password;

    @Column(name = "FirsName")
    private String m_FirstName;

    @Column(name = "LastName")
    private String m_LastName;

    @Column(name = "Address")
    private String m_Address;

    @Column(name = "ContactNo")
    private String m_ContactNo;

    @Column(name = "Active")
    private boolean m_Active;


    // Many to Many relation between user and role.
    @ElementCollection(targetClass = UserRole.class)
    @CollectionTable(name = "Role", joinColumns = @JoinColumn(name = "UserId"))
    @Column(name = "RoleName", nullable = false)
    private Set<UserRole> m_Roles = new HashSet<>();


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
        return m_Roles;
    }

    public Long getId() {
        return m_Id;
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
        return m_Email;
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
        return m_Password;
    }

    public String getFirstName() {
        return m_FirstName;
    }

    public String getLastName() {
        return m_LastName;
    }

    public String getAddress() {
        return m_Address;
    }
    
    public String getContactNo() {
        return m_ContactNo;
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
        return m_Active;
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
        m_Password = passwordHash;
    }

    public void setEmail(String email) {
        m_Email = email;
    }

    public void setFirstName(String firstName) {
        m_FirstName = firstName;
    }

    public void setLastName(String lastName) {
        m_LastName = lastName;
    }

    public void setAddress(String address) {
        m_Address = address;
    }

    public void setContactNo(String contactNo) {
        m_ContactNo = contactNo;
    }

    public void setRoles(Set<UserRole> roles) {
        m_Roles = roles;
    }

    public void setContactNo(boolean active) {
        m_Active = active;
    }
}
