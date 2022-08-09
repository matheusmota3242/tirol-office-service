package br.mmmgg.tirolofficeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mmmgg.tirolofficeservice.model.ServiceUnit;

@Repository
public interface ServiceUnitRepository extends JpaRepository<ServiceUnit, Integer> {
    
}
