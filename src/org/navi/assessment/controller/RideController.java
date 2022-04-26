package org.navi.assessment.controller;

import org.navi.assessment.models.RideSelectionRequest;
import org.navi.assessment.service.RideService;

public class RideController {
    private RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    public void offerRide(String vehicleOwner, String origin, int availableSeats,
                          String vehicleName, String vehicleNum, String destination){
        try {
            rideService.createRide(vehicleOwner, origin, availableSeats, vehicleName,
                    vehicleNum, destination);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void selectRide(RideSelectionRequest rideRequest){
         if (rideService.selectRide(rideRequest).isPresent())
             System.out.println("Ride selected for "+rideRequest.getRequesterName());
         else
             System.out.println("No ride found for request : "+rideRequest);
    }
    public void endRide(String vehicleNum){
        rideService.endRide(vehicleNum);
    }

}
