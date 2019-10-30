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
 * <p>
 * 文章实体类
 * </p>
 *
 * @package: com.xkcoding.mongodb.model
 * @description: 文章实体类
 * @author: yangkai.shen
 * @date: Created in 2018-12-28 16:21
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    /**
     * 文章id
     */
    @Id
    private String postId;

    @Nullable
    private String parentId;

    private String userId;

    private String username;
    /**
     * 文章类型
     */
    private String type;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 点赞数量
     */
    private int count_thumbUp;
    private int count_comment;

    /**
     * 子评论
     */
    private List<Article> childrenCommentList;

}
