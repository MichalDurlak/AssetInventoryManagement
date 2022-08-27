package pl.michaldurlak.AssetsInventoryManagement.web.basics;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import pl.michaldurlak.AssetsInventoryManagement.web.MainWeb;
import pl.michaldurlak.AssetsInventoryManagement.web.assets_managment.AssetAddWeb;
import pl.michaldurlak.AssetsInventoryManagement.web.assets_managment.AssetListWeb;
import pl.michaldurlak.AssetsInventoryManagement.web.assets_managment.AssetManageWeb;
import pl.michaldurlak.AssetsInventoryManagement.web.users_managment.UserAddWeb;
import pl.michaldurlak.AssetsInventoryManagement.web.users_managment.UserListWeb;

import java.util.HashMap;
import java.util.Map;

public class NavbarLayout extends AppLayout {
    private Map<Component, Class> mapAllTabs = new HashMap<>();
    private Tabs tabs = new Tabs();

    public NavbarLayout() {
        //Home section
        addHomeMainSection();
        //Assets section
        addAssetsManagmentSection();
        //Users section
        addUsersManagmentSection();
        //Logout button
        addLogoutSection();

        AppLayoutBasicNew();
    }

    private void addNewTab(Tab newTab, Class newClass){
        tabs.add(newTab);
        mapAllTabs.put(newTab, newClass);
    }


    private void addHomeMainSection() {
        Tab tabHome = new Tab(VaadinIcon.HOME.create(),new Label("Home"));
        addNewTab(tabHome, MainWeb.class);
    }

    private void addAssetsManagmentSection() {
        //Asset list
        Tab tabAssetList = new Tab(VaadinIcon.PENCIL.create(), new Span("List of all Assets"));
        addNewTab(tabAssetList, AssetListWeb.class);
        //Asset add new
        Tab tabAssetAdd = new Tab(VaadinIcon.PENCIL.create(), new Span("Add new Asset"));
        addNewTab(tabAssetAdd, AssetAddWeb.class);
        //Search for asset
        Tab tabAssetSearch = new Tab(VaadinIcon.PENCIL.create(), new Span("Search for Asset"));
        addNewTab(tabAssetSearch, AssetManageWeb.class);
    }

    private void addUsersManagmentSection() {
        //Users list
        Tab tabUserList = new Tab(VaadinIcon.USER.create(), new Span("List of all Users"));
        addNewTab(tabUserList, UserListWeb.class);
        //User add new
        Tab tabUserAdd = new Tab(VaadinIcon.USER.create(), new Span("Add new User"));
        addNewTab(tabUserAdd, UserAddWeb.class);
    }

    private void addLogoutSection() {
        Button logoutButton = new Button("Logout", VaadinIcon.SIGN_OUT.create());
        logoutButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_SMALL);
        logoutButton.addClickListener(e -> {
            VaadinSession.getCurrent().getSession().invalidate();
            UI.getCurrent().getPage().reload();
        });
        tabs.add(logoutButton);
    }



    private void AppLayoutBasicNew() {
        DrawerToggle toggle = new DrawerToggle();
        H1 title = new H1("Assets Inventory Management");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        //If user click the menu tab forward him to specific class
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addSelectedChangeListener(event -> {
            UI.getCurrent().navigate(mapAllTabs.get(event.getSelectedTab()));
        });

        addToDrawer(tabs);
        addToNavbar(toggle, title);
    }



}
