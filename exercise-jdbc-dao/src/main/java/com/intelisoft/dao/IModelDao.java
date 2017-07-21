package com.intelisoft.dao;

import java.sql.SQLException;
import java.util.List;

import com.intelisoft.model.Model;

public interface IModelDao<T extends Model> {

	public void create(T model) throws SQLException;

	public T getById(long id) throws SQLException;

	public List<T> getAll() throws SQLException;

	public void update(T model) throws SQLException;

	public void delete(T model) throws SQLException;

}
