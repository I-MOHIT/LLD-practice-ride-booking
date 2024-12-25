package main.strategies;

import main.models.Trip;

public interface IPaymentStrategy {
    public abstract double generatePayment(Trip trip);
}
