package com.github.TheBrainfucker.Fanhub.repository;

import java.util.List;
import java.util.Set;

import com.github.TheBrainfucker.Fanhub.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.user.id = ?1")
    List<Post> findByUserid(Long userid);

    Set<Post> findTop100ByUser_IdInOrderByDateDesc(Set<Long> userid);

}
