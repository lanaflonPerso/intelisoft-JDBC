package com.intelisoft.api.service;

import java.util.List;

import com.intelisoft.model.Model;

public interface IModelService<T extends Model> {

	public void create(T model);

	public T getById(long id);

	public List<T> getAll();

	public void update(T model);

	public void delete(T model);

}
