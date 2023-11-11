package com.github.blogsite.blog.service;

public interface UserService<T> {

    String deleteById(Long id);

    // void subscribe(Long fanid, Long creatorid);

    // void unsubscribe(Long fanid, Long creatorid);

    // Set<T> getFans(Long creatorid);

    // Set<T> getFollowing(Long fanid);
}