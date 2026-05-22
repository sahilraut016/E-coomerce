package ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.Model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}