package ch.uzh.winf.sparctron.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import ch.uzh.winf.sparctron.model.Material;


@Entity
public class OrderEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    protected Long            id;

    @Version
    protected long            version;

    @OneToMany
    protected List<Material>  materials;

    protected String          customer;
    protected String          specification;
    protected boolean         allMaterialsAvailable;



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

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
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
