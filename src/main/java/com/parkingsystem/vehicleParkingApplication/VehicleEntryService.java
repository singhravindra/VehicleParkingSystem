package com.parkingsystem.vehicleParkingApplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.parkingsystem.Exception.SlotNotFoundException;

@Component
public class VehicleEntryService {
		
	private static List<VehicleEntry> vehicalEntries = new ArrayList<>();
	private static List<VehicleEntry> CopiedVehicalEntries = new CopyOnWriteArrayList<>(vehicalEntries);

		
	public List<VehicleEntry> findAllVehicleEntries(){	
		for(int i=0; i<CopiedVehicalEntries.size(); i++){
			if((CopiedVehicalEntries.get(i).getVehicleType()==null)){
				 CopiedVehicalEntries.remove(i);
			}
		}

		return CopiedVehicalEntries;
	}
	
	public VehicleEntry createVehicleEntry(VehicleEntry vehicleEntry, Integer slot_id){
				
		if(VehicleConstants.firstInput<1){
			vehicleEntry.setSlot_id(slot_id);
			CopiedVehicalEntries.add(vehicleEntry);
			++VehicleConstants.firstInput;
		}else{
			vehicleEntry.setSlot_id(slot_id);
			CopiedVehicalEntries.add(vehicleEntry);
		}

		if(vehicleEntry.getEntry_gate().equals(VehicleConstants.AVAILABLE)){
			++VehicleConstants.totalVehicleCount;
		}
		
		return vehicleEntry;
		
	}
	
	public VehicleEntry findVehicleBySlotID(int slot_id){
		for(VehicleEntry vehicleEntry : CopiedVehicalEntries){
			if(vehicleEntry.getSlot_id()==slot_id){
				return vehicleEntry;
			}
		}
		throw new SlotNotFoundException("slot_id" + slot_id);
	}
	
	public VehicleEntry clearSlot(Integer slot_id) {
		Iterator<VehicleEntry> iterator = CopiedVehicalEntries.iterator();
		while(iterator.hasNext()){
			VehicleEntry vehicleEntry = iterator.next();
			if(vehicleEntry.getSlot_id() == slot_id){
				vehicleEntry.setAvailability(VehicleConstants.AVAILABLE);
				vehicleEntry.setParking_fee(0);
				vehicleEntry.setEntry_gate(VehicleConstants.ENTRY_GATE_FALSE);
				vehicleEntry.setExit_gate(VehicleConstants.EXIT_GATE_TRUE);
				vehicleEntry.setIn_time(null);
				vehicleEntry.setOut_time(null);
				vehicleEntry.setSlot_id(slot_id);
				vehicleEntry.setVehicle_number(null);
				vehicleEntry.setVehicleType(null);
				return vehicleEntry;
			}
		}

		return null;
	}
	
	public int getTotalVehicalCount(){		
		return VehicleConstants.totalVehicleCount;				
	}
	
			

}
