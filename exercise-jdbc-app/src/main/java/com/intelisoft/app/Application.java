package com.intelisoft.app;

import org.apache.log4j.Logger;

import com.intelisoft.api.service.ICarService;
import com.intelisoft.api.service.IConsumerService;
import com.intelisoft.model.Car;
import com.intelisoft.model.Consumer;
import com.intelisoft.service.CarServiceImpl;
import com.intelisoft.service.ConsumerServiceImpl;

public class Application {

	private static final Logger log = Logger.getLogger(Application.class);

	static ICarService carService = new CarServiceImpl();
	static IConsumerService consumerService = new ConsumerServiceImpl();

	static long id = 7;

	public static void main(String[] args) {

		update();

		getById();

		create();

		getAll();

		getByIdWith();
	}

	public static void create() {

		log.info("CREATE");
		carService.create(carService.getByIdWithConsumer(id));

		consumerService.create(consumerService.getById(id));
	}

	public static void getById() {

		log.info("GET_BY_ID");
		log.info(carService.getById(id));

		log.info(consumerService.getById(id));

	}

	public static void update() {

		log.info("UPDATE");
		Car car = carService.getById(id);

		log.info(car);

		car.setEngineType("ELECTRO");
		carService.update(car);

		Consumer consumer = consumerService.getById(id);

		log.info(consumer);

		consumer.setLastName("ПУТИН");
		consumerService.update(consumer);

	}

	public static void getAll() {

		log.info("GET_ALL");
		log.info(carService.getAll());

		log.info(consumerService.getAll());

	}

	public static void getByIdWith() {

		log.info("GET_BY_ID_WITH");
		log.info(carService.getByIdWithConsumer(id));

		log.info(consumerService.getByIdWithCars(id));
	}
}