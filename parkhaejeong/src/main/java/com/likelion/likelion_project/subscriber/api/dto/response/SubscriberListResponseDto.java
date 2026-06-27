package com.likelion.likelion_project.subscriber.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record SubscriberListResponseDto(
        List<SubscriberInfoResponseDto> subscribers
) {
    public static SubscriberListResponseDto from (
            List<SubscriberInfoResponseDto> subscribers) {
        return SubscriberListResponseDto.builder()
                .subscribers(subscribers)
                .build();
    }

}
