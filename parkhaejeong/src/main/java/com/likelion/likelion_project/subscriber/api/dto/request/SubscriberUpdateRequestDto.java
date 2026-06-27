package com.likelion.likelion_project.subscriber.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubscriberUpdateRequestDto(

        @NotBlank(message="이름을 필수로 입력해야 합니다.")
        String name,

        @Min(value = 1, message = "나이는 1 이상이어야 합니다.")
        @Max(value = 120, message = "나이는 120 이하여야 합니다.")
        int age,

        @NotBlank(message = "주소를 필수로 입력해야 합니다.")
        String residence
) {
}
