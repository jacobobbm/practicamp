package com.es.spaceship.sale.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.es.spaceship.sale.factory.MockFactoryInt;
import com.es.spaceship.sale.models.entity.Comments;
import com.es.spaceship.sale.models.entity.Offers;
import com.es.spaceship.sale.models.entity.SalesShip;
import com.es.spaceship.sale.models.entity.Ships;
import com.es.spaceship.sale.models.entity.User;
import com.es.spaceship.sale.service.IManageBuySellService;
@Api
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sale/ship/management")
public class ManageBuySellController {

	
	private IManageBuySellService service;
	private MockFactoryInt mock;
	
	@Autowired
	public ManageBuySellController(IManageBuySellService service, MockFactoryInt mock) {
		this.service = service;
		this.mock = mock;
	}

	@GetMapping("/get/all/ships")
	public List<Ships> getAllShips() {
		List<Ships> list = service.getAllShips();
		return list;
	}

	@GetMapping("/get/ship/{id}")
	public ResponseEntity<?> getShip(@PathVariable("id") Long id) {
		return new ResponseEntity<Ships>(service.getShip(id), HttpStatus.OK);
	}

	@GetMapping("/get/offers")
	public ResponseEntity<?> getOffers(@RequestParam("typeShip") Long typeShip) {
		return new ResponseEntity<List<Offers>>(service.getOffers(typeShip), HttpStatus.OK);
	}

	@PostMapping("/create/ship")
	public ResponseEntity<?> createShip(@RequestBody Ships ship) {
		Map<String, Object> response = new HashMap<>();
		response.put("Respuesta", service.createShip(ship));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/create/user")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		Map<String, Object> response = new HashMap<>();
		response.put("Respuesta", service.createUser(user));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/create/Pirate")
	public ResponseEntity<?> createPirate(@RequestParam("isPirate") boolean isPirate, @RequestParam("id") String id) {
		Map<String, Object> response = new HashMap<>();
		service.createPirate(isPirate, id);
		response.put("Respuesta", "Create");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/create/fraud")
	public ResponseEntity<?> createFraud(@RequestParam("isFraud") boolean isFraud, @RequestParam("id") String id) {
		Map<String, Object> response = new HashMap<>();
		service.createFraud(isFraud, id);
		response.put("Respuesta", "Create");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/create/offers")
	public ResponseEntity<?> createOffers(@RequestBody Offers offers) {
		Map<String, Object> response = new HashMap<>();
		response.put("Respuesta", service.createOffers(offers));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/create/sale")
	public ResponseEntity<?> createSale(@RequestBody SalesShip sale) {
		Map<String, Object> response = new HashMap<>();
		response.put("Respuesta", service.createSale(sale));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/votacion/nave") 
	public ResponseEntity<?> createVote(@RequestParam("numVote") int numVote,
			@RequestParam("idShip") Long idShip) {
		Map<String, Object> response = new HashMap<>();
		response.put("Respuesta", service.createVote(numVote, idShip));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/get/votaciones/ofertas/naves")
	public ResponseEntity<?> getVote(@RequestParam("idShip") Long idShip) {
		Map<String, Object> response = new HashMap<>();
		response.put("Respuesta", service.getVote(idShip));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/comments/ship")
	public ResponseEntity<?> createComments(@RequestParam("comments") String comments,
			@RequestParam("idOffers") Long idOffers) {
		Map<String, Object> response = new HashMap<>();
		response.put("Respuesta", service.createComments(comments, idOffers));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/get/comments/offers/ship")
	public List<Comments> getComments(@RequestParam("idOffers") Long idOffers) {
		return service.getComments(idOffers);
	}
}
