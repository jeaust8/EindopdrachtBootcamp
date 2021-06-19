package nl.aartj.GarageApp.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByProductId(Long productId);
    Optional<Product> findProductByTitle(String name);
    Optional<Product> deleteByProductId(Long productId);
}
