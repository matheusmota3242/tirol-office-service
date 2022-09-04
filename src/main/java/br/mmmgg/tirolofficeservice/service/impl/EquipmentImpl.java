package br.mmmgg.tirolofficeservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mmmgg.tirolofficeservice.ValidationException;
import br.mmmgg.tirolofficeservice.model.Equipment;
import br.mmmgg.tirolofficeservice.repository.EquipmentRepository;
import br.mmmgg.tirolofficeservice.service.IService;
import br.mmmgg.tirolofficeservice.util.ErrorMessageUtil;

@Service
public class EquipmentImpl implements IService<Equipment> {

	@Autowired
	private EquipmentRepository repository;
	
	@Override
	public Equipment save(Equipment entity) throws ValidationException {
		try {
			return repository.save(entity);
		} catch (Exception e) {
			throw new ValidationException(e, ErrorMessageUtil.SAVE_REGISTER);
		}
	}

	@Override
	public Equipment getById(Integer id) throws ValidationException {
		return repository.findById(id).orElseThrow(() -> new ValidationException(ErrorMessageUtil.INEXISTENT_REGISTER));
	}

	@Override
	public List<Equipment> getAll() {
		return repository.findAll();
	}

	@Override
	public void removeById(Integer id) throws ValidationException {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new ValidationException(e, ErrorMessageUtil.REMOVE_REGISTER);
		}
	}

}
