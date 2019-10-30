package com.example.post.controller;
import com.example.post.form.PostForm;
import com.example.post.model.Article;
import com.example.post.repository.ArticleRepository;
import com.example.post.util.FormToModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {

    @Autowired
    ArticleRepository articleRepository;


    @GetMapping("/")
    public String test(){
        return "test";
    }

    @GetMapping("/ListAll")
    public List<Article> getAllArticles() {
        System.out.println("收到请求");
        List<Article> articles=articleRepository.findAll();
        return articleRepository.findAll();
    }

    @GetMapping("/findByTypes")
    public List<Article> findByTypes(@RequestParam("type") String type) {
        return articleRepository.findByType(type);
    }

    @GetMapping("/findByUsername")
    public List<Article> findByUserName(@RequestParam("username") String username) {
        System.out.println("username is"+username);
        return articleRepository.findByUsername(username);
    }

    @GetMapping("/findByContent")
    public List<Article> findBy(@RequestParam("content") String content) {
        System.out.println("content is"+content);
        return articleRepository.findByContentLike(content);
    }


    @PostMapping("/newPost")
    public String addNewPost(PostForm postForm) {
        Article article = new FormToModel().covertToArticle(postForm);
        Article saved = articleRepository.save(article);
        if (saved != null) {
            return "success";
        }
        return "fail to post";
    }


    @PostMapping("/appendPost")
    public String appendPost(PostForm postForm) {
        log.info("postForm是{}",postForm);
        Article article = new FormToModel().covertToArticle(postForm);
        log.info("article是{}",article);
        Article parentArticle = articleRepository.findByPostId(postForm.getParentId());
        log.info("parentArticle是{}",parentArticle);
        List<Article> childArticles = parentArticle.getChildrenCommentList();
        childArticles.add(article);
        parentArticle.setCount_comment(parentArticle.getCount_comment()+1);
        parentArticle.setChildrenCommentList(childArticles);
        Article saved = articleRepository.save(parentArticle);
        if (saved != null) {
            return "success";
        }
        return "fail to append post";
    }

}


