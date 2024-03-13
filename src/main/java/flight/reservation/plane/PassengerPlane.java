package flight.reservation.plane;

import flight.reservation.plane_builder.PlaneBuilder;

public class PassengerPlane {

    public String model;
    public int passengerCapacity;
    public int crewCapacity;

    public PassengerPlane(String model) {
        this.model = model;

        director = new PlaneDirector();

        PlaneBuilder builder = null;

        switch (model) {
            case "A380":
                builder = new A380Builder();
            case "A350":
                builder = new A350Builder();
            case "Embraer 190":
                builder = new Embraer190Builder();
            case "Antonov AN2":
                builder = new AntonovAN2Builder();
            default:
                throw new IllegalArgumentException(String.format("Model type '%s' is not recognized", model));
        }

        director.construct(builder);

        this.passengerCapacity = builder.getPlane().passengerCapacity;
        this.crewCapacity = builder.getPlane().crewCapacity;
    }

}
