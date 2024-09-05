package CreationalDesignPatterns.Builder.builders;

import CreationalDesignPatterns.Builder.cars.Car;
import CreationalDesignPatterns.Builder.cars.CarType;
import CreationalDesignPatterns.Builder.components.Engine;
import CreationalDesignPatterns.Builder.components.GPSNavigator;
import CreationalDesignPatterns.Builder.components.Transmission;
import CreationalDesignPatterns.Builder.components.TripComputer;

public class CarBuilder implements Builder{
    private CarType type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    @Override
    public void setCarType(CarType type) {
        this.type = type;
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public void SetEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
    }

    @Override
    public void setGPSNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator=gpsNavigator;
    }

    public Car getResult(){
        return new Car(type,seats,engine,transmission,tripComputer,gpsNavigator);
    }
}
