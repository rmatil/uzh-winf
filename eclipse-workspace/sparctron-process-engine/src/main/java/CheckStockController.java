
 
import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.cdi.BusinessProcess;

import ch.uzh.winf.sparctron.entity.SpecificationEntity;
import ch.uzh.winf.sparctron.logic.DefineSpecificationBusinessLogic;
 
@Named
@ConversationScoped
public class CheckStockController implements Serializable {
 
  private static  final long serialVersionUID = 1L;
 
  // Inject the BusinessProcess to access the process variables
  @Inject
  private BusinessProcess businessProcess;
 
  // Inject the EntityManager to access the persisted order
  @PersistenceContext
  private EntityManager entityManager;
 
  // Inject the OrderBusinessLogic to update the persisted order
  @Inject
  private DefineSpecificationBusinessLogic defineSpecificationBusinessLogic;
 
  // Caches the OrderEntity during the conversation
  private SpecificationEntity specificationEntity;
 
  public SpecificationEntity getSpecificationEntity() {
    if (specificationEntity == null) {
      // Load the order entity from the database if not already cached
      specificationEntity = defineSpecificationBusinessLogic.getSpecification((Long) businessProcess.getVariable("specificationId"));
    }
    
    return specificationEntity;
  }
 
  public void submitForm() throws IOException {
    // Persist updated order entity and complete task form
    defineSpecificationBusinessLogic.mergeSpecificationAndCompleteTask(specificationEntity);
  }
}
