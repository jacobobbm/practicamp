package com.es.spaceship.sale.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="comments")
public class Comments {

	@Id
	private Long id;
	private String comments;
	private Long idOffers;
}
