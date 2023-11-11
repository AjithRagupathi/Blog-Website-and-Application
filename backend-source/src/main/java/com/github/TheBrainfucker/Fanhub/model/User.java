package com.github.TheBrainfucker.Fanhub.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "posts", "fans", "subscriptions", "loves" }) // Infinite
                                                                                                            // Recursive
                                                                                                            // fetching
                                                                                                            // of data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "profilepic")
    private String profilepic;

    @Column(name = "coverpic")
    private String coverpic;

    @Column(name = "bio")
    private String bio;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "city")
    private String city;

    @ManyToOne
    @JoinColumn(name = "roleid")
    private Role role;

    @OneToMany(targetEntity = Post.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "fans", joinColumns = @JoinColumn(name = "creatorid"), inverseJoinColumns = @JoinColumn(name = "fanid"))
    private Set<User> fans = new HashSet<User>();

    @ManyToMany(targetEntity = User.class, mappedBy = "fans")
    private Set<User> subscriptions = new HashSet<User>();

    @ManyToMany(targetEntity = Post.class, mappedBy = "lovers")
    private Set<Post> loves = new HashSet<Post>();

    public User() {
    }

    public User(String email, String password, String username, String name) {
        super();
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getCoverpic() {
        return coverpic;
    }

    public void setCoverpic(String coverpic) {
        this.coverpic = coverpic;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Set<User> getFans() {
        return fans;
    }

    public Set<Long> getFanIds() {
        Set<Long> fanids = new HashSet<>();
        for (User fan : fans) {
            fanids.add(fan.id);
        }
        return fanids;
    }

    public void setFans(Set<User> fans) {
        this.fans = fans;
    }

    public Set<User> getSubscriptions() {
        return subscriptions;
    }

    public Set<Long> getSubscriptionIds() {
        Set<Long> subscriptionids = new HashSet<>();
        for (User subscription : subscriptions) {
            subscriptionids.add(subscription.id);
        }
        return subscriptionids;
    }

    public void setSubscriptions(Set<User> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Set<Post> getLoves() {
        return loves;
    }

    public Set<Long> getLovesIds() {
        Set<Long> lovesids = new HashSet<>();
        for (Post love : loves) {
            lovesids.add(love.getId());
        }
        return lovesids;
    }

    public void setLoves(Set<Post> loves) {
        this.loves = loves;
    }

    public void addFanSubscription(User creator) {
        subscriptions.add(creator);
        creator.getFans().add(this);
    }

    public void removeFanSubscription(User creator) {
        subscriptions.remove(creator);
        creator.getFans().remove(this);
    }

    @Async
    public void addDemoUser(User tester, String role) {
        System.out.println();
        System.out.println(Thread.currentThread().getName());
        System.out.println();
        if (role == "USER") {
            tester.setEmail("tester0@test.com");
            tester.setUsername("tester0");
            tester.setName("Tester 0");
            tester.setPassword(new BCryptPasswordEncoder().encode("tester0pass"));
        } else if (role == "ADMIN") {
            tester.setEmail("admin0@admin.com");
            tester.setUsername("admin0");
            tester.setName("Admin 0");
            tester.setPassword(new BCryptPasswordEncoder().encode("admin0pass"));
        }
    }

}
