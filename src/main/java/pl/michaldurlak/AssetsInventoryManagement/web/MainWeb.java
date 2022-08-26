package pl.michaldurlak.AssetsInventoryManagement.web;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import pl.michaldurlak.AssetsInventoryManagement.web.basics.NavbarLayout;



@Route(value = "", layout = NavbarLayout.class)
public class MainWeb extends VerticalLayout {

    public MainWeb() {
        TextField textField = new TextField("test");
        add(textField);
    }
}
