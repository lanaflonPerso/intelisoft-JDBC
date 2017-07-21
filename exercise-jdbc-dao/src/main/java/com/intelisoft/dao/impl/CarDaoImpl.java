package com.intelisoft.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intelisoft.dao.ICarDao;
import com.intelisoft.model.Car;
import com.intelisoft.model.Consumer;

public class CarDaoImpl extends GenericDaoImpl<Car> implements ICarDao {

	private final String CREATE = "INSERT INTO car (maker, model, productionYear, color, engineType, odometer) VALUES ((?), (?), (?), (?), (?), (?))";
	private final String GET_BY_ID = "SELECT * FROM car WHERE id = (?)";
	private final String GET_ALL = "SELECT * FROM car";
	private final String UPDATE = "UPDATE car SET maker = (?), model = (?), productionYear = (?), color = (?), engineType = (?), odometer = (?) WHERE id = (?)";
	private final String DELETE = "DELETE FROM car WHERE id = (?)";
	private final String CAR_GET_BY_ID_WITH_CONSUMER = "SELECT * FROM car LEFT OUTER JOIN consumer ON car.consumer_id = consumer.id WHERE car.id = (?)";

	@Override
	public Car getByIdWithConsumer(long id) throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(CAR_GET_BY_ID_WITH_CONSUMER)) {

			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			Car car = toCarWithConsumer(rs);

			return car;
		}
	}

	private Car toCarWithConsumer(ResultSet rs) throws SQLException {

		rs.next();
		Car car = toModel(rs);
		Consumer consumer = new ConsumerDaoImpl().toModel(rs);
		car.setConsumer(consumer);

		return car;

	}

	@Override
	Car toModel(ResultSet rs) throws SQLException {

		Car car = new Car();

		car.setId(rs.getLong("id"));
		car.setMaker(rs.getString("maker"));
		car.setModel(rs.getString("model"));
		car.setProductionYear(rs.getInt("productionYear"));
		car.setColor(rs.getString("color"));
		car.setEngineType(rs.getString("engineType"));
		car.setOdometer(rs.getInt("odometer"));

		return car;
	}

	@Override
	PreparedStatement fillPStatement(PreparedStatement ps, Car model) throws SQLException {

		ps.setString(1, model.getMaker());
		ps.setString(2, model.getModel());
		ps.setInt(3, model.getProductionYear());
		ps.setString(4, model.getColor());
		ps.setString(5, model.getEngineType());
		ps.setInt(6, model.getOdometer());

		return ps;
	}

	@Override
	PreparedStatement fillId(PreparedStatement ps, Car model) throws SQLException {
		ps.setLong(7, model.getId());
		return ps;
	}

	@Override
	List<Car> toList(ResultSet rs) throws SQLException {

		List<Car> carList = new ArrayList<Car>();
		while (rs.next()) {
			Car car = toModel(rs);
			carList.add(car);
		}

		return carList;
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
