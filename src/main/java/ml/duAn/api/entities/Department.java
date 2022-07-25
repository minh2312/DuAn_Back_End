package ml.duAn.api.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name="Department")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	@Column(name="DepartmentCode")
	private String departmentCode;
	@Column(name="NameDepartment")
	private String nameDepartment;
	@Column(name="Status")
	private Boolean status;
	@Column(name="UpdateDate")
	private Date updateDate;
	
	@OneToMany(mappedBy = "dep", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private Set<Employees> empDep;

	public Department() {
		
	}

	public Department(int id, String departmentCode, String nameDepartment, Boolean status, Date updateDate,
			Set<Employees> empDep) {
		super();
		this.id = id;
		this.departmentCode = departmentCode;
		this.nameDepartment = nameDepartment;
		this.status = status;
		this.updateDate = updateDate;
		this.empDep = empDep;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getNameDepartment() {
		return nameDepartment;
	}

	public void setNameDepartment(String nameDepartment) {
		this.nameDepartment = nameDepartment;
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

	public Set<Employees> getEmpDep() {
		return empDep;
	}

	public void setEmpDep(Set<Employees> empDep) {
		this.empDep = empDep;
	}
	
	
	
}
