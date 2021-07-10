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
@Table(name="ships")
public class Ships {

	@Id
	private Long id;
	
	@Column(name = "ship_type")
	@ManyToMany(targetEntity = ShipType.class, fetch = FetchType.LAZY)
	private List<ShipType> shipType;
	
	@Column(name = "register_number")
	private String registryNumber;
	
	private String owner;

	@Column(name = "owner_Id")
	private String ownerId;
	
	@Column(name = "quantity_Propellers")
	private Integer quantityPropellers;
	
	@Column(name = "type_motor")
	@ManyToMany(targetEntity = Motor.class, fetch = FetchType.LAZY)
	private List<Motor> typeMotor;
	
	@Column(name = "amount_crew")
	private Long amountCrew;
	
	private Long price;
	
	private int vote;
	
}
