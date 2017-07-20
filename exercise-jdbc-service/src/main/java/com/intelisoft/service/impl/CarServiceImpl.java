package com.intelisoft.service.impl;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.intelisoft.dao.IcarDAO;
import com.intelisoft.dao.ImodelDAO;
import com.intelisoft.dao.mysql_impl.CarDAOimpl;
import com.intelisoft.model.Car;
import com.intelisoft.service.IcarService;

public class CarServiceImpl extends GenericServiceImpl<Car> implements IcarService {

	private static final Logger log = Logger.getLogger(CarServiceImpl.class);

	private IcarDAO dao = new CarDAOimpl();

	@Override
	public Car getByIdWithConsumer(long id) {

		Car model = null;

		try {

			model = dao.getByIdWithConsumer(id);
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
	ImodelDAO<Car> getDAO() {
		return dao;
	}

}
