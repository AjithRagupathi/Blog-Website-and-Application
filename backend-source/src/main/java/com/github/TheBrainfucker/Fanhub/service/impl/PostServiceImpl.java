package com.github.TheBrainfucker.Fanhub.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import com.github.TheBrainfucker.Fanhub.exception.ResourceNotFoundException;
import com.github.TheBrainfucker.Fanhub.model.Post;
import com.github.TheBrainfucker.Fanhub.model.User;
import com.github.TheBrainfucker.Fanhub.repository.PostRepository;
import com.github.TheBrainfucker.Fanhub.repository.UserRepository;
import com.github.TheBrainfucker.Fanhub.service.PostService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService<Post> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post saveOrUpdate(Post post) {
        return postRepository.saveAndFlush(post);
    }

    @Override
    public String deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            postRepository.deleteById(id);
            jsonObject.put("message", "Post deleted successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public Set<JSONObject> getFeed(User user) {
        // Set<User> subscriptions = user.getSubscriptions();
        // subscriptions.add(user);
        // Set<Long> ids =
        // subscriptions.stream().map(User::getId).collect(Collectors.toSet());
        Set<Long> ids = user.getSubscriptionIds();
        ids.add(user.getId());
        Set<Post> posts = postRepository.findTop100ByUser_IdInOrderByDateDesc(ids);
        Set<JSONObject> newPosts = new HashSet<>();
        for (Post post : posts) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", post.getId());
            jsonObject.put("caption", post.getCaption());
            jsonObject.put("content", post.getContent());
            jsonObject.put("date", post.getDate());
            jsonObject.put("love", post.getLove());
            JSONObject userObject = new JSONObject();
            userObject.put("id", post.getUser().getId());
            jsonObject.put("user", userObject);
            jsonObject.put("loversIds", post.getLoversIds());
            newPosts.add(jsonObject);
        }
        return newPosts;
    }

    public Set<JSONObject> getTimeline(User user) {
        List<Post> posts = postRepository.findByUserid(user.getId());
        Set<JSONObject> newPosts = new HashSet<>();
        for (Post post : posts) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", post.getId());
            jsonObject.put("caption", post.getCaption());
            jsonObject.put("content", post.getContent());
            jsonObject.put("date", post.getDate());
            jsonObject.put("love", post.getLove());
            JSONObject userObject = new JSONObject();
            userObject.put("id", post.getUser().getId());
            jsonObject.put("user", userObject);
            jsonObject.put("loversIds", post.getLoversIds());
            newPosts.add(jsonObject);
        }
        return newPosts;
    }

    @Transactional
    public void love(Long postid, Long userid) {
        Post post = postRepository.findById(postid)
                .orElseThrow(() -> new ResourceNotFoundException("Post id:" + postid + ") not found!"));
        User lover = userRepository.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("Lover id:" + userid + ") not found!"));
        post.addFanLove(lover);
    }

    @Transactional
    public void unlove(Long postid, Long userid) {
        Post post = postRepository.findById(postid)
                .orElseThrow(() -> new ResourceNotFoundException("Post id:" + postid + ") not found!"));
        User lover = userRepository.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("Lover id:" + userid + ") not found!"));
        post.removeFanLove(lover);
    }
}
