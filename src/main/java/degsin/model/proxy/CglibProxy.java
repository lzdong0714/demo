package degsin.model.proxy;

import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年09月18日 16:25:00
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;


    public Object getProxyInstance(final Object target){
        this.target = target;

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object invoke = methodProxy.invokeSuper(o, objects);
        after();
        return invoke;
    }

    private void before(){
        System.out.println("--------- cglib before-----------" );
    }

    private void after(){
        System.out.println("----------cglib after-------------");
    }
}
