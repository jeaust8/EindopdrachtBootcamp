package nl.aartj.GarageApp.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static nl.aartj.GarageApp.security.AppUserPermission.*;

public enum AppUserRole {
    MANAGER(Sets.newHashSet(CUSTOMER_READ, CUSTOMER_WRITE, CAR_READ, CAR_WRITE, EMPLOYEE_READ, EMPLOYEE_WRITE)),
    MECHANIC(Sets.newHashSet(CUSTOMER_READ, CAR_READ, CAR_WRITE)),
    ADMIN(Sets.newHashSet(CUSTOMER_READ, CUSTOMER_WRITE, CAR_READ, CAR_WRITE, PRODUCT_READ, PRODUCT_WRITE)),
    BACKOFFICE(Sets.newHashSet(CUSTOMER_READ, CUSTOMER_WRITE, CAR_READ, CAR_WRITE, PRODUCT_READ, PRODUCT_WRITE)),
    CUSTOMER(Sets.newHashSet(CAR_READ, BILL_READ));


    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) { this.permissions = permissions;}

    public Set<AppUserPermission> getPermissions(){ return permissions;}

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.permissions));
        return permissions;
    }
}
