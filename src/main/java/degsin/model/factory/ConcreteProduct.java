package degsin.model.factory;

public class ConcreteProduct extends Product {


    //第二层的工厂模式，假设用来或区运行的竹内资源
    SourceFactory sourceFactory;

    public ConcreteProduct(SourceFactory sourceFactory){
        this.sourceFactory = sourceFactory;
    }

    //获取资源
    @Override
    void prepare() {
        SourceOne ingredient = sourceFactory.getIngredient();
        String sourceOne = ingredient.getSourceOne();
        System.out.println(sourceOne);

        SourceTwo mySource = sourceFactory.getMySource();
        String sourceTwo = mySource.getSourceTwo();
        System.out.println(sourceTwo);
    }
}
