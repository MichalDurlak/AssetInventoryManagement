package pl.michaldurlak.AssetsInventoryManagement.web.assets_managment;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import pl.michaldurlak.AssetsInventoryManagement.assets.AssetModel;
import pl.michaldurlak.AssetsInventoryManagement.assets.AssetRepo;
import pl.michaldurlak.AssetsInventoryManagement.web.basics.NavbarLayout;

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

        //remove layout
        removeAll();

        if (id == null || id.length() == 0) {
            getBlankAssetManageWeb();
        } else {
            getSpecifyAssetManageWeb(Long.parseLong(id));
        }

    }


    public void getAssetManageWeb(){
    }

    public void getBlankAssetManageWeb(){
        //remove layout
        removeAll();

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

        Optional<AssetModel> assets = assetRepo.findById(id);

        // ID
        TextField textFieldID = new TextField();
        textFieldID.setLabel("ID");
        textFieldID.setValue(String.valueOf(assets.get().getId()));
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

        // SHOW IMAGE
        Image image = new Image(assets.get().getUrlToImage(), "No image");
        add(image);
    }

}


