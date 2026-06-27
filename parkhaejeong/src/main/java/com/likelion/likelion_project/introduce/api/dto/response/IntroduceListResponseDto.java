package com.likelion.likelion_project.introduce.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record IntroduceListResponseDto(
        List<IntroduceInfoResponseDto> introduces
) {
    public static IntroduceListResponseDto from(List<IntroduceInfoResponseDto> introduces) {
        return IntroduceListResponseDto.builder()
                .introduces(introduces)
                .build();
    }
}
