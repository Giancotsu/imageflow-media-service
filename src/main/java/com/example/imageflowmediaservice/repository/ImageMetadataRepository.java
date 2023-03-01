package com.example.imageflowmediaservice.repository;

import com.example.imageflowmediaservice.model.ImageMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageMetadataRepository extends MongoRepository<ImageMetadata, String> {

}
