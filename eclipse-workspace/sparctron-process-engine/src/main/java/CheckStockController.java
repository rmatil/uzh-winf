
import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.cdi.BusinessProcess;

import ch.uzh.winf.sparctron.dao.Material;
import ch.uzh.winf.sparctron.entity.BillOfMaterialEntity;
import ch.uzh.winf.sparctron.logic.EnterBomBusinessLogic;
import ch.uzh.winf.stockservice.StockService;
import ch.uzh.winf.stockservice.StockServiceImplService;


@Named
@ConversationScoped
public class CheckStockController
        implements Serializable {

    private static final long     serialVersionUID = 1L;

    // Inject the BusinessProcess to access the process variables
    @Inject
    private BusinessProcess       businessProcess;

    // Inject the EntityManager to access the persisted order
    @PersistenceContext
    private EntityManager         entityManager;

    // Inject the OrderBusinessLogic to update the persisted order
    @Inject
    private EnterBomBusinessLogic enterBomBusinessLogic;

    // Caches the OrderEntity during the conversation
    private BillOfMaterialEntity  billOfMaterialEntity;

    public BillOfMaterialEntity getBillOfMaterialEntity() {
        if (billOfMaterialEntity == null) {
            // Load the order entity from the database if not already cached
            billOfMaterialEntity = enterBomBusinessLogic.getBillOfMaterialEntity((Long) businessProcess.getVariable("bomId"));
        }

        return billOfMaterialEntity;
    }

    public void submitForm()
            throws IOException {
        
        checkAvailability(billOfMaterialEntity);
        
        // Persist updated order entity and complete task form
        enterBomBusinessLogic.mergeBillOfMaterialAndCompleteTask(billOfMaterialEntity);
    }

    private void checkAvailability(BillOfMaterialEntity bom) {
        StockServiceImplService sis = new StockServiceImplService();
        StockService s = sis.getStockServiceImplPort();
        
        for (Material m : bom.getMaterials()) {
            if (s.getQuantityInStock(m.getId()) < 1) {
                bom.setAllMaterialsAvailable(false);
                break;
            }
        }

        bom.setAllMaterialsAvailable(true);
    }
}
