package BehavioralDesignPatterns.ChainOfResponsibility.middleware;

//Проверка на лимит запросов

public class ThrottlingMiddleware extends Middleware{
    private int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingMiddleware(int requestPerMinute){
        this.requestPerMinute=requestPerMinute;
        this.currentTime=System.currentTimeMillis();
    }

    @Override
    public boolean check(String email, String password) {
        if(System.currentTimeMillis()>currentTime+60000){
            request=0;
            currentTime=System.currentTimeMillis();
        }
        request++;
        if(request>requestPerMinute){
            System.out.println("Request limit exceede!");
            Thread.currentThread().interrupt();
        }
        return checkNext(email, password);
    }
}
