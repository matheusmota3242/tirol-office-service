package br.mmmgg.tirolofficeservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mmmgg.tirolofficeservice.model.Department;
import br.mmmgg.tirolofficeservice.model.ServiceUnit;
import br.mmmgg.tirolofficeservice.repository.DepartmentRepository;
import br.mmmgg.tirolofficeservice.repository.ServiceUnitRepository;
import br.mmmgg.tirolofficeservice.service.IService;

@Service
public class DepartmentImpl implements IService<Department> {

	@Autowired
	private DepartmentRepository repository;
	
	@Autowired
	private ServiceUnitRepository serviceUnitRepository;

	@Override
	public Department save(Department entity) {
		ServiceUnit serviceUnit = serviceUnitRepository.findById(entity.getServiceUnit().getId()).orElseThrow();
		entity.setServiceUnit(serviceUnit);
		return repository.save(entity);
	}

	@Override
	public Department getById(Integer id) {
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
