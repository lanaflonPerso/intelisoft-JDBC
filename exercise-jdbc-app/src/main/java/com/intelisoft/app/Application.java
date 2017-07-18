package com.intelisoft.app;

import java.sql.SQLException;

import com.intelisoft.model.Car;
import com.intelisoft.model.Consumer;
import com.intelisoft.service.IcarService;
import com.intelisoft.service.IconsumerService;
import com.intelisoft.service.impl.CarServiceImpl;
import com.intelisoft.service.impl.ConsumerServiceImpl;

public class Application {

	static IcarService carService = new CarServiceImpl();
	static IconsumerService consumerService = new ConsumerServiceImpl();

	static long id = 1;

	static String divider = "-----------------------------------------------\n";

	public static void main(String[] args) {

		System.out.println("[START]\n");

		try {

			update();

			getById();

			create();

			getAll();

			getByIdWith();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("[END]");
	}

	public static void create() throws SQLException {

		System.out.println("CREATE" + divider);
		carService.create(carService.getByIdWithConsumer(id));

		System.out.println(divider);

		consumerService.create(consumerService.getById(id));
	}

	public static void getById() throws SQLException {

		System.out.println("GET_BY_ID" + divider);
		System.out.println(carService.getById(id));

		System.out.println(divider);

		System.out.println(consumerService.getById(id));

	}

	public static void update() throws SQLException {

		System.out.println("UPDATE" + divider);
		Car car = carService.getById(id);

		System.out.println(car);

		car.setEngineType("ELECTRO");
		carService.update(car);

		System.out.println(divider);

		Consumer consumer = consumerService.getById(id);

		System.out.println(consumer);

		consumer.setLastName("ПУТИН");
		consumerService.update(consumer);

	}

	public static void getAll() throws SQLException {

		System.out.println("GET_ALL" + divider);
		System.out.println(carService.getAll());

		System.out.println(divider);

		System.out.println(consumerService.getAll());

	}

	public static void getByIdWith() throws SQLException {

		System.out.println("GET_BY_ID_WITH" + divider);
		System.out.println(carService.getByIdWithConsumer(id));

		System.out.println(divider);

		System.out.println(consumerService.getByIdWithCars(id));

		System.out.println(divider);

	}
}