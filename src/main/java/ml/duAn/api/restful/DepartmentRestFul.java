package ml.duAn.api.restful;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ml.duAn.api.entities.Department;


@RestController
@RequestMapping("/api")
public class DepartmentRestFul extends General{

	
	// lấy ra danh sách phòng ban
	@GetMapping("/ListDepartment")
	public ResponseEntity<List<Department>> ListDepartment() {
		List<Department> listDep = _IDepartmentDAO.getAll();
		return new ResponseEntity<List<Department>>(listDep,HttpStatus.OK);
	}
	

	// lịch sử đã xóa
	@GetMapping("/ListDepartment/history")
	public ResponseEntity<List<Department>> ListDepartmentHistory() {
		List<Department> listDep = _IDepartmentDAO.history();
		return new ResponseEntity<List<Department>>(listDep,HttpStatus.OK);
	}
	
	
	// tìm kiếm theo name
	@GetMapping("/ListDepartment/SearchByName={name}")
	public ResponseEntity<List<Department>> ListDepartmentByName(@PathVariable("name") String name) {
		List<Department> getByName = _IDepartmentDAO.getByName(name);
		return new ResponseEntity<List<Department>>(getByName,HttpStatus.OK);
	}
	
	
	// sắp xếp theeo DESC
	@GetMapping("/ListDepartment/SortBy={orderBy}")
	public ResponseEntity<List<Department>> ListDepartmentSortByDESC(@PathVariable("orderBy") String orderBy) {
		List<Department> sortByDESC = _IDepartmentDAO.sortByDESC(orderBy);
		return new ResponseEntity<List<Department>>(sortByDESC,HttpStatus.OK);
	}
	
	
	// sắp xếp theo asc
	@GetMapping("/ListDepartment/SortByASC={orderBy}")
	public ResponseEntity<List<Department>> ListDepartmentSortByASC(@PathVariable("orderBy") String orderBy) {
		List<Department> sortByDESC = _IDepartmentDAO.sortByASC(orderBy);
		return new ResponseEntity<List<Department>>(sortByDESC,HttpStatus.OK);
	}
	
	
	// thêm mới phòng ban
	//@RequestBody dùng để ánh xạ HTTPRequest tự động. tự động ánh xạ dữu liệu Json trong HTTP sang một Java Type tương ứng
	@PostMapping("/ListDepartment")
	public ResponseEntity<Boolean> saveDepartment(@RequestBody Department d){
		return new ResponseEntity<Boolean>(_IDepartmentDAO.add(d),HttpStatus.OK);
	}
	
	
		
	
	// tìm kiếm theo id
	@GetMapping("/ListDepartment/{id}")
	public ResponseEntity<Department> ListDepartmentById(@PathVariable("id") Integer id){
		Department depById = _IDepartmentDAO.getById(id);
		return new ResponseEntity<Department>(depById,HttpStatus.OK);
	}
	
	
	// cập nhập thông tin
	@PutMapping("/ListDepartment")
	public ResponseEntity<Boolean> updateDepartment(@RequestBody Department d){
		boolean edit = _IDepartmentDAO.edit(d);
		return new ResponseEntity<Boolean>(edit,HttpStatus.OK);
	}
	
	
	
	// Xóa 
	@DeleteMapping("/ListDepartment/{id}")
	public ResponseEntity<Boolean> deleteDepartment(@PathVariable("id") Integer id){
		boolean delete = _IDepartmentDAO.delete(id);
		return new ResponseEntity<Boolean>(delete,HttpStatus.OK);
	}
	
	// Xóa All item
		@DeleteMapping("/ListDepartment/idDeps={id}")
		public ResponseEntity<?> deleteDepartments(@PathVariable("id") List<Integer> id){
			id.forEach(d->{
				_IDepartmentDAO.delete(d);
			});
			return ResponseEntity.ok().body("remove success");
		}

		// update Status
		@PutMapping("/ListDepartment/updateDelete")
		public ResponseEntity<Boolean> deleteItem(@RequestBody Department d){
			boolean edit = _IDepartmentDAO.updateStatus(d);
			return new ResponseEntity<Boolean>(edit,HttpStatus.OK);
		}

		// update status by id
		// update Status
		@PutMapping("/ListDepartment/idUpdate={id}")
		public ResponseEntity<Boolean> updateById(@PathVariable("id") Integer id){
			
			// return new ResponseEntity<Boolean>(edit,HttpStatus.OK);
			return null;
		}

		@PutMapping("/ListDepartment/deleteItemToHistory")
		public ResponseEntity<?> deleteItemToHistory(@RequestBody Department d){
				d.setStatus(false);
				boolean edit = _IDepartmentDAO.updateStatus(d);
			return new ResponseEntity<Boolean>(edit,HttpStatus.OK);
		}
}
