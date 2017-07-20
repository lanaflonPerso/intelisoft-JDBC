package com.intelisoft.service;

import com.intelisoft.model.Car;

public interface IcarService extends ImodelService<Car> {

	public Car getByIdWithConsumer(long id);

}
