package nl.aartj.GarageApp.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeAccountRepository extends JpaRepository<EmployeeAccount, Long> {
    Optional<EmployeeAccount> findById(Long employeeId);
    Optional<EmployeeAccount> findEmployeeByEmail(String email);
}
