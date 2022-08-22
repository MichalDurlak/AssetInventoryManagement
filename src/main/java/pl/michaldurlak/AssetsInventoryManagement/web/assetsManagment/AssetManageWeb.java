package pl.michaldurlak.AssetsInventoryManagement.web.assetsManagment;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.michaldurlak.AssetsInventoryManagement.web.basics.NavbarLayout;

@Route(value = "asset/{id}", layout = NavbarLayout.class)
@PageTitle("{id} info")
public class AssetManageWeb extends VerticalLayout {
}
