package com.example.post.form;

import lombok.Data;


/**
 * this class map the send post request form to java object
 * Created by Hangqi Yu
 * 2019-10-10 16:57
 */
@Data
public class PostForm {
    private String parentId;
    private String username;
    private String userId;
    private String type;
    private String content;
}
