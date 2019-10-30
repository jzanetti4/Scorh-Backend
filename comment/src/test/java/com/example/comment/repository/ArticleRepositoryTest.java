package com.example.comment.repository;

import cn.hutool.core.date.DateUtil;
import com.example.comment.CommentApplicationTests;
import com.example.comment.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import java.util.Date;
import java.util.List;


import static org.bouncycastle.asn1.x500.style.RFC4519Style.cn;
import static org.junit.jupiter.api.Assertions.*;

class ArticleRepositoryTest extends CommentApplicationTests {


    @Autowired
    private Snowflake snowflake;

    @Autowired
    private ArticleRepository articleRepository;
    @Test
    void findByTitleLike() {
        List<Article> articleList=articleRepository.findByContentLike("scorh");

    }


    @Test
    void save(){
        Article article=new Article(snowflake.nextId(),"1","1","scorh",new Date(),new Date(),1L,null);
        articleRepository.save(article);
    }

//
//    @Test
//    public void testUpdate() {
//        articleRepo.findById(1L).ifPresent(article -> {
//            article.setTitle(article.getTitle() + "更新之后的标题");
//            article.setUpdateTime(DateUtil.date());
//            articleRepo.save(article);
//            log.info("【article】= {}", JSONUtil.toJsonStr(article));
//        });
//    }

//    @Test
//    public void testThumbUp() {
//        articleRepo.findById(1L).ifPresent(article -> {
//            article.setThumbUp(article.getThumbUp() + 1);
//            article.setVisits(article.getVisits() + 1);
//            articleRepo.save(article);
//            log.info("【标题】= {}【点赞数】= {}【访客数】= {}", article.getTitle(), article.getThumbUp(), article.getVisits());
//        });
//    }


}