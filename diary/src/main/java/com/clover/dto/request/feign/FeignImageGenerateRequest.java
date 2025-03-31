package com.clover.dto.request.feign;

import com.clover.domain.Diary;
import com.clover.domain.type.EmotionType;
import com.clover.domain.type.WeatherType;
import com.clover.dto.response.feign.FeignPetInfoResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record FeignImageGenerateRequest(
        String petName,
        String pbti,
        String petType,
        EmotionType emotionType,
        WeatherType weatherType,
        String title,
        String content,
        List<FeignImageGenScheduleRequest> scheduleTimeList
) {

    public static FeignImageGenerateRequest of(
            FeignPetInfoResponse petInfoResponse,
            Diary diary,
            List<FeignImageGenScheduleRequest> scheduleTimeList
    ) {
        return FeignImageGenerateRequest.builder()
                .petName(petInfoResponse.name())
                .pbti(petInfoResponse.pbti())
                .petType(petInfoResponse.petType())
                .emotionType(diary.getEmotionType())
                .weatherType(diary.getWeatherType())
                .title(diary.getTitle())
                .content(diary.getContent())
                .scheduleTimeList(scheduleTimeList)
                .build();
    }
}
