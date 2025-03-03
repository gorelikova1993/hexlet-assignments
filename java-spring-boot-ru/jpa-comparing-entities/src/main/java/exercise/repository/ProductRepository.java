package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exercise.model.Product;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Проверка существования товара по названию
    boolean existsByTitle(String title);

    // Поиск товара по названию (опционально)
    Optional<Product> findByTitle(String title);

    boolean existsByPrice(int price);

    Optional<Product> findByPrice(int price);
}
