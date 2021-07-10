package com.es.spaceship.sale.controller;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.es.spaceship.sale.factory.MockFactory;
import com.es.spaceship.sale.factory.MockFactoryInt;
import com.es.spaceship.sale.models.entity.Comments;
import com.es.spaceship.sale.models.entity.Ships;
import com.es.spaceship.sale.service.IManageBuySellService;

@ExtendWith(MockitoExtension.class)
class ManageBuySellControllerTest {

	private IManageBuySellService service;
	private ManageBuySellController controller;
	private MockFactoryInt mock;
	
	@BeforeEach
	void setUp() throws Exception {
		service = Mockito.mock(IManageBuySellService.class);
		mock = Mockito.mock(MockFactoryInt.class);
		controller = new ManageBuySellController(service, mock);
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKGetAllShip() {
		Mockito.when(service.getAllShips()).thenReturn(MockFactory.generateListShips());
		List<Ships> ship = controller.getAllShips();
		Assertions.assertEquals(ship, MockFactory.generateListShips());
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKGetShip() {
		Mockito.when(service.getShip(1000L)).thenReturn(MockFactory.generateShips());
		ResponseEntity<?> response = controller.getShip(1000L);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKGetOffers() {
		Mockito.when(service.getOffers(100L)).thenReturn(MockFactory.generateListOffers());
		ResponseEntity<?> response = controller.getOffers(100L);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreateShip() {
		Mockito.when(service.createShip(MockFactory.generateShips())).thenReturn(MockFactory.generateShips());
		ResponseEntity<?> response = controller.createShip(MockFactory.generateShips());
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreateUser() {
		Mockito.when(service.createUser(MockFactory.generateUser())).thenReturn(MockFactory.generateUser());
		ResponseEntity<?> response = controller.createUser(MockFactory.generateUser());
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreatePirate() {
		Mockito.when(service.createPirate(true, "14587965254")).thenReturn("ok");
		ResponseEntity<?> response = controller.createPirate(true, "14587965254");
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreateFraud() {
		Mockito.when(service.createFraud(true, "14587965254")).thenReturn("ok");
		ResponseEntity<?> response = controller.createFraud(true, "14587965254");
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}
	
	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreateOffers() {
		Mockito.when(service.createOffers(MockFactory.generateOffers())).thenReturn(MockFactory.generateOffers());
		ResponseEntity<?> response = controller.createOffers(MockFactory.generateOffers());
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreateSale() {
		Mockito.when(service.createSale(MockFactory.generateSalesShip())).thenReturn(MockFactory.generateSalesShip());
		ResponseEntity<?> response = controller.createSale(MockFactory.generateSalesShip());
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

//	@Test
//	public void itShouldReturnTheServiceValueWithresponseOKCreateSubcribe() {
//		Mockito.when(service.createSubcribe("1000", "1000", MockFactory.generateOffers())).thenReturn("ok");
//		ResponseEntity<?> response = controller.createSubcribe("1000", "1000", MockFactory.generateOffers());
//		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
//	}
	
	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreateVote() {
		Mockito.when(service.createVote(5, 1000L)).thenReturn("ok");
		ResponseEntity<?> response = controller.createVote(5, 1000L);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKGetVote() {
		Mockito.when(service.getVote(1000L)).thenReturn(5);
		ResponseEntity<?> response = controller.getVote(1000L);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKCreateComments() {
		Mockito.when(service.createComments("comentario", 1000L)).thenReturn("ok");
		ResponseEntity<?> response = controller.createComments("comentario", 1000L);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void itShouldReturnTheServiceValueWithresponseOKGetComments() {
		Mockito.when(service.getComments(1000L)).thenReturn(MockFactory.generateListComments());
		List<Comments> response = controller.getComments(1000L);
		Assertions.assertEquals(response, MockFactory.generateListComments());
	}

}
