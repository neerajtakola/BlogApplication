package com.app.blogapplication.entities;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class PostTag {
    @EmbeddedId
    private PostTagIdentity id;

    @ManyToOne
    @MapsId("postId")
    private Post post;

    @ManyToOne
    @MapsId("tagId")
    private Tag tag;

    private Calendar createdAt = Calendar.getInstance();
    private Calendar updatedAt;

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
}
