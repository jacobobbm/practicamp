package com.es.spaceship.sale.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.es.spaceship.sale.exception.BusinessException;
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
import com.es.spaceship.sale.service.IManageBuySellService;

@Service
public class ManageBuySellServiceImpl implements IManageBuySellService {

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

	@Autowired
	public ManageBuySellServiceImpl(ShipDao dao, OffersDao offerDao, UserDao userDao, MessageSource messageSource,
			CommentsDao commentsDao, SalesShipDao salesShipDao, WeaponDao weaponDao, DefenseSystemDao defenseSystemDao,
			ShipTypeDao shipTypeDao, ShieldDao shieldDao, ArmorDao armorDao, MockFactoryInt mock) {
		this.dao = dao;
		this.offerDao = offerDao;
		this.messageSource = messageSource;
		this.userDao = userDao;
		this.commentsDao = commentsDao;
		this.salesShipDao = salesShipDao;
		this.defenseSystemDao = defenseSystemDao;
		this.weaponDao = weaponDao;
		this.shipTypeDao = shipTypeDao;
		this.shieldDao = shieldDao;
		this.armorDao = armorDao;
		this.mock = mock;
	}

	@Override
	public List<Ships> getAllShips() {
		return dao.findAll();
	}

	@Override
	public Ships getShip(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public List<Offers> getOffers(Long typeShip) {
		Offers of = offerDao.findById(typeShip).get();
		List<Offers> list = new ArrayList<>();
		list.add(of);
		return list;
	}

	@Override
	public Ships createShip(Ships ship) {
		shipTypeDao.save(ship.getShipType().stream().map(sh -> sh).collect(Collectors.toList()).get(0));
		Ships ships = dao.save(ship);
		return ships;
	}

	@Override
	public User createUser(User user) {
		return userDao.save(user);
	}

	@Override
	public String createPirate(boolean isPirate, String id) {
		User us = userDao.findById(id).orElseThrow(() -> new BusinessException(messageSource));
		us.setPirates(isPirate);
		userDao.save(us);
		return "Create";
	}

	@Override
	public String createFraud(boolean isFraud, String id) {
		User us = userDao.findById(id).get();
		us.setIsfraud(isFraud);
		userDao.save(us);
		return "Create";
	}

	@Override
	public Offers createOffers(Offers offers) {
		return validOffers(offers);
	}

	private Offers validOffers(Offers offers) {
		if ("".equals(offers.getPackageOffers()) || null == offers.getPackageOffers() || offers.getPrice() == null
				|| offers.getPrice() == 0 ) {
			User us = userDao.findById(offers.getIdUser()).get();
			if (us.getWarning() >= 2) {
				us.setIsexception(true);
				us.setPunishment(LocalDate.now().plusDays(5));
				userDao.save(us);
			} else  {
				us.setWarning(us.getWarning() + 1);
				userDao.save(us);
			}
			if( us.getPunishment().equals(LocalDate.now())) {
				throw new BusinessException(messageSource);
			}
		} else  {
			return offerDao.save(offers);
		}
		return null;
	}

	@Override
	public SalesShip createSale(SalesShip sale) {
		return salesShipDao.save(sale);
	}

	@Override
	public String createVote(int numVote, Long idShip) {
		Ships sh = dao.findById(idShip).get();
		sh.setVote(numVote);
		dao.save(sh);
		return "Create";
	}

	@Override
	public int getVote(Long idShip) {
		return dao.findById(idShip).orElseThrow(() -> new BusinessException(messageSource)).getVote();
	}

	@Override
	public String createComments(String comments, Long idOffers) {
		Comments com = new Comments();
		com.setComments(comments);
		com.setIdOffers(idOffers);
		commentsDao.save(com);
		return "Create";
	}

	@Override
	public List<Comments> getComments(Long idOffers) {
		return commentsDao.findAll();
	}

}
