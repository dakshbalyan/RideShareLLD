package org.navi.assessment.service.strategy;

import org.navi.assessment.models.Ride;
import org.navi.assessment.models.RideSelectionRequest;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MaxVacantSeatsStrategy implements RideSelectionStrategy {

    private static final String MOST_VACANT_STRATEGY="most vacant";

    @Override
    public Optional<Ride> findRideAccordingToStrategy(RideSelectionRequest rideRequest, List<Ride> applicableRides) {

        return applicableRides.stream()
                .max(Comparator.comparing(Ride::getAvailableSeats));

    }

    @Override
    public boolean isStrategyApplicable(String strategy) {
        strategy = strategy.toLowerCase();

        return strategy.equals(MOST_VACANT_STRATEGY);
    }


}
