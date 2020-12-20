package com.app.blogapplication.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;

@Entity
public class PostTag {
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private PostTagIdentity id;

    @ManyToOne
    @MapsId("postId")
    private Post post;

    @ManyToOne
    @MapsId("tag")
    private Tag tag;

    private Calendar createdAt = Calendar.getInstance();
    private Calendar updatedAt;

    public PostTagIdentity getId() {
        return id;
    }

    public void setId(PostTagIdentity id) {
        this.id = id;
    }

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

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "PostTag{" +
                "id=" + id +
                ", post=" + post +
                ", tag=" + tag +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
