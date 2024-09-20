package BehavioralDesignPatterns.TemplateMethod.networks;

public abstract class Network {
    String userName;
    String password;

    Network(){}

    public boolean post(String message){
        if(logIn(this.userName, this.password)){
            boolean result = sendData(message.getBytes());
            logOut();
            return result;
        }
        return false;
    }

    abstract boolean logIn(String userName, String password);
    abstract boolean sendData(byte[] data);
    abstract void logOut();

    protected void simulateNetworkLatency(){
        try{
            int i=0;
            System.out.println();
            while(i<10){
                System.out.print(".");
                Thread.sleep(500);
                i++;
            }
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
