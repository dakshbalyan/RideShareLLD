package org.navi.assessment.repository;

import org.navi.assessment.models.Ride;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideRepository {
    // the num of rides <= number of vehicles
    Map<String, Ride> vehicleNumToRideMap ;

    public RideRepository() {
        this.vehicleNumToRideMap = new HashMap<>();
    }

    public void save(Ride ride) {
        vehicleNumToRideMap.put(ride.getVehicle().getVehicleNum(), ride);
    }

    public Ride findRideByVehicleNum(String vehicleNum) {
        return vehicleNumToRideMap.getOrDefault(vehicleNum, null);
    }

    public List<Ride> getAllAvailableRides(){
        return new ArrayList<>(vehicleNumToRideMap.values());

    }
}
