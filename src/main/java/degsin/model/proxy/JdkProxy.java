package degsin.model.proxy;

import java.lang.reflect.Proxy;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年09月18日 16:02:00
 */
public class JdkProxy {


    // 不限定代理对象的种类
    private Object star;

    public JdkProxy(Object star){
        this.star = star;
    }

    public Object getProxyInstance(){
        Object retObj =
            Proxy.newProxyInstance(star.getClass().getClassLoader(),
            star.getClass().getInterfaces(),
            ((proxy, method, args) -> {
                before();
                // 不限定代理对象的方法
                Object invoke = method.invoke(star, args);
                after();
                return invoke;
            }));

        return retObj;
    }


    //主要的增强方式
    private void before(){System.out.println("+++before+++++");}

    private void after(){System.out.println("++++++after++++++++");}
}
