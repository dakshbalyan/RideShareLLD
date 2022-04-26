package org.navi.assessment.repository;

import org.navi.assessment.models.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class VehicleRepository {
    Map<String, Vehicle> vehicleNumToVehicleMap;

    public VehicleRepository() {
        this.vehicleNumToVehicleMap = new HashMap<>();
    }

    public void save(Vehicle vehicle){
        vehicleNumToVehicleMap.put(vehicle.getVehicleNum(), vehicle);
    }

    public Vehicle findVehicleByVehicleNum(String vehicleNum) {
        return vehicleNumToVehicleMap.getOrDefault(vehicleNum,null);
    }
}
