package small.com.small_demo.di.presenter;

import javax.inject.Inject;

import small.com.small_demo.bean.HomeBannerBean;
import small.com.small_demo.di.core.HomeDataCall;
import small.com.small_demo.di.model.HomeBannerModel;
import small.com.small_demo.removecoupling.compontent.DaggerHomeBannerCompontent;
import small.com.small_demo.removecoupling.module.HomeBannerModule;

public class HomeBannerPresenter extends BasePresenter<HomeDataCall> {
    @Inject
    HomeBannerModel homeBannerModel;


    public HomeBannerPresenter(HomeDataCall view) {
        super(view);
    }


    @Override
    protected void initModel() {
        //耦合性处理
        DaggerHomeBannerCompontent.builder().homeBannerModule(new HomeBannerModule()).build()
                .HomeBannerInject(this);
    }

    public void homeBanner() {
        homeBannerModel.homeBanner(new HomeBannerModel.BannerModelCallBack() {
            @Override
            public void getSuccess(HomeBannerBean homeBannerBean) {
                view.onBanner(homeBannerBean);
            }

            @Override
            public void getFaid(String error) {
                view.onFaild(error);
            }
        });
    }
}