package flight.reservation.plane_builder;

public class PlaneDirector {
    private PlaneBuilderInterface planeBuilder;

    public PlaneDirector(PlaneBuilderInterface planeBuilder) {
        this.planeBuilder = planeBuilder;
    }

    public void constructPlane() {
        planeBuilder.buildPassengerCapacity();
        planeBuilder.buildCrewCapacity();
    }
}