package ru.denis.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AuthorEvent {
    private Long authorId;
    private String username;
    private String email;
    private String type;

    public AuthorEvent() {
    }

    public AuthorEvent(Long authorId, String username, String email, String type) {
        this.authorId = authorId;
        this.username = username;
        this.email = email;
        this.type = type;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
