package main.strategies;

import main.models.Trip;

public class DefaultPaymentStrategy implements IPaymentStrategy{
    @Override
    public double generatePayment(Trip trip) {
        return 100;
    }
}
