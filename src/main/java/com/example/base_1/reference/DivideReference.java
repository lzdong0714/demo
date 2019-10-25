package com.example.base_1.reference;

import java.lang.ref.SoftReference;

/**
 * @Author: Liu Zhendong
 * @Description 强引用，软引用，弱引用，虚引用
 * @createTime 2019年09月25日 09:56:00
 */
public class DivideReference {

    public static void main(String[] args) {
        DivideReference obj = new DivideReference();
        obj.softReferenceDemo();
    }

    private void softReferenceDemo(){
        //当内存足够大时可以把数组存入软引用，取数据时就可从内存里取数据，提高运行效率
        //
        //软引用在实际中有重要的应用，例如浏览器的后退按钮
        T t = new T();
        SoftReference<T> softReference_t = new SoftReference<T>(t);

        t = null;

        if(softReference_t != null){
            t = softReference_t.get();
            System.out.println("get from reference");
        }else {
            t = new T();
            softReference_t = new SoftReference<T>(t);
            System.out.println("new a reference to build");
        }
    }

    private void weakRefenceDemo(){
        // 如果这个对象是偶尔的使用，并且希望在使用时随时就能获取到，
        // 但又不想影响此对象的垃圾收集，那么你应该用 Weak Reference 来记住此对象。
    }


}

class T{
    int[] array;
    public T(){
        array = new int[100000];
    }
}
