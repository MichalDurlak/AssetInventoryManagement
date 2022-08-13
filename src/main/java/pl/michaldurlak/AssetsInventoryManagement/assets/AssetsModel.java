package pl.michaldurlak.AssetsInventoryManagement.assets;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class AssetsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;
    private String name;
    private String description;
    private String responsiblePerson;
    private String photoOfAsset;
    private String qrCode;
    private int quantity;
    private int actualQuantity;
    private String status;

    public AssetsModel() {
    }

    public AssetsModel(String type, String name, String description, int quantity) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }
}
