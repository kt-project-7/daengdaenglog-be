package com.clover.enums;

public enum PromptType {
    HOSPITAL(
        """
        나는 강아지를 키우는 반려인으로, 강아지에 대한 정보와 일지들을 제공할 거야.
        제공된 일지들을 요약하고, 강아지의 건강에 대한 중요한 정보를 추출해서 전달해줘.
        날짜별 관찰 내용 중 특이사항만 종합적으로 요약해주고, 예상되는 진료 원인도 정리해줘.
        """
    ),
    SITTER(
            """
                    
                    """
    ),
    PBTI(
        """
        나는 강아지를 키우는 반려인으로, 강아지에 대한 정보와 일지들을 제공할 거야.
        제공된 일지들을 분석하여 사람의 MBTI와 같이 강아지의 PBTI를 분석해줘.
        응답은 ISTJ 이런 식으로 PBTI로만 해줘.
        """
    )
    ;

    private final String prompt;

    PromptType(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}
