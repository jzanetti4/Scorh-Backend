package com.example.post.model;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * class  mapped to the post in mongoDB
 * @description: Post entity
 * @author: Hangqi Yu
 * @date: Created in 2019-10-10 16:21
 * @version: V1.0
 * @modified: Hangqi Yu
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    /**
     * post id
     */
    @Id
    private String postId;

    @Nullable
    private String parentId;

    private String userId;

    private String username;
    /**
     * post type
     */
    private String type;
    /**
     * post content
     */
    private String content;

    private Date createTime;

    private Date updateTime;

    private int count_thumbUp;
    private int count_comment;

    /**
     * children comment
     */
    private List<Article> childrenCommentList;

}
