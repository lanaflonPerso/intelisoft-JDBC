package com.intelisoft.service;

import com.intelisoft.model.Consumer;

public interface IConsumerService extends IModelService<Consumer> {

	public Consumer getByIdWithCars(long id);

}
