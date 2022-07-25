package ml.duAn.api.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Employees")
public class Employees {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	@Column(name="CodeEmployee")
	private String codeEmployee;
	@Column(name="NameEmployee")
	private String nameEmployee;
	@Column(name="Image")
	private String image;
	@Column(name="Email")
	private String email;
	@Column(name="Phone")
	private String phone;
	@Column(name="Address")
	private String address;
	@Column(name="Ethnic")
	private String ethnic;
	@Column(name="Gender")
	private int gender;
	@Column(name="Birthday")
	private Date birthday;
	@Column(name="Status")
	private int status;
	@Column(name="UpdateDate")
	private Date updateDate;

	
	// @JsonIgnore
	// @ManyToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "IdDepartment", referencedColumnName = "Id")
	// private Department department;



	// @JsonIgnore
	// @ManyToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "IdLevel", referencedColumnName = "Id")
	// private Levels level;


	@ManyToOne
	@JoinColumn(name = "IdDepartment",referencedColumnName = "Id")
	// @JsonBackReference
	private Department dep;
	
	@ManyToOne
	@JoinColumn(name = "IdLevel",referencedColumnName = "Id")
	// @JsonBackReference
	private Levels level;

	public Employees() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employees(int id, String codeEmployee, String nameEmployee, String image, String email, String phone,
			String address, String ethnic, int gender, Date birthday, int status, Date updateDate, Department dep,
			Levels level) {
		super();
		this.id = id;
		this.codeEmployee = codeEmployee;
		this.nameEmployee = nameEmployee;
		this.image = image;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.ethnic = ethnic;
		this.gender = gender;
		this.birthday = birthday;
		this.status = status;
		this.updateDate = updateDate;
		this.dep = dep;
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeEmployee() {
		return codeEmployee;
	}

	public void setCodeEmployee(String codeEmployee) {
		this.codeEmployee = codeEmployee;
	}

	public String getNameEmployee() {
		return nameEmployee;
	}

	public void setNameEmployee(String nameEmployee) {
		this.nameEmployee = nameEmployee;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Department getDep() {
		return dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}

	public Levels getLevel() {
		return level;
	}

	public void setLevel(Levels level) {
		this.level = level;
	}
	
	
	
}
