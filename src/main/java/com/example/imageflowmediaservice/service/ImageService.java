package com.example.imageflowmediaservice.service;

import com.example.imageflowmediaservice.model.ImageMetadata;
import com.example.imageflowmediaservice.repository.ImageMetadataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ImageService {

    private final ImageMetadataRepository imageMetadataRepository;
    private final FileStorageService fileStorageService;

    public ImageService(ImageMetadataRepository imageMetadataRepository, FileStorageService fileStorageService) {
        this.imageMetadataRepository = imageMetadataRepository;
        this.fileStorageService = fileStorageService;
    }


    public ImageMetadata upload(MultipartFile file, String username) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        log.info("storing file {}", filename);

        ImageMetadata metadata = fileStorageService.store(file, username);
        return imageMetadataRepository.save(metadata);
    }
}
