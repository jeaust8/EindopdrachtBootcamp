package nl.aartj.GarageApp.customer;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class CustomerAccountServiceTest {

    @Autowired
    private CustomerAccountRepository underTest;

    @AfterEach
    void deleteAll(){underTest.deleteAll();}

    String email = "angela.merkel@gmail.de";
    CustomerAccount customerAccount = new CustomerAccount(
            "Angela",
            "Merkel",
            "Brandenburgerweg 71",
            "1155KT",
            "Nieuwe Schans",
            "06-55886644", email,
            "$2y$10$ScKk0yZEtJNpDIJHbxIEa.ZW8aZfh/xY.kxIHs7aQ2AlqY6SCw30q", CUSTOMER.name()
    );

    underTest.save(account);

    Optional<CustomerAccount> exists = underTest.findCustomerAccountByEmail(email);

    assertThat(exists).isNotEmpty();

}
