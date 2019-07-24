package degsin.model.factory;

public abstract class Product {
    String name;

    ProductProperty property;

    abstract void prepare();

    void init(){
        System.out.println("init to produce product");
    }

    void submit(){
        System.out.println("submit order");
    }

    void runing(){
        System.out.println("running producing");
    }

    void finish(){
        System.out.println("product a finished");
    }

    void setName(String name){
        this.name = name;
    }

    String getName(){
        return this.name;
    }
}
