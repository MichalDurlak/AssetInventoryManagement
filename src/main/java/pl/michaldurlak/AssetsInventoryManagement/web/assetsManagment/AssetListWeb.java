package pl.michaldurlak.AssetsInventoryManagement.web.assetsManagment;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.michaldurlak.AssetsInventoryManagement.web.basics.NavbarLayout;

@Route(value = "asset/list", layout = NavbarLayout.class)
@PageTitle("List of all assets")
public class AssetListWeb extends VerticalLayout {
}
