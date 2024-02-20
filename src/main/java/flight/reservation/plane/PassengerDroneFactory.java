package flight.reservation.plane;

public class PassengerDroneFactory extends AirTransportFactory {
    @Override
    public AirTransport createAirTransport() {
        return new PassengerDrone(model);
    }
}
