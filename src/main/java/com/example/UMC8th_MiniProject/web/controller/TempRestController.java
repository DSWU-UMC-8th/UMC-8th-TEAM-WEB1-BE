package com.example.UMC8th_MiniProject.web.controller;

import com.example.UMC8th_MiniProject.apiPayload.ApiResponse;
import com.example.UMC8th_MiniProject.converter.TempConverter;
import com.example.UMC8th_MiniProject.service.TempService.TempQueryService;
import com.example.UMC8th_MiniProject.web.dto.TempResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {
    private final TempQueryService tempQueryService;

    @Operation(
            summary = "테스트",
            description = "스웨거 사용 예시입니다")
    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testAPI(){
        return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){
        tempQueryService.CheckFlag(flag);
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }
}