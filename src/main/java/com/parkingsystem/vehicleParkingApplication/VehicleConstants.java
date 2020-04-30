package com.parkingsystem.vehicleParkingApplication;

import org.springframework.stereotype.Component;

@Component
public class VehicleConstants {

	public static final String AVAILABLE = "AVAILABLE";
	public static final String OCCUPIED = "OCCUPIED";
	public static int totalVehicleCount = 0;
	public static int firstInput = 0;
	public static final String VEHICLE_NUMBER = "MH 01 100";
	public static Boolean ENTRY_GATE_TRUE = true;
	public static Boolean ENTRY_GATE_FALSE = false;
	public static Boolean EXIT_GATE_TRUE = true;
	public static Boolean EXIT_GATE_FALSE = false;
}
