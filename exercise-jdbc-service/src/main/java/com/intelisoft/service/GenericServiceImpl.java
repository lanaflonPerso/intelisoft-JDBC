package com.intelisoft.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.intelisoft.api.dao.IModelDao;
import com.intelisoft.api.service.IModelService;
import com.intelisoft.model.Model;
import com.intelisoft.util.ConnectorDB;

abstract class GenericServiceImpl<T extends Model> implements IModelService<T> {

	private static final Logger log = Logger.getLogger(GenericServiceImpl.class);

	Connection connection = ConnectorDB.getConnectorDBInstance().getConnection();

	@Override
	public void create(T model) {

		try {
			
			getDAO().create(model, connection);
			connection.commit();

		} catch (SQLException e) {

			log.error(e.getMessage(), e);

			try {
				connection.rollback();
			} catch (SQLException e1) {
				log.error(e1.getMessage(), e1);
			}
		}
	}

	@Override
	public T getById(long id) {

		T model = null;

		try {

			model = getDAO().getById(id, connection);
			connection.commit();

		} catch (SQLException e) {

			log.error(e.getMessage(), e);

			try {
				connection.rollback();
			} catch (SQLException e1) {
				log.error(e1.getMessage(), e1);
			}
		}
		return model;
	}

	@Override
	public List<T> getAll() {

		List<T> modelList = null;

		try {

			modelList = getDAO().getAll(connection);
			connection.commit();

		} catch (SQLException e) {

			log.error(e.getMessage(), e);

			try {
				connection.rollback();
			} catch (SQLException e1) {
				log.error(e1.getMessage(), e1);
			}
		}
		return modelList;
	}

	@Override
	public void update(T model) {

		try {

			getDAO().update(model, connection);
			connection.commit();

		} catch (SQLException e) {

			log.error(e.getMessage(), e);

			try {
				connection.rollback();
			} catch (SQLException e1) {
				log.error(e1.getMessage(), e1);
			}
		}
	}

	@Override
	public void delete(T model) {

		try {

			getDAO().delete(model, connection);
			connection.commit();

		} catch (SQLException e) {

			log.error(e.getMessage(), e);

			try {
				connection.rollback();
			} catch (SQLException e1) {
				log.error(e1.getMessage(), e1);
			}
		}
	}

	abstract IModelDao<T> getDAO();
}
