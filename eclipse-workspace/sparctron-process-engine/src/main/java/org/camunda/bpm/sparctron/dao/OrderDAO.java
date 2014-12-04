package org.camunda.bpm.sparctron.dao;

import java.util.ArrayList;
import java.util.List;


public class OrderDAO {

    protected List<MaterialDAO>        materials;

    protected List<MissingMaterialDAO> missingMaterials;

    protected String                   customer;

    protected String                   specification;

    protected boolean                  allMaterialsAvailable;

    
    public OrderDAO(List<MaterialDAO> materials, List<MissingMaterialDAO> missingMaterials, String customer, String specification, boolean allMaterialsAvailable) {
        super();
        this.materials = materials;
        this.missingMaterials = missingMaterials;
        this.customer = customer;
        this.specification = specification;
        this.allMaterialsAvailable = allMaterialsAvailable;
    }
    
    public OrderDAO(String customer, String specification, boolean allMaterialsAvailable) {
        super();
        this.customer = customer;
        this.specification = specification;
        this.allMaterialsAvailable = allMaterialsAvailable;
        this.materials = new ArrayList<MaterialDAO>();
        this.missingMaterials = new ArrayList<MissingMaterialDAO>();
    }


    public OrderDAO() {
        this.materials = new ArrayList<MaterialDAO>();
        this.missingMaterials = new ArrayList<MissingMaterialDAO>();
    }

    public List<MaterialDAO> getMaterials() {
        return materials;
    }

    
    public void setMaterials(List<MaterialDAO> materials) {
        this.materials = materials;
    }

    
    public List<MissingMaterialDAO> getMissingMaterials() {
        return missingMaterials;
    }

    
    public void setMissingMaterials(List<MissingMaterialDAO> missingMaterials) {
        this.missingMaterials = missingMaterials;
    }

    
    public String getCustomer() {
        return customer;
    }

    
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    
    public String getSpecification() {
        return specification;
    }

    
    public void setSpecification(String specification) {
        this.specification = specification;
    }

    
    public boolean isAllMaterialsAvailable() {
        return allMaterialsAvailable;
    }

    
    public void setAllMaterialsAvailable(boolean allMaterialsAvailable) {
        this.allMaterialsAvailable = allMaterialsAvailable;
    }
    
    
    

}
