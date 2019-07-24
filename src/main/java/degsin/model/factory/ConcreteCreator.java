package degsin.model.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConcreteCreator extends Creator{
    @Override
    protected Product createProduct(String item) {
        Product product = null;
        ConcreteSourceFactory sourceFactory = new ConcreteSourceFactory();

        String value = ProductSpecies.getValue(item);

        Class productClz = Product.class;
        String concreteClzName = productClz.getName() + value;
        try {
            Class<?> concreteClz = Class.forName(concreteClzName);
            Constructor<?> constructor = concreteClz.getConstructor(ConcreteSourceFactory.class);
            Product instance =(Product) constructor.newInstance(sourceFactory);
            return instance;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        switch (value){
            case "one": product = new ConcreteProduct(sourceFactory);
            product.setName("make_one_product");
            break;
            default: product = new ConcreteProduct(sourceFactory);
            product.setName(item);
        }

        return product;
    }
}
