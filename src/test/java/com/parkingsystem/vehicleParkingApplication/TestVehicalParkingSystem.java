package com.parkingsystem.vehicleParkingApplication;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestVehicalParkingSystem extends VehicleParkingApplicationTests{

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	   }
	   protected <T> T mapFromJson(String json, Class<T> clazz)
	      throws JsonParseException, JsonMappingException, IOException {
	      
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.readValue(json, clazz);
	   }

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testVehicaldetails() throws Exception {
		 String uri = "/GetVehicleDetails";
		   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
		      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
		   String content = mvcResult.getResponse().getContentAsString();
		   VehicleEntry[] productlist = mapFromJson(content, VehicleEntry[].class);
		   assertTrue(productlist.length > 0);
	}
	
	
	@Test
	public void createVehicleEntry() throws Exception {
	   String uri = "/CreateVehicleEntry/{slot_id}";
	   VehicleEntry vehicleEntry = new VehicleEntry();
	   vehicleEntry.setSlot_id(1);
	   vehicleEntry.setIn_time(new Date());
	   vehicleEntry.setOut_time(new Date());
	   vehicleEntry.setParking_fee(20);
	   vehicleEntry.setVehicle_number(VehicleConstants.VEHICLE_NUMBER);
	   vehicleEntry.setAvailability(VehicleConstants.AVAILABLE);
	   vehicleEntry.setVehicleType(VehicleType.BIKE);
	   vehicleEntry.setEntry_gate(VehicleConstants.ENTRY_GATE_TRUE);
	   vehicleEntry.setExit_gate(VehicleConstants.EXIT_GATE_FALSE);
	   
	   String inputJson = mapToJson(vehicleEntry);
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(201, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertEquals(content, "VehicleEntry is created successfully");
	}
	
	@Test
	public void deleteProduct() throws Exception {
	   String uri = "/DeleteVehicleEntry/2";
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertEquals(content, "VehicleEntry is deleted successsfully.. (Vehicle Exited..)");
	}
	
	
}
