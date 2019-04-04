package com.example.observer;

public interface Observer {
    void update(ConcreteObserver concreteObserver,MyMessage message);
}
