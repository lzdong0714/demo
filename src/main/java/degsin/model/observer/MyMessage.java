package degsin.model.observer;

public class MyMessage {
     String tmpMsg;

    public MyMessage(String msg){
        tmpMsg=msg;
    }

    public MyMessage(){
        tmpMsg = "default message is None";
    }

    @Override
    public String toString() {
        return  tmpMsg;
    }
}
