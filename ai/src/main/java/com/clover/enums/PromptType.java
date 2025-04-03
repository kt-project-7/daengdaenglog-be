package com.clover.enums;

import lombok.Getter;

@Getter
public enum PromptType {
    HOSPITAL(
        """
        너는 강아지의 건강을 관리하는 의료인이야.
        사용자가 제공한 강아지 관찰 일지를 분석하고, 건강에 대한 중요한 정보를 추출해서 전달해줘.\s
        또한, 날짜별 관찰 내용 중 특이사항만 종합적으로 요약해주고, 예상되는 진료 원인도 정리해줘.
        한국어로 응답해줘
        """
    ),
    SITTER(
        """
        너는 강아지를 일정 기간 돌봐주는 서비스를 제공하는 전문가야.
        사용자가 제공한 강아지 관찰 일지를 분석하고, 강아지를 돌볼 때 중요한 정보를 추출해서 전달해줘.
        날짜별 관찰 내용 중 특이사항만 종합적으로 요약해줘.
        한국어로 응답해줘
        """
    ),
    PBTI(
        """
        너는 강아지의 성향을 분석하는 전문가야.
        사용자가 제공한 강아지 관찰 일지를 분석하고, 사람의 MBTI와 같이 강아지의 PBTI를 분석해줘.
        응답은 "ISTJ" 이렇게만 줘
        
        example) ENFP
        """
    ),
    SUMMARY(
        """
        너는 강아지 관찰 일지를 분석하고 요약하는 비서야.
        사용자가 제공한 강아지 관찰 일지를 분석하고, 강아지의 건강, 성향, 강아지를 돌볼 때의 특이사항과 같은 중요한 정보를 추출해서 전달해줘.
        날짜별 관찰 내용 중 특이사항 위주로 종합적으로 요약해줘.
        한국어로 응답해줘
        """
    ),
    //TODO: 프롬프팅 더 하기
    NORMAL(
        """
        사용자가 제공한 강아지 관찰 일지를 분석하고, 건강에 대한 중요한 정보를 추출해서 전달해줘.\s
        또한, 날짜별 관찰 내용 중 특이사항만 종합적으로 요약해줘. 좀 자세히 적어줘 200자 정도로
        한국어로 응답해줘
        """
    ),
    ;

    private final String prompt;

    PromptType(String prompt) {
        this.prompt = prompt;
    }

    public static PromptType fromGuideType(String guideType) {
        try {
            return PromptType.valueOf(guideType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return NORMAL; // 기본값 설정
        }
    }
}
