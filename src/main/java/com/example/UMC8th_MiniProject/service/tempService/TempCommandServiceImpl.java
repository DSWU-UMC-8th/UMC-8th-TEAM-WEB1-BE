package com.example.UMC8th_MiniProject.service.tempService;

import com.example.UMC8th_MiniProject.apiPayload.code.status.ErrorStatus;
import com.example.UMC8th_MiniProject.apiPayload.exception.handler.TempHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempCommandServiceImpl implements TempQueryService{

    @Override
    public void CheckFlag(Integer flag) {
        if (flag == 1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}