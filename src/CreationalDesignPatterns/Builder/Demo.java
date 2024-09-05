package CreationalDesignPatterns.Builder;

import CreationalDesignPatterns.Builder.builders.CarBuilder;
import CreationalDesignPatterns.Builder.builders.CarManualBuilder;
import CreationalDesignPatterns.Builder.cars.Car;
import CreationalDesignPatterns.Builder.cars.Manual;
import CreationalDesignPatterns.Builder.director.Director;

public class Demo {
    public static void main(String[] args){
        Director director = new Director();


        CarBuilder builder = new CarBuilder();
        director.constructSportsCar(builder);
        Car sportsCar = builder.getResult();
        System.out.println("Car built:\n"+sportsCar.getCarType());

        CarManualBuilder manualBuilder = new CarManualBuilder();
        director.constructSportsCar(manualBuilder);
        Manual sportsCarManual = manualBuilder.getResult();
        System.out.println("\nCar manual built:\n"+sportsCarManual.print());

        director.constructSUV((manualBuilder));
        Manual suvCar = manualBuilder.getResult();
        System.out.println("\nCar manual built:\n"+suvCar.print());
    }
}
