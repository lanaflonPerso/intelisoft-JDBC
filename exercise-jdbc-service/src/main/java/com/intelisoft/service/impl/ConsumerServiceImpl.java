package com.intelisoft.service.impl;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.intelisoft.dao.IconsumerDAO;
import com.intelisoft.dao.ImodelDAO;
import com.intelisoft.dao.mysql_impl.ConsumerDAOimpl;
import com.intelisoft.model.Consumer;
import com.intelisoft.service.IconsumerService;

public class ConsumerServiceImpl extends GenericServiceImpl<Consumer> implements IconsumerService {

	private static final Logger log = Logger.getLogger(ConsumerServiceImpl.class);

	private IconsumerDAO dao = new ConsumerDAOimpl();
	
	@Override
	public Consumer getByIdWithCars(long id) {

		Consumer model = null;

		try {

			model = dao.getByIdWithCars(id);
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
	ImodelDAO<Consumer> getDAO() {
		return dao;
	}

}
