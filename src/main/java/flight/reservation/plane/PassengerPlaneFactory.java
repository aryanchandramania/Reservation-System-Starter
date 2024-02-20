package flight.reservation.plane;

public class PassengerPlaneFactory extends AirTransportFactory{
    private final int passengerCapacity;
    private final int crewCapacity;

    @Override 
    public AirTransport createAirTransport() {
        return new PassengerPlane(model, passengerCapacity, crewCapacity);
    }
}
