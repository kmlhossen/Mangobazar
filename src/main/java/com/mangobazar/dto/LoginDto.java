package com.mangobazar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("LoginDto")
public class LoginDto {
    @ApiModelProperty(value = "User email address", required = true)
    private String email;
    @ApiModelProperty(value = "User password", required = true)
    private String password;


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
}
