package com.oluwaponire.airline_reservation_ticket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oluwaponire.airline_reservation_ticket.dto.NotificationDto;
import com.oluwaponire.airline_reservation_ticket.entity.Notification;
import com.oluwaponire.airline_reservation_ticket.entity.User;
import com.oluwaponire.airline_reservation_ticket.repository.NotificationRepository;
import com.oluwaponire.airline_reservation_ticket.repository.UserRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Notification createNotification(NotificationDto notificationDto) {
        Optional<User> userOpt = userRepository.findById(notificationDto.getUserId());
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }

        Notification notification = new Notification();
        notification.setUser(userOpt.get());
        notification.setType(notificationDto.getType());
        notification.setMessage(notificationDto.getMessage());
        notification.setTimestamp(notificationDto.getTimestamp());
        notification.setRead(notificationDto.isRead());
        return notificationRepository.save(notification);
    }

    @Override
    public Notification markAsRead(Long id) {
        Optional<Notification> notificationOpt = notificationRepository.findById(id);
        if (!notificationOpt.isPresent()) {
            throw new RuntimeException("Notification not found");
        }

        Notification notification = notificationOpt.get();
        notification.setRead(true);
        return notificationRepository.save(notification);
    }

    @Override
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    @Override
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public List<Notification> getUnreadNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserIdAndRead(userId, false);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

}
