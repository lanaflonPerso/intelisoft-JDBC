package com.intelisoft.service;

import com.intelisoft.model.Car;

public interface ICarService extends IModelService<Car> {

	public Car getByIdWithConsumer(long id);

}
