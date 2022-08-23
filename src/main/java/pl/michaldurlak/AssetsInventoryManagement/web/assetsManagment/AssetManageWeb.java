package pl.michaldurlak.AssetsInventoryManagement.web.assetsManagment;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/asset")
@PageTitle("{id} info")
public class AssetManageWeb extends VerticalLayout {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void  getDeatailsOfAsset(@PathVariable Long id) {
        getAssetManageWeb(id);
    }

    public AssetManageWeb() {
    }

    public void getAssetManageWeb(Long id){
        TextField textFieldId = new TextField(id.toString());
        add(textFieldId);
    }

}


