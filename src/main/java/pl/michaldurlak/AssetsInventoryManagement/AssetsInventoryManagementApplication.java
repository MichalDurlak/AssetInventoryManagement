package pl.michaldurlak.AssetsInventoryManagement;

import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import pl.michaldurlak.AssetsInventoryManagement.assets.AssetRepo;
import pl.michaldurlak.AssetsInventoryManagement.users.UserDetailsServiceImpl;
import pl.michaldurlak.AssetsInventoryManagement.users.UserModel;
import pl.michaldurlak.AssetsInventoryManagement.users.UserRepo;
import pl.michaldurlak.AssetsInventoryManagement.users.UsersRoles;
import pl.michaldurlak.AssetsInventoryManagement.web.basics.LoginView;

@SpringBootApplication
//@PropertySource("classpath:application.properties")
public class AssetsInventoryManagementApplication extends VaadinWebSecurityConfigurerAdapter {

	private UserDetailsServiceImpl userDetailsService;
	private UserRepo userRepo;
	private AssetRepo assetRepo;
	public static void main(String[] args) {
		SpringApplication.run(AssetsInventoryManagementApplication.class, args);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.userDetailsService(userDetailsService);
		super.configure(http);
		setLoginView(http, LoginView.class);
	}

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
		super.configure(auth);
//		setLoginView(auth, LoginView.class);
    }


	@Autowired
	public AssetsInventoryManagementApplication(UserDetailsServiceImpl userDetailsService, UserRepo userRepo, AssetRepo assetRepo) {
		this.userDetailsService = userDetailsService;
		this.userRepo = userRepo;
		this.assetRepo = assetRepo;
	}


	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new InMemoryUserDetailsManager(
				User.withUsername("adam")
						.password("{noop}p")
						.roles("ADMIN")
						.build()
		);
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


