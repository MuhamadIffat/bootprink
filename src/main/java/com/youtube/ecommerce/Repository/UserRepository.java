package com.youtube.ecommerce.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.youtube.ecommerce.model.User;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByMobile(String mobile);
        Optional<User> findUserById(Long id);
        void deleteUserById(Long id);
        List<User> findByNameContaining(String name);
        
}
