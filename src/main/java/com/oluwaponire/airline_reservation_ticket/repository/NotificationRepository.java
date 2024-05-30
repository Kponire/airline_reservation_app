package com.oluwaponire.airline_reservation_ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oluwaponire.airline_reservation_ticket.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserIdAndRead(Long userId, boolean read);
    List<Notification> findByUserId(Long userId);
    
}
