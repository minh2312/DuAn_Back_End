package ml.duAn.api.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="Levels")
public class Levels {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	@Column(name="Name")
	private String name;
	@Column(name="BasicSalary")
	private Double basicSalary;
	@Column(name="Status")
	private Boolean status;
	@Column(name="UpdateDate")
	private Date updateDate;

	@OneToMany(mappedBy = "level")
	@JsonIgnore
	private Set<Employees> empLevel;

	public Levels() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Levels(int id, String name, Double basicSalary, Boolean status, Date updateDate, Set<Employees> empLevel) {
		super();
		this.id = id;
		this.name = name;
		this.basicSalary = basicSalary;
		this.status = status;
		this.updateDate = updateDate;
		this.empLevel = empLevel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Set<Employees> getEmpLevel() {
		return empLevel;
	}

	public void setEmpLevel(Set<Employees> empLevel) {
		this.empLevel = empLevel;
	}
	
	
	
	
	
}
