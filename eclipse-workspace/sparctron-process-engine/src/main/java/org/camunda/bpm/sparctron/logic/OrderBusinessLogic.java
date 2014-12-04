package org.camunda.bpm.sparctron.logic;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.sparctron.dao.MaterialDAO;
import org.camunda.bpm.sparctron.dao.OrderDAO;
import org.camunda.bpm.sparctron.entity.MaterialEntity;
import org.camunda.bpm.sparctron.entity.MissingMaterialEntity;
import org.camunda.bpm.sparctron.entity.OrderEntity;
import org.camunda.bpm.stockservice.StockService;
import org.camunda.bpm.stockservice.StockServiceImplService;

@Stateless
@Named
public class OrderBusinessLogic {

    // Inject the entity manager
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private TaskForm      taskForm;

    private StockService  stockService;

    public long persist(OrderDAO orderDAO) {
        OrderEntity oe = new OrderEntity();
        oe.setCustomer(orderDAO.getCustomer());
        oe.setSpecification(orderDAO.getSpecification());
        oe.setAllMaterialsAvailable(true);

        entityManager.persist(oe);
        entityManager.flush();

        return oe.getId();
    }

    public void persistMaterialForOrderEntity(long orderEntityId, MaterialDAO materialDAO) {
        MaterialEntity material = new MaterialEntity();
        material.setAmount(materialDAO.getAmount());
        material.setArticleId(materialDAO.getArticleId());
        
        String materialDescription = null;
        if (null != (materialDescription = getStockService().getComponentNameById(material.getArticleId()))) {
            material.setDescription(materialDescription);
        } else {
            // material not found
            throw new RuntimeException("Material with id " + material.getArticleId() + " not found");
        }
        
        OrderEntity orderEntity = getOrderEntity(orderEntityId);
        orderEntity.getMaterials().add(material);
        
        entityManager.merge(orderEntity);
        entityManager.flush();
    }

    private StockService getStockService() {
        if (null == stockService) {
            StockServiceImplService sis = new StockServiceImplService();
            stockService = sis.getStockServiceImplPort();
        }
        
        return stockService;
    }
    
    public OrderDAO getOrderDAO(long orderId) {
        OrderEntity orderEntity = getOrderEntity(orderId);
        OrderDAO orderDAO = new OrderDAO(orderEntity.getCustomer(), orderEntity.getSpecification(), orderEntity.isAllMaterialsAvailable());
    
        for (MaterialEntity material : orderEntity.getMaterials()) {
            MaterialDAO materialDAO = new MaterialDAO();
            materialDAO.setArticleId(material.getArticleId());
            materialDAO.setAmount(material.getAmount());
            materialDAO.setDescription(material.getDescription());
            
            orderDAO.getMaterials().add(materialDAO);
        }
        
        return orderDAO;
    }

    public void checkStockForOrder(long orderId) {
        StockService stockService = getStockService();
        OrderEntity orderEntity = getOrderEntity(orderId);
        
        boolean allMaterialsAvailable = true;
        for (MaterialEntity materialEntity : orderEntity.getMaterials()) {
            // check status in stock
            int amountAvailable = stockService.getQuantityInStock(materialEntity.getArticleId());
            if (amountAvailable < materialEntity.getAmount()) {
                // place new order
                allMaterialsAvailable = false;
                break;
            }
        }
        // update status
        orderEntity.setAllMaterialsAvailable(allMaterialsAvailable);
        
        entityManager.merge(orderEntity);
        entityManager.flush();
    }
    
    public void generateMissingMaterialOrder(long orderId) {
        StockService stockService = getStockService();
        OrderEntity orderEntity = getOrderEntity(orderId);
        
        for (MaterialEntity materialEntity : orderEntity.getMaterials()) {
            int amountAvailable = stockService.getQuantityInStock(materialEntity.getArticleId());
            if (amountAvailable < materialEntity.getAmount()) {
                MissingMaterialEntity mm = new MissingMaterialEntity();
                
                mm.setAmountsMissing(materialEntity.getAmount() - amountAvailable);
                orderEntity.getMissingMaterials().add(mm);
            }
        }
        
        entityManager.merge(orderEntity);
        entityManager.flush();
    }
    
    public void acknowledgeOrder(long orderId) {
        OrderEntity orderEntity = getOrderEntity(orderId);
        // acknowledge order
        orderEntity.getMissingMaterials().clear();
        
        entityManager.merge(orderEntity);
        entityManager.flush();
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


}
