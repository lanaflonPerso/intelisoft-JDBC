package com.intelisoft.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.intelisoft.dao.ImodelDAO;
import com.intelisoft.model.Model;
import com.intelisoft.service.ImodelService;
import com.intelisoft.util.ConnectorDB;

abstract class GenericServiceImpl<T extends Model> implements ImodelService<T> {

	Connection connection = ConnectorDB.getConnectorDBInstance().getConnection();

	@Override
	public void create(T model) {

		try {
			
			getDAO().create(model);
			connection.commit();

		} catch (SQLException e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		}
	}

	@Override
	public T getById(long id) {

		T model = null;

		try {

			model = getDAO().getById(id);
			connection.commit();

		} catch (SQLException e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		}
		return model;
	}

	@Override
	public List<T> getAll() {

		List<T> modelList = null;

		try {

			modelList = getDAO().getAll();
			connection.commit();

		} catch (SQLException e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		}
		return modelList;
	}

	@Override
	public void update(T model) {

		try {

			getDAO().update(model);
			connection.commit();

		} catch (SQLException e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		}
	}

	@Override
	public void delete(T model) {

		try {

			getDAO().delete(model);
			connection.commit();

		} catch (SQLException e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		}
	}

	abstract ImodelDAO<T> getDAO();
}
