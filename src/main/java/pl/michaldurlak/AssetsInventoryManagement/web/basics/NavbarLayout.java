package pl.michaldurlak.AssetsInventoryManagement.web.basics;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import pl.michaldurlak.AssetsInventoryManagement.web.MainWeb;
import pl.michaldurlak.AssetsInventoryManagement.web.assets_managment.AssetAddWeb;
import pl.michaldurlak.AssetsInventoryManagement.web.assets_managment.AssetListWeb;
import pl.michaldurlak.AssetsInventoryManagement.web.assets_managment.AssetManageWeb;
import pl.michaldurlak.AssetsInventoryManagement.web.users_managment.UserAddWeb;
import pl.michaldurlak.AssetsInventoryManagement.web.users_managment.UserListWeb;

public class NavbarLayout extends AppLayout {


    VerticalLayout tabsAll = new VerticalLayout();





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



//        addToDrawer(tabsAll);
        addToNavbar(toggle, title);
    }

    private void setAllTabs(){
//START HOME SECTION
        RouterLink routerLinkHome = new RouterLink("Home", MainWeb.class);
        routerLinkHome.add(VaadinIcon.HOME.create());
        tabsAll.add(routerLinkHome);
//STOP HOME SECTION
//START ASSET SECTION
        Tab tabAssetManagment = new Tab(VaadinIcon.PENCIL.create(), new Span("Assets Managment"));
        tabAssetManagment.setEnabled(false);
        tabsAll.add(tabAssetManagment);

        //Asset list
        RouterLink routerAssetList = new RouterLink("List of all assets", AssetListWeb.class);
        tabsAll.add(routerAssetList);
        //Asset add
        RouterLink routerAssetAdd = new RouterLink("Add new asset", AssetAddWeb.class);
        tabsAll.add(routerAssetAdd);
        //Search for specific asset
        RouterLink routerAssetSearch = new RouterLink("Search for asset", AssetManageWeb.class);
        tabsAll.add(routerAssetSearch);


//STOP ASSET SECTION
//START USER SECTION
        //User section
        Tab tabUserManagment = new Tab(VaadinIcon.USER.create(), new Span("User Managment"));
        tabUserManagment.setEnabled(false);

            //User list
        RouterLink routerUserList = new RouterLink("List of all users", UserListWeb.class);
            //User add
        RouterLink routerUserAdd = new RouterLink("Add new user", UserAddWeb.class);

        tabsAll.add(tabUserManagment);
        tabsAll.add(routerUserList);
        tabsAll.add(routerUserAdd);
//STOP USER SECTION

//START LOGOUT SECTION

        Button logoutButton = new Button("Logout", VaadinIcon.SIGN_OUT.create());
        logoutButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_SMALL);
        logoutButton.addClickListener(e -> {
            VaadinSession.getCurrent().getSession().invalidate();
            UI.getCurrent().getPage().reload();
        });


        tabsAll.add(logoutButton);
//STOP LOGOUT SECTION
    }
}
