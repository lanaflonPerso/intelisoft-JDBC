package com.intelisoft.dao.mysql_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.intelisoft.dao.ImodelDAO;
import com.intelisoft.model.Model;
import com.intelisoft.util.ConnectorDB;

abstract class GenericDAO<T extends Model> implements ImodelDAO<T> {

	Connection connection = ConnectorDB.getConnectorDBInstance().getConnection();

	@Override
	public void create(T model) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(getCREATE());
		fillPStatement(ps, model).executeUpdate();

		ps.close();
	}

	@Override
	public T getById(long id) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(getGET_BY_ID());
		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();
		rs.next();

		T model = toModel(rs);

		ps.close();

		return model;
	}

	@Override
	public List<T> getAll() throws SQLException {

		PreparedStatement ps = connection.prepareStatement(getGET_ALL());
		ResultSet rs = ps.executeQuery();

		List<T> modelList = toList(rs);

		ps.close();

		return modelList;
	}

	@Override
	public void update(T model) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(getUPDATE());
		ps = fillPStatement(ps, model);
		ps = fillId(ps, model);

		ps.executeUpdate();

		ps.close();
	}

	@Override
	public void delete(T model) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(getDELETE());
		ps.setLong(1, model.getId());
		ps.executeUpdate();

		ps.close();
	}

	abstract T toModel(ResultSet rs) throws SQLException;

	abstract PreparedStatement fillPStatement(PreparedStatement ps, T model) throws SQLException;

	abstract List<T> toList(ResultSet rs) throws SQLException;

	abstract PreparedStatement fillId(PreparedStatement ps, T model) throws SQLException;

	abstract String getCREATE();

	abstract String getGET_BY_ID();

	abstract String getGET_ALL();

	abstract String getUPDATE();

	abstract String getDELETE();

}
