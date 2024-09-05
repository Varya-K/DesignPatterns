package CreationalDesignPatterns.Builder.director;

import CreationalDesignPatterns.Builder.builders.Builder;
import CreationalDesignPatterns.Builder.cars.CarType;
import CreationalDesignPatterns.Builder.components.Engine;
import CreationalDesignPatterns.Builder.components.GPSNavigator;
import CreationalDesignPatterns.Builder.components.Transmission;
import CreationalDesignPatterns.Builder.components.TripComputer;

public class Director {
    public void constructSportsCar(Builder builder){
        builder.setCarType(CarType.SPORT_CAR);
        builder.setSeats(2);
        builder.SetEngine(new Engine(3,0));
        builder.setTransmission(Transmission.SEMI_AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    public void constructCityCar(Builder builder){
        builder.setCarType(CarType.CITY_CAR);
        builder.setSeats(4);
        builder.SetEngine(new Engine(1.2,0));
        builder.setTransmission(Transmission.AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    public void constructSUV(Builder builder){
        builder.setCarType(CarType.SUV);
        builder.setSeats(4);
        builder.SetEngine(new Engine(2.5,0));
        builder.setTransmission(Transmission.MANUAL);
        builder.setTripComputer(null);
        builder.setGPSNavigator(new GPSNavigator());

    }
}
