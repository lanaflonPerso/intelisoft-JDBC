package com.intelisoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.intelisoft.api.dao.IModelDao;
import com.intelisoft.model.Model;

abstract class GenericDaoImpl<T extends Model> implements IModelDao<T> {

	private static final Logger log = Logger.getLogger(GenericDaoImpl.class);

	private final String CREATE;
	private final String GET_BY_ID;
	private final String GET_ALL;
	private final String UPDATE;
	private final String DELETE;

	protected GenericDaoImpl(String CREATE, String GET_BY_ID, String GET_ALL, String UPDATE, String DELETE) {
		this.CREATE = CREATE;
		this.GET_BY_ID = GET_BY_ID;
		this.GET_ALL = GET_ALL;
		this.UPDATE = UPDATE;
		this.DELETE = DELETE;
	}

	@Override
	public void create(T model, Connection connection) throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(CREATE)) {

			fillPStatement(ps, model, false).executeUpdate();

		} catch (SQLException e) {
			log.warn("Exceplion at create method.", e);
		}
	}

	@Override
	public T getById(long id, Connection connection) throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(GET_BY_ID)) {

			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();
			rs.next();

			T model = toModel(rs);

			return model;

		} catch (SQLException e) {
			log.warn("Exception at getById method.", e);
			return null;
		}
	}

	@Override
	public List<T> getAll(Connection connection) throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(GET_ALL)) {

			ResultSet rs = ps.executeQuery();

			List<T> modelList = toList(rs);

			return modelList;
		} catch (SQLException e) {
			log.warn("Exception at getAll method.", e);
			return null;
		}
	}

	@Override
	public void update(T model, Connection connection) throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {

			fillPStatement(ps, model, true).executeUpdate();

		} catch (SQLException e) {
			log.warn("Exception at update method.", e);
		}
	}

	@Override
	public void delete(T model, Connection connection) throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(DELETE)) {

			ps.setLong(1, model.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			log.warn("Exception at delete method.", e);
		}
	}

	abstract T toModel(ResultSet rs) throws SQLException;

	abstract PreparedStatement fillPStatement(PreparedStatement ps, T model, boolean isUpdate) throws SQLException;

	abstract List<T> toList(ResultSet rs) throws SQLException;
}
