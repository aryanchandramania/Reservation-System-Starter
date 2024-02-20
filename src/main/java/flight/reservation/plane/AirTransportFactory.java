package flight.reservation.plane;

public abstract class AirTransportFactory {

    public abstract AirTransport createAirTransport(String model);
}