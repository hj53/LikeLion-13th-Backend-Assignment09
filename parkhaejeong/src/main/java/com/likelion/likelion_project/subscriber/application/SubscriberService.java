package com.likelion.likelion_project.subscriber.application;

import com.likelion.likelion_project.common.exception.BusinessException;
import com.likelion.likelion_project.common.response.code.ErrorCode;
import com.likelion.likelion_project.subscriber.api.dto.request.SubscriberSaveRequestDto;
import com.likelion.likelion_project.subscriber.api.dto.request.SubscriberUpdateRequestDto;
import com.likelion.likelion_project.subscriber.api.dto.response.SubscriberInfoResponseDto;
import com.likelion.likelion_project.subscriber.api.dto.response.SubscriberListResponseDto;
import com.likelion.likelion_project.subscriber.domain.Subscriber;
import com.likelion.likelion_project.subscriber.domain.repository.SubscriberRepository;
import com.sun.net.httpserver.Authenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // service 계층임을 알림. spring이 관리하는 객체가 됨.
@RequiredArgsConstructor // final이 붙은 필드를 파라미터로 받는 생성자를 자동으로 생성.
@Transactional(readOnly = true) // 이 클래스의 모든 메서드에 기본적으로 트랜잭ㄱ션을 적용. readOnly = true 는 읽기 전용으로 설정한 것.
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;

    // 사용자 정보 저장
    @Transactional
    public void subscriberSave(SubscriberSaveRequestDto subscriberSaveRequestDto) {
        Subscriber subscriber = Subscriber.builder()
                .name(subscriberSaveRequestDto.name())
                .age(subscriberSaveRequestDto.age())
                .part(subscriberSaveRequestDto.part())
                .residence(subscriberSaveRequestDto.residence())
                .build();
        subscriberRepository.save(subscriber);
    }

    // 단일 사용자 조회
    public SubscriberInfoResponseDto subscriberFindOne(Long subscriberId) {
        Subscriber subscriber = subscriberRepository.findById(subscriberId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.SUBSCRIBER_NOT_FOUND_EXCEPTION,
                        ErrorCode.SUBSCRIBER_NOT_FOUND_EXCEPTION.getMessage() + subscriberId
                ));

        return SubscriberInfoResponseDto.from(subscriber);
    }

    // 사용자 모두 조회
    public Page<SubscriberInfoResponseDto> subscriberFindAll(Pageable pageable) {
        Page<Subscriber> subscribers = subscriberRepository.findAll(pageable);
        return subscribers.map(SubscriberInfoResponseDto::from);
    }

    // 사용자 정보 수정
    @Transactional
    public void subscriberUpdate(Long subscriberId, SubscriberUpdateRequestDto subscriberUpdateRequestDto) {
        Subscriber subscriber = subscriberRepository.findById(subscriberId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.SUBSCRIBER_NOT_FOUND_EXCEPTION,
                        ErrorCode.SUBSCRIBER_NOT_FOUND_EXCEPTION.getMessage() + subscriberId));
        subscriber.update(subscriberUpdateRequestDto);
    }

    // 사용자 정보 삭제
    @Transactional
    public void subscriberDelete(Long subscriberId) {
        Subscriber subscriber = subscriberRepository.findById(subscriberId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.SUBSCRIBER_NOT_FOUND_EXCEPTION,
                        ErrorCode.SUBSCRIBER_NOT_FOUND_EXCEPTION.getMessage() + subscriberId));
        subscriberRepository.delete(subscriber);
    }

}
