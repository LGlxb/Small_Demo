package small.com.small_demo.compontent;

import dagger.Component;
import small.com.small_demo.di.presenter.HomeBannerPresenter;
import small.com.small_demo.module.HomeBannerModule;

@Component(modules = HomeBannerModule.class)//提供
public abstract class HomeBannerCompontent {
    public abstract void HomeBannerInject(HomeBannerPresenter homeBannerPresenter);//使用
}

