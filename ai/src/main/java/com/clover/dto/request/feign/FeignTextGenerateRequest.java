package com.clover.dto.request.feign;

import com.clover.enums.PromptType;

public record FeignTextGenerateRequest(
        String userInput,
        PromptType promptType
) {

}
