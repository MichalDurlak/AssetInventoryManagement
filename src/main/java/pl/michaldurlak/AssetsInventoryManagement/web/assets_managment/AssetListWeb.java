package pl.michaldurlak.AssetsInventoryManagement.web.assets_managment;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.michaldurlak.AssetsInventoryManagement.assets.AssetModel;
import pl.michaldurlak.AssetsInventoryManagement.assets.AssetRepo;
import pl.michaldurlak.AssetsInventoryManagement.web.basics.NavbarLayout;

import javax.annotation.security.PermitAll;
import java.util.List;

@Route(value = "asset/list", layout = NavbarLayout.class)
@PageTitle("List of all assets")
@PermitAll
public class AssetListWeb extends VerticalLayout {

    @Autowired
    private AssetRepo assetRepo;

    public AssetListWeb(AssetRepo assetRepo) {
        this.assetRepo = assetRepo;
        getAssetListWeb();
    }

    public void getAssetListWeb(){

        Grid<AssetModel> gridAsset = new Grid<>(AssetModel.class, false);
        gridAsset.addColumn(AssetModel::getId).setHeader("Product ID");
        gridAsset.addColumn(AssetModel::getName).setHeader("Name");
        gridAsset.addColumn(AssetModel::getDescription).setHeader("Description");
        gridAsset.addColumn(AssetModel::getBrand).setHeader("Brand");
        gridAsset.addColumn(AssetModel::getModel).setHeader("Model");
        gridAsset.addColumn(AssetModel::getDateOfProduction).setHeader("Date Of Production");
        gridAsset.addColumn(AssetModel::getEndDateOfWarranty).setHeader("Last day of warranty");
        gridAsset.addComponentColumn(asset -> {
            Button editButton = new Button(new Icon(VaadinIcon.PENCIL));
            editButton.addClickListener(buttonClickEvent -> {
                getUI().get().getPage().setLocation("/asset/"+asset.getId());
            });
            return editButton;
        }).setHeader("Edit record");

        List<AssetModel> assets = assetRepo.findAll();
        gridAsset.setItems(assets);

        add(gridAsset);

    }
}
