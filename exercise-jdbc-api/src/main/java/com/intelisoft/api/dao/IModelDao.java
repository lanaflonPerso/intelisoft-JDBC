package com.intelisoft.api.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.intelisoft.model.Model;

public interface IModelDao<T extends Model> {

	public void create(T model, Connection connection) throws SQLException;

	public T getById(long id, Connection connection) throws SQLException;

	public List<T> getAll(Connection connection) throws SQLException;

	public void update(T model, Connection connection) throws SQLException;

	public void delete(T model, Connection connection) throws SQLException;

}
