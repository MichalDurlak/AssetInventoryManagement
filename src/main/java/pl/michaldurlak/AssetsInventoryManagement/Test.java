package pl.michaldurlak.AssetsInventoryManagement;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.security.PermitAll;


@Route("test")
public class Test  extends  VerticalLayout{


    public Test() {

        EmailField validEmailField = new EmailField();
        validEmailField.setLabel("Email address");
        validEmailField.getElement().setAttribute("name", "email");
        validEmailField.setValue("julia.scheider@email.com");
        validEmailField.setErrorMessage("Please enter a valid email address");
        validEmailField.setClearButtonVisible(true);

        EmailField invalidEmailField = new EmailField();
        invalidEmailField.setLabel("Email address");
        invalidEmailField.getElement().setAttribute("name", "email");
        invalidEmailField.setValue("This is not an email");
        invalidEmailField.setErrorMessage("Please enter a valid email address");
        invalidEmailField.setClearButtonVisible(true);
        invalidEmailField.setInvalid(true);
        add(validEmailField, invalidEmailField);

    }



}
