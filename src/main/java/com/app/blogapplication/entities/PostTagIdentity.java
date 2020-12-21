package com.app.blogapplication.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostTagIdentity implements Serializable {
    @Column(name="post_id")
    private int postId;
    @Column(name="tag_id")
    private int tagId;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tag) {
        this.tagId = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostTagIdentity that = (PostTagIdentity) o;
        return postId == that.postId && tagId == that.tagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, tagId);
    }
}
