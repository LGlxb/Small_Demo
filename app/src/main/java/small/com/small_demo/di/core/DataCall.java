package small.com.small_demo.di.core;


import small.com.small_demo.bean.HomeBannerBean;
import small.com.small_demo.bean.HomeGoodsBean;
import small.com.small_demo.bean.LoginBean;
import small.com.small_demo.bean.RegisterBean;

public interface DataCall extends IView {
    void onLogin(LoginBean loginBean);

    void onHotGoods(HomeGoodsBean homeGoodsBean);

    void onFashion(HomeGoodsBean homeGoodsBean);

    void onQualityLive(HomeGoodsBean homeGoodsBean);

    void onRegister(RegisterBean registerBean);

    void onBanner(HomeBannerBean homeBannerBean);

    void onFaild(String error);
}
