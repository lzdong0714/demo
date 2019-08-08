package degsin.model.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject {

    List<Observer> observerList;
    MyMessage message;

    public ConcreteSubject(){
        observerList=new ArrayList<>();
    }

    public void setMessage(MyMessage message) {
        this.message = message;
    }

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if(observerList.isEmpty()){return;}
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver(MyMessage message) {
        for (Observer observer:observerList
             ) {
            observer.update((ConcreteObserver) observer,message);
        }
    }

    public void sendMessage(){
        notifyObserver(this.message);
    }
}
