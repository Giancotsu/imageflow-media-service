package com.example.imageflowmediaservice.controller;

import com.example.imageflowmediaservice.model.ImageMetadata;
import com.example.imageflowmediaservice.payload.UploadFileResponse;
import com.example.imageflowmediaservice.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/images")
    public UploadFileResponse uploadFile(@RequestParam("image") MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        log.info("received a request to upload file {} for user {}", filename);

        ImageMetadata metadata = imageService.upload(file, "user");

        return new UploadFileResponse(metadata.getFilename(), metadata.getUri(),
                metadata.getFileType());
    }

}