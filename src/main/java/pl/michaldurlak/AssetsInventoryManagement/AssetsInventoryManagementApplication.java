package pl.michaldurlak.AssetsInventoryManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.michaldurlak.AssetsInventoryManagement.users.UserModel;
import pl.michaldurlak.AssetsInventoryManagement.users.UsersRoles;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class AssetsInventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetsInventoryManagementApplication.class, args);

	}

	//temporary turn off Spring Security (need to create connection to database)
//	@Override
//	protected void configure(HttpSecurity security) throws Exception
//	{
//		security.httpBasic().disable();
//		security.formLogin().disable();
//		security.csrf().disable();
//		security.anonymous();
//	}



//	private static void CreateDefaultUsers(){
//
//		UserModel defaultUserModel = new UserModel("Default","Default");
//		UserModel adminUserModel = new UserModel("Admin","Admin");
//		adminUserModel.setUserRole(UsersRoles.ADMIN);
//		UserModel readUserModel = new UserModel("Read","Read");
//		readUserModel.setUserRole(UsersRoles.READ);
//		UserModel technicianUserModel = new UserModel("Technician","Technician");
//		technicianUserModel.setUserRole(UsersRoles.TECHNICIAN);
//
//	}
}


