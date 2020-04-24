package com.parkingsystem.vehicleParkingApplication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository // Interacts with the DB
@Transactional
public class VehicleEntryService {

	@PersistenceContext
	private EntityManager entityManager;


	public long insert(VehicleEntry vehicleEntry) {
		entityManager.persist(vehicleEntry);
		return vehicleEntry.getId();
	}

	public EntityManager findAll() {
		return entityManager;
	}


	public VehicleEntry save(VehicleEntry vehicleEntry, int slot_id) {
		entityManager.persist(vehicleEntry);
		return vehicleEntry;
	}

/*	public VehicleEntry clearSlot(int slot_id) {
		if (vehicleEntry.getSlot_id() == slot_id) {
			vehicleEntry.setAvailability("AVAILABLE");
			vehicleEntry.setParking_fee(0);
			vehicleEntry.setEntry_gate(false);
			vehicleEntry.setExit_gate(true);
			vehicleEntry.setIn_time(null);
			vehicleEntry.setOut_time(null);
			vehicleEntry.setSlot_id(slot_id);
			vehicleEntry.setVehicle_number(null);
			vehicleEntry.setVehicleType(null);
			return vehicleEntry;
		}

		return null;
	}*/

}
