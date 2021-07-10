package com.es.spaceship.sale.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.es.spaceship.sale.models.entity.Armor;

public interface ArmorDao extends JpaRepository<Armor, Long> {

}
