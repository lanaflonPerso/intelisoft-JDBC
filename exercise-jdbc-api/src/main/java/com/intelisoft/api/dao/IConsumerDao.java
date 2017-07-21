package com.intelisoft.api.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.intelisoft.model.Consumer;

public interface IConsumerDao extends IModelDao<Consumer> {

	public Consumer getByIdWithCars(long id, Connection connection) throws SQLException;

}
