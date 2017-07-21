package com.intelisoft.dao;

import java.sql.SQLException;

import com.intelisoft.model.Car;

public interface ICarDao extends IModelDao<Car> {

	public Car getByIdWithConsumer(long id) throws SQLException;

}
