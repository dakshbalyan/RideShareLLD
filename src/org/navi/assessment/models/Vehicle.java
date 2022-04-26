package org.navi.assessment.models;

import org.navi.assessment.enums.VehicleStatus;

public class Vehicle {
    private final String owner;
    private final String vehicleNum;
    private final String vehicleName;
    private VehicleStatus status;

    public Vehicle(String owner, String vehicleNum, String vehicleName) {
        this.owner = owner;
        this.vehicleNum = vehicleNum;
        this.vehicleName = vehicleName;
        this.status = VehicleStatus.AVAILABLE;
    }

    public String getOwner() {
        return owner;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleOwner='" + owner + '\'' +
                ", vehicleNum='" + vehicleNum + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                '}';
    }
}
