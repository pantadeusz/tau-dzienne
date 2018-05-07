package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNoException;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Car;
import com.example.shdemo.domain.Person;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@Rollback
//@Commit
@Transactional(transactionManager = "txManager")
public class SellingManagerTest {

	@Autowired
	SellingManager sellingManager;

	List<Long> carIds = new LinkedList<>();
	List<Long> personIds = new LinkedList<>();

	@Before
	public void setup() {
		Car car = new Car();
		car.setMake("Kamaz");
		car.setModel("K123");
		car.setSold(false);

		carIds.add(sellingManager.addNewCar(car));
		car = new Car();
		car.setMake("Uaz");
		car.setModel("U223");
		car.setSold(false);
		carIds.add(sellingManager.addNewCar(car));

		Person person = new Person();
		person.setFirstName("Janusz");
		person.setPin("2346");
		person.setRegistrationDate(new Date());
		personIds.add(sellingManager.addClient(person));


		person = new Person();
		person.setFirstName("Grazyna");
		person.setPin("5555");
		person.setRegistrationDate(new Date());
		personIds.add(sellingManager.addClient(person));
	}

	
	@Test
	public void getPersonByPinCheck() {
		Person p = sellingManager.findClientByPin("5555");
		assertNotNull(p);
		assertEquals("Grazyna", p.getFirstName());
		assertEquals(new Date().getDay(), p.getRegistrationDate().getDay());
	}


	@Test
	public void sellCarCheck() {
		Car car = sellingManager.findCarById(carIds.get(0));
		Person person = sellingManager.findClientByPin("5555");
		assertNull(person.getCars());

		sellingManager.sellCar(person.getId(), car.getId());

		person = sellingManager.findClientByPin("5555");
		assertEquals(1, person.getCars().size());
		assertTrue(sellingManager.findCarById(carIds.get(0)).getSold());

	}


}
