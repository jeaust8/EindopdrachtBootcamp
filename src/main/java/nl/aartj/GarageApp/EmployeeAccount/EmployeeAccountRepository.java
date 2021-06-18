package nl.aartj.GarageApp.EmployeeAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeAccountRepository extends JpaRepository<EmployeeAccount, Long> {
    Optional<EmployeeAccount> findById(Long accountId);
    Optional<EmployeeAccount> findAccountByEmail(String email);
}
