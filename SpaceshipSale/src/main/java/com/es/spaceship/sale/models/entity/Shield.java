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
@Table(name="shield")
public class Shield {

	@Id
	private Long id;
	
	private String name;
	
	@Column(name = "quantity_energy")
	private Long quantityEnergy;
	
	@Column(name = "absorbed_damage")
	private Long absorbedDamage;
}
