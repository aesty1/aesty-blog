package ru.denis.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PostEvent {
    private Long postId;
    private String title;
    private Long authorId;
    private String type;

    public PostEvent() {
    }

    public PostEvent(Long postId, String title, Long authorId, String type) {
        this.postId = postId;
        this.title = title;
        this.authorId = authorId;
        this.type = type;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
