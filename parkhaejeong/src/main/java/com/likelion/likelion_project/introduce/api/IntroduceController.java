package com.likelion.likelion_project.introduce.api;

import com.likelion.likelion_project.common.response.code.SuccessCode;
import com.likelion.likelion_project.common.template.ApiResTemplate;
import com.likelion.likelion_project.introduce.api.dto.request.IntroduceSaveRequestDto;
import com.likelion.likelion_project.introduce.api.dto.request.IntroduceUpdateRequestDto;
import com.likelion.likelion_project.introduce.api.dto.response.IntroduceInfoResponseDto;
import com.likelion.likelion_project.introduce.api.dto.response.IntroduceListResponseDto;
import com.likelion.likelion_project.introduce.application.IntroduceService;
import com.likelion.likelion_project.introduce.domain.Introduce;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class IntroduceController {
    private final IntroduceService introduceService;

    // 게시물 저장
    @PostMapping()
    @Operation(summary = "자기소개서 저장", description = "자기 소개서 작성란입니다.")
    public ApiResTemplate<Void> introduceSave(@RequestBody @Valid IntroduceSaveRequestDto introduceSaveRequestDto){
        introduceService.introduceSave(introduceSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.POST_SAVE_SUCCESS);
    }

    // 사용자 id를 기준으로 해당 사용자가 작성한 게시글 목록 조회
    @GetMapping("/{subscriberId}")
    @Operation(summary = "자기소개서 조회", description = "자기 소개서 조회")
    public ApiResTemplate<IntroduceListResponseDto> myPostFindAll(@PathVariable("subscriberId") Long subscriberId) {
        IntroduceListResponseDto introduceListResponseDto = introduceService.introduceFindMember(subscriberId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, introduceListResponseDto);
    }

    // 게시물 id를 기준으로 사용자가 작성한 게시물 수정
    @PatchMapping("/{introduceId}")
    @Operation(summary = "자기소개서 수정", description = "자기 소개서 수정")
    public ApiResTemplate<Void> introduceUpdate(@PathVariable("introduceId") Long introduceId,
                                                @RequestBody @Valid IntroduceUpdateRequestDto introduceUpdateRequestDto) {
        introduceService.introduceUpdate(introduceId, introduceUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.SUBSCRIBER_UPDATE_SUCCESS);
    }

    // 게시물 id를 기준으로 사용자가 작성한 게시물 삭제
    @DeleteMapping("/{introduceId}")
    @Operation(summary = "자기소개서 삭제", description = "자기 소개서 삭제.")
    public ApiResTemplate<Void> introduceDelete(@PathVariable("introduceId") Long introduceId) {
        introduceService.introduceDelete(introduceId);
        return ApiResTemplate.successWithNoContent(SuccessCode.SUBSCRIBER_DELETE_SUCCESS);
    }

}
