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

import ch.uzh.winf.sparctron.entity.SpecificationEntity;
 
@Stateless
@Named
public class DefineSpecificationBusinessLogic {
 
  // Inject the entity manager
  @PersistenceContext
  private EntityManager entityManager;
  
  @Inject
  private TaskForm taskForm;
 
  /**
   * Persists a Specification
   * @param delegateExecution
   */
  public void persistSpecification(DelegateExecution delegateExecution) {
    // Create new order instance
    SpecificationEntity specificationEntity = new SpecificationEntity();
 
    // get process variables from form
    Map<String, Object> variables = delegateExecution.getVariables();
    specificationEntity.setCustomer((String) variables.get("customer"));
    specificationEntity.setSpecification((String) variables.get("specification"));
    
    entityManager.persist(specificationEntity);
    entityManager.flush();
 
    // Remove no longer needed process variables
    delegateExecution.removeVariables(variables.keySet());
 
    // Add newly created order id as process variable
    delegateExecution.setVariable("specficationId", specificationEntity.getId());
  }
  
  /**
   * Get Specification by its id
   * @param specificationId
   * @return SpecificationEntity
   */
  public SpecificationEntity getSpecification(Long specificationId) {
	  return entityManager.find(SpecificationEntity.class, specificationId);
  }
  
  /**
   * Merges SpecificationEntity and completes taskForm
   * @param specificationEntity
   */
  public void mergeSpecificationAndCompleteTask(SpecificationEntity specificationEntity) {
	  entityManager.merge(specificationEntity);
	  try {
		  taskForm.completeTask();
	  } catch (IOException ioe) {
		  throw new RuntimeException("Cannot complete task: mergeSpecification", ioe);
	  }
  }
 
}
