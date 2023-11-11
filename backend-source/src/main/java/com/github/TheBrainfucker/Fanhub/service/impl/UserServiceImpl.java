package com.github.TheBrainfucker.Fanhub.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import com.github.TheBrainfucker.Fanhub.exception.ResourceNotFoundException;
import com.github.TheBrainfucker.Fanhub.model.User;
import com.github.TheBrainfucker.Fanhub.repository.UserRepository;
import com.github.TheBrainfucker.Fanhub.service.UserService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService<User> {

    @Autowired
    private UserRepository userRepository;

    // @Override
    // public Collection<User> findAll() {
    // return userRepository.findAll();
    // }

    // @Override
    // public Optional<User> findById(Long id) {
    // return userRepository.findById(id);
    // }

    // @Override
    // public User saveOrUpdate(User user) {
    // return userRepository.saveAndFlush(user);
    // }

    @Override
    public String deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            userRepository.deleteById(id);
            jsonObject.put("message", "User deleted successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    // We are using the findById method to fetch our parent user and access the
    // collection inside within a Hibernate Session, which will be handled
    // automatically in the method's context by annotating it with @Transactional.
    @Transactional
    public void subscribe(Long fanid, Long creatorid) {
        User fan = userRepository.findById(fanid)
                .orElseThrow(() -> new ResourceNotFoundException("Fan id:" + fanid + ") not found!"));
        User creator = userRepository.findById(creatorid)
                .orElseThrow(() -> new ResourceNotFoundException("Creator id:" + creatorid + ") not found!"));
        fan.addFanSubscription(creator);
    }

    @Transactional
    public void unsubscribe(Long fanid, Long creatorid) {
        User fan = userRepository.findById(fanid)
                .orElseThrow(() -> new ResourceNotFoundException("Fan id:" + fanid + ") not found!"));
        User creator = userRepository.findById(creatorid)
                .orElseThrow(() -> new ResourceNotFoundException("Creator id:" + creatorid + ") not found!"));
        fan.removeFanSubscription(creator);
    }

    @Transactional
    public Set<JSONObject> getFans(Long creatorid) {
        User creator = userRepository.findById(creatorid)
                .orElseThrow(() -> new ResourceNotFoundException("User (id:" + creatorid + ") not found!"));
        Set<User> fans = creator.getFans();
        Set<JSONObject> newFans = new HashSet<>();
        for (User fan : fans) {
            if (fan.getRole().getName().equals("USER")) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", fan.getId());
                jsonObject.put("username", fan.getUsername());
                jsonObject.put("profilepic", fan.getProfilepic());
                newFans.add(jsonObject);
            }
        }
        return newFans;
    }

    public Set<JSONObject> getSubscriptions(Long fanid) {
        User fan = userRepository.findById(fanid)
                .orElseThrow(() -> new ResourceNotFoundException("User (id:" + fanid + ") not found!"));
        Set<User> subscriptions = fan.getSubscriptions();
        Set<JSONObject> newSubscriptions = new HashSet<>();
        for (User subscription : subscriptions) {
            if (subscription.getRole().getName().equals("USER")) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", subscription.getId());
                jsonObject.put("username", subscription.getUsername());
                jsonObject.put("profilepic", subscription.getProfilepic());
                newSubscriptions.add(jsonObject);
            }
        }
        return newSubscriptions;
    }

    public Set<JSONObject> getSuggestions(Set<Long> subscriptionIds) {
        Set<User> suggestions = userRepository.findByIdNotIn(
                subscriptionIds)
                .orElseThrow(() -> new ResourceNotFoundException("Suggestions not found!"));

        Set<JSONObject> newSuggestions = new HashSet<>();
        for (User suggestion : suggestions) {
            if (suggestion.getRole().getName().equals("USER")) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", suggestion.getId());
                jsonObject.put("username", suggestion.getUsername());
                jsonObject.put("profilepic", suggestion.getProfilepic());
                newSuggestions.add(jsonObject);
            }
        }
        return newSuggestions;
    }

    public Set<JSONObject> getSearchResults(String username) {
        Set<User> users = userRepository.searchByName(username);
        Set<JSONObject> searchResults = new HashSet<>();
        for (User user : users) {
            if (user.getRole().getName().equals("USER")) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", user.getId());
                jsonObject.put("username", user.getUsername());
                jsonObject.put("profilepic", user.getProfilepic());
                searchResults.add(jsonObject);
            }
        }
        return searchResults;
    }
}