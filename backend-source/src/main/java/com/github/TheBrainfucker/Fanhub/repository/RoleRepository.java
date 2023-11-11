package com.github.TheBrainfucker.Fanhub.repository;

import com.github.TheBrainfucker.Fanhub.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}