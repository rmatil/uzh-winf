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

import ch.uzh.winf.sparctron.dao.Material;
import ch.uzh.winf.sparctron.entity.BillOfMaterialEntity;

@Stateless
@Named
public class EnterBomBusinessLogic {

	// Inject the entity manager
	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private TaskForm taskForm;

	/**
	 * Persists a Specification
	 * 
	 * @param delegateExecution
	 */
	public void persistBom(DelegateExecution delegateExecution) {
		// Create new order instance
		BillOfMaterialEntity bomEntity = new BillOfMaterialEntity();

		// get process variables from form
		Map<String, Object> variables = delegateExecution.getVariables();
		
		int amountOfTransistorBc547 	= (Integer) variables.get("transistor-bc547");
		int amountOfTransistor2N3055 	= (Integer) variables.get("transistor-2n3055");
		int amountOfDiode1n4004 		= (Integer) variables.get("diode-1n4004");
		int amountOfCondensator100Uf 	= (Integer) variables.get("kondensator-100uf");
		int amountOfResistance470 		= (Integer) variables.get("widerstand-kohleschicht-470");
		
		for (int i = 0; i < amountOfTransistorBc547; i++) {
			Material m = new Material();
			m.setId(1L);
			m.setDescription("Transistor (BC547)");
			bomEntity.getMaterials().add(m);
		}
		for (int i = 0; i < amountOfTransistor2N3055; i++) {
			Material m = new Material();
			m.setId(2L);
			m.setDescription("Transistor (2N3055)");
			bomEntity.getMaterials().add(m);
		}
		for (int i = 0; i < amountOfDiode1n4004; i++) {
			Material m = new Material();
			m.setId(3L);
			m.setDescription("Diode (1N4004)");
			bomEntity.getMaterials().add(m);
		}
		for (int i = 0; i < amountOfCondensator100Uf; i++) {
			Material m = new Material();
			m.setId(4L);
			m.setDescription("Kondensator (100uF)");
			bomEntity.getMaterials().add(m);
		}
		for (int i = 0; i < amountOfResistance470; i++) {
			Material m = new Material();
			m.setId(5L);
			m.setDescription("Widerstand Kohleschicht (470 Ohm 5%)");
			bomEntity.getMaterials().add(m);
		}
		

		entityManager.persist(bomEntity);
		entityManager.flush();

		// Remove no longer needed process variables
		delegateExecution.removeVariables(variables.keySet());

		// Add newly created order id as process variable
		delegateExecution.setVariable("bomMaterialId", bomEntity.getId());
	}

	/**
	 * Get BillOfMaterialEntity by its id
	 * 
	 * @param specificationId
	 * @return SpecificationEntity
	 */
	public BillOfMaterialEntity getBillOfMaterialEntity(Long bomId) {
		return entityManager.find(BillOfMaterialEntity.class, bomId);
	}

	/**
	 * Merges MergeBillOfMaterialEntity and completes taskForm
	 * 
	 * @param bomEntity
	 */
	public void mergeBillOfMaterialAndCompleteTask(BillOfMaterialEntity bomEntity) {
		entityManager.merge(bomEntity);
		
		try {
			taskForm.completeTask();
		} catch (IOException ioe) {
			throw new RuntimeException("Cannot complete task: mergeBomEntity", ioe);
		}
	}

}
