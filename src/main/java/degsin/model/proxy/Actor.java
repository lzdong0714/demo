package degsin.model.proxy;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年09月18日 15:58:00
 */
public class Actor implements Star {

    @Override
    public void act() {
        System.out.println("表演一个话剧");
    }
}
