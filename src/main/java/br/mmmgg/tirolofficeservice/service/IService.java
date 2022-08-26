package br.mmmgg.tirolofficeservice.service;

import java.util.List;

public interface IService<T> {

	T save(T entity);
	
	T getById(Integer id);
	
	List<T> getAll();
	
	void removeById(Integer id) throws IllegalArgumentException;
}
