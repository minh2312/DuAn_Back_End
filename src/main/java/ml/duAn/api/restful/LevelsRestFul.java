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

import ml.duAn.api.entities.Levels;

@RestController
@RequestMapping("/api")
public class LevelsRestFul extends General{
	
	
	// lấy ra danh sách cấp bậc
	@GetMapping("/ListLevels")
	public ResponseEntity<List<Levels>> ListLevels() {
		return new ResponseEntity<List<Levels>>(_ILevelsDAO.getAll(),HttpStatus.OK);
	}


		// lịch sử đã xóa
		@GetMapping("/ListLevels/history")
		public ResponseEntity<List<Levels>> ListDepartmentHistory() {
			List<Levels> listDep = _ILevelsDAO.history();
			return new ResponseEntity<List<Levels>>(listDep,HttpStatus.OK);
		}
	
	
	
//	// tìm kiếm theo name
	@GetMapping("/ListLevels/nameSearch={name}")
	public ResponseEntity<List<Levels>> getLevelByName(@PathVariable("name") String name){
		List<Levels> byName = _ILevelsDAO.getByName(name);
		return new ResponseEntity<List<Levels>>(byName,HttpStatus.OK);
	}
	
	
	// tìm kiếm theo id
	@GetMapping("/ListLevels/{id}")
	public ResponseEntity<Levels> getLevelById(@PathVariable("id") Integer id){
		Levels byId = _ILevelsDAO.getById(id);
		return new ResponseEntity<Levels>(byId,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/ListLevels/SortBy={orderBy}")
	public  ResponseEntity<List<Levels>> ListLevelOrderByDESC(@PathVariable("orderBy") String orderBy){
		List<Levels> sortByDESC = _ILevelsDAO.sortByDESC(orderBy);
		return new ResponseEntity<List<Levels>>(sortByDESC,HttpStatus.OK);
	}
	
	@GetMapping("/ListLevels/SortByASC={orderBy}")
	public  ResponseEntity<List<Levels>> ListLevelOrderByASC(@PathVariable("orderBy") String orderBy){
		List<Levels> sortByASC = _ILevelsDAO.sortByASC(orderBy);
		return new ResponseEntity<List<Levels>>(sortByASC,HttpStatus.OK);
	}
	
	// thêm mới cấp bậc
	@PostMapping("/ListLevels")
	public ResponseEntity<Boolean> saveLevels(@RequestBody Levels level){
		return new ResponseEntity<Boolean>(_ILevelsDAO.add(level),HttpStatus.OK);
	}
	
	
	
	// cập nhập thông tin
	@PutMapping("/ListLevels")
	public ResponseEntity<Boolean> updateLevels(@RequestBody Levels level){
		boolean edit = _ILevelsDAO.edit(level);
		return new ResponseEntity<Boolean>(edit,HttpStatus.OK);
	}
	
	
	
	// xóa
	@DeleteMapping("/ListLevels/{id}")
	public ResponseEntity<?> deleteLevels(@PathVariable("id") Integer id){
		boolean delete = _ILevelsDAO.delete(id);
		return new ResponseEntity<Boolean>(delete,HttpStatus.OK);
	}

// xóa all item
	@DeleteMapping("/ListLevels/idlevel={id}")
		public ResponseEntity<?> deleteDepartments(@PathVariable("id") List<Integer> id){
			id.forEach(d->{
				_IDepartmentDAO.delete(d);
			});
			return ResponseEntity.ok().body("remove success");
		}


		@PutMapping("/ListLevels/deleteItemToHistory")
		public ResponseEntity<?> deleteItemToHistory(@RequestBody Levels d){
				d.setStatus(false);
				boolean edit = _ILevelsDAO.updateStatus(d);
			return new ResponseEntity<Boolean>(edit,HttpStatus.OK);
		}
}



// spring hibernate transacition ,hql