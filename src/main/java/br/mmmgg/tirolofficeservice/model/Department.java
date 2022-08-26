package br.mmmgg.tirolofficeservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "service_unit_id")
    private ServiceUnit serviceUnit;

    @OneToMany(mappedBy = "department")
    private List<Equipment> equipments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ServiceUnit getServiceUnit() {
        return serviceUnit;
    }

    public void setServiceUnit(ServiceUnit serviceUnit) {
        this.serviceUnit = serviceUnit;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", serviceUnit=" + serviceUnit + ", equipments=" + equipments
				+ "]";
	}

}
