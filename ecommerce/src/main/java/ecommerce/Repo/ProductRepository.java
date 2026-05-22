package ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}