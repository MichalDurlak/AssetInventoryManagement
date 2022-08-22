package pl.michaldurlak.AssetsInventoryManagement.web.assetsManagment;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.michaldurlak.AssetsInventoryManagement.assets.AssetModel;
import pl.michaldurlak.AssetsInventoryManagement.web.basics.NavbarLayout;

@Route(value = "asset/add", layout = NavbarLayout.class)
@PageTitle("Add new asset")
public class AssetAddWeb extends VerticalLayout {

    H3 h3Title = new H3("Add new asset");
    TextField textFieldName = new TextField("Name");
    TextField textFieldDescription = new TextField("Description");
    TextField textFieldBrand = new TextField("Brand");
    TextField textFieldModel = new TextField("Model");
    DatePicker dateDateOfProduction = new DatePicker("Date of Production");
    DatePicker dateEndDateOfWarranty = new DatePicker("Date of Production");
    TextField textFieldQuantityInStock = new TextField("Quantity In Stock");

    HorizontalLayout horizontalLayoutButtons = new HorizontalLayout();
    Button buttonAdd = new Button("Add");
    Button buttonClear = new Button("Clear");

//    @Autowired
//    private AssetModel assetModel;


    public AssetAddWeb() {
        getAssetAddWeb();
    }

    public void getAssetAddWeb(){
        add(h3Title);
        add(textFieldName);
        add(textFieldDescription);
        add(textFieldBrand);
        add(textFieldModel);
        add(dateDateOfProduction);
        add(dateEndDateOfWarranty);
        add(textFieldQuantityInStock);

        horizontalLayoutButtons.add(buttonAdd);
        horizontalLayoutButtons.add(buttonClear);
        add(horizontalLayoutButtons);


        //Add button Clear
        buttonClear.addClickListener(clearEvent -> {
            textFieldName.clear();
            textFieldDescription.clear();
            textFieldBrand.clear();
            textFieldModel.clear();
            dateDateOfProduction.clear();
            dateEndDateOfWarranty.clear();
            textFieldQuantityInStock.clear();
        });

    }

//    public void addRecord(){
//        AssetModel newAsset = new AssetModel();
//    }


}
