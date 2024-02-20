package flight.reservation.plane;

public class PassengerDrone implements AirTransport{
    private final String model;

    public PassengerDrone(String model) {
        if (model.equals("HypaHype")) {
            this.model = model;
        } else {
            throw new IllegalArgumentException(String.format("Model type '%s' is not recognized", model));
        }
    }

    public int getPassengerCapacity(){
        return 4;
    }

    public int getCrewCapacity(){
        return 0;
    }

    public String getModel(){
        return model;
    }
}
