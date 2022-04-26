package org.navi.assessment.controller;

import org.navi.assessment.service.VehicleService;

public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public void addVehicle(String name, String vehicleNum, String vehicleName){
        vehicleService.addVehicle(name, vehicleNum, vehicleName);
    }
}
