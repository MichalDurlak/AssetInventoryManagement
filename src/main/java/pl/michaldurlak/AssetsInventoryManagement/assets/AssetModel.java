package pl.michaldurlak.AssetsInventoryManagement.assets;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class AssetModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //global
    private String name;
    private String description;
    private String qrCode;
    private String brand;
    private String model;
    private String dateOfProduction;
    private String endDateOfWarranty;
    private int quantityInStock;
    private int currentlyInStock;
    private String urlToImage;



    public AssetModel() {
    }

    public AssetModel(String name) {
        this.name = name;
    }

    public AssetModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public AssetModel(String name, String description, String brand, String model) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.model = model;
    }


}
