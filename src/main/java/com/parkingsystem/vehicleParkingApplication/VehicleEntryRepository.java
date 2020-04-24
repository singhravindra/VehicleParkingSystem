package com.parkingsystem.vehicleParkingApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleEntryRepository extends JpaRepository<VehicleEntry, Integer>{

}
