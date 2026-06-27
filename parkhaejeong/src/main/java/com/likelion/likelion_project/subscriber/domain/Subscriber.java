package com.likelion.likelion_project.subscriber.domain;


import com.likelion.likelion_project.introduce.domain.Introduce;
import com.likelion.likelion_project.subscriber.api.dto.request.SubscriberUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity // 클래스가 jpa 엔티티임을 선언. DB의 테이블과 1대1 매핑되는 객체라는 뜻
@Getter // lombok 애너테이션. 모든 필드에 대해 getter 메서드 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 생성
public class Subscriber {

    @Id // 테이블의 기본키 역할을 할 필드 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 생성 전략. identity는 mySQL 같은 DB에서 사용하며, DB강 알아서 숫자를 1씩 올려주며 생성하게 한다.
    @Column(name = "subscribe_id") // @Column 객체의 필드를 테이블 컬럼과 매핑 (이름을 바꾸거나 길이 제한할때 사용)
    private Long subscribeId;

    private String name;

    private int age;

    private String residence;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Part part;

    @OneToMany(mappedBy = "subscriber",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Introduce> Introduces = new ArrayList<>();

    @Builder //생성자 (맥)cmd + n, (윈도우) Alt + Insert
    private Subscriber(String name, int age, String residence, Part part) {
        this.name = name;
        this.age = age;
        this.residence = residence;
        this.part = part;
    }

    public void update(SubscriberUpdateRequestDto subscriberUpdateRequestDto) {
        this.name = subscriberUpdateRequestDto.name();
        this.age = subscriberUpdateRequestDto.age();
        this.residence = subscriberUpdateRequestDto.residence();
    }
}
