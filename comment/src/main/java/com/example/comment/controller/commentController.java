package com.example.comment.controller;


import com.example.comment.model.Article;
import com.example.comment.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class commentController {
    @Autowired
    ArticleRepository articleRepository;
    @GetMapping("/ListAll")
    public List<Article> getAllArtles(){
        return articleRepository.findAll();
    }

    @GetMapping("/findByTypes")
    public List<Article> findByTypes(@RequestParam("type") String type){
        return articleRepository.findByType(type);
    }


}
