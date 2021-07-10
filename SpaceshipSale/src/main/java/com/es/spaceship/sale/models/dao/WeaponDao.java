package com.es.spaceship.sale.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.es.spaceship.sale.models.entity.Weapon;

public interface WeaponDao extends JpaRepository<Weapon, Long> {

}
