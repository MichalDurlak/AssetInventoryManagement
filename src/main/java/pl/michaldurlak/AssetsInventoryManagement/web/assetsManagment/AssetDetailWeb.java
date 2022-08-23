package pl.michaldurlak.AssetsInventoryManagement.web.assetsManagment;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import pl.michaldurlak.AssetsInventoryManagement.web.basics.NavbarLayout;

@Route(value = "asset")
public class AssetDetailWeb extends VerticalLayout
        implements HasUrlParameter<String> {


    public AssetDetailWeb() {
        getAssetDetailWeb();
    }

    TextField test = new TextField();

    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String parameter) {



        if (parameter == null) {
            test.setValue("Anon");
        } else {
            test.setValue(parameter);
        }
    }

    public void getAssetDetailWeb(){

        add(test);
    }
}
