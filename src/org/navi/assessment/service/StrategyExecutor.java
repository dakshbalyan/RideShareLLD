package org.navi.assessment.service;

import org.navi.assessment.models.Ride;
import org.navi.assessment.models.RideSelectionRequest;
import org.navi.assessment.service.strategy.RideSelectionStrategy;

import java.util.List;
import java.util.Optional;

public class StrategyExecutor {
    List<RideSelectionStrategy> strategies;

    public StrategyExecutor(List<RideSelectionStrategy> strategies) {
        this.strategies = strategies;
    }

    public Optional<Ride> execute(RideSelectionRequest rideRequest, List<Ride> applicableRides){

        for (RideSelectionStrategy strategy : strategies){
            if (strategy.isStrategyApplicable(rideRequest.getStrategy()))
                return strategy.findRideAccordingToStrategy(rideRequest, applicableRides);
        }

        return Optional.empty();
    }
}
