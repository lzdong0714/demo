package degsin.model.factory;

public class A_MainShow {

    public static void main(String[] args) {
        ConcreteCreator concreteCreator = new ConcreteCreator();
        Product product = concreteCreator.orderProduct("one");
        System.out.println("---------");
        String name = product.getName();
        System.out.println("product name "+ name);
    }
}
