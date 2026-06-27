package com.likelion.likelion_project.subscriber.api.dto.response;

import com.likelion.likelion_project.subscriber.domain.Part;
import com.likelion.likelion_project.subscriber.domain.Subscriber;
import lombok.Builder;

@Builder
public record SubscriberInfoResponseDto(
        String name,
        int age,
        String residence,
        Part part
) {
    public static SubscriberInfoResponseDto from(Subscriber subscriber) {
        return SubscriberInfoResponseDto.builder()
                .name(subscriber.getName())
                .age(subscriber.getAge())
                .residence(subscriber.getResidence())
                .part(subscriber.getPart())
                .build();
    }
}
