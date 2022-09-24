package pl.michaldurlak.AssetsInventoryManagement.web;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pl.michaldurlak.AssetsInventoryManagement.users.UsersRoles;


import javax.annotation.security.RolesAllowed;

import static pl.michaldurlak.AssetsInventoryManagement.users.UsersRoles.admin;

@Route("admin")
@RolesAllowed("admin")
public class AdminView extends VerticalLayout {

    public AdminView(){
        add(
                new H1("Admin View")
        );
    }
}
