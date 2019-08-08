package degsin.model.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcreteObserver implements Observer {
    MyMessage myMessage;
    String observerId;
    private static final Logger logger = LoggerFactory.getLogger(ConcreteObserver.class);
    public ConcreteObserver(String observerId){
        this.observerId=observerId;
    }

    public ConcreteObserver(){}

//    public ConcreteObserver(String observerId,MyMessage message){
//        this.observerId = observerId;
//        this.myMessage = message ;
//    }

    @Override
    public void update(ConcreteObserver concreteObserver, MyMessage message) {
        this.myMessage=message;
        logger.info(observerId + "is update message:"+message.toString());
    }
}
