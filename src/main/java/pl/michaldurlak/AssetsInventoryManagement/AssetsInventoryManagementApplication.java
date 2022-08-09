package pl.michaldurlak.AssetsInventoryManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableWebSecurity
public class AssetsInventoryManagementApplication  extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(AssetsInventoryManagementApplication.class, args);
	}

	//temporary turn off Spring Security (need to create connection to database)
	@Override
	protected void configure(HttpSecurity security) throws Exception
	{
		security.httpBasic().disable();
		security.formLogin().disable();
		security.csrf().disable();
		security.anonymous();
	}

}
