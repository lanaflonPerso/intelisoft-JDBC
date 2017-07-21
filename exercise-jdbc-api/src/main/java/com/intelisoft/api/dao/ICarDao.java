package com.intelisoft.api.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.intelisoft.model.Car;

public interface ICarDao extends IModelDao<Car> {

	public Car getByIdWithConsumer(long id, Connection connection) throws SQLException;

}
