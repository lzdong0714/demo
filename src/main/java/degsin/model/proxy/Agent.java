package degsin.model.proxy;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年09月18日 15:59:00
 */
public class Agent implements Star {

    private Star star;

    public Agent(Star star){
        this.star = star;
    }



    @Override
    public void act() {
        before();
        star.act();
        after();
    }

    private void before(){System.out.println("=======agent before=====");}

    private void after(){System.out.println("=======agent after =====");}
}
