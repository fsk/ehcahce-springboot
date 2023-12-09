package com.fsk.ehcache.service;

import com.fsk.ehcache.model.Post;
import com.fsk.ehcache.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "CachePost")
public class PostService {

    private final PostRepository postRepository;
    public Post getPostById(String id) {
        return postRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post Not Found"));
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Cacheable(key = "#writerId", condition = "#result != null")
    public List<Post> getPostsByWriterId(String writerId) {
        return postRepository.findByWriterId(writerId);
    }
}
