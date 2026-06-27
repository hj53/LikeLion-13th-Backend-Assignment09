package com.likelion.likelion_project.global.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OlympicPoolResponseDto(
        @JsonProperty("title")
        String title,               // 자원의 명칭 (예: 7월 수영 강습 안내, 올림픽수영장 공지 등)

        @JsonProperty("creator")
        String creator,             // 주된 책임을 진 개체 (예: 한국체육산업개발주식회사)

        @JsonProperty("regDate")
        String regDate,             // 등록일 (글이 올라온 날짜)

        @JsonProperty("subjectKeyword")
        String subjectKeyword,      // 핵심주제어 (예: 수영장시설, 레인안내, 강습프로그램)

        @JsonProperty("description")
        String description,         // 내용 (강습 시간표나 상세 공지 내용)

        @JsonProperty("spatialCoverage")
        String spatialCoverage      // 관련 장소 (예: 올림픽수영장 위치나 장소 안내)
) {}