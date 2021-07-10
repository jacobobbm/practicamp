package com.es.spaceship.sale.models.entity;

import com.es.spaceship.sale.util.Constants;
import lombok.Data;


import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="battles")
public class Battle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String attacker;
	private String defender;
	private Long attackerOffensivePower = 0L;
	private Long attackerDefensivePower = 0L;
	private Long defenderOffensivePower = 0L;
	private Long defenderDefensivePower = 0L;
	private Date battleDate;
	private String winner;
	private String challengeStatus;

	@Column(name = "ships")
	@JoinTable(name = "ship_battle")
	@ManyToMany(targetEntity = Ships.class, fetch = FetchType.LAZY)
	private List<Ships> shipsBattle;

	@Column(name = "shipsToTransfers")
	@JoinTable(name = "ships_to_transfer")
	@ManyToMany(targetEntity = Ships.class, fetch = FetchType.LAZY)
	private List<Ships> shipsToTransfers;

	@Column(name = "shipsToDefense")
	@JoinTable(name = "ships_defense")
	@ManyToMany(targetEntity = Ships.class, fetch = FetchType.LAZY)
	private List<Ships> shipsDefense;
}
