package pl.michaldurlak.AssetsInventoryManagement.web.usersManagment;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.michaldurlak.AssetsInventoryManagement.users.UserModel;
import pl.michaldurlak.AssetsInventoryManagement.users.UserRepo;
import pl.michaldurlak.AssetsInventoryManagement.users.UsersRoles;
import pl.michaldurlak.AssetsInventoryManagement.web.basics.NavbarLayout;

@Route(value = "user/add", layout = NavbarLayout.class)
@PageTitle("Add User")
public class UserAddWeb extends VerticalLayout {


    private UserRepo userRepo;

    @Autowired
    public UserAddWeb(UserRepo userRepo) {
        this.userRepo = userRepo;
        AddNewUser();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public void AddNewUser(){

        H3 h3Title = new H3("Create new user");
        TextField fieldUsername = new TextField("Username");
        PasswordField fieldPassword = new PasswordField("Password");

        //List of all roles
        ComboBox<UsersRoles> comboBoxUserRole = new ComboBox<>("User's Role");
        comboBoxUserRole.setItems(UsersRoles.values());
        comboBoxUserRole.setItemLabelGenerator(UsersRoles::name);
        comboBoxUserRole.setValue(UsersRoles.READ);


        //Buttons
        HorizontalLayout horizontalLayoutButtons = new HorizontalLayout();
        Button buttonAdd = new Button("Add");
        Button buttonClear = new Button("Clear");
        horizontalLayoutButtons.add(buttonAdd);
        horizontalLayoutButtons.add(buttonClear);

        //Add button Clicked
        buttonAdd.addClickListener(addEvent -> {

            //Check if user exist
            if (userRepo.findByUsername(fieldUsername.getValue()) == null) {
                //Get data from forms
                UserModel newUser = new UserModel(fieldUsername.getValue(), passwordEncoder().encode(fieldPassword.getValue()),comboBoxUserRole.getValue());
                //Save user to database
                userRepo.save(newUser);
                //Show success message
                Notification notification = Notification.show("User added: " + fieldUsername.getValue());
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                //Clear fields by using clear button
                buttonClear.click();
            } else {
                //Show error message
                Notification notification = Notification.show("Can't create user. PLease change login: " + fieldUsername.getValue());
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }

        });

        //Add button Clear
        buttonClear.addClickListener(clearEvent -> {
            fieldUsername.clear();
            fieldPassword.clear();
        });


        //Add all components
        add(h3Title);
        add(fieldUsername);
        add(fieldPassword);
        add(comboBoxUserRole);
        add(horizontalLayoutButtons);
    }



}
