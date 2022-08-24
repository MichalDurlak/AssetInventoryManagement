package pl.michaldurlak.AssetsInventoryManagement.web.assetsManagment;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.springframework.web.bind.annotation.*;
import pl.michaldurlak.AssetsInventoryManagement.assets.AssetModel;
import pl.michaldurlak.AssetsInventoryManagement.assets.AssetRepo;
import pl.michaldurlak.AssetsInventoryManagement.web.basics.NavbarLayout;

import java.util.List;
import java.util.Optional;

@Route(value = "asset" , layout = NavbarLayout.class)
@PageTitle("Asset info")
public class AssetManageWeb extends VerticalLayout
        implements HasUrlParameter<String> {


    private AssetRepo assetRepo;

    public AssetManageWeb(AssetRepo assetRepo) {
        this.assetRepo = assetRepo;
    }

    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String id) {


        if (id == null || id.length() == 0) {
            getBlankAssetManageWeb();
        } else {
            getSpecifyAssetManageWeb(Long.parseLong(id));
        }

    }


    public void getAssetManageWeb(){
    }

    public void getBlankAssetManageWeb(){
        TextField textFieldId = new TextField("Asset's id");
        Button buttonSearch = new Button("Search for id");


        buttonSearch.addClickListener(addEvent -> {
            getSpecifyAssetManageWeb(Long.valueOf(textFieldId.getValue()));
        });

        add(textFieldId);
        add(buttonSearch);

    }

    public void getSpecifyAssetManageWeb(Long id){
        //remove layout
        removeAll();

        //tabel
        Grid<AssetModel> gridAsset = new Grid<>(AssetModel.class, false);
        gridAsset.addColumn(AssetModel::getId).setHeader("Product ID");
        gridAsset.addColumn(AssetModel::getName).setHeader("Name");
        gridAsset.addColumn(AssetModel::getDescription).setHeader("Description");
        gridAsset.addColumn(AssetModel::getBrand).setHeader("Brand");
        gridAsset.addColumn(AssetModel::getModel).setHeader("Model");
        gridAsset.addColumn(AssetModel::getDateOfProduction).setHeader("Date Of Production");
        gridAsset.addColumn(AssetModel::getEndDateOfWarranty).setHeader("Last day of warranty");

        Optional<AssetModel> assets = assetRepo.findById(id);
        gridAsset.setItems(assets.get());

        // ID
        TextField textFieldID = new TextField();
        textFieldID.setLabel("ID");
        textFieldID.setValue(String.valueOf(assets.get().getName()));
        textFieldID.setReadOnly(true);
        add(textFieldID);

        // NAME
        TextField textFieldName = new TextField();
        textFieldName.setLabel("Name");
        textFieldName.setValue(String.valueOf(assets.get().getName()));
        textFieldName.setReadOnly(true);
        add(textFieldName);

        // DESCRIPTION
        TextField textFieldDescription = new TextField();
        textFieldDescription.setLabel("Description");
        textFieldDescription.setValue(String.valueOf(assets.get().getDescription()));
        textFieldDescription.setReadOnly(true);
        add(textFieldDescription);

        // BRAND
        TextField textFieldBrand = new TextField();
        textFieldBrand.setLabel("Brand");
        textFieldBrand.setValue(String.valueOf(assets.get().getBrand()));
        textFieldBrand.setReadOnly(true);
        add(textFieldBrand);

        // MODEL
        TextField textFieldModel = new TextField();
        textFieldModel.setLabel("Model");
        textFieldModel.setValue(String.valueOf(assets.get().getModel()));
        textFieldModel.setReadOnly(true);
        add(textFieldModel);

        // DATE OF PRODUCTION
        TextField textFieldDateOfProduction = new TextField();
        textFieldDateOfProduction.setLabel("Date of Production");
        textFieldDateOfProduction.setValue(String.valueOf(assets.get().getDateOfProduction()));
        textFieldDateOfProduction.setReadOnly(true);
        add(textFieldDateOfProduction);

        // END DATE OF WARRANTY
        TextField textFieldEndDateOfWarranty = new TextField();
        textFieldEndDateOfWarranty.setLabel("End Date of Warranty");
        textFieldEndDateOfWarranty.setValue(String.valueOf(assets.get().getEndDateOfWarranty()));
        textFieldEndDateOfWarranty.setReadOnly(true);
        add(textFieldEndDateOfWarranty);


//        add(gridAsset);
    }

}


