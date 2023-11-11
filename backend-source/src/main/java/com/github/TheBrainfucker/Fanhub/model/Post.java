package com.github.TheBrainfucker.Fanhub.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "caption")
    private String caption;

    @Column(name = "content")
    private String content;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "love", nullable = false)
    private Long love;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToMany
    @JoinTable(name = "loves", joinColumns = @JoinColumn(name = "postid"), inverseJoinColumns = @JoinColumn(name = "userid"))
    private Set<User> lovers = new HashSet<User>();

    public Post() {
    }

    public Post(String caption, String content, LocalDateTime date, User user) {
        super();
        this.caption = caption;
        this.content = content;
        this.date = date;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = LocalDateTime.parse(date);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLove(Long love) {
        this.love = love;
    }

    public Long getLove() {
        return love;
    }

    public Set<User> getLovers() {
        return lovers;
    }

    public Set<Long> getLoversIds() {
        Set<Long> loversids = new HashSet<>();
        for (User lover : lovers) {
            loversids.add(lover.getId());
        }
        return loversids;
    }

    public void setLovers(Set<User> lovers) {
        this.lovers = lovers;
    }

    public void addFanLove(User lover) {
        lovers.add(lover);
        lover.getLoves().add(this);
        this.love += 1;
    }

    public void removeFanLove(User lover) {
        lovers.remove(lover);
        lover.getLoves().remove(this);
        this.love -= 1;
    }

}
