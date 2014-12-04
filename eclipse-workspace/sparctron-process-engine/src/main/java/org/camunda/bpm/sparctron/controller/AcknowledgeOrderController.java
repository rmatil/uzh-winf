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
public class AcknowledgeOrderController
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
   
    public OrderDAO getOrderDAO() {
        long orderId = businessProcess.getVariable("orderId");
        this.orderDAO = orderBusinessLogic.getOrderDAO(orderId);

        return this.orderDAO;
    }

    public void acknowledgeOrder()
            throws IOException {
        long orderId = businessProcess.getVariable("orderId");
        orderBusinessLogic.acknowledgeOrder(orderId);

        try {
            taskForm.completeTask();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
