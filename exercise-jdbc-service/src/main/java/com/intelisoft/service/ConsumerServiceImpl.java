package com.intelisoft.service;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.intelisoft.api.dao.IConsumerDao;
import com.intelisoft.api.dao.IModelDao;
import com.intelisoft.api.service.IConsumerService;
import com.intelisoft.dao.ConsumerDaoImpl;
import com.intelisoft.model.Consumer;

public class ConsumerServiceImpl extends GenericServiceImpl<Consumer> implements IConsumerService {

	private static final Logger log = Logger.getLogger(ConsumerServiceImpl.class);

	private IConsumerDao dao = new ConsumerDaoImpl();
	
	@Override
	public Consumer getByIdWithCars(long id) {

		Consumer model = null;

		try {

			model = dao.getByIdWithCars(id, connection);
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
	IModelDao<Consumer> getDAO() {
		return dao;
	}

}
