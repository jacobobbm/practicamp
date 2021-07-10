package com.es.spaceship.sale.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="armor")
public class Armor {

	@Id
	private Long id;
	
	@Column(name="absorbed_damage")
	private Long absorbedDamage;
	
	@Column(name="material_name")
	private String materialName;
	
	private Long weight;
}
