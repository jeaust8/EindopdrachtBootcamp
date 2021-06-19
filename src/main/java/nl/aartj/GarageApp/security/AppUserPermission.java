package nl.aartj.GarageApp.security;

public enum AppUserPermission {
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer: write"),
    EMPLOYEE_READ("employee: read"),
    EMPLOYEE_WRITE("employee: write"),
    CAR_READ("car:read"),
    CAR_WRITE("car:write"),
    PRODUCT_READ("product: read"),
    PRODUCT_WRITE("product: write");

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }
    public String getPermission(){
        return permission;
    }
}
