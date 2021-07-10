package com.es.spaceship.sale.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="rol")
public class Rol {

	@Id
	private int id;
	private String name;
}
