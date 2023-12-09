package com.fsk.ehcache.service;

import com.fsk.ehcache.model.Post;
import com.fsk.ehcache.model.Writer;
import com.fsk.ehcache.repository.PostRepository;
import com.fsk.ehcache.repository.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "CacheWriter")
public class WriterService {

    private final WriterRepository writerRepository;
    private final PostRepository postRepository;

    public WriterService(WriterRepository writerRepository,
                         PostRepository postRepository) {
        this.writerRepository = writerRepository;
        this.postRepository = postRepository;
    }

    private final Logger LOGGER = LoggerFactory.getLogger(PostService.class);

    public Writer addWriter(Writer writer) {
        return writerRepository.save(writer);
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Writer getWriterById(String id) {

        return writerRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Writer Not Found"));
    }


    public long getPostCountForWriter(String writerId) {
        return postRepository.findAllByWriterId(writerId).size();
    }

    public boolean isWriterEligibleForCaching(String writerId) {
        long postCount = getPostCountForWriter(writerId);
        return postCount > 1;
    }


    public List<Writer> getAllWriters() {
        return writerRepository.findAll();
    }

}


