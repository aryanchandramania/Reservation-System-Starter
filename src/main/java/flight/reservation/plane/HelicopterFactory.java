package flight.reservation.plane;

public class HelicopterFactory extends AirTransportFactory {

    @Override
    public AirTransport createAirTransport(String model) {
        return new Helicopter(model);
    }
}
