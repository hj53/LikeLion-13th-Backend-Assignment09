package com.likelion.likelion_project.common.response.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 파라미터로 받는 생성자 자동 생성 어노테이션
public enum SuccessCode {

    /**
     * 200 OK (성공)
     */
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    SUBSCRIBER_UPDATE_SUCCESS(HttpStatus.OK, "회원 정보가 성공적으로 수정되었습니다."),
    POST_UPDATE_SUCCESS(HttpStatus.OK, "소개글이 성공적으로 수정되었습니다."),
    SUBSCRIBER_DELETE_SUCCESS(HttpStatus.OK, "사용자가 성공적으로 삭제되었습니다."),
    POST_DELETE_SUCCESS(HttpStatus.OK, "소개글이 성공적으로 삭제되었습니다."),

    /**
     * 201 CREATED (생성 성공)
     */
    SUBSCRIBER_SAVE_SUCCESS(HttpStatus.CREATED, "성공적으로 회원 가입이 완료되었습니다."),
    POST_SAVE_SUCCESS(HttpStatus.CREATED, "소개글이 성공적으로 등록되었습니다.");

    private final HttpStatus httpStatus;   // HTTP 상태 코드를 스프링에서 쉽게 작성하기 위한 enum값들의 모임
    private final String message;          // 응답 메세지

    public int getHttpStatusCode() {       // HTTP 상태 코드에서 404와 같은 숫자 값만 반환해 주기 위한 메소드
        return httpStatus.value();
    }
}
