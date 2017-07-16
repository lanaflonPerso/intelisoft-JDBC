package com.intelisoft.service.impl;

import java.sql.SQLException;

import com.intelisoft.dao.IcarDAO;
import com.intelisoft.dao.ImodelDAO;
import com.intelisoft.dao.mysql_impl.CarDAOimpl;
import com.intelisoft.model.Car;
import com.intelisoft.service.IcarService;

public class CarServiceImpl extends GenericServiceImpl<Car> implements IcarService {

	private IcarDAO dao = new CarDAOimpl();

	@Override
	public Car getByIdWithConsumer(long id) {

		Car model = null;

		try {

			model = dao.getByIdWithConsumer(id);
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
	ImodelDAO<Car> getDAO() {
		return dao;
	}

}
