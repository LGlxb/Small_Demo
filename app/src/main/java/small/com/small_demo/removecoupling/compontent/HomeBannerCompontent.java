package small.com.small_demo.removecoupling.compontent;

import dagger.Component;
import small.com.small_demo.di.presenter.HomeBannerPresenter;
import small.com.small_demo.removecoupling.module.HomeBannerModule;

@Component(modules = HomeBannerModule.class)//提供
public abstract class HomeBannerCompontent {
    public abstract void HomeBannerInject(HomeBannerPresenter homeBannerPresenter);//使用
}

