package com.fsk.ehcache.repository;


import com.fsk.ehcache.model.Writer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WriterRepository extends MongoRepository<Writer, String> {


}
