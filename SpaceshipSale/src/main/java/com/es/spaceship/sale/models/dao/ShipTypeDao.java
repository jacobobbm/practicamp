package com.es.spaceship.sale.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.es.spaceship.sale.models.entity.ShipType;

public interface ShipTypeDao extends JpaRepository<ShipType, Long> {

}
