package com.clover.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WeatherType {
    SUNNY("PT-WT-001", "맑음"),
    CLOUDY("PT-WT-002", "흐림"),
    RAINY("PT-WT-003", "비"),
    SNOWY("PT-WT-004", "눈"),
    THUNDER("PT-WT-005", "천둥번개"),
    HAILSTONE("PT-WT-006", "우박"),
    FOG("PT-WT-007", "안개"),
    YELLOW_DUST("PT-WT-008", "황사"),
    ;

    private final String code;
    private final String description;
}
