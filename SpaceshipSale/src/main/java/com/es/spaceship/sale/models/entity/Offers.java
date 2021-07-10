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
@Table(name="offers")
public class Offers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "package_offers")
	private String packageOffers;
	
	@Column(name = "type_ship")
	private String typeShip;
	
	@Column(name = "date_limit_offers")
	private String dateLimitOffers;
	
	private Long price;
	
	private String subcribe;
	
	private String idUser;
}
