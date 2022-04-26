package org.navi.assessment.models;

public class RideSelectionRequest {
    private final String requesterName;
    private final String origin;
    private final String destination;
    private final int numOfSeats;
    private final String strategy;
    private final String preferredVehicle;

    public RideSelectionRequest(String requesterName, String origin, String destination, int numOfSeats, String strategy, String preferredVehicle) {
        this.requesterName = requesterName;
        this.origin = origin;
        this.destination = destination;
        this.numOfSeats = numOfSeats;
        this.strategy = strategy;
        this.preferredVehicle = preferredVehicle;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getPreferredVehicle() {
        return preferredVehicle;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public String getStrategy() {
        return strategy;
    }

    @Override
    public String toString() {
        return "RideSelectionRequest{" +
                "requesterName='" + requesterName + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", numOfSeats=" + numOfSeats +
                ", strategy='" + strategy + '\'' +
                ", preferredVehicle='" + preferredVehicle + '\'' +
                '}';
    }
}
