package pl.michaldurlak.AssetsInventoryManagement.web.users_managment;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import pl.michaldurlak.AssetsInventoryManagement.users.UserModel;
import pl.michaldurlak.AssetsInventoryManagement.users.UserRepo;
import pl.michaldurlak.AssetsInventoryManagement.web.basics.NavbarLayout;

import java.util.List;
import java.util.Locale;


@Route(value = "user/list", layout = NavbarLayout.class)
public class UserListWeb extends VerticalLayout {

    private UserRepo userRepo;

    @Autowired
    public UserListWeb(UserRepo userRepo) {
        this.userRepo = userRepo;
        getListOfUser();

    }

    private void getListOfUser(){

        Grid<UserModel> grid = new Grid<>(UserModel.class, false);
        grid.addColumn(UserModel::getId).setHeader("User ID");
        grid.addColumn(UserModel::getUsername).setHeader("Username");
        grid.addColumn(UserModel::getUserRole).setHeader("User's role");
        grid.addColumn(user -> user.isActive() ? "Yes" : "No").setHeader("Is user active");
        grid.addComponentColumn(user -> {
                    Button editButton = new Button(new Icon(VaadinIcon.PENCIL));
                    editButton.addClickListener(buttonClickEvent -> {
                        getUI().get().getPage().setLocation("/user/manage/"+user.getId());
                    });
                    return editButton;
                }).setHeader("Edit record");


            List<UserModel> users = userRepo.findAll();
        grid.setItems(users);


        add(grid);

    }
}
