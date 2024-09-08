package StructuralDesignPatterns.Bridge.remotes;

import StructuralDesignPatterns.Bridge.devices.Device;

public class AdvancedRemote extends BasicRemote{
    public AdvancedRemote(Device device){
        super(device);
    }

    public void mute(){
        System.out.println("Remote: mute");
        device.setVolume(0);
    }

}
