package com.example.UMC8th_MiniProject.web.controller;

import com.example.UMC8th_MiniProject.apiPayload.ApiResponse;
import com.example.UMC8th_MiniProject.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestContoller {

    private final TestService testService;

    @Operation(summary = "사진 업로드 TEST",description = "백엔드 테스트용입니다.")
    @PostMapping(value = "/s3", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<String> S3Test(@RequestParam("file") MultipartFile file){
        // 파일 업로드
        String result = testService.testS3(file);
        return ApiResponse.onSuccess(result);
    }
}
