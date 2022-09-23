package br.mmmgg.tirolofficeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mmmgg.tirolofficeservice.model.Maintenance;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {

}
