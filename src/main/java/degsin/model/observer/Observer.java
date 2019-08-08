package degsin.model.observer;

public interface Observer {
    void update(ConcreteObserver concreteObserver,MyMessage message);
}
