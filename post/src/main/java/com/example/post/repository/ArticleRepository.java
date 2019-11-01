package com.example.post.repository;




import com.example.post.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


/**
 * the operations provided by MongoRepository
 * @description: MongoRepository
 * @author: Hangqi Yu
 * @date: Created in 2019-10-10 16:21
 * @version: V1.0
 * @modified: Hangqi Yu
 */

public interface ArticleRepository extends MongoRepository<Article, String> {


    List<Article> findByContentLike(String Content);

    @Override
    <S extends Article> S save(S s);

    List<Article> findByType(String Type);

    List<Article> findByUsername(String username);

    Article findByPostId(String id);

    List<Article> findAll();
}
