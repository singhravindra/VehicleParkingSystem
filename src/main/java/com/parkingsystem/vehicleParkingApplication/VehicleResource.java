package com.parkingsystem.vehicleParkingApplication;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.parkingsystem.Exception.SlotNotFoundException;

@RestController
public class VehicleResource {

	@Autowired
	private VehicleEntryService vehicleEntryService;
	
	@GetMapping("/GetVehicleDetails")
	public List<VehicleEntry> getAllVehicleEntries(){
		return vehicleEntryService.findAllVehicleEntries();		
	}

	@GetMapping("/GetVehicleDetails/{slot_id}")
	public VehicleEntry getVehicleEntryBySlotID(@PathVariable Integer slot_id){
		return vehicleEntryService.findVehicleBySlotID(slot_id);
	}
	
	@GetMapping("/TotalVehicalCount")
	public int getTotalVehicalEntries(){
		return vehicleEntryService.getTotalVehicalCount();		
	}
	
	@PostMapping("/CreateVehicleEntry/{slot_id}")
	public ResponseEntity<Object> createVehicleEntry(@RequestBody VehicleEntry vehicleEntry,
			@PathVariable Integer slot_id){
		VehicleEntry savedVehicleEntry = vehicleEntryService.createVehicleEntry(vehicleEntry,slot_id);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{slot_id}")
				.buildAndExpand(savedVehicleEntry.getSlot_id()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/DeleteVehicleEntry/{slot_id}")
	public void deleteVehicleEntry(@PathVariable Integer slot_id){
		VehicleEntry vehicleEntry = vehicleEntryService.clearSlot(slot_id);
		if(vehicleEntry==null){
			throw new SlotNotFoundException("Slot id:-" + slot_id);
		}		
		
	}
	
	

}
