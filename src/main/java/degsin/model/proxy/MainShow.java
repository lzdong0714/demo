package degsin.model.proxy;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年09月18日 16:08:00
 */
public class MainShow {


    public static void main(String[] args) {
        Actor actor = new Actor();

        //用代码写的需要编译的静态代理
        Agent agent = new Agent(actor);
        agent.act();

        //用jdk 在调用反射做的动态代理，但是已经不涉及具体的代理对象限制，直接做增强
        Star jdkProxy = (Star) new JdkProxy(actor).getProxyInstance();
        jdkProxy.act();

        //以上代理，都必须使用接口的方法才实现，用cglib实现字节层次的动态代理
        Star cglibProxy = (Star) new CglibProxy().getProxyInstance(actor);
        cglibProxy.act();
    }
}
