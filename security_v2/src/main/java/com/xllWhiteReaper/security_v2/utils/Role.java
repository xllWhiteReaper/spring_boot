package com.xllWhiteReaper.security_v2.utils;

import java.util.Arrays;
import java.util.List;

public enum Role {
    CUSTOMER(Arrays.asList(Permission.READ_ALL_PRODUCTS)),
    ADMINISTRATOR(Arrays.asList(Permission.READ_ALL_PRODUCTS, Permission.SAVE_ONE_PRODUCT));

    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
