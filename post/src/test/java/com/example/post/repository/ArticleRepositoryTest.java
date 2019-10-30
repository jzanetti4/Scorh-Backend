package com.example.post.repository;

import cn.hutool.core.lang.Snowflake;
import com.example.post.PostApplicationTests;
import com.example.post.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleRepositoryTest extends PostApplicationTests {
    @Autowired
    ArticleRepository articleRepository;




    @Test
    void findByContentLike() {
      List<Article> articles= articleRepository.findByContentLike("good");
      System.out.println(articles);
    }


    @Test
    void save() {
        Article article=new Article( UUID.randomUUID().toString(),"1","123","yhq","post","good morning",new Date(),new Date(),0,0,new ArrayList<>());
        articleRepository.save(article);
    }

    @Test
    void findByType() {
        List<Article> articles=articleRepository.findByType("post");
        System.out.println(articles);
    }

    @Test
    void findByUsername() {
        List<Article> articles=articleRepository.findByUsername("yhq");
        System.out.println(articles);
    }

//    @Test
//    void findByPostId() {
//        Article article=articleRepository.findByPostId(1188838698695921664);
//        System.out.println(article);
//    }


    @Test
    void findAll(){
        List<Article> articles=articleRepository.findAll();
        System.out.println(articles);
    }
}