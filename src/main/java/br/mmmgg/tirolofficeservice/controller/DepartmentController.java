package br.mmmgg.tirolofficeservice.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mmmgg.tirolofficeservice.model.Department;
import br.mmmgg.tirolofficeservice.service.impl.DepartmentImpl;
import br.mmmgg.tirolofficeservice.util.LogUtil;

@RestController
@RequestMapping("department")
public class DepartmentController {

	private DepartmentImpl service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	@GetMapping
	private Department save(@RequestBody @Valid Department department) {
		LOGGER.info(LogUtil.SAVE_ENTRY_POINT, department);
		department = service.save(department);
		LOGGER.info(LogUtil.SAVE_EXIT_POINT, department);
		return department;
	}
}
