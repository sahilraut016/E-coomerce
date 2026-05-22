package ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.Model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserId(int userId);
}
