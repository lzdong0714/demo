Quartz：用来任务调度的框架

Stomp：就像HTTP在TCP套接字之上添加了请求-响应模型层一样，STOMP在WebSocket之上提供了一个基于帧的线路格式（frame-based wire format）层，用来定义消息的语义。

## Sping

### AOP

​	解决“业务”与“功能”的耦合，“功能”比如：登录验证，异常提示，有效期验证。将功能添加到“业务”中，

用AOP的方式，用的是切点的方式。可以定义调用的业务类，方法（excutaion），可以用注解，

``` java
//切点表达式
@Pointcut()
execution：用于匹配方法执行的连接点；
within：用于匹配指定类型内的方法执行；
this：用于匹配当前AOP代理对象类型的执行方法；注意是AOP代理对象的类型匹配，这样就可能包括引入接口也类型匹配； 

```



​	aop是使用代理模式，将指定被pointcut的类，用jdk动态代理或值cglib字节代理，将类代理增强

##### 代理模式简介

``` java
public interface Star{
    public void act();
}

public Actor implements Star{
    public void act(){
        System.out,println("actor is acting a drama");
    }
}

public Agent implements Star{
    private Star star;

	public Agent(Star star){
        this.star = star;
    }
    
    publc void setStar(Star star){
        this.star = star;
    }
    
    public void act(){
        before();
        star.act();
        after();
    }
    
    public Object before(){ 
        //do sth 
    }
    
    pubilc Object after(){
        // do sth
    }
}

// 实现代理人，代理明星的经纪工作
// 以上代理方式还有一个缺陷，就是必须实现统一个接口
//
```

​	

``` java
// JDK 中有方法实现类似的代理功能，但是还是没有摆脱，继承同样的接口形式
// 例子

public class JdkProxyHandle{
    private Object star;
    
    public JdkProxyHandle(Star star){
        super();
        this.star = star;
    }
    
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(star.getClass().getClassLoader(),
                              star,getClass().getInterface(),
                              (proxy,methode,args)->{
                                  before();
                                  star.act();
                                  after();
                                  return object
                              })
    }
    
    Proxy.newProxyInstance
    
    
}
```





``` java
    @Pointcut("args()")
    private void cutArgs(){}

    @Pointcut("@annotation(com.example.demo.annotated.Constraints)")
    private void cutAnnotation(){}

    //匹配注解有AdminOnly注解的方法
    @Pointcut("@annotation(com.sample.security.AdminOnly)")
    public void demo1(){}

    //匹配标注有admin的类中的方法，要求RetentionPolicy级别为CLASS
    @Pointcut("@within(com.sample.annotation.admin)")
    public void demo2(){}

    //匹配传入参数的类标注有Repository注解的方法
    @Pointcut("args(org.springframework.stereotype.Repository)")
    public void demo3(){}

    //注解标注有Repository的类中的方法，要求RetentionPolicy级别为RUNTIME
    @Pointcut("target(org.springframework.stereotype.Repository)")
    public void demo4(){}



    @Before(value = "@annotation(com.example.demo.annotated.Constraints)",argNames = "")
    public void before(JoinPoint joinPoint){}

    @Around(value = "cutMethod()",argNames = "args")
    public Object doAround(ProceedingJoinPoint pjp,String[] args){
        return null;
    }

    @After(value = "",argNames = "")
    public void after(JoinPoint joinPoint){}

    @AfterReturning(pointcut = "execution()",returning = "",value = "",argNames = "")
    public void afterReturning(JoinPoint joinPoint){}

    @AfterThrowing(value = "",pointcut = "",throwing = "",argNames = "")
    public void afterThrowing(JoinPoint joinPoint){}
```



 ### IOC

​	spring框架加载

``` java

```





## Mybaits

``` xml
insert：   插入n条记录，返回影响行数n。（n>=1，n为0时实际为插入失败）

update：更新n条记录，返回影响行数n。（n>=0）

delete： 删除n条记录，返回影响行数n。（n>=0）
```

``` xml
<insert id ="INSERT-CODE-BATCH" parameterType="java.util.List" >
      insert into redeem_code
      (bach_id, code, type, facevalue,create_user,create_time)
      values
       <foreach collection ="list" item="reddemCode" index= "index" separator =",">
           (
           #{reddemCode.batchId}, #{reddemCode.code},
           #{reddemCode.type},
           #{reddemCode.facevalue},
           #{reddemCode.createUser}, #{reddemCode.createTime}
           )
       </foreach >
</insert >

<update id="WHOLESALE-UPDATE-REBATE-CONFIG-BY-PRODUCTCODE" >
	UPDATE 
		rebate_config
	SET
		rebate_type = 'BY_RATE',rebate_config_value = #rebateConfigValue#
	WHERE
		<iterate  property="list" conjunction="or" open="(" close=")">
		  product_code =#list[]#
		</iterate>
</update>

```



