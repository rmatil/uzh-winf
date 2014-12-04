package org.camunda.bpm.sparctron.controller;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.sparctron.dao.MaterialDAO;
import org.camunda.bpm.sparctron.dao.OrderDAO;
import org.camunda.bpm.sparctron.logic.OrderBusinessLogic;

@Named
@ConversationScoped
public class EnterBomController
        implements Serializable {

    private static final long  serialVersionUID = 1L;

    @Inject
    private BusinessProcess    businessProcess;

    @Inject
    private TaskForm           taskForm;

    @PersistenceContext
    private EntityManager      entityManager;

    @Inject
    private OrderBusinessLogic orderBusinessLogic;

    private OrderDAO           orderDAO;
    private MaterialDAO        materialDAO;

    public EnterBomController() {
        materialDAO = new MaterialDAO();
    }

    
    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    
    public MaterialDAO getMaterialDAO() {
        return materialDAO;
    }

    
    public void setMaterialDAO(MaterialDAO materialDAO) {
        this.materialDAO = materialDAO;
    }

    public void addMaterial() {
        long orderEntityId = businessProcess.getVariable("orderId");
        orderBusinessLogic.persistMaterialForOrderEntity(orderEntityId, materialDAO);

        // update orderDAO to represent the new added material on view
        materialDAO = new MaterialDAO(); // reset values
        long orderId = businessProcess.getVariable("orderId");
        orderDAO = orderBusinessLogic.getOrderDAO(orderId);
    }

}
