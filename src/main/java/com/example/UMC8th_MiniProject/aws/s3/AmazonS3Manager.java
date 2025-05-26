package com.example.UMC8th_MiniProject.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.UMC8th_MiniProject.configuration.AmazonConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager{

    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig;

    public String uploadFile(String keyName, MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        // ✅ Content-Type 설정
        String contentType = Optional.ofNullable(file.getContentType()).orElse("application/octet-stream");
        metadata.setContentType(contentType);

        try {
            amazonS3.putObject(new PutObjectRequest(
                    amazonConfig.getBucket(), keyName, file.getInputStream(), metadata
            ));
        } catch (IOException e) {
            log.error("error at AmazonS3Manager uploadFile : {}", e.getMessage(), e);
        }

        return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
    }


    //리뷰 사진 저장
    public String generateReviewKeyName(String uuid) {
        return amazonConfig.getReviewPath() + '/' + uuid;
    }
}