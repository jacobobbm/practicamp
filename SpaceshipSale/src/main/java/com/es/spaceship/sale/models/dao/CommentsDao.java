package com.es.spaceship.sale.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.es.spaceship.sale.models.entity.Comments;

public interface CommentsDao extends JpaRepository<Comments, Long> {

}
