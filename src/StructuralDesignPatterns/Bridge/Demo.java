package StructuralDesignPatterns.Bridge;

import StructuralDesignPatterns.Bridge.devices.Device;
import StructuralDesignPatterns.Bridge.devices.Radio;
import StructuralDesignPatterns.Bridge.devices.TV;
import StructuralDesignPatterns.Bridge.remotes.AdvancedRemote;
import StructuralDesignPatterns.Bridge.remotes.BasicRemote;

public class Demo {
    public static void main(String[] args){
        testDevice(new TV());
        testDevice(new Radio());
    }

    public static void testDevice(Device device){
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote=new BasicRemote(device);
        basicRemote.power();
        basicRemote.volumeDown();
        basicRemote.channelDown();
        device.printStatus();

        System.out.println("Tests with advanced remote");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        basicRemote.channelDown();
        device.printStatus();
    }
}
