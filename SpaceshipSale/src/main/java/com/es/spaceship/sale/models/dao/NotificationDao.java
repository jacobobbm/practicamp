package com.es.spaceship.sale.models.dao;

import com.es.spaceship.sale.models.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationDao extends JpaRepository<Notification, Long> {

    List<Notification> findByUserId(String userId);
}
