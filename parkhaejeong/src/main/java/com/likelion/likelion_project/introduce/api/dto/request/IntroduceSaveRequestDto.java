package com.likelion.likelion_project.introduce.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record IntroduceSaveRequestDto(
        @NotNull(message = "id를 필수로 입력해야 합니다.")
        Long subscribeId,

        @NotBlank(message = "제목을 입력해주세요.")
        @Size(max=60, message="60자 이하로 작성해주세요.")
        String title,

        @NotBlank(message="내용을 입력해주세요.")
        @Size(max=800, message="800자 이하로 작성해주세요.")
        String contents
) {
}
