package com.oluwaponire.airline_reservation_ticket.service;

import java.util.List;

import com.oluwaponire.airline_reservation_ticket.dto.NotificationDto;
import com.oluwaponire.airline_reservation_ticket.entity.Notification;

public interface NotificationService {

    Notification createNotification(NotificationDto notificationDto);

    Notification markAsRead(Long id);

    Notification getNotificationById(Long id);

    List<Notification> getNotificationsByUserId(Long userId);

    List<Notification> getUnreadNotificationsByUserId(Long userId);

    List<Notification> getAllNotifications();

}
