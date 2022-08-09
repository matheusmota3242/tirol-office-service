package br.mmmgg.tirolofficeservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.mmmgg.tirolofficeservice.model.Department;
import br.mmmgg.tirolofficeservice.repository.DepartmentRepository;
import br.mmmgg.tirolofficeservice.service.IService;

@Service
public class DepartmentImpl implements IService<Department> {

	private DepartmentRepository repository;

	@Override
	public Department save(Department entity) {
		return repository.save(entity);
	}

	@Override
	public Department getById(Integer id) throws NoSuchMethodException {
		return repository.findById(id).orElseThrow();
	}

	@Override
	public List<Department> getAll() {
		return repository.findAll();
	}

	@Override
	public void removeById(Integer id) {
		repository.deleteById(id);
	}
}
