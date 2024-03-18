package com.olim.authservice.service;

import com.olim.authservice.dto.request.ImageUploadResponse;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public interface S3Service {
    @Transactional
    String s3Upload(ImageUploadResponse dto) throws IOException;
}