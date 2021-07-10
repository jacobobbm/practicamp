package com.es.spaceship.sale.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.es.spaceship.sale.models.entity.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, String> {

    List<User> findAllByIdNot(String ownerId);

    User findByNick(String nick);
}
