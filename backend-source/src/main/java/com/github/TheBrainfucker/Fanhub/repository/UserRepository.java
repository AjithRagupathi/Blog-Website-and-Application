package com.github.TheBrainfucker.Fanhub.repository;

import java.util.Optional;
import java.util.Set;

import com.github.TheBrainfucker.Fanhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    void deleteByUsername(String username);

    Optional<Set<User>> findByIdNotIn(Set<Long> subscriptionIds);

    Set<User> findByUsernameContains(String username);

    @Query("SELECT u FROM User u WHERE lower(u.username) like %:name% OR lower(u.name) like %:name%")
    Set<User> searchByName(@Param("name") String name);
}
