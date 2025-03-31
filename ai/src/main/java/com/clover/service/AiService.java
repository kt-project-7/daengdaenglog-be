package com.clover.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiService {

    private final ImageModel imageModel;
    private final ChatModel chatModel;

    private static final String SYSTEM_PROMPT = """
        나는 강아지를 키우는 반려인으로, 강아지에 대한 정보와 일지들을 제공할 거야.
        제공된 일지들을 요약하고, 강아지의 건강에 대한 중요한 정보를 추출해서 전달해줘.
        날짜별 관찰 내용 중 특이사항만 종합적으로 요약해주고, 예상되는 진료 원인도 정리해줘.
        """;

    public String generateImage(String prompt) {
        String instruction = "귀여운 강아지를 생성해줘. 이떄 지브리 풍으로 만들어주라";

        ImagePrompt imagePrompt = new ImagePrompt(instruction);
        ImageResponse response = imageModel.call(imagePrompt);

        if (!response.getResults().isEmpty()) {
            // 이미지 생성 성공
            String imageUrl =  response.getResults().get(0).getOutput().getUrl();
            log.info("Generated image URL: {}", imageUrl);

            return imageUrl;
        } else {
            throw new RuntimeException("이미지 생성 실패");
        }
    }

    public String getChatResponse(String userInput) {
        SystemMessage systemMessage = new SystemMessage(SYSTEM_PROMPT);
        UserMessage userMessage = new UserMessage(userInput);

        String response = chatModel.call(systemMessage, userMessage);

        log.info("Chat response: {}", response);

        return response;
    }
}
