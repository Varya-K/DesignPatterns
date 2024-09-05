package CreationalDesignPatterns.Builder.builders;

import CreationalDesignPatterns.Builder.cars.CarType;
import CreationalDesignPatterns.Builder.components.Engine;
import CreationalDesignPatterns.Builder.components.GPSNavigator;
import CreationalDesignPatterns.Builder.components.Transmission;
import CreationalDesignPatterns.Builder.components.TripComputer;

public interface Builder {
    void setCarType(CarType type);
    void setSeats (int seats);
    void SetEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);
}
