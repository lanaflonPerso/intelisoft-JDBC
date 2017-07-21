package com.intelisoft.api.service;

import com.intelisoft.model.Consumer;

public interface IConsumerService extends IModelService<Consumer> {

	public Consumer getByIdWithCars(long id);

}
