package degsin.model.factory;

public class ConcreteSourceFactory implements SourceFactory {
    @Override
    public SourceOne getIngredient() {
        return new ConcreteSourceOne();
    }

    @Override
    public SourceTwo getMySource() {
        return new ConcreteSourceTwo();
    }
}

class ConcreteSourceOne implements SourceOne{

    @Override
    public String getSourceOne() {
        return " ConcreteSourceOne ";
    }
}

class ConcreteSourceTwo implements SourceTwo{

    @Override
    public String getSourceTwo() {
        return " ConcreteSourceTwo ";
    }
}
