package com.likelion.likelion_project.introduce.domain.repository;

import com.likelion.likelion_project.introduce.domain.Introduce;
import com.likelion.likelion_project.subscriber.domain.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IntroduceRepository extends JpaRepository<Introduce, Long> {
    List<Introduce> findBySubscriber(Subscriber subscriber);
}
