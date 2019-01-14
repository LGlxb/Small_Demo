package small.com.small_demo.di.core;


import small.com.small_demo.bean.HomeBannerBean;
import small.com.small_demo.bean.HomeGoodsBean;
import small.com.small_demo.bean.LoginBean;
import small.com.small_demo.bean.PopButtomBean;
import small.com.small_demo.bean.PopTopBean;
import small.com.small_demo.bean.RegisterBean;

public interface DataCall extends IView {
    //登录
    void onLogin(LoginBean loginBean);

    //poprecyone
    void onPopRecyOne(PopTopBean popTopBean);

    //poprecytwo
    void onPopRecyTwo(PopButtomBean popButtomBean);

    //热销商品
    void onHotGoods(HomeGoodsBean homeGoodsBean);

    //时尚商品
    void onFashion(HomeGoodsBean homeGoodsBean);

    //生活商品
    void onQualityLive(HomeGoodsBean homeGoodsBean);

    //注册
    void onRegister(RegisterBean registerBean);

    //首页轮播
    void onBanner(HomeBannerBean homeBannerBean);

    //失败
    void onFaild(String error);
}
