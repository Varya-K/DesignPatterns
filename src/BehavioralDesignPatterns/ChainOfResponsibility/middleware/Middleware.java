package BehavioralDesignPatterns.ChainOfResponsibility.middleware;

//базовый класс цепочки

public abstract class Middleware {
    private Middleware next;


    // Помогает строить цепи из объектов
    public static Middleware link(Middleware first, Middleware... chain){
        Middleware head = first;
        for (var nextInChain: chain){
            head.next=nextInChain;
            head = nextInChain;

        }
        return first;
    }

    public abstract boolean check(String email, String password);

    protected boolean checkNext(String email, String password){
        if(next==null){
            return true;
        }
        return next.check(email,password);
    }
}

