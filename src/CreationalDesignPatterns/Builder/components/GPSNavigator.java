package CreationalDesignPatterns.Builder.components;

public class GPSNavigator {
    private String route;
    public GPSNavigator(){
        this.route = "The Red Square, Moskow - Palace Square, St. Petersburg";
    }
    public void setRoute(String manualRoute){
        route=manualRoute;
    }

    public String getRoute(){
        return route;
    }
}
