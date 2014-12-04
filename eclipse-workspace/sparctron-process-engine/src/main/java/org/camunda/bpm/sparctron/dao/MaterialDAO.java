package org.camunda.bpm.sparctron.dao;


public class MaterialDAO {
    
    protected int articleId;
    
    protected String description;
    
    protected int amount;
    
    
    public int getAmount() {
        return amount;
    }

    
    public void setAmount(int amount) {
        this.amount = amount;
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

}
