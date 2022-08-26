package pl.michaldurlak.AssetsInventoryManagement.web.assetsManagment;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.michaldurlak.AssetsInventoryManagement.assets.AssetModel;
import pl.michaldurlak.AssetsInventoryManagement.assets.AssetRepo;
import pl.michaldurlak.AssetsInventoryManagement.web.basics.NavbarLayout;

@Route(value = "asset/add", layout = NavbarLayout.class)
@PageTitle("Add new asset")
public class AssetAddWeb extends VerticalLayout {



    private AssetRepo assetRepo;

    @Autowired
    public AssetAddWeb(AssetRepo assetRepo) {
        this.assetRepo = assetRepo;
        getAssetAddWeb();
    }

    H3 h3Title = new H3("Add new asset");
    TextField textFieldName = new TextField("Name");
    TextField textFieldDescription = new TextField("Description");
    TextField textFieldBrand = new TextField("Brand");
    TextField textFieldModel = new TextField("Model");
    DatePicker dateDateOfProduction = new DatePicker("Date of Production");
    DatePicker dateEndDateOfWarranty = new DatePicker("End date of warranty");
    IntegerField textFieldQuantityInStock = new IntegerField("Quantity In Stock");

    HorizontalLayout horizontalLayoutButtons = new HorizontalLayout();
    Button buttonAdd = new Button("Add");
    Button buttonClear = new Button("Clear");


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

        //Add button Clicked
        buttonAdd.addClickListener(addEvent -> {

            if(textFieldName.isEmpty()){

                //Show error notification
                Notification notification = Notification.show("Asset can not be added wrong name.");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

            } else {

                AssetModel assetModel1 = new AssetModel(textFieldName.getValue());

                //Description
                if (!textFieldDescription.isEmpty()) {
                    assetModel1.setDescription(textFieldDescription.getValue());
                }

                //Brand
                if (!textFieldBrand.isEmpty()) {
                    assetModel1.setBrand(textFieldBrand.getValue());
                }

                //Model
                if (!textFieldModel.isEmpty()) {
                    assetModel1.setModel(textFieldModel.getValue());
                }

                //Date Of Production
                if (!dateDateOfProduction.isEmpty()) {
                    assetModel1.setDateOfProduction(String.valueOf(dateDateOfProduction.getValue()));
                }

                //End Date Of Warranty
                if (!dateEndDateOfWarranty.isEmpty()) {
                    assetModel1.setEndDateOfWarranty(String.valueOf(dateEndDateOfWarranty.getValue()));
                }

                //Save asset to database
                assetRepo.save(assetModel1);

                //Show notification
                Notification notification = Notification.show("Asset added: " + textFieldName.getValue());
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

                //Clear all fields
                buttonClear.click();
            }});

        //Clear button Clicked
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

}
