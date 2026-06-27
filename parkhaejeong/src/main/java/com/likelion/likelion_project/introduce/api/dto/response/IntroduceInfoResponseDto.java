package com.likelion.likelion_project.introduce.api.dto.response;

import com.likelion.likelion_project.introduce.domain.Introduce;
import lombok.Builder;

@Builder
public record IntroduceInfoResponseDto (
        String title,
        String contents,
        String writer
) {
    public static IntroduceInfoResponseDto from(Introduce introduce) {
        return IntroduceInfoResponseDto.builder()
                .title(introduce.getTitle())
                .contents(introduce.getContents())
                .writer(introduce.getSubscriber().getName())
                .build();
    }

}
