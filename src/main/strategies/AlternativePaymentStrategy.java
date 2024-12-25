package main.strategies;

import main.models.Trip;

public class AlternativePaymentStrategy implements IPaymentStrategy{
    @Override
    public double generatePayment(Trip trip) {
        double distance = Math.sqrt(Math.pow(trip.getEndLocation().getLatitude() - trip.getStartLocation().getLatitude(), 2) + Math.pow(trip.getEndLocation().getLongitude() - trip.getStartLocation().getLongitude(), 2));
        return distance * 0.1;
    }
}
