package by.epam.shop.model;

public enum Permission {
    PRODUCTS_READ("products:read"),
    CATEGORIES_READ("categories:read"),
    CATEGORIES_WRITE("categories:write"),
    PRODUCTS_WRITE("products:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
