package br.mmmgg.tirolofficeservice.service;

import java.util.List;

import br.mmmgg.tirolofficeservice.ValidationException;

public interface IService<T> {

	T save(T entity) throws ValidationException;
	
	T getById(Integer id) throws ValidationException;
	
	List<T> getAll();
	
	void removeById(Integer id) throws ValidationException;
}
