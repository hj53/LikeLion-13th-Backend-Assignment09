package com.likelion.likelion_project.introduce.domain;

import com.likelion.likelion_project.introduce.api.dto.request.IntroduceUpdateRequestDto;
import com.likelion.likelion_project.subscriber.domain.Subscriber;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Introduce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "introduce_id")
    private Long introduceId;

    private String title;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id") // 외래키 설정.
    private Subscriber subscriber;

    @Builder
    private Introduce(String title, String contents, Subscriber subscriber) {
        this.title = title;
        this.contents = contents;
        this.subscriber = subscriber;
    }

    public void update(IntroduceUpdateRequestDto introduceUpdateRequestDto) {
        this.title = introduceUpdateRequestDto.title();
        this.contents = introduceUpdateRequestDto.contents();
    }
}
