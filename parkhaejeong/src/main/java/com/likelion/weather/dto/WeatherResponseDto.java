package com.likelion.weather.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeatherResponseDto {
    private String cityName;       // 도시 이름
    private Double temperature;    // 현재 온도 (섭씨)
    private String description;    // 날씨 설명 (예: 맑음, 흐림)
    private Integer humidity;      // 습도 (%)
    private String icon;           // 날씨 아이콘 코드
}