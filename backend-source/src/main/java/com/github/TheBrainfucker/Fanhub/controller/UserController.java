package com.github.TheBrainfucker.Fanhub.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.TheBrainfucker.Fanhub.exception.ResourceNotFoundException;
import com.github.TheBrainfucker.Fanhub.model.User;
import com.github.TheBrainfucker.Fanhub.repository.UserRepository;
import com.github.TheBrainfucker.Fanhub.service.impl.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    // get all users
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    // create user rest api
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get user by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<String> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User (id:" + id + ") not found!"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", user.getId());
        jsonObject.put("username", user.getUsername());
        jsonObject.put("name", user.getName());
        jsonObject.put("profilepic", user.getProfilepic());
        jsonObject.put("coverpic", user.getCoverpic());
        jsonObject.put("bio", user.getBio());
        jsonObject.put("relationship", user.getRelationship());
        jsonObject.put("city", user.getCity());
        return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
    }

    // get any user profile by username
    @GetMapping("/profile")
    public ResponseEntity<String> getUserByUsername(@RequestParam("username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(username + " not found"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", user.getId());
        jsonObject.put("username", user.getUsername());
        jsonObject.put("name", user.getName());
        jsonObject.put("profilepic", user.getProfilepic());
        jsonObject.put("coverpic", user.getCoverpic());
        jsonObject.put("bio", user.getBio());
        jsonObject.put("relationship", user.getRelationship());
        jsonObject.put("city", user.getCity());
        return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
    }

    // update user rest api
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User (id:" + id + ") not found!"));

        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        user.setName(userDetails.getName());

        User updateUser = userRepository.save(user);
        return ResponseEntity.ok(updateUser);
    }

    // @PutMapping("/{username}")
    // public ResponseEntity<User> updateUser(@PathVariable("username") String
    // username,
    // @RequestBody User userDetails) {
    // User user = userRepository.findById(userDetails.getId())
    // .orElseThrow(() -> new ResourceNotFoundException(username + " not found"));

    // user.setEmail(userDetails.getEmail());
    // user.setUsername(userDetails.getUsername());
    // user.setName(userDetails.getName());

    // return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    // }

    // delete user rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User (id:" + id + ") not found!"));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // @DeleteMapping("/{username}")
    // public ResponseEntity<HttpStatus> deleteUser(@PathVariable("username") String
    // username) {
    // userRepository.deleteByUsername(username);

    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }

    @DeleteMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        userRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/fans/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getFans(@PathVariable Long id) {
        Set<JSONObject> fans = userServiceImpl.getFans(id);
        return new ResponseEntity<>(fans.toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/subscriptions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSubscriptions(@PathVariable Long id) {
        Set<JSONObject> subscriptions = userServiceImpl.getSubscriptions(id);
        return new ResponseEntity<>(subscriptions.toString(), HttpStatus.OK);
    }

    // @GetMapping("/fanids/{id}")
    // public ResponseEntity<Set<Long>> getFanids(@PathVariable Long id) {
    // User user = userRepository.findById(id)
    // .orElseThrow(() -> new ResourceNotFoundException("User (id:" + id + ") not
    // found!"));
    // return new ResponseEntity<>(user.getFanIds(), HttpStatus.OK);
    // }

    // @GetMapping("/subscriptionids/{id}")
    // public ResponseEntity<List<String>> getSubscriptionids(@PathVariable Long id)
    // {
    // User user = userRepository.findById(id)
    // .orElseThrow(() -> new ResourceNotFoundException("User (id:" + id + ") not
    // found!"));
    // System.out.println(user.getSubscriptionIds());
    // return new ResponseEntity<>(user.getSubscriptionIds(), HttpStatus.OK);
    // }

    @PutMapping("/{creatorid}/subscribe/{fanid}")
    public ResponseEntity<String> subscribe(@PathVariable Long creatorid, @PathVariable Long fanid) {
        userServiceImpl.subscribe(fanid, creatorid);
        return ResponseEntity.ok("Subscribed");
    }

    @PutMapping("/{creatorid}/unsubscribe/{fanid}")
    public ResponseEntity<String> unsubscribe(@PathVariable Long creatorid, @PathVariable Long fanid) {
        userServiceImpl.unsubscribe(fanid, creatorid);
        return ResponseEntity.ok("Unsubscribed");
    }

    @GetMapping(path = "/suggestions/{fanid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSuggestions(@PathVariable Long fanid) {
        User user = userRepository.findById(fanid)
                .orElseThrow(() -> new ResourceNotFoundException("User (id:" + fanid + ") not found!"));
        Set<Long> subscriptionids = user.getSubscriptionIds();
        subscriptionids.add(user.getId());
        Set<JSONObject> suggestions = userServiceImpl.getSuggestions(subscriptionids);
        return ResponseEntity.ok(suggestions.toString());
    }

    @PostMapping("/search/{username}")
    public ResponseEntity<Object> searchUser(@PathVariable String username) {
        if (username.length() < 2) {
            return null;
        }
        Set<JSONObject> searchResults = userServiceImpl.getSearchResults(username);
        return ResponseEntity.ok(searchResults.toString());
    }

}
