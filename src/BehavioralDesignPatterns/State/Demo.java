package BehavioralDesignPatterns.State;

import BehavioralDesignPatterns.State.ui.Player;
import BehavioralDesignPatterns.State.ui.UI;

public class Demo {
    public static void main(String[] args){
        Player player = new Player();
        UI ui = new UI(player);
        ui.init();
    }
}
