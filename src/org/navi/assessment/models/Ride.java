package org.navi.assessment.models;

import org.navi.assessment.enums.RideStatus;

public class Ride {
    private final Vehicle vehicle;
    private final String origin;
    private final String destination;
    private int availableSeats;
    private RideStatus rideStatus;  // status of ride keeps a track of active inactive rides (endRides)

    public Ride(Vehicle vehicle, String origin, String destination, int availableSeats) {
        this.vehicle = vehicle;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.rideStatus = RideStatus.ACTIVE;
    }

    public void endRide(){
        this.rideStatus = RideStatus.INACTIVE;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public RideStatus getStatus() {
        return rideStatus;
    }

    public void decrementAvailableSeatsBy(int val) {
        this.availableSeats -= val;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "vehicle=" + vehicle +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", availableSeats=" + availableSeats +
                ", rideStatus=" + rideStatus +
                '}';
    }
}
