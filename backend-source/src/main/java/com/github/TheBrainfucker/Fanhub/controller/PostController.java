package com.github.TheBrainfucker.Fanhub.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;
import com.github.TheBrainfucker.Fanhub.exception.ResourceNotFoundException;
import com.github.TheBrainfucker.Fanhub.model.Post;
import com.github.TheBrainfucker.Fanhub.model.User;
import com.github.TheBrainfucker.Fanhub.repository.PostRepository;
import com.github.TheBrainfucker.Fanhub.repository.UserRepository;
import com.github.TheBrainfucker.Fanhub.service.FilesStorageService;
import com.github.TheBrainfucker.Fanhub.service.impl.PostServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @Autowired
    FilesStorageService storageService;

    // create a post
    @PostMapping("/")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        post.setDate(LocalDateTime.now());
        post.setLove(0L);
        try {
            return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get post by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post (id:" + id + ") not found!"));
        return ResponseEntity.ok(post);
    }

    // get all subscriptions' posts by userid rest api
    @GetMapping(path = "/feed/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getFeedByUserid(@PathVariable("userid") Long userid) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("Userid:" + userid + ") not found!"));
        Set<JSONObject> posts = postServiceImpl.getFeed(user);
        return new ResponseEntity<>(posts.toString(), HttpStatus.OK);
    }

    // get all posts of a creator rest api
    @GetMapping(path = "/timeline/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPostsByUsername(@PathVariable("username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username:" + username + ") not found!"));
        Set<JSONObject> posts = postServiceImpl.getTimeline(user);
        return new ResponseEntity<>(posts.toString(), HttpStatus.OK);
    }

    // update a post rest api
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post (id:" + id + ") not found!"));

        post.setCaption(postDetails.getCaption());
        post.setContent(postDetails.getContent());
        post.setLove(postDetails.getLove());

        Post updatePost = postRepository.save(post);
        return ResponseEntity.ok(updatePost);
    }

    // delete a post rest api
    @DeleteMapping("/{id}/{userid}")
    public ResponseEntity<Map<String, Boolean>> deletePost(@PathVariable Long id, @PathVariable Long userid) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post (id:" + id + ") not found!"));
        Map<String, Boolean> response = new HashMap<>();
        if (post.getUser().getId() != userid) {
            response.put("Not Permitted", Boolean.FALSE);
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
        postRepository.delete(post);

        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // delete a file rest api
    @DeleteMapping("/{filename:.+}")
    public ResponseEntity<String> deleteContent(@PathVariable String filename) {
        try {
            storageService.deleteFile(filename);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Not permitted");
        }
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    // love react a post rest api
    @PutMapping("/{id}/love/{userid}")
    public ResponseEntity<String> love(@PathVariable Long id, @PathVariable Long userid) {
        String message = "";
        try {
            Post post = postRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Post (id:" + id + ") not found!"));
            Set<Long> lovers = post.getLoversIds();
            if (!lovers.contains(userid)) {
                postServiceImpl.love(id, userid);
                message = "Reacted love.";
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } else {
                postServiceImpl.unlove(id, userid);
                message = "Removed love.";
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }
        } catch (Exception e) {
            message = "Could not love/unlove!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
