package com.parkingsystem.vehicleParkingApplication;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;

import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.annotation.JsonFormat;

@ComponentScan																
public class VehicleEntry implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7283494808429454206L;

	@Max(value = 100, message = "The maximum building capacity is of 100 parking slots..")
	private Integer slot_id;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date in_time;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date out_time;
	private Integer parking_fee;
	private String vehicle_number;
	private String availability;
	private VehicleType vehicleType;
	private Boolean entry_gate;
	private Boolean exit_gate;

	public VehicleEntry() {

	}
	
	public VehicleEntry(
			@Max(value = 100, message = "The maximum building capacity is of 100 parking slots..") Integer slot_id,
			Date in_time, Date out_time, Integer parking_fee, String vehicle_number, String availability,
			VehicleType vehicleType, Boolean entry_gate, Boolean exit_gate) {
		super();
		this.slot_id = slot_id;
		this.in_time = in_time;
		this.out_time = out_time;
		this.parking_fee = parking_fee;
		this.vehicle_number = vehicle_number;
		this.availability = availability;
		this.vehicleType = vehicleType;
		this.entry_gate = entry_gate;
		this.exit_gate = exit_gate;
	}


	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}

	public Date getOut_time() {
		return out_time;
	}

	public void setOut_time(Date out_time) {
		this.out_time = out_time;
	}

	public Integer getParking_fee() {
		return parking_fee;
	}

	public void setParking_fee(Integer parking_fee) {
		this.parking_fee = parking_fee;
	}

	public String getVehicle_number() {
		return vehicle_number;
	}

	public void setVehicle_number(String vehicle_number) {
		this.vehicle_number = vehicle_number;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Integer getSlot_id() {
		return slot_id;
	}

	public void setSlot_id(Integer slot_id) {
		this.slot_id = slot_id;
	}
	

	public Boolean getEntry_gate() {
		return entry_gate;
	}

	public void setEntry_gate(Boolean entry_gate) {
		this.entry_gate = entry_gate;
	}

	public Boolean getExit_gate() {
		return exit_gate;
	}

	public void setExit_gate(Boolean exit_gate) {
		this.exit_gate = exit_gate;
	}

	@Override
	public String toString() {
		return "VehicleEntry [slot_id=" + slot_id + ", in_time=" + in_time + ", out_time=" + out_time + ", parking_fee="
				+ parking_fee + ", vehicle_number=" + vehicle_number + ", availability=" + availability
				+ ", vehicleType=" + vehicleType + ", entry_gate=" + entry_gate + ", exit_gate=" + exit_gate + "]";
	}




}
