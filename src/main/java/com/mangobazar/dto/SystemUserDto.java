package com.mangobazar.dto;

import java.util.HashSet;
import java.util.Set;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("UserDto")
public class SystemUserDto {

    @ApiModelProperty(value = "User email address", required = true)
    private String email;

    @ApiModelProperty(value = "User password address", required = true)
    private String password;

    @ApiModelProperty(value = "User first name", required = true)
    private String firstName;

    @ApiModelProperty(value = "User last name", required = true)
    private String lastName;

    @ApiModelProperty(value = "User address", required = true)
    private String address;

    @ApiModelProperty(value = "User contact no", required = true)
    private String contactNo;
    
    @ApiModelProperty(value = "Role:1-ROLE_ADMIN,"+
    "2-ROLE_SUPPORT_USER,"+
     "ROLE_CUSTOMER(3)", required = true)
   private  Set<Long> roleList = new HashSet<Long>();


   

	public Set<Long> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<Long> roleList) {
		this.roleList = roleList;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

}
