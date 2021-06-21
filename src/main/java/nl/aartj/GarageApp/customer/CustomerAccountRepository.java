package nl.aartj.GarageApp.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerAccountRepository
        extends JpaRepository<CustomerAccount, Long> {

    @Query("SELECT c FROM CustomerAccount c WHERE c.name = ?1")
    Optional<CustomerAccount> findCustomerByName(String name);
    Optional<CustomerAccount> findCustomerByEmail(String email);
    Optional<CustomerAccount> findCustomerAccountByEmail(String email);
}
