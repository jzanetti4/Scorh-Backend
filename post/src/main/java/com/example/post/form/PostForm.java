package com.example.post.form;

import lombok.Data;

@Data
public class PostForm {
    private String parentId;
    private String username;
    private String userId;
    private String type;
    private String content;
}
