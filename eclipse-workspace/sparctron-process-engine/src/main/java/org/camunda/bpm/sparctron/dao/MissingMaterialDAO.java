package org.camunda.bpm.sparctron.dao;



public class MissingMaterialDAO {

    private MaterialDAO material;

    private int         amountMissing;

    public MissingMaterialDAO(MaterialDAO material, int amountMissing) {
        super();
        this.material = material;
        this.amountMissing = amountMissing;
    }

    
    public MaterialDAO getMaterial() {
        return material;
    }

    
    public void setMaterial(MaterialDAO material) {
        this.material = material;
    }

    
    public int getAmountMissing() {
        return amountMissing;
    }

    
    public void setAmountMissing(int amountMissing) {
        this.amountMissing = amountMissing;
    }
    
    
}
