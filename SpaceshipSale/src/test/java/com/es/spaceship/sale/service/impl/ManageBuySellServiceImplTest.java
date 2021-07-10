package com.es.spaceship.sale.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;

import com.es.spaceship.sale.factory.MockFactory;
import com.es.spaceship.sale.factory.MockFactoryInt;
import com.es.spaceship.sale.models.dao.ArmorDao;
import com.es.spaceship.sale.models.dao.CommentsDao;
import com.es.spaceship.sale.models.dao.DefenseSystemDao;
import com.es.spaceship.sale.models.dao.OffersDao;
import com.es.spaceship.sale.models.dao.SalesShipDao;
import com.es.spaceship.sale.models.dao.ShieldDao;
import com.es.spaceship.sale.models.dao.ShipDao;
import com.es.spaceship.sale.models.dao.ShipTypeDao;
import com.es.spaceship.sale.models.dao.UserDao;
import com.es.spaceship.sale.models.dao.WeaponDao;
import com.es.spaceship.sale.models.entity.Comments;
import com.es.spaceship.sale.models.entity.Offers;
import com.es.spaceship.sale.models.entity.SalesShip;
import com.es.spaceship.sale.models.entity.Ships;
import com.es.spaceship.sale.models.entity.User;

class ManageBuySellServiceImplTest {

	private ShipDao dao;
	private OffersDao offerDao;
	private UserDao userDao;
	private CommentsDao commentsDao;
	private SalesShipDao salesShipDao;
	private MessageSource messageSource;
	private DefenseSystemDao defenseSystemDao;
	private WeaponDao weaponDao;
	private ShipTypeDao shipTypeDao;
	private ShieldDao shieldDao;
	private ArmorDao armorDao;
	private MockFactoryInt mock;
	private ManageBuySellServiceImpl service;

	@BeforeEach
	public void setUp() {
		dao = Mockito.mock(ShipDao.class);
		offerDao = Mockito.mock(OffersDao.class);
		userDao = Mockito.mock(UserDao.class);
		commentsDao = Mockito.mock(CommentsDao.class);
		salesShipDao = Mockito.mock(SalesShipDao.class);
		mock = Mockito.mock(MockFactoryInt.class);
		messageSource = Mockito.mock(MessageSource.class);
		defenseSystemDao = Mockito.mock(DefenseSystemDao.class);
		weaponDao = Mockito.mock(WeaponDao.class);
		shipTypeDao = Mockito.mock(ShipTypeDao.class);
		shieldDao = Mockito.mock(ShieldDao.class);
		armorDao = Mockito.mock(ArmorDao.class);
		service = new ManageBuySellServiceImpl(dao, offerDao, userDao, messageSource, commentsDao, salesShipDao,
				weaponDao, defenseSystemDao, shipTypeDao, shieldDao, armorDao, mock);
	}

	@Test
	public void itShouldReturnTheServiceValueWithResponseGetAllShips() {
		Mockito.when(service.getAllShips()).thenReturn(MockFactory.generateListShips());
		List<Ships> response = service.getAllShips();
		assertEquals(response, MockFactory.generateListShips());
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKGetShip() {
		Mockito.when(dao.findById(1000L)).thenReturn(Optional.of(MockFactory.generateShips()));
		Ships response = service.getShip(1000L);
		Assertions.assertEquals(response, MockFactory.generateShips());
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKGetOffers() {
		Mockito.when(offerDao.findById(100L)).thenReturn(Optional.of(MockFactory.generateOffers()));
		List<Offers> response = service.getOffers(100L);
		Assertions.assertEquals(response, MockFactory.generateListOffers());
	}
	
	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreateShip() {
		Mockito.when(service.createShip(MockFactory.generateShips())).thenReturn(MockFactory.generateShips());
		Ships response = service.createShip(MockFactory.generateShips());
		Assertions.assertEquals(response, MockFactory.generateShips());
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreateUser() {
		Mockito.when(service.createUser(MockFactory.generateUser())).thenReturn(MockFactory.generateUser());
		User response = service.createUser(MockFactory.generateUser());
		Assertions.assertEquals(response, MockFactory.generateUser());
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreatePirate() {
		Mockito.when(userDao.findById("14587965254")).thenReturn(Optional.of(MockFactory.generateUser()));
		String response = service.createPirate(true, "14587965254");
		Assertions.assertEquals(response, "Create");
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreateFraud() {
		Mockito.when(userDao.findById("14587965254")).thenReturn(Optional.of(MockFactory.generateUser()));
		String response = service.createFraud(true, "14587965254");
		Assertions.assertEquals(response, "Create");
	}
	
	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreateOffers() {
		Mockito.when(service.createOffers(MockFactory.generateOffers())).thenReturn(MockFactory.generateOffers());
		Offers response = service.createOffers(MockFactory.generateOffers());
		Assertions.assertEquals(response, null);
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreateSale() {
		Mockito.when(service.createSale(MockFactory.generateSalesShip())).thenReturn(MockFactory.generateSalesShip());
		SalesShip response = service.createSale(MockFactory.generateSalesShip());
		Assertions.assertEquals(response, MockFactory.generateSalesShip());
	}
	
	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreateVote() {
		Mockito.when(dao.findById(1000L)).thenReturn(Optional.of(MockFactory.generateShips()));
		String response = service.createVote(5, 1000L);
		Assertions.assertEquals(response, "Create");
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKGetVote() {
		Mockito.when(dao.findById(1000L)).thenReturn(Optional.of(MockFactory.generateShips()));
		int response = service.getVote(1000L);
		Assertions.assertEquals(response, 5);
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreateComments() {
		Mockito.when(commentsDao.save(MockFactory.generateComments())).thenReturn(MockFactory.generateComments());
		String response = service.createComments("comentario", 1000L);
		Assertions.assertEquals(response, "Create");
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKGetComments() {
		Mockito.when(service.getComments(1000L)).thenReturn(MockFactory.generateListComments());
		List<Comments> response = service.getComments(1000L);
		Assertions.assertEquals(response, MockFactory.generateListComments());
	}

}
