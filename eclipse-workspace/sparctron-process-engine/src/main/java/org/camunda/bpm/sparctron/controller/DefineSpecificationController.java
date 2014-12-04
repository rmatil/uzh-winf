package org.camunda.bpm.sparctron.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.sparctron.dao.OrderDAO;
import org.camunda.bpm.sparctron.logic.OrderBusinessLogic;

@Named
@ConversationScoped
public class DefineSpecificationController
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

    public DefineSpecificationController() {
        orderDAO = new OrderDAO();
    }
    
    
    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void submitForm()
            throws IOException {
        
        long orderId = orderBusinessLogic.persist(orderDAO);

        // save orderId for next task
        businessProcess.setVariable("orderId", orderId);
        
        try {
            taskForm.completeTask();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
    
    
}
