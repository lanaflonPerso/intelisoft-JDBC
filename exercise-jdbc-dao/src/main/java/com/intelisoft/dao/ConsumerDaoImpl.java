package com.intelisoft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intelisoft.dao.IConsumerDao;
import com.intelisoft.model.Car;
import com.intelisoft.model.Consumer;

public class ConsumerDaoImpl extends GenericDaoImpl<Consumer> implements IConsumerDao {

	private final String CREATE = "INSERT INTO consumer (firstName, lastName, birthDate, country, city) VALUES ((?), (?), (?), (?), (?))";
	private final String GET_BY_ID = "SELECT * FROM consumer WHERE id = (?)";
	private final String GET_ALL = "SELECT * FROM consumer";
	private final String UPDATE = "UPDATE consumer SET firstName = (?), lastName = (?), birthDate = (?), country = (?), city =(?) WHERE id = (?)";
	private final String DELETE = "DELETE FROM consumer WHERE id = (?)";
	private final String CONSUMER_GET_BY_ID_WITH_CAR = "SELECT * FROM consumer LEFT OUTER JOIN car ON car.consumer_id = consumer.id WHERE consumer.id = (?)";

	@Override
	public Consumer getByIdWithCars(long id, Connection connection) throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(CONSUMER_GET_BY_ID_WITH_CAR)) {

			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			Consumer consumer = toConsumerWithCars(rs);

			return consumer;
		}
	}

	Consumer toConsumerWithCars(ResultSet rs) throws SQLException {

		rs.next();
		Consumer consumer = toModel(rs);
		if (rs.getString("maker") != null) {
			List<Car> carList = new ArrayList<>();
			do {
				Car car = new CarDaoImpl().toModel(rs);
				carList.add(car);
			} while (rs.next());
			consumer.setCarList(carList);
		}

		return consumer;
	}

	@Override
	Consumer toModel(ResultSet rs) throws SQLException {

		Consumer consumer = new Consumer();

		consumer.setId(rs.getLong("id"));
		consumer.setFirstName(rs.getString("firstName"));
		consumer.setLastName(rs.getString("lastName"));
		consumer.setBirthDate(rs.getDate("birthDate"));
		consumer.setCountry(rs.getString("country"));
		consumer.setCity(rs.getString("city"));

		return consumer;
	}

	@Override
	PreparedStatement fillPStatement(PreparedStatement ps, Consumer model) throws SQLException {

		ps.setString(1, model.getFirstName());
		ps.setString(2, model.getLastName());
		ps.setDate(3, model.getBirthDate());
		ps.setString(4, model.getCountry());
		ps.setString(5, model.getCity());

		return ps;
	}

	@Override
	PreparedStatement fillId(PreparedStatement ps, Consumer model) throws SQLException {
		ps.setLong(6, model.getId());
		return ps;
	}

	@Override
	List<Consumer> toList(ResultSet rs) throws SQLException {

		List<Consumer> consumerList = new ArrayList<Consumer>();
		while (rs.next()) {
			Consumer consumer = toModel(rs);
			consumerList.add(consumer);
		}

		return consumerList;
	}

	@Override
	String getCREATE() {
		return CREATE;
	}

	@Override
	String getGET_BY_ID() {
		return GET_BY_ID;
	}

	@Override
	String getGET_ALL() {
		return GET_ALL;
	}

	@Override
	String getUPDATE() {
		return UPDATE;
	}

	@Override
	String getDELETE() {
		return DELETE;
	}

}