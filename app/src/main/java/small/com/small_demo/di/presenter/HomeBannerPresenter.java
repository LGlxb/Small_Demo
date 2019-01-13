package small.com.small_demo.di.presenter;

import java.util.Map;

import javax.inject.Inject;

import small.com.small_demo.bean.HomeBannerBean;
import small.com.small_demo.compontent.DaggerHomeBannerCompontent;
import small.com.small_demo.di.core.DataCall;
import small.com.small_demo.di.model.HomeBannerModel;
import small.com.small_demo.module.HomeBannerModule;

public class HomeBannerPresenter extends BasePresenter<DataCall> {
    @Inject
    HomeBannerModel homeBannerModel;


    public HomeBannerPresenter(DataCall view) {
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