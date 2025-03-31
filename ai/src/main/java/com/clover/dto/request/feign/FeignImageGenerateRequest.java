package com.clover.dto.request.feign;

import lombok.Builder;

import java.util.List;

@Builder
public record FeignImageGenerateRequest(
        String petName,
        String pbti,
        String petType,
        String emotionType,
        String weatherType,
        String title,
        String content,
        List<FeignImageGenScheduleRequest> scheduleTimeList
) {

    public String toPrompt() {
        StringBuilder prompt = new StringBuilder("지브리 애니메이션 스타일의 동물 사진을 만들어 주세요. 준 모든 정보를 사용할 필요는 없고 귀엽게 만들어줘.\n");
        prompt.append("동물 이름: ").append(petName).append("\n");
        prompt.append("동물 유형: ").append(petType).append("\n");
        prompt.append("성격 유형(PBTI): ").append(pbti).append("\n");
        prompt.append("감정 상태: ").append(emotionType).append("\n");
        prompt.append("날씨 상태: ").append(weatherType).append("\n");
        prompt.append("사진의 제목: ").append(title).append("\n");
        prompt.append("상세 설명: ").append(content).append("\n\n");
        prompt.append("일정 정보:\n");

        for (FeignImageGenScheduleRequest schedule : scheduleTimeList) {
            prompt.append("- 일정 유형: ").append(schedule.scheduleType()).append("\n");
            prompt.append("  시작 시간: ").append(schedule.startTime()).append("\n");
            prompt.append("  종료 시간: ").append(schedule.endTime()).append("\n");
        }

        return prompt.toString();
    }
}
