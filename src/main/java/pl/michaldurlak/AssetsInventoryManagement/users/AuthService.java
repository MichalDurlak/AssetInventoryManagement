package pl.michaldurlak.AssetsInventoryManagement.users;

import com.vaadin.flow.component.Component;

public class AuthService {
    public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {

    }


    public class AuthException extends Exception {

    }


}
