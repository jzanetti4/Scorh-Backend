package com.example.post.util;

import cn.hutool.core.lang.Snowflake;

import com.example.post.form.PostForm;
import com.example.post.model.Article;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * provide method to covert the form to article class
 * @description: FormToModel
 * @author: Hangqi Yu
 * @date: Created in 2019-10-10 16:21
 * @version: V1.0
 * @modified: Hangqi Yu
 */
public class FormToModel {


    public Article covertToArticle(PostForm postForm) {
        Article article = new Article();
        BeanUtils.copyProperties(postForm, article);
        article.setPostId(UUID.randomUUID().toString());
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article.setChildrenCommentList(new ArrayList<Article>());
        article.setCount_comment(0);
        article.setCount_thumbUp(0);
        return article;
    }
}
