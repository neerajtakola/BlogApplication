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
    private int tag;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostTagIdentity that = (PostTagIdentity) o;
        return postId == that.postId && tag == that.tag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, tag);
    }
}
