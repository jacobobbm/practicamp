package com.es.spaceship.sale.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.es.spaceship.sale.models.entity.Offers;

public interface OffersDao extends JpaRepository<Offers, Long> {

}
