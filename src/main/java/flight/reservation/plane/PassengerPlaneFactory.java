package flight.reservation.plane;

public class PassengerPlaneFactory extends AirTransportFactory{

    @Override 
    public AirTransport createAirTransport(String model) {
        return new PassengerPlane(model);
    }
}
