package org.camunda.bpm.sparctron.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;




@Entity
public class OrderEntity
        implements Serializable {

    private static final long             serialVersionUID = 1L;

    @Id
    @GeneratedValue
    protected Long                        id;

    @Version
    protected long                        version;

    @OneToMany(cascade = CascadeType.ALL)
    protected List<MaterialEntity>        materials;

    @OneToMany(cascade = CascadeType.ALL)
    protected List<MissingMaterialEntity> missingMaterials;

    @Column
    protected String                      customer;

    @Column
    protected String                      specification;

    @Column
    protected boolean                     allMaterialsAvailable;

    public OrderEntity() {
        materials = new ArrayList<MaterialEntity>();
        missingMaterials = new ArrayList<MissingMaterialEntity>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public List<MaterialEntity> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialEntity> materials) {
        this.materials = materials;
    }



    public List<MissingMaterialEntity> getMissingMaterials() {
        return missingMaterials;
    }


    public void setMissingMaterials(List<MissingMaterialEntity> missingMaterials) {
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    public boolean isAllMaterialsAvailable() {
        return allMaterialsAvailable;
    }


    public void setAllMaterialsAvailable(boolean allMaterialsAvailable) {
        this.allMaterialsAvailable = allMaterialsAvailable;
    }



}
