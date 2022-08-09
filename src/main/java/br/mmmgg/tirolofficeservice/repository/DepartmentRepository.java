package br.mmmgg.tirolofficeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mmmgg.tirolofficeservice.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
