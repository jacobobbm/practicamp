package com.es.spaceship.sale.models.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="defense_system")
public class DefenseSystem {

	@Id
	private Long id;
	
	@OneToMany(targetEntity = Shield.class, fetch = FetchType.LAZY)
	private List<Shield> shield;
	
	@OneToMany(targetEntity = Armor.class, fetch = FetchType.LAZY)
	private List<Armor> armor;
}
