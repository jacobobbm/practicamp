package com.es.spaceship.sale.factory;

import java.util.ArrayList;
import java.util.List;

import com.es.spaceship.sale.models.entity.Armor;
import com.es.spaceship.sale.models.entity.Comments;
import com.es.spaceship.sale.models.entity.DefenseSystem;
import com.es.spaceship.sale.models.entity.Motor;
import com.es.spaceship.sale.models.entity.Offers;
import com.es.spaceship.sale.models.entity.SalesShip;
import com.es.spaceship.sale.models.entity.Shield;
import com.es.spaceship.sale.models.entity.ShipType;
import com.es.spaceship.sale.models.entity.Ships;
import com.es.spaceship.sale.models.entity.User;
import com.es.spaceship.sale.models.entity.Weapon;

public class MockFactory {

	public static List<Ships> generateListShips() {
		Ships ships = new Ships();
		ships.setId(100L);
		ships.setAmountCrew(10000L);
		ships.setOwner("Langris");
		ships.setPrice(10000000L);
		ships.setQuantityPropellers(4);
		ships.setRegistryNumber("A657895");
		ships.setShipType(createShiptype());
		ships.setTypeMotor(createTypeMotor());
		ships.setVote(5);
		List<Ships> list = new ArrayList<>();
		list.add(ships);
		return list;
	}

	public static Ships generateShips() {
		Ships ships = new Ships();
		ships.setId(100L);
		ships.setAmountCrew(10000L);
		ships.setOwner("Langris");
		ships.setPrice(10000000L);
		ships.setQuantityPropellers(4);
		ships.setRegistryNumber("A657895");
		ships.setShipType(createShiptype());
		ships.setTypeMotor(createTypeMotor());
		ships.setVote(5);
		return ships;
	}
	
	private static List<Motor> createTypeMotor() {
		Motor motor = new Motor();
		motor.setId(1L);
		motor.setMaximaVelocidad("100000000000");
		motor.setTypePropulcion("antimateria");
		List<Motor> listMo = new ArrayList<>();
		listMo.add(motor);
		return listMo;
	}

	private static List<ShipType> createShiptype() {
		ShipType shty = new ShipType();
		shty.setId(1L);
		shty.setHangares(100L);
		shty.setMaximumLoadCapacity(1000000L);
		shty.setIscaza(true);
		shty.setNameShipType("crucero espacial");
		shty.setNumberMaxPassenger(1000L);
		shty.setSetWeapons(createWeapon());
		shty.setSystemDefence(createSystemDefence());
		List<ShipType> list = new ArrayList<>();
		list.add(shty);
		return list;
	}

	private static List<DefenseSystem> createSystemDefence() {
		DefenseSystem defense = new DefenseSystem();
		defense.setId(100L);
		defense.setArmor(createArmor());
		defense.setShield(createShield());
		return null;
	}

	private static List<Shield> createShield() {
		Shield sh = new Shield();
		sh.setId(100L);
		sh.setAbsorbedDamage(100000000L);
		sh.setName("titanium");
		sh.setQuantityEnergy(1000000000L);
		List<Shield> list = new ArrayList<Shield>();
		list.add(sh);
		return list;
	}

	private static List<Armor> createArmor() {
		Armor armor = new Armor();
		armor.setId(1000L);
		armor.setAbsorbedDamage(100000000000L);
		armor.setMaterialName("bibranium");
		armor.setWeight(1000000L);
		List<Armor> list = new ArrayList<>();
		list.add(armor);
		return list;
	}

	private static List<Weapon> createWeapon() {
		Weapon we = new Weapon();
		we.setId(100L);
		we.setNameWeapon("laser fotonico");
		we.setPower(1000000000L);
		List<Weapon> list = new ArrayList<>();
		list.add(we);
		return list;
	}
	
	
	public static User generateUser() {
		User user = new User();
		user.setId("14587965254");
		return user;
	} 
	
	public static Offers generateOffers() {
		Offers of = new Offers();
		of.setId(1000L);
		of.setDateLimitOffers("07/08/2020");
		of.setPackageOffers("CAZAS");
		of.setPrice(25000000L);
		of.setSubcribe("1000");
		of.setTypeShip("CAZAS");
		of.setPackageOffers("100, 200");
		return of;
	}
	
	public static SalesShip generateSalesShip() {
		SalesShip ss = new SalesShip();
		ss.setIdShip(1000L);
		return ss;
	}
	
	public static List<Offers> generateListOffers() {
		Offers of = new Offers();
		of.setId(1000L);
		of.setDateLimitOffers("07/08/2020");
		of.setPackageOffers("CAZAS");
		of.setPrice(25000000L);
		of.setSubcribe("1000");
		of.setTypeShip("CAZAS");
		of.setPackageOffers("100, 200");
		List<Offers> list = new ArrayList<>();
		list.add(of);
		return list;
	}
	
	public static List<Comments> generateListComments(){
		Comments c = new Comments();
		c.setId(1000L);
		c.setComments("comentarios");
		c.setIdOffers(100L);
		List<Comments> list = new ArrayList<>();
		list.add(c);
		return list;
	}
	
	public static Comments generateComments(){
		Comments c = new Comments();
		c.setId(1000L);
		c.setComments("comentarios");
		c.setIdOffers(100L);
		return c;
	}
}
