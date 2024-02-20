package flight.reservation.plane;

public class PassengerDroneFactory extends AirTransportFactory {
    @Override
    public AirTransport createAirTransport(String model) {
        return new PassengerDrone(model);
    }
}
