package com.parkingsystem.vehicleParkingApplication;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.parkingsystem.Exception.SlotFullException;
import com.parkingsystem.Exception.SlotNotFoundException;

@RestController
@ComponentScan
public class VehicleResource {

	//private static Integer total_Vehicle_count = 0;

	@Autowired
	private VehicleEntryRepository VehicleEntryRepository;

	// For Getting all the Vehicle details
	@GetMapping("/GetVehicleDetails")
	public List<VehicleEntry> getAllVehicleEntries() {
		return VehicleEntryRepository.findAll();
	}
	
	// For Getting Specific Slot Vehicle details
	@GetMapping("/GetVehicleDetails/{slot_id}")
	public Optional<VehicleEntry> getSpecificSlotDetails(@PathVariable Integer slot_id) {
		Optional<VehicleEntry> vehicleEntry = VehicleEntryRepository.findById(slot_id);
		if (!vehicleEntry.isPresent()) {
			throw new SlotNotFoundException("Slot Id:-" + slot_id);
		}
		return vehicleEntry;
	}

	// For Deleting a Vehicle Entry at a given slot id
	@DeleteMapping("/DeleteVehicleDetails/{slot_id}")
	public void createVehicleExit(@PathVariable Integer slot_id) {
		if(VehicleEntryRepository.existsById(slot_id)){
			VehicleEntryRepository.deleteById(slot_id);			
		}else{
			throw new SlotNotFoundException("Slot Id:-" + slot_id);			
		}
	}
	

	// Creating a Vehicle Entry in that Specific slot ID
	@PostMapping("/CreateVehicleEntry/{slot_id}")
	public ResponseEntity<Object> createVehicleEntry(@Valid @RequestBody VehicleEntry vehicleEntry,
			@PathVariable int slot_id) {
		
		VehicleEntry savedVehicleEntry = null;
		
		if(VehicleEntryRepository.existsById(slot_id)){
			throw new SlotFullException("Slot Id:-" + slot_id);
		}else if (slot_id>100){
			throw new SlotNotFoundException("Slot Id:-" + slot_id);			
		}else{
			savedVehicleEntry = VehicleEntryRepository.save(vehicleEntry);
			//++total_Vehicle_count;
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/slot_id")
				.buildAndExpand(savedVehicleEntry.getSlot_id()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/TotalVehicleEntries")
	public long getTotalVehicleEntries() {
		return VehicleEntryRepository.count();
	}


}
