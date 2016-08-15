package com.mangobazar.util;

/*
 * Defines the available role in the system.
 */
public enum RoleType {
    ROLE_ADMIN(1),
    ROLE_SUPPORT_USER(2),
    ROLE_CUSTOMER(3);
    
    private int value;    

    private RoleType(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
}
