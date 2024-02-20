package flight.reservation.plane;

public abstract class AirTransportFactory {
    protected final String model;

    public abstract AirTransport createAirTransport();
}