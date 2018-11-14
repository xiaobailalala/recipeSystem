package com.smxy.recipe.realm;

public enum  LoginType {
    /*loginType*/
    MERCHANT("Merchant"),  ADMIN("Admin"), COMMON("Common");

    private String type;

    private LoginType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
}