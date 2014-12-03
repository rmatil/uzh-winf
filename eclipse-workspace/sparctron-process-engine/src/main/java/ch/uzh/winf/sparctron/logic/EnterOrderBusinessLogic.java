package ch.uzh.winf.sparctron.logic;

import java.io.IOException;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import ch.uzh.winf.sparctron.entity.OrderEntity;
import ch.uzh.winf.sparctron.model.Material;
import ch.uzh.winf.stockservice.StockService;
import ch.uzh.winf.stockservice.StockServiceImplService;


@Stateless
@Named
public class EnterOrderBusinessLogic {

    // Inject the entity manager
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private TaskForm      taskForm;

    /**
     * Persists a Specification
     * 
     * @param delegateExecution
     */
    public void persistOrderEntity(DelegateExecution delegateExecution) {
        // Create new order instance
        OrderEntity orderEntity = new OrderEntity();

        // get process variables from form
        Map<String, Object> variables = delegateExecution.getVariables();
        
        orderEntity.setCustomer((String) variables.get("customer"));
        orderEntity.setSpecification((String) variables.get("specification"));

        int amountOfTransistorBc547 = (Integer) variables.get("transistor-bc547");
        int amountOfTransistor2N3055 = (Integer) variables.get("transistor-2n3055");
        int amountOfDiode1n4004 = (Integer) variables.get("diode-1n4004");
        int amountOfCondensator100Uf = (Integer) variables.get("kondensator-100uf");
        int amountOfResistance470 = (Integer) variables.get("widerstand-kohleschicht-470");

        for (int i = 0; i < amountOfTransistorBc547; i++) {
            Material m = new Material();
            m.setId(1);
            m.setDescription("Transistor (BC547)");
            orderEntity.getMaterials().add(m);
        }
        for (int i = 0; i < amountOfTransistor2N3055; i++) {
            Material m = new Material();
            m.setId(2);
            m.setDescription("Transistor (2N3055)");
            orderEntity.getMaterials().add(m);
        }
        for (int i = 0; i < amountOfDiode1n4004; i++) {
            Material m = new Material();
            m.setId(3);
            m.setDescription("Diode (1N4004)");
            orderEntity.getMaterials().add(m);
        }
        for (int i = 0; i < amountOfCondensator100Uf; i++) {
            Material m = new Material();
            m.setId(4);
            m.setDescription("Kondensator (100uF)");
            orderEntity.getMaterials().add(m);
        }
        for (int i = 0; i < amountOfResistance470; i++) {
            Material m = new Material();
            m.setId(5);
            m.setDescription("Widerstand Kohleschicht (470 Ohm 5%)");
            orderEntity.getMaterials().add(m);
        }


        entityManager.persist(orderEntity);
        entityManager.flush();

        // Remove no longer needed process variables
        delegateExecution.removeVariables(variables.keySet());

        // Add newly created order id as process variable
        delegateExecution.setVariable("orderId", orderEntity.getId());
    }

    /**
     * Get BillOfMaterialEntity by its id
     * 
     * @param specificationId
     * @return SpecificationEntity
     */
    public OrderEntity getOrderEntity(Long orderId) {
        return entityManager.find(OrderEntity.class, orderId);
    }

    /**
     * Merges MergeBillOfMaterialEntity and completes taskForm
     * 
     * @param orderEntity
     */
    public void mergeOrderAndCompleteTask(OrderEntity orderEntity) {
        entityManager.merge(orderEntity);

        try {
            taskForm.completeTask();
        } catch (IOException ioe) {
            throw new RuntimeException("Cannot complete task: mergeOrderEntity", ioe);
        }
    }

    public void checkAvailability(DelegateExecution delegateExecution) {
        persistOrderEntity(delegateExecution);
        
        OrderEntity orderEntity = getOrderEntity((Long) delegateExecution.getVariable("orderId"));

        StockServiceImplService sis = new StockServiceImplService();
        StockService s = sis.getStockServiceImplPort();

        boolean allMaterialsAvailable = true;
        for (Material m : orderEntity.getMaterials()) {
            if (s.getQuantityInStock(m.getId()) < 1) {
                allMaterialsAvailable = false;
                break;
            }
        }

        orderEntity.setAllMaterialsAvailable(allMaterialsAvailable);
        
        entityManager.refresh(orderEntity);
        entityManager.flush();
    }
    
    public void createMaterialOrder(DelegateExecution delegateExecution) {
        OrderEntity orderEntity = getOrderEntity((Long) delegateExecution.getVariable("orderId"));
        
        StockServiceImplService sis = new StockServiceImplService();
        StockService s = sis.getStockServiceImplPort();
        
        // i know this is stupid, but who cares
        for (Material m : orderEntity.getMaterials()) {
            if (s.getQuantityInStock(m.getId()) < 1) {
                
                int amountOfMissingMaterial = 0;
                for (Material mm : orderEntity.getMaterials()) {
                    if (mm.getId() == m.getId()) {
                        amountOfMissingMaterial++;
                    }
                }
                
                orderEntity.getMissingMaterials().put(m, amountOfMissingMaterial);
            }
        }
    }

}
