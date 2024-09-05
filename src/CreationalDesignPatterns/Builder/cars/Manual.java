package CreationalDesignPatterns.Builder.cars;

import CreationalDesignPatterns.Builder.components.Engine;
import CreationalDesignPatterns.Builder.components.GPSNavigator;
import CreationalDesignPatterns.Builder.components.Transmission;
import CreationalDesignPatterns.Builder.components.TripComputer;

public class Manual {
    private final CarType carType;
    private final int seats;
    private final Engine engine;
    private final Transmission transmission;
    private final TripComputer tripComputer;
    private final GPSNavigator gpsNavigator;

    public Manual(CarType carType, int seats, Engine engine,Transmission transmission,
               TripComputer tripComputer, GPSNavigator gpsNavigator){
        this.carType=carType;
        this.seats=seats;
        this.engine=engine;
        this.transmission=transmission;
        this.tripComputer=tripComputer;
        this.gpsNavigator=gpsNavigator;
    }

    public  String print(){
        StringBuilder info=new StringBuilder();
        info.append("Type of car: "+carType+"\n");
        info.append("Count of seats: "+seats+"\n");
        info.append("Engine: volume - "+engine.getVolume()+"; mileage - "+engine.getMileage()+"\n");
        info.append("Transmission: "+transmission+"\n");
        if(this.tripComputer!=null){
            info.append("TripComputer: Functional\n");
        }
        else info.append("Trip Computer: N/A\n");
        if(this.gpsNavigator!=null){
            info.append("GPS Navigator: Functional\n");
        }
        else info.append("GPS Navigator: N/A\n");
        return info.toString();
    }
}
