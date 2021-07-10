package com.es.spaceship.sale.models.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ship_type")
public class ShipType {

	@Id
	private Long id;
	
	@Column(name = "name_ship_type")
	private String nameShipType;
	
	@Column(name = "number_max_passenger")
	private Long numberMaxPassenger;
	
	private Long hangares;
	
	@Column(name = "system_defence")
	@ManyToMany(targetEntity = DefenseSystem.class, fetch = FetchType.LAZY)
	private List<DefenseSystem> systemDefence;//regla si es destructor solo tien uno o dos sistemas de defensa && si es estacion espacial tiene de uno a tres systemas de defensa
	//si es cargero solo tiene un sistema de defensa
	
	@Column(name = "set_weapon")
	@ManyToMany(targetEntity = Weapon.class, fetch = FetchType.LAZY)
	private List<Weapon> setWeapons;
	
	@Column(name = "maximum_load_capacity")
	private Long maximumLoadCapacity;
	
	@Column(name = "is_caza")
	private boolean iscaza;
	
}
