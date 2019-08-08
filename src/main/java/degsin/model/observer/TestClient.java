package degsin.model.observer;

public class TestClient {
    public static void main(String[] args){
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserver("id= lpl"));
        subject.registerObserver(new ConcreteObserver("id = lol"));
        MyMessage message=new MyMessage("Hel hydra");
        subject.setMessage(message);
        subject.sendMessage();

    }
}
