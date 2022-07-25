package ml.duAn.api.restful;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import ml.duAn.api.entities.Employees;

@RestController
@RequestMapping("/api")
public class EmployeesRestFul extends General {

	@Autowired
	ServletContext context;
	public final String UPLOAD_DIR = "C:\\Users\\Admin\\New folder (2)\\Java\\SPRING HIBERNATE\\DuAn1\\src\\main\\webapp\\images\\";



	
	// // lấy ra danh sách employees
	@GetMapping("/listEmployees")
	public ResponseEntity<List<Employees>> getListEmp() {
		List<Employees> list = _IEmployeesDAO.getAll();
		return new ResponseEntity<List<Employees>>(list, HttpStatus.OK);
	}





	// tìm kiếm theo id
	@GetMapping("/listEmployees/{id}")
	public ResponseEntity<Employees> getEmpById(@PathVariable("id") int id) {
		Employees emp = _IEmployeesDAO.getById(id);
		return new ResponseEntity<Employees>(emp, HttpStatus.OK);
	}








	// // tìm kiếm theo name
	@GetMapping("/listEmployees/search={name}")
	public ResponseEntity<List<Employees>> getEmpByName(@PathVariable("name") String name) {
		List<Employees> listByName = _IEmployeesDAO.getByName(name);
		return new ResponseEntity<List<Employees>>(listByName, HttpStatus.OK);
	}
	
	
	
	
	// thêm mới
	// @RequestMapping(value = "/listEmployees/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	// @ResponseBody
	// public ResponseEntity<Boolean> insertEmp(@RequestBody Employees e) {
	// 	boolean add = _IEmployeesDAO.add(e);
	// 	return new ResponseEntity<Boolean>(add, HttpStatus.OK);
	// }




	@PostMapping(value = "/listEmployees/")
	@ResponseBody
	public ResponseEntity<?> insertEmp(@RequestBody Employees e) {
		
		boolean add = _IEmployeesDAO.add(e);
		return new ResponseEntity<Boolean>(add, HttpStatus.OK);
	}
	

	
	
	
	
	
	// getImage
	@RequestMapping(value = "/listEmployees/image={image}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<InputStreamResource> getImage(@PathVariable("image") String image) throws IOException {
		ClassPathResource imgFile = new ClassPathResource("static/images/" + image);
		return ResponseEntity
				.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(new InputStreamResource(imgFile.getInputStream()));
	}




	// get image by id
	@GetMapping(path="/listEmployees/id={id}")
	public byte[] getPhoto(@PathVariable("id") Integer id) throws Exception{
		Employees byId = _IEmployeesDAO.getById(id);
		return Files.readAllBytes(Paths.get(context.getRealPath("/images/")+byId.getImage()));
	}

	// get image by name
	@GetMapping(path="/listEmployees/nameImage={image}")
	public byte[] getImageByName(@PathVariable("image") String image) throws Exception{
		Employees byName = _IEmployeesDAO.getByImage(image);
		return Files.readAllBytes(Paths.get(context.getRealPath("/images/")+byName.getImage()));
	}






	// upload ảnh
	@PostMapping("listEmployees/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("img") MultipartFile file) {
		// validate
		if (file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("request must contain file");
		}
		// file upload code...
		try {
			Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			return ResponseEntity.ok("upload image success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some webt wrong ! Try again");
	}







	// sửa
	@PutMapping("/listEmployees")
	public ResponseEntity<Boolean> updateEmp(@RequestBody Employees e) {
		boolean edit = _IEmployeesDAO.edit(e);
		return new ResponseEntity<Boolean>(edit, HttpStatus.OK);
	}








	// // xóa
	@DeleteMapping("/listEmployees/idEmp={id}")
	public ResponseEntity<Boolean> updateEmp(@PathVariable("id") int id) {
		boolean delete = _IEmployeesDAO.delete(id);
		return new ResponseEntity<Boolean>(delete, HttpStatus.OK);
	}




	// @GetMapping(value="getImage")
	// @CrossOrigin
	// public ResponseEntity<List<String>> getImages(){
	// 	List<String> images = new ArrayList<String>();
	// 	String filePath = context.getRealPath("/images");
	// 	File fileForder = new File(filePath);
	// 	if(fileForder!=null){
	// 		for(final File file : fileForder.listFiles()){
	// 			if (!file.isDirectory()) {
	// 				String encodeBasse64 = null;
	// 				try {
	// 					String extension = FilenameUtils.getExtension(file.getName());
	// 					FileInputStream fileInputStream = new FileInputStream(file);
	// 					byte[] bytes = new byte[(int) file.length()];
	// 					fileInputStream.read(bytes);
	// 					encodeBasse64 = Base64.getEncoder().encodeToString(bytes);
	// 					images.add("data::image/"+extension+";base64,"+encodeBasse64);
	// 					fileInputStream.close();
	// 				} catch (Exception e) {
	// 					//TODO: handle exception
	// 				}
	// 			}
	// 		}
	// 	}
	// 	return new ResponseEntity<List<String>>(images,HttpStatus.OK);
	// }



}

//// @PostMapping(value = "listEmployees/postImage",produces =
//// MediaType.APPLICATION_JSON_VALUE)
// @PostMapping(path =
//// "/listEmployees/postImage",headers=("content-type=multipart/*"),consumes =
//// {MediaType.MULTIPART_FORM_DATA_VALUE} )
// public @ResponseBody String uploadImage(@RequestParam("img") MultipartFile
//// file) throws Exception{
////// UploadImage upload = new UploadImage("images", file);
////// upload.UploadImages();
// String curDirectory = System.getProperty("user.dir");
//// File resourcePath = new
//// File(curDirectory+"\\src\\main\\resources\\"+"images");
// File resourcePath = new File(curDirectory+"\\"+"images");
// File destination = new
//// File(resourcePath.getAbsolutePath()+"/"+file.getOriginalFilename());
// if(!destination.exists()) {
//
// Files.write(destination.toPath(), file.getBytes(),
//// StandardOpenOption.CREATE);
//
// }
// return "Thêm Mới Ảnh Thành Công";
// }