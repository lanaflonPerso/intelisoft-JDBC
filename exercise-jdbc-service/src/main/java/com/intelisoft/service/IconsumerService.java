package com.intelisoft.service;

import com.intelisoft.model.Consumer;

public interface IconsumerService extends ImodelService<Consumer> {

	public Consumer getByIdWithCars(long id);

}
