package pl.michaldurlak.AssetsInventoryManagement.web.assets_managment;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.apache.catalina.webresources.FileResource;
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
            getUI().get().getPage().setLocation("/asset/"+textFieldId.getValue());
        });

        add(textFieldId);
        add(buttonSearch);

    }

    public void getSpecifyAssetManageWeb(Long id){
        //remove layout
        removeAll();

        try{

            HorizontalLayout horizontalLayout = new HorizontalLayout();
            VerticalLayout leftPanel = new VerticalLayout();
            VerticalLayout rightPanel = new VerticalLayout();

            horizontalLayout.add(leftPanel);
            horizontalLayout.add(rightPanel);


            Optional<AssetModel> assets = assetRepo.findById(id);

            // ID
            TextField textFieldID = new TextField();
            textFieldID.setLabel("ID");
            textFieldID.setValue(String.valueOf(assets.get().getId()));
            textFieldID.setReadOnly(true);
            leftPanel.add(textFieldID);

            // NAME
            TextField textFieldName = new TextField();
            textFieldName.setLabel("Name");
            textFieldName.setValue(String.valueOf(assets.get().getName()));
            textFieldName.setReadOnly(true);
            leftPanel.add(textFieldName);

            // DESCRIPTION
            TextField textFieldDescription = new TextField();
            textFieldDescription.setLabel("Description");
            textFieldDescription.setValue(String.valueOf(assets.get().getDescription()));
            textFieldDescription.setReadOnly(true);
            leftPanel.add(textFieldDescription);

            // BRAND
            TextField textFieldBrand = new TextField();
            textFieldBrand.setLabel("Brand");
            textFieldBrand.setValue(String.valueOf(assets.get().getBrand()));
            textFieldBrand.setReadOnly(true);
            leftPanel.add(textFieldBrand);

            // MODEL
            TextField textFieldModel = new TextField();
            textFieldModel.setLabel("Model");
            textFieldModel.setValue(String.valueOf(assets.get().getModel()));
            textFieldModel.setReadOnly(true);
            leftPanel.add(textFieldModel);

            // DATE OF PRODUCTION
            TextField textFieldDateOfProduction = new TextField();
            textFieldDateOfProduction.setLabel("Date of Production");
            textFieldDateOfProduction.setValue(String.valueOf(assets.get().getDateOfProduction()));
            textFieldDateOfProduction.setReadOnly(true);
            leftPanel.add(textFieldDateOfProduction);

            // END DATE OF WARRANTY
            TextField textFieldEndDateOfWarranty = new TextField();
            textFieldEndDateOfWarranty.setLabel("End Date of Warranty");
            textFieldEndDateOfWarranty.setValue(String.valueOf(assets.get().getEndDateOfWarranty()));
            textFieldEndDateOfWarranty.setReadOnly(true);
            leftPanel.add(textFieldEndDateOfWarranty);


            // SHOW ASSET'S IMAGE
            Text imageText = new Text("Asset's Image");
            Image imageAssetImage = new Image(assets.get().getUrlToImage(), "No image");
            rightPanel.add(imageText);
            rightPanel.add(imageAssetImage);


            // SHOW QR CODE IMAGE
            Button printButton = new Button("Print This QR Code");
            printButton.addClickListener( ( ClickEvent < Button > clickEvent ) ->
            {
                UI.getCurrent().getPage().executeJs( "print();" );
            } );

            Text qrText = new Text("Asset's QR Code");
            Image imageQrCode = new Image(assets.get().getQrCode(), "No QR Code");
            rightPanel.add(qrText);
            rightPanel.add(imageQrCode);
            rightPanel.add(printButton);



            add(horizontalLayout);


        } catch (Exception e){

            Text textError = new Text("ERROR 404");
            add(textError);
            Notification notification = Notification.show("Can't find asset");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        }




    }

}


