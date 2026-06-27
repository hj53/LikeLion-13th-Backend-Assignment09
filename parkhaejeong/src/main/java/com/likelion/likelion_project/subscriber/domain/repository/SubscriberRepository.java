package com.likelion.likelion_project.subscriber.domain.repository;

import com.likelion.likelion_project.subscriber.domain.Subscriber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Page<Subscriber> findAll(Pageable pageable);
}
