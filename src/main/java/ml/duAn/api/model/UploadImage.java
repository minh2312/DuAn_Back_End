package ml.duAn.api.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;
public class UploadImage {
	private String folderImage;
	private MultipartFile file;
	public UploadImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public UploadImage(String folderImage) {
		super();
		this.folderImage = folderImage;
	}




	public UploadImage( String folderImage, MultipartFile file) {
		super();
		this.folderImage = folderImage;
		this.file = file;
	}

	public String getOriginalFilename() {
		return this.file.getOriginalFilename();
	}


	
//	src\main\resources
	public void UploadImages() {
		String curDirectory = System.getProperty("user.dir");
		File resourcePath = new File(curDirectory+"\\src\\main\\resources\\"+this.folderImage);
		File destination = new File(resourcePath.getAbsolutePath()+"/"+this.file.getOriginalFilename());
		if(!destination.exists()) {
			try {
				Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	public String storeFile(MultipartFile file) {
        // Normalize file name
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = "";
        System.out.println(originalFileName);
        return null;
    }
	
}
