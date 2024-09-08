package CreationalDesignPatterns.Singleton.NonThreadSafe;

public class DemoMultiThread {
    public static void main(String[] args){
        System.out.println("If you see the same value, then singleton was reused (yay!)\n" +
                "If you see different values, then 2 singletons were created (booo!)\n\n" +
                "RESULT:\n");
        Thread threadHello = new Thread(new ThreadHello());
        Thread threadWorld = new Thread(new ThreadWorld());
        threadHello.start();
        threadWorld.start();
    }

    static class ThreadHello implements Runnable{
        @Override
        public void run() {
            Singleton singleton=Singleton.getInstance("Hello");
            System.out.println(singleton.value);
        }
    }

    static class ThreadWorld implements Runnable{
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("World");
            System.out.println(singleton.value);
        }
    }
}
