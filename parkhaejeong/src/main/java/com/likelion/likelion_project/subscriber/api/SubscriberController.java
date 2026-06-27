package com.likelion.likelion_project.subscriber.api;


import com.likelion.likelion_project.common.template.ApiResTemplate;
import com.likelion.likelion_project.subscriber.api.dto.request.SubscriberSaveRequestDto;
import com.likelion.likelion_project.subscriber.api.dto.request.SubscriberUpdateRequestDto;
import com.likelion.likelion_project.subscriber.api.dto.response.SubscriberInfoResponseDto;
import com.likelion.likelion_project.subscriber.api.dto.response.SubscriberListResponseDto;
import com.likelion.likelion_project.subscriber.application.SubscriberService;
import com.likelion.likelion_project.subscriber.domain.Subscriber;
import com.likelion.likelion_project.common.response.code.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriber")
@Tag(name = "회원 API", description = "회원 관리하는 api ")
public class SubscriberController {

    private final SubscriberService subscriberService;

    // 사용자 저장
    @PostMapping()
    @Operation(summary = "새로운 회원 가입", description = "회원가입 설명란입니다.")
    public ApiResTemplate<Void> memberSave(@RequestBody @Valid SubscriberSaveRequestDto subscriberSaveRequestDto) {
        subscriberService.subscriberSave(subscriberSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.SUBSCRIBER_SAVE_SUCCESS);
    }

    // 사용자 전체 조회
    @GetMapping("/all")
    @Operation(summary = "회원 전체조회", description = "회원 전체조회")
    public ApiResTemplate<Page<SubscriberInfoResponseDto>> subscriberFindAll(
            @ParameterObject
            @PageableDefault(
                    size = 10,
                    sort = "subscriberId",
                    direction = Sort.Direction.ASC
            ) Pageable pageable
    ) {
        Page<SubscriberInfoResponseDto> subscribers = subscriberService.subscriberFindAll(pageable);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, subscribers);
    }


    // 회원 id를 통해 특정 사용자 조회
    @GetMapping("/{subscriberId}")
    @Operation(summary = "회원 1명 조회", description = "회원 id로 멤버조회")
    public ApiResTemplate<SubscriberInfoResponseDto> subscriberFindOne(@PathVariable("subscriberId") Long subscriberId) {
        SubscriberInfoResponseDto subscriberInfoResponseDto = subscriberService.subscriberFindOne(subscriberId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, subscriberInfoResponseDto);
    }

    // 회원 id를 통한 사용자 수정
    @PatchMapping("/{subscriberId}")
    @Operation(summary = "회원 업데이트", description = "회원 업데이트")
    public ApiResTemplate<Void> subscriberUpdate(@PathVariable("subscriberId") Long subscriberId,
                                             @RequestBody @Valid SubscriberUpdateRequestDto subscriberUpdateRequestDto) {
        subscriberService.subscriberUpdate(subscriberId, subscriberUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.SUBSCRIBER_UPDATE_SUCCESS);
    }

    // 회원 id를 통한 사용자 삭제
    @DeleteMapping("/{subscriberId}")
    @Operation(summary = "회원 삭제", description = "회원 삭제")
    public ApiResTemplate<Void> subscriberDelete(@PathVariable("subscriberId") Long subscriberId) {
        subscriberService.subscriberDelete(subscriberId);
        return ApiResTemplate.successWithNoContent(SuccessCode.SUBSCRIBER_DELETE_SUCCESS);
    }
}
