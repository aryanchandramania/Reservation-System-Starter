package flight.reservation.plane;

public class HelicopterFactory extends AirTransportFactory {
    private final int passengerCapacity;

    @Override
    public AirTransport createAirTransport() {
        return new Helicopter(model);
    }
}
