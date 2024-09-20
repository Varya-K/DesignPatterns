package BehavioralDesignPatterns.TemplateMethod.networks;

public class Facebook extends Network{
    public Facebook(String userName, String password){
        this.userName=userName;
        this.password=password;
    }

    @Override
    public boolean logIn(String userName, String password){
        System.out.println("\nChecking user's parametrs");
        System.out.println("Name: " + this.userName);
        System.out.print("Password: ");
        for(int i=0;i<this.password.length();i++){
            System.out.print("*");
        }
        simulateNetworkLatency();
        System.out.println("\n\nLogIn success on Facebook");
        return true;
    }

    @Override
    boolean sendData(byte[] data) {
        boolean messagePosted = true;
        if(messagePosted) {
            System.out.println("Message: '" + new String(data) + "' was posted on Facebook");
            return true;
        }
        return false;
    }

    @Override
    void logOut() {
        System.out.println("User: '" + userName + "' was logged out from Facebook");
    }

}
