package pl.michaldurlak.AssetsInventoryManagement.web.basics;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import pl.michaldurlak.AssetsInventoryManagement.web.MainWeb;
import pl.michaldurlak.AssetsInventoryManagement.web.usersManagment.UserAddWeb;
import pl.michaldurlak.AssetsInventoryManagement.web.usersManagment.UserListWeb;

public class NavbarLayout extends AppLayout {

    Tab tabsAll = new Tab();

    public NavbarLayout() {
        setAllTabs();
        AppLayoutBasic();
    }
    private void AppLayoutBasic() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Assets Inventory Management");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");


        addToDrawer(tabsAll);
        addToNavbar(toggle, title);
    }

    private void setAllTabs(){
        //HOME
        RouterLink routerLinkHome = new RouterLink("Home", MainWeb.class);
        routerLinkHome.add(VaadinIcon.HOME.create());
        tabsAll.add(routerLinkHome);

//START USER SECTION
        VerticalLayout verticalLayoutUserSection = new VerticalLayout();
        //User section
        Tab tabUserManagment = new Tab(VaadinIcon.USER.create(), new Span("User Managment"));
        tabUserManagment.setEnabled(false);

            //User list
        RouterLink routerUserList = new RouterLink("List of all users", UserListWeb.class);
            //User add
        RouterLink routerUserAdd = new RouterLink("Add new user", UserAddWeb.class);

        verticalLayoutUserSection.add(tabUserManagment);
        verticalLayoutUserSection.add(routerUserList);
        verticalLayoutUserSection.add(routerUserAdd);
//STOP USER SECTION




        tabsAll.add(verticalLayoutUserSection);
    }
}
