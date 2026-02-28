package ru.denis.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CommentEvent {
    private Long commentId;
    private Long postId;

    public CommentEvent() {
    }

    public CommentEvent(Long commentId, Long postId) {
        this.commentId = commentId;
        this.postId = postId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
