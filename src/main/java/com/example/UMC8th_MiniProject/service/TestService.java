package com.example.UMC8th_MiniProject.service;

import com.example.UMC8th_MiniProject.aws.s3.AmazonS3Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TestService {

    private final AmazonS3Manager s3Manager;

    public String testS3 (MultipartFile file){
        String uuid = UUID.randomUUID().toString(); // uuid 생성
        String fileUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(uuid), file); // 업로드
        return fileUrl;
    }

}
