package com.fsk.ehcache.repository;

import com.fsk.ehcache.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;


public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{ 'writer._id' : ?0 }")
    List<Post> getPostByWriterId(String writerId);

    Optional<Post> getPostById(String postId);

    List<Post> findAllByWriterId(String writerId);

    List<Post> findByWriterId(String writerId);


}
