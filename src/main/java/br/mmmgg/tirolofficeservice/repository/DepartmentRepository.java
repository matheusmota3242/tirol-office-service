package br.mmmgg.tirolofficeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mmmgg.tirolofficeservice.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
