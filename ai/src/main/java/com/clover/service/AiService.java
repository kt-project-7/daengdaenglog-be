package com.clover.service;

import com.clover.dto.request.SummaryDataRequest;
import com.clover.dto.request.SummaryDataScheduleRequest;
import com.clover.dto.request.SummaryRequest;
import com.clover.dto.request.feign.FeignImageGenerateRequest;
import com.clover.dto.request.feign.FeignPetDiaryDetailResponse;
import com.clover.dto.response.SummaryResponse;
import com.clover.dto.request.feign.FeignTextGenerateRequest;
import com.clover.enums.PromptType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiService {

    private final ImageModel imageModel;
    private final ChatModel chatModel;
    private final KafkaProducer kafkaProducer;

    public String generateImage(
            FeignImageGenerateRequest input
    ) {
        ImagePrompt imagePrompt = new ImagePrompt(input.toPrompt());
        ImageResponse response = imageModel.call(imagePrompt);

        if (!response.getResults().isEmpty()) {
            // 이미지 생성 성공
            String imageUrl =  response.getResults().getFirst().getOutput().getUrl();
            log.info("Generated image URL: {}", imageUrl);

            return imageUrl;
        } else {
            throw new RuntimeException("이미지 생성 실패");
        }
    }

    public String generateText(
            FeignTextGenerateRequest prompt
    ) {
        SystemMessage systemMessage = new SystemMessage(prompt.promptType().getPrompt());
        UserMessage userMessage = new UserMessage(prompt.userInput());

        String response = chatModel.call(systemMessage, userMessage);

        log.info("Chat response: {}", response);

        return response;
    }

    public String generatePbti(
            List<FeignPetDiaryDetailResponse> requestList
    ) {
        SystemMessage systemMessage = new SystemMessage(PromptType.PBTI.getPrompt());
        UserMessage userMessage = new UserMessage(formatDiaryList(requestList));

        String response = chatModel.call(systemMessage, userMessage);

        log.info("PBTI response: {}", response);

        return response;
    }

    private String formatDiaryList(List<FeignPetDiaryDetailResponse> diaryList) {
        StringBuilder result = new StringBuilder();

        for (FeignPetDiaryDetailResponse diary : diaryList) {
            result.append("📔 Pet Diary Details\n");
            result.append("Diary ID: ").append(diary.diaryId()).append("\n")
                    .append("- Pet ID: ").append(diary.petId()).append("\n")
                    .append("- Title: ").append(diary.title()).append("\n")
                    .append("- Content: ").append(diary.content()).append("\n")
                    .append("- Emotion: ").append(diary.emotionType()).append("\n")
                    .append("- Weather: ").append(diary.weatherType()).append("\n")
                    .append("- Created Date: ").append(diary.createdDate()).append("\n")
                    .append("- Memory URI: ").append(diary.memoryUri()).append("\n\n");

            result.append("🗓️ Schedules:\n");

            result.append("\n---\n");
        }

        return result.toString();
    }

    public void generateSummary(
            SummaryRequest request
    ) {
        SystemMessage systemMessage = new SystemMessage(SYSTEM_PROMPT_SUMMARY);
        UserMessage userMessage = new UserMessage(formatDiaryList(request));

        String response = chatModel.call(systemMessage, userMessage);

        log.info("Chat response: {}", response);

        SummaryResponse summaryResponse = new SummaryResponse(
                request.petId(),
                request.year(),
                request.month(),
                response
        );

        kafkaProducer.send("summary-response", summaryResponse);
    }

    private String formatDiaryList(SummaryRequest request) {
        StringBuilder result = new StringBuilder();

        for (SummaryDataRequest summary : request.summaryDataList()) {
            result.append("📔 Pet Diary Details\n");
            result.append("Pet ID: ").append(request.petId()).append("\n")
                    .append("- Year: ").append(request.year()).append("\n")
                    .append("- Month: ").append(request.month()).append("\n")
                    .append("- Title: ").append(summary.title()).append("\n")
                    .append("- Content: ").append(summary.content()).append("\n")
                    .append("- Emotion: ").append(summary.EmotionType()).append("\n")
                    .append("- Weather: ").append(summary.WeatherType()).append("\n");

            result.append("🗓️ Schedules:\n");

            for (SummaryDataScheduleRequest schedule : summary.summaryDataScheduleRequestList()) {
                result.append("- Schedule ID: ").append(schedule.scheduleType()).append("\n")
                        .append("- Schedule Date: ").append(schedule.startHour()).append(":").append(schedule.startMinute()).append("\n");
            }
        }

        return result.toString();
    }
}
