package com.intelisoft.service.impl;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.intelisoft.dao.ICarDao;
import com.intelisoft.dao.IModelDao;
import com.intelisoft.dao.impl.CarDaoImpl;
import com.intelisoft.model.Car;
import com.intelisoft.service.ICarService;

public class CarServiceImpl extends GenericServiceImpl<Car> implements ICarService {

	private static final Logger log = Logger.getLogger(CarServiceImpl.class);

	private ICarDao dao = new CarDaoImpl();

	@Override
	public Car getByIdWithConsumer(long id) {

		Car model = null;

		try {

			model = dao.getByIdWithConsumer(id, connection);
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
	IModelDao<Car> getDAO() {
		return dao;
	}

}
