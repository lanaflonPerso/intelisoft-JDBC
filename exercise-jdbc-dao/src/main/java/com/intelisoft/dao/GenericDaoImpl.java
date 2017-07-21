package com.intelisoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.intelisoft.api.dao.IModelDao;
import com.intelisoft.model.Model;

abstract class GenericDaoImpl<T extends Model> implements IModelDao<T> {

	@Override
	public void create(T model, Connection connection) throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(getCREATE())) {

			fillPStatement(ps, model, false).executeUpdate();

		}
	}

	@Override
	public T getById(long id, Connection connection) throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(getGET_BY_ID())) {

			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();
			rs.next();

			T model = toModel(rs);

			return model;
		}
	}

	@Override
	public List<T> getAll(Connection connection) throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(getGET_ALL())) {

			ResultSet rs = ps.executeQuery();

			List<T> modelList = toList(rs);

			return modelList;
		}
	}

	@Override
	public void update(T model, Connection connection) throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(getUPDATE())) {

			fillPStatement(ps, model, true).executeUpdate();

		}
	}

	@Override
	public void delete(T model, Connection connection) throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(getDELETE())) {

			ps.setLong(1, model.getId());
			ps.executeUpdate();

		}
	}

	abstract T toModel(ResultSet rs) throws SQLException;

	abstract PreparedStatement fillPStatement(PreparedStatement ps, T model, boolean isUpdate) throws SQLException;

	abstract List<T> toList(ResultSet rs) throws SQLException;

	abstract String getCREATE();

	abstract String getGET_BY_ID();

	abstract String getGET_ALL();

	abstract String getUPDATE();

	abstract String getDELETE();

}
