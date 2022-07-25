package ml.duAn.api;


import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration {
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource source = new DriverManagerDataSource();
		source.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		source.setUrl(env.getProperty("spring.datasource.url"));
		source.setUsername(env.getProperty("spring.datasource.username"));
		source.setPassword(env.getProperty("spring.datasource.password"));
		return source;
	}
	
	@Bean
	public SessionFactory getSessionFactory() throws IOException {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(getDataSource());
		sf.setPackagesToScan("ml.duAn.api.entities");
		Properties prop = new Properties();
		prop.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		prop.setProperty("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
		prop.setProperty("hibernate.current_session_context_class", env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
		sf.setHibernateProperties(prop);
		sf.afterPropertiesSet();
		return sf.getObject();		
	}
	
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedMethods("GET","POST","PUT","DELETE")
					.allowedHeaders("*")
					.allowedOrigins("http://localhost:4200");
			}
		};
	}
	
	@Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new 
        CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000000);
        return multipartResolver;
      }
}
