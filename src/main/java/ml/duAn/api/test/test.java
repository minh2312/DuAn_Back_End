package ml.duAn.api.test;
import java.io.File;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;


public class test {
	@Autowired 
	public static ServletContext context;
	public static void main(String[] args) {
		ClassPathResource imgFile = new ClassPathResource("static/images/");
		System.out.println(imgFile);
	}
}
