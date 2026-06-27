package com.likelion.likelion_project.common.response.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter // getter 메소드 자동 생성 lombok 어노테이션
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 파라미터로 받는 생성자 자동 생성 어노테이션
public enum ErrorCode {

    /**
     * 404 NOT FOUND (찾을 수 없음)
     */
    SUBSCRIBER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 사용자가 없습니다. memberId = "),
    POST_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 글이 없습니다. postId = "),

    /**
     * 400 BAD REQUEST
     */
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "유효성 검사에 실패하였습니다 - "),

    /**
     * 401 UNAUTHORIZED (인증 오류)
     */
    INVALID_API_KEY_EXCEPTION(HttpStatus.UNAUTHORIZED, "문화공공데이터 인증키가 유효하지 않습니다."),

    /**
     * 502 BAD GATEWAY (외부 서버 에러)
     */
    OLYMPIC_API_EMPTY_EXCEPTION(HttpStatus.BAD_GATEWAY, "올림픽수영장 API 응답 데이터가 비어있습니다."),

    /**
     * 503 SERVICE UNAVAILABLE (서버 이용 불가)
     */
    OLYMPIC_SERVER_CONNECT_EXCEPTION(HttpStatus.SERVICE_UNAVAILABLE, "한국체육산업개발(주) API 서버에 연결할 수 없습니다."),

    /**
     * 500 INTERNAL SERVER ERROR (내부 서버 에러)
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 에러가 발생했습니다");

    private final HttpStatus httpStatus;    // HTTP 상태 코드를 스프링에서 쉽게 작성하기 위한 enum값들의 모임
    private final String message;           // 에러 메세지


    public int getHttpStatusCode() {        // HTTP 상태 코드에서 404와 같은 숫자 값만 반환해 주기 위한 메소드
        return httpStatus.value();
    }
}