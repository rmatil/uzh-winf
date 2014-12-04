package org.camunda.bpm.sparctron.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class MaterialEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    protected Long            id;

    @Column
    protected int             articleId;

    @Column
    protected String          description;

    @Column
    protected int             amount;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public int getArticleId() {
        return articleId;
    }


    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }



    public int getAmount() {
        return amount;
    }



    public void setAmount(int amount) {
        this.amount = amount;
    }

}
