package org.camunda.bpm.sparctron.controller;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.sparctron.logic.OrderBusinessLogic;

@Named
@ConversationScoped
public class CheckStockController
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


    public void checkStock(DelegateExecution delegateExecution) {
        long orderId = businessProcess.getVariable("orderId");
        orderBusinessLogic.checkStockForOrder(orderId);
    }
}
