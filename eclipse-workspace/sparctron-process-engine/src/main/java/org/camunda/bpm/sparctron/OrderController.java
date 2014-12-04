package org.camunda.bpm.sparctron;
import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.sparctron.entity.OrderEntity;
import org.camunda.bpm.sparctron.logic.EnterOrderBusinessLogic;



@Named
@ConversationScoped
public class OrderController
        implements Serializable {

    private static final long       serialVersionUID = 1L;

    @Inject
    private BusinessProcess         businessProcess;

    @PersistenceContext
    private EntityManager           entityManager;

    @Inject
    private EnterOrderBusinessLogic enterOrderBusinessLogic;

    private OrderEntity             orderEntity;

    public OrderEntity getBillOfMaterialEntity() {
        if (orderEntity == null) {
            orderEntity = enterOrderBusinessLogic.getOrderEntity((Long) businessProcess.getVariable("bomId"));
        }

        return orderEntity;
    }

    public void submitForm()
            throws IOException {

        // Persist updated order entity and complete task form
        enterOrderBusinessLogic.mergeOrderAndCompleteTask(orderEntity);
    }
    
    public void acknowledgeOrder() {
        orderEntity.getMissingMaterials().clear();
        
        enterOrderBusinessLogic.mergeOrderAndCompleteTask(orderEntity);
    }
}
