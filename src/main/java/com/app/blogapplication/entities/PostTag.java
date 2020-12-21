package com.app.blogapplication.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
public class PostTag {
    @EmbeddedId
    private PostTagIdentity postTagIdentity = new PostTagIdentity();

    @ManyToOne
    @MapsId("postId")
    private Post post;

    @ManyToOne
    @MapsId("tagId")
    private Tag tag;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "PostTag{" +
                "postTagIdentity=" + postTagIdentity +
                ", post=" + post +
                ", tag=" + tag +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public PostTagIdentity getPostTagIdentity() {
        return postTagIdentity;
    }

    public void setPostTagIdentity(PostTagIdentity postTagIdentity) {
        this.postTagIdentity = postTagIdentity;
    }
}
