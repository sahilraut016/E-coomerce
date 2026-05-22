package ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.Model.CartItem;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCartId(int cartId);
}
