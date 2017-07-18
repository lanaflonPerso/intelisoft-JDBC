package com.intelisoft.service.impl;

import java.sql.SQLException;

import com.intelisoft.dao.IconsumerDAO;
import com.intelisoft.dao.ImodelDAO;
import com.intelisoft.dao.mysql_impl.ConsumerDAOimpl;
import com.intelisoft.model.Consumer;
import com.intelisoft.service.IconsumerService;

public class ConsumerServiceImpl extends GenericServiceImpl<Consumer> implements IconsumerService {

	private IconsumerDAO dao = new ConsumerDAOimpl();
	
	@Override
	public Consumer getByIdWithCars(long id) {

		Consumer model = null;

		try {

			model = dao.getByIdWithCars(id);
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
	ImodelDAO<Consumer> getDAO() {
		return dao;
	}

}
