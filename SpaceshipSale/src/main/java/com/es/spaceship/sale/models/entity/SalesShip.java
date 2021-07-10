package com.es.spaceship.sale.models.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="sales_ship")
public class SalesShip {

	@Id
	private Long idShip;
	private LocalDate dateSale;
	private String vendedor;
	private String comprador;
	private Long precio;
	
}
