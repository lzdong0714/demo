package degsin.model.factory;

public abstract class Creator {
    //必须实现的factoryMethod()
    protected abstract Product createProduct(String item);

    //调用产品实现的方法
    public Product orderProduct(String type){
        Product product = createProduct(type);
        System.out.println("create a "+product.getName()+" -------- ");

        product.prepare();
        product.init();
        product.runing();
        product.finish();

        return product;
    }
}
