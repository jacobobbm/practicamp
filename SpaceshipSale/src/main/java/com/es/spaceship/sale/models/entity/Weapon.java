package com.es.spaceship.sale.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="weapon")
public class Weapon {

	@Id
	private Long id;
	
	@Column(name = "name_weapon")
	private String nameWeapon;
	private Long power;
}
