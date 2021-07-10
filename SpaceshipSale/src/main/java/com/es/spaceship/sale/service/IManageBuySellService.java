package com.es.spaceship.sale.service;

import java.util.List;

import com.es.spaceship.sale.models.entity.Comments;
import com.es.spaceship.sale.models.entity.Offers;
import com.es.spaceship.sale.models.entity.SalesShip;
import com.es.spaceship.sale.models.entity.Ships;
import com.es.spaceship.sale.models.entity.User;

public interface IManageBuySellService {

	public List<Ships> getAllShips();

	public Ships getShip(Long id);

	public List<Offers> getOffers(Long typeShip);

	public Ships createShip(Ships ship);

	public User createUser(User user);

	public String createPirate(boolean isPirate, String id);

	public String createFraud(boolean isFraud, String id);

	public Offers createOffers(Offers offers);

	public SalesShip createSale(SalesShip sale);

//	public String createSubcribe(String tipoNave, String idUser, Offers of);

	public String createVote(int numVote, Long idShip);

	public int getVote(Long idShip);

	public String createComments(String Comments, Long idOffers);

	public List<Comments> getComments(Long idOffers);
}
