package ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.Model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(int userId);
}
