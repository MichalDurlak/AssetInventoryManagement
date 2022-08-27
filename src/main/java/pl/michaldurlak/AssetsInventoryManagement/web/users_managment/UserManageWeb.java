package pl.michaldurlak.AssetsInventoryManagement.web.users_managment;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.michaldurlak.AssetsInventoryManagement.users.UserModel;
import pl.michaldurlak.AssetsInventoryManagement.users.UserRepo;
import pl.michaldurlak.AssetsInventoryManagement.web.basics.NavbarLayout;
import java.util.Optional;

@Route(value = "user/manage" , layout = NavbarLayout.class)
@PageTitle("User settings")
public class UserManageWeb extends VerticalLayout
        implements HasUrlParameter<String> {

    private UserRepo userRepo;

    @Autowired
    public UserManageWeb(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String id) {
        //remove layout
        removeAll();

        if (id == null || id.length() == 0) {
//            System.out.println("null lub 0");
        } else {
            getSpecifyUserManageWeb(Long.parseLong(id));
        }

    }

    private void getSpecifyUserManageWeb(long id) {
        //remove layout
        removeAll();

        try{
            Optional<UserModel> user = userRepo.findById(id);

            // ID
            TextField textFieldID = new TextField();
            textFieldID.setLabel("ID");
            textFieldID.setValue(String.valueOf(user.get().getId()));
            textFieldID.setReadOnly(true);
            add(textFieldID);

            // USERNAME
            TextField textFieldUsername = new TextField();
            textFieldUsername.setLabel("USERNAME");
            textFieldUsername.setValue(String.valueOf(user.get().getUsername()));
            textFieldUsername.setReadOnly(true);
            add(textFieldUsername);

            // USER ROLE
            TextField textFieldUserRole = new TextField();
            textFieldUserRole.setLabel("USER'S ROLE");
            textFieldUserRole.setValue(String.valueOf(user.get().getUserRole()));
            textFieldUserRole.setReadOnly(true);
            add(textFieldUserRole);

            // IS ACTIVE
            TextField textFieldIsActive = new TextField();
            textFieldIsActive.setLabel("IS ACTIVE");
            textFieldIsActive.setValue(user.get().isActive() ? "Active" : "Disabled");
            textFieldIsActive.setReadOnly(true);
            add(textFieldIsActive);

            //BUTTONS
            HorizontalLayout horizontalLayoutButtons = new HorizontalLayout();
            Button buttonSave = new Button("Save Changes");
            buttonSave.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
            horizontalLayoutButtons.add(buttonSave);
            add(horizontalLayoutButtons);

        } catch (Exception e){
            Text textError = new Text("ERROR 404");
            add(textError);
            Notification notification = Notification.show("Can't find user");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }


    }


}
