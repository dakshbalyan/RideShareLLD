package org.navi.assessment.service;

import org.navi.assessment.exceptions.ResourceExistenceException;
import org.navi.assessment.models.Vehicle;
import org.navi.assessment.repository.VehicleRepository;

public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserService userService;

    public VehicleService(VehicleRepository vehicleRepository, UserService userService) {
        this.vehicleRepository = vehicleRepository;
        this.userService = userService;
    }

    public void addVehicle(String name, String vehicleNum, String vehicleName) {
        try{
            if (!userService.checkUserExists(name)) {
                throw new ResourceExistenceException("User : " + name + " doesn't exist!");
            }
            if (checkVehicleExists(vehicleNum)) {
                throw new ResourceExistenceException("Vehicle : " + vehicleNum + " already exist!");
            }

            Vehicle vehicle = new Vehicle(name, vehicleNum, vehicleName);
            vehicleRepository.save(vehicle);

        } catch (ResourceExistenceException e){
            System.err.println(e.getMessage());
        }
    }

    public boolean checkVehicleExists(String vehicleNum){
        return vehicleRepository.findVehicleByVehicleNum(vehicleNum) != null;
    }

    public boolean checkVehicleOwner(String vehicleOwner, String vehicleNum) {
        Vehicle vehicle = vehicleRepository.findVehicleByVehicleNum(vehicleNum);
        if (vehicle != null)
            return vehicleOwner.equals(vehicle.getOwner());
        else
            return false;
    }

    public Vehicle getVehicleByVehicleNum(String vehicleNum){
        return vehicleRepository.findVehicleByVehicleNum(vehicleNum);
    }
}
