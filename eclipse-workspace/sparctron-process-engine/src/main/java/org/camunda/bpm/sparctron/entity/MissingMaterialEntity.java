package org.camunda.bpm.sparctron.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class MissingMaterialEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int               id;

    @OneToOne
    private MaterialEntity          material;
    private int               amountsMissing;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public MaterialEntity getMaterial() {
        return material;
    }
    
    public void setMaterial(MaterialEntity material) {
        this.material = material;
    }
    
    public int getAmountsMissing() {
        return amountsMissing;
    }
    
    public void setAmountsMissing(int amountsMissing) {
        this.amountsMissing = amountsMissing;
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    
}
