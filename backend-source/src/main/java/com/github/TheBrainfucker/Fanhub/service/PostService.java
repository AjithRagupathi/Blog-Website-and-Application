package com.github.TheBrainfucker.Fanhub.service;

import java.util.Collection;
import java.util.Optional;

public interface PostService<T> extends UserService<T> {

    Collection<T> findAll();

    Optional<T> findById(Long id);

    T saveOrUpdate(T t);

    String deleteById(Long id);
}
