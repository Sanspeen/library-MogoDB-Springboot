package com.example.library.repositories;

import com.example.library.collections.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResourceRepository extends MongoRepository<Resource, String> {
    Iterable<Resource> findByThematic(final String thematic);
    List<Resource> findByKind(final String kind);
}
